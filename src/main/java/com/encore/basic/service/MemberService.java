package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberDetailResponseDto;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.memberRepository = springDataJpaMemberRepository;
    }


    public List<MemberDetailResponseDto> members() {
        List<Member> members = memberRepository.findAll();
        List<MemberDetailResponseDto> memberDetailResponseDtos = new ArrayList<>();
        for (Member member : members) {
            MemberDetailResponseDto memberDetailResponseDto = new MemberDetailResponseDto();
            memberDetailResponseDto.setId(member.getId());
            memberDetailResponseDto.setName(member.getName());
            memberDetailResponseDto.setEmail(member.getEmail());
            memberDetailResponseDto.setPassword(member.getPassword());
            memberDetailResponseDtos.add(memberDetailResponseDto);
        }
        return memberDetailResponseDtos;
    }

    public MemberDetailResponseDto findById(int id) throws NoSuchElementException{
        MemberDetailResponseDto memberDetailResponseDto = new MemberDetailResponseDto();
        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("검색한 아이디의 멤버가 없습니다."));
        memberDetailResponseDto.setId(member.getId());
        memberDetailResponseDto.setName(member.getName());
        memberDetailResponseDto.setEmail(member.getEmail());
        memberDetailResponseDto.setPassword(member.getPassword());
        memberDetailResponseDto.setCreate_time(member.getCreate_time());

        return memberDetailResponseDto;
    }

    @Transactional // 예외 발생시 롤백
//    Transactional 어노테이션 클래스 단위로 붙이면 모든 메서드에 각각 Transaction 적용
//    Transactional을 적용하면 한 메서드 단위로 틀내잭션 지정
    public void save(MemberRequestDto memberRequestDto) throws IllegalArgumentException{
        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(),
                memberRequestDto.getPassword());
        memberRepository.save(member);
////        transaction 테스트
//        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(),
//                memberRequestDto.getPassword());
//        memberRepository.save(member);
//        if (member.getName().equals("kim")) {
//            throw new IllegalArgumentException();
//        }
    }

    public void update(MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(memberRequestDto.getId()).orElseThrow(NoSuchElementException::new);
        member.UpdateUserPassword(memberRequestDto.getName(), memberRequestDto.getPassword());
        memberRepository.save(member);
    }

    public void delete(int id) {
        memberRepository.delete(memberRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

}
