package com.sparta.memo.service;


import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // 메모 생성하기
    @Transactional
    public Memo createMemo(MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;

    }
    // 메모 전체 목록 보기
    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    // 선택한 메모 보기
    @Transactional(readOnly = true)
    public Optional<Memo> inquiryMemo(Long id) {return memoRepository.findById(id);}




    // 메모 수정하기
    @Transactional(readOnly = true)
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        memo.update(requestDto);
        return memo.getId();
    }
    // 메모 삭제하기
    @Transactional
    public Long deleteMemo(Long id){
        memoRepository.deleteById(id);
        return id;
    }


}
