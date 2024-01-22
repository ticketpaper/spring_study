package com.encore.basic.controller;

import com.encore.basic.domain.MemberDetailResponseDto;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest")
@Api(tags ="화원 관리 컨트롤러")
public class MemberRestController {
    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("member-list")
    public List<MemberDetailResponseDto> MemberList() {
        return memberService.members();
    }

    @PostMapping("create")
    public String MemberCreate(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.save(memberRequestDto);
        return "ok";
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Map<String, Object>> FindMemberDetail(@PathVariable int id) {
        MemberDetailResponseDto memberDetailResponseDto = null;
        try {
            memberDetailResponseDto = memberService.findById(id);
            return ResponseEntityController.ResMessage(HttpStatus.OK, memberDetailResponseDto);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntityController.errorResMessage(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping("update")
//    원래는 form 넘어오는데 json으로 받겠다
//    @RequestBody
    public MemberDetailResponseDto UpdateMember(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.update(memberRequestDto);
        return memberService.findById(memberRequestDto.getId());
    }

    @DeleteMapping("delete/{id}")
    public String DeleteMember(@PathVariable int id) {
        memberService.delete(id);
        return "ok";
    }
}
