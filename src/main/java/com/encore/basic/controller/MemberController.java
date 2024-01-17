package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberDetailResponseDto;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

/**
 * 1. 회원 목록조희 (GetMapping, 화면)
 * url : members
 * 화면 : member/memberList
 * => 이름, email, password
 * 테이블 구조 -> td는 적당히 3줄 정도 채우기
 * 2. 회원가입
 * 2.1) get-url : members/create
 * 2.2) post-url : members/create
 * 2.3) form 태그, input태그
 */

// Service 어노테이션을 통해 싱글톤 컴포넌트로 생성된다 -> 스프링 빈으로 등록된다.
//스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
//제어의 역전(IoC) -> IoC컨테이너가 스프링빈을 관리(빈을 생성, 의존성 주입)
@Controller
@RequestMapping("members")
public class MemberController {
//    의존성 주입(DI)하는 방법 1. 필드 주입 방식
//    @Autowired
//    private MemberService memberService;

    //    의존성 주입 방법 2. 생성자 주입 방식 => 가장 많이 사용
//    장점 : final을 통해 상수로 사용가능, 다형성 구현 가능, 순환 참조 방지
//    생성자가 1개밖에 없을때에는 Autowired 생략가능
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    의존성 주입 방법 3. @RequiredArgsConstructor를 이용한 방식
//    @RequiredArgsConstructor :
//    @NonNull 어노테이션이 붙어있는 필드 또는 초기화되지 않은 final필드를 대상으로 생성자 생성
//    private final MemberService memberService;


    @GetMapping("/")
    public String MemberHome() {
        return "header";
    }

    @GetMapping("member-list")

    public String MemberList(Model model) {
        model.addAttribute("memberList", memberService.members());
        return "member-list";
    }

    @GetMapping("create")
    public String GetMemberCreate() {
        return "create";
    }

    @PostMapping("create")
    public String PostMemberCreate(MemberRequestDto memberRequestDto) {
////        트랜잭션 및 예외 처리 테스트
//        try {
//            memberService.save(memberRequestDto);
//            return "redirect:/members/member-list";
//        } catch (IllegalArgumentException e) {
//            return "404-error";
//        }

        memberService.save(memberRequestDto);
//        url 리다이렉트
        return "redirect:/members/member-list";
    }

    //    상세보기회면 resDTO에 id값 추가
//    controller에 /find?id=xx
//    service : repository호출
//    repository에서 실질적으로 search -> interface 수정
//    상세보기화면 : name, emial, password, 가입시간
    @GetMapping("find")
    public String FindMemberDetail(@RequestParam(value = "id") int id, Model model) {
        try {
            MemberDetailResponseDto memberDetailResponseDto = memberService.findById(id);
            model.addAttribute("memberList", memberDetailResponseDto);
            return "find";
        } catch (EntityNotFoundException e) {
            return "404-error";
        }
    }

    @PostMapping("update")
    public String UpdateMember(MemberRequestDto memberRequestDto) {
        memberService.update(memberRequestDto);

        return "redirect:/members/find?id=" + memberRequestDto.getId();
    }

    @GetMapping("delete")
    public String DeleteMember(@RequestParam(value = "id") int id) {
        memberService.delete(id);
        return "redirect:/members/member-list";
    }
}
