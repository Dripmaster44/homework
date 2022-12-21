package com.sparta.memo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {

    private final String Title;
    private final String Contents;



    public MemoRequestDto(String title, String contents) {
        this.Title = title;
        this.Contents = contents;
    }

}
