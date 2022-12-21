package com.sparta.memo.service;

import com.sparta.memo.dto.LoginRequestDto;
import com.sparta.memo.dto.RegistrationRequestDto;
import com.sparta.memo.entity.User;
import com.sparta.memo.entity.UserRoleEnum;
import com.sparta.memo.jwt.JwtUtil;
import com.sparta.memo.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String Token = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    //사용자 등록
    @Transactional
    public void registration(RegistrationRequestDto registrationRequestDto){
        String username = registrationRequestDto.getUsername();
        String password = registrationRequestDto.getPassword();


        // 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()){
            throw new IllegalArgumentException("중복 사용자 존재");
        }


        // Admin 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (registrationRequestDto.isAdmin()){
            if (!registrationRequestDto.getAdminToken().equals(Token)){
                throw new IllegalArgumentException("AdminToken이 틀립니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username,password,role);
        userRepository.save(user);
    }

    // 로그인
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));

    }

    @Transactional
    public List<User> getUsers(){
        return userRepository.findAllByOrderByUsername();
    }
}
