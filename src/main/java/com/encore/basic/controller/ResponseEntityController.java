package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {
    //    @ResponseStatus 어노테이션 방식
    @GetMapping("response-status")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus() {
        return "OK";
    }

    @GetMapping("response-status2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2() {
        return new Member("kim", "email", "124");
    }

    //    ResponseEntity 객체를 직접 생성한 방식
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1() {
        Member member = new Member("kim", "email", "124");
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @GetMapping("custom2")
    public ResponseEntity<String> custom2() {
        String html = "<h1>없는 아이디 입니다</h1>";
        return new ResponseEntity<>(html, HttpStatus.NOT_FOUND);
    }

    //    map형태의 메시지 커스텀
    public static ResponseEntity<Map<String, Object>> errorResMessage(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", String.valueOf(status.value()));
        body.put("error message", message);
        return new ResponseEntity<>(body, status);
    }

    //     status 201, message : 객체
    public static ResponseEntity<Map<String, Object>> ResMessage(HttpStatus status, Object object) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("message", object);
        return new ResponseEntity<>(body, status);
    }

    //    메서드 체이닝 방식 : ResponseEntity의 클래스메서드 사용
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1() {
        Member member = new Member("kim", "email", "124");
        return ResponseEntity.ok(member);
    }

    @GetMapping("chaining2")
    public ResponseEntity<Member> chaining2() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("chaining3")
    public ResponseEntity<Member> chaining3() {
        Member member = new Member("kim", "email", "124");
//        여러가지 상태값을 넘기고싶으면 아래와 같이
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

}
