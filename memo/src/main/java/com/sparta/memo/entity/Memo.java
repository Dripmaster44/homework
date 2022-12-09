package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class Memo extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;


    public Memo(String username, String contents, String title ) {
        this.username = username;
        this.contents = contents;
        this.title = title;
    }

    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

    public void update(MemoRequestDto memoRequestDto) {
        this.username = memoRequestDto.getUsername();
        this.contents = memoRequestDto.getContents();
        this.title = memoRequestDto.getTitle();
    }

}
