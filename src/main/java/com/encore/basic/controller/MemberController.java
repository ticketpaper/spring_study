package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1. 회원 목록조희 (GetMapping, 화면)
 * url : members
 * 화면 : member/memberList
 * => 이름, email, password
 * 테이블 구조 -> td는 적당히 3줄 정도 채우기
 * 2. 회원가입
 */
@Controller
@RequestMapping("members")
public class MemberController {
    @GetMapping("member-list")
    public String MemberList() {
        return "member-list";
    }
}
