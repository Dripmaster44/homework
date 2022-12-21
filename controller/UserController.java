package com.sparta.memo.controller;


import com.sparta.memo.dto.LoginRequestDto;
import com.sparta.memo.dto.RegistrationRequestDto;
import com.sparta.memo.entity.User;
import com.sparta.memo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    // 회원 정보 등록
    @PostMapping("/registration")
    public String registration(@RequestBody RegistrationRequestDto registrationRequestDto) {
        userService.registration(registrationRequestDto);
        return "완료";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto , response);
        return "success";
    }

    // 회원 정보 확인
    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
