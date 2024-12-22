package com.gdgBlog.gdgBlog.domain.user.service;

import com.gdgBlog.gdgBlog.config.jwt.JwtTokenProvider;
import com.gdgBlog.gdgBlog.domain.token.RefreshToken;
import com.gdgBlog.gdgBlog.domain.token.repository.RefreshTokenRepository;
import com.gdgBlog.gdgBlog.domain.user.User;
import com.gdgBlog.gdgBlog.domain.user.dto.*;
import com.gdgBlog.gdgBlog.domain.user.repository.UserRepository;
import com.gdgBlog.gdgBlog.exception.user.UserNotFoundException;
import com.gdgBlog.gdgBlog.exception.user.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDetailsResponse signup(CreateUserRequest request){
        //이메일 형식 검사
        //정규식: ^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$
        //로컬(0~9숫자, 영어대소문자, _-. 허용, 시작/끝 점, 연속 . 허용x)
        //도메인(0~9숫자, 영어대소문자 허용, 도메인 시작끝의 .-, 연속 . 허용x)
        String email = request.getEmail();
        String name = request.getName();
        assert(EmailValidator.getInstance()
                .isValid(email)): "이메일 형식이 맞지 않습니다.";

        // 이메일, 닉네임 중복검사
        userRepository.findByEmail(email).orElseThrow();
        userRepository.findByName(name).orElseThrow();

        //비밀번호 salt, 해쉬값 뜨기
        String salt = generateSalt();
        String pwd = getEncrypt(salt, request.getPassword());

        User user;
        //삼항연산자 사용해서 중복코드 줄이기
        user = userRepository.save(User.createUserBuilder()
                .name(name)
                .password(pwd)
                .salt(salt)
                .auth(email.equals("ruth1442@naver.com")? 0: 5)
                .email(email)
                .build()
        );

        return new UserDetailsResponse(user.getId(), user.getEmail(),user.getName());
    }

    //예외처리 추가
    //리턴은 한개만 null말고 에러예외로 처리
    @Transactional
    public UserWithTokenResponse login(LoginUserRequest request) {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

//        String pwd = getEncrypt(user.getSalt(), request.getPassword());
//        if (!pwd.equals(user.getPassword())) {
//            throw new WrongPasswordException();
//        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, request.getPassword());

        //시큐리티 컨텍스트에 인증정보 저장
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String access = tokenProvider.createAccessToken(authentication);
        String refresh = tokenProvider.createRefreshToken(authentication);
        Long accessTokenExpire = tokenProvider.getAccessTokenExpire(access).getTime();

        RefreshToken refreshEntity = new RefreshToken(refresh, authentication.getName());
        refreshTokenRepository.save(refreshEntity);

        return new UserWithTokenResponse(user.getId(), user.getName(), access, refresh, accessTokenExpire);
    }

    @Transactional
    public UserDetailsResponse loadUserByUsername(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email + " -> DB에 존재하지 않습니다."));
        return new UserDetailsResponse(user.getId(), user.getEmail(), user.getName());
    }
    public UserDetailsResponse update(UpdateUserRequest request) {
        //토큰으로 유저검사
        String pwd = request.getPassword();
        String name= request.getName();
        String email = request.getEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

        user.update(request);
        return new UserDetailsResponse(user.getId(), user.getEmail(), user.getName());
    }

    // 솔트 생성 메소드
    public String generateSalt() {
        SecureRandom secure = new SecureRandom(); // 암호화 랜덤값 생성 객체
        byte bytes[] = new byte[24];
        secure.nextBytes(bytes);
        // 1byte = 8bit = 2^8 : -256~255
        // byte값을 16진수로 변환 :
        StringBuffer salt = new StringBuffer();
        for (byte b : bytes) {
            salt.append(String.format("%02x", b)); // %o
        }
        return salt.toString();
    }

    // 솔트를 사용해 비밀번호를 암호화하는 메소드
    public String getEncrypt(String salt, String pw) {
        pw += salt;

        // 암호화 해시 함수: 암호화방식 지정- SHA-256
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pw.getBytes());
            byte[] bytes = md.digest();
            // 16진수로 변환

            StringBuffer data = new StringBuffer();
            for (byte b : bytes) {
                data.append(String.format("%02x", b));
            }
            pw = data.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return pw;
    }
}
