package com.encore.basic.controller;

import com.encore.basic.domain.MemberDetailResponseDto;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest")
public class MemberRestController {
    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("member-list")
    public List<MemberResponseDto> MemberList() {
        model.addAttribute("memberList", memberService.members());
    }

    @PostMapping("create")
    public String PostMemberCreate(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.save(memberRequestDto);
        return "ok";
    }

    @GetMapping("find/{id}")
    public MemberResponseDto FindMemberDetail(@PathVariable int id, Model model) {
        MemberDetailResponseDto memberDetailResponseDto = memberService.findById(id);
        model.addAttribute("memberList", memberDetailResponseDto);
    }

    @PatchMapping("update")
    public MemberDetailResponseDto UpdateMember(MemberRequestDto memberRequestDto) {
        memberService.update(memberRequestDto);
        return memberService.findById(memberRequestDto.getId());
    }

    @DeleteMapping("delete/{id}")
    public String DeleteMember(@RequestParam(value = "id") int id) {
        memberService.delete(id);
        return "ok";
    }
}
