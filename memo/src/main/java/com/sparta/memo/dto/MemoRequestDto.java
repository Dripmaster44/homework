package com.sparta.memo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {

    private String Title;
    private String Username;
    private String Contents;



    public MemoRequestDto(String title, String username, String contents) {
        this.Title = title;
        this.Username = username;
        this.Contents = contents;
    }

}
