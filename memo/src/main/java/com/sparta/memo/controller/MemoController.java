package com.sparta.memo.controller;


import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor

public class MemoController {

    private final MemoService memoService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView();
    }

    // 목록 전체 보기 GET 방식
    @GetMapping("/api/posts")
    public List<Memo> getMemos() {
        return memoService.getMemos();
    }

    // 선택한 게시글 조회 GET 방식 /api/post/{id}
    @GetMapping("/api/post/{id}")
    public Optional<Memo> inquiryMemo(@PathVariable Long id) {return memoService.inquiryMemo(id);}


    // 게시글 작성 POST 방식 /api/post
    @PostMapping("/api/post")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto){
        return memoService.createMemo(requestDto);
    }

    // 게시글 수정 PUT 방식 /api/post/{id}
    @PutMapping("/api/post/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.update(id, requestDto);
    }

    // 게시글 삭제 DELETE 방식 /api/post/{id}
    @DeleteMapping("/api/post/{id}")
    public Long deleteMemo(@PathVariable Long id){
        return memoService.deleteMemo(id);
    }


}
