package com.gdgBlog.gdgBlog.user.service;

import com.gdgBlog.gdgBlog.user.User;
import com.gdgBlog.gdgBlog.user.dto.CreateUserRequest;
import com.gdgBlog.gdgBlog.user.dto.LoginUserRequest;
import com.gdgBlog.gdgBlog.user.dto.UpdateUserRequest;
import com.gdgBlog.gdgBlog.user.dto.UserResponse;
import com.gdgBlog.gdgBlog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse signup(CreateUserRequest request){
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
        if (email.equals("ruth1442@naver.com")){
            user = userRepository.save(User.createUserBuilder()
                    .name(name)
                    .password(pwd)
                    .salt(salt)
                    .auth(0)
                    .email(email)
                    .build()
            );
        }else{
            user = userRepository.save(User.createUserBuilder()
                    .name(name)
                    .password(pwd)
                    .salt(salt)
                    .auth(5)
                    .email(email)
                    .build()
            );
        }

        return new UserResponse(user.getId(), user.getName());
    }

    @Transactional
    public UserResponse login(LoginUserRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String pwd = getEncrypt(user.getSalt(), request.getPassword());
        if (pwd.equals(user.getPassword())){
            return new UserResponse(user.getId(), user.getName());
        }
        return null;
    }

    public int update(UpdateUserRequest request) {
        //응답 코드 테이블
        //400: 요청오류 310:동일비밀번호/이름 200:업뎃완
        //토큰으로 유저검사

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String pwd = request.getPassword();
        String name= request.getName();

        if (request.getMode()==0){
            if (pwd==null) return 400;
            pwd=getEncrypt(user.getSalt(), pwd);

            //변경비번이 같을경우 같다고 서버에서 알려줄것인가
            if(pwd.equals(user.getPassword())) return 310;
            else{
                user.updatePassword(pwd);
                return 200;
            }
        }else if (request.getMode()==1){
            if(name==null) return 400;

            if (userRepository.existsByName(name)){
                return 310; //다른 응답 어케 줄까
            }else{
                user.updateName(name);
                return 200;
            }
        }else {
            return 400;
        }
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
