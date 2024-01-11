package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// 모든 요청에 ResponseBody를 붙이고 싶다면, @RestController 사용
@Controller
// class 차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스위에 선언하면서 경로 지정
@RequestMapping("hello")

public class HelloController {

//    @ResponseBody가 없고 return 타입이 String 이면 templates폴더 밑에 해당 이름 파일 리턴
//    Data만을 return할 때에는 @ResponseBody를 붙인다.
    @GetMapping("screen") // localhost:8080/hello/screen
    public String helloScreen() { // 사용자는 메서드 이름 모르니까 매핑함
        // @ResponseBody가 없고 return 타입이 String 이면 templates 폴더 밑에 screen.html 파일을 리턴
        return "screen";
    }
    @GetMapping("string")
    @ResponseBody
//    String이면 문자열을 반환, 객체면 json을 반환한다.
    public String helloString() {
        return "hello_string";
    }

    @GetMapping("json")
    @ResponseBody
    public String helloJson() {
        return "hello_string";
    }

    @GetMapping("screen-model-param")
//    parameter 방식 : ?key=value 방식으로 호출한다.
//    (@RequestParam(value = "name") <- 키, String input_name <- 밸류
    public String helloScreenModelParam(@RequestParam(value = "name") String input_name, Model model) {
//        화면에 data를 넘기고 싶을때는 model객체 사용
//        model에 key:value 형식으로 전달한다.
        model.addAttribute("myData", input_name);
        return "screen";
    }

    @GetMapping("screen-model-path/{id}")
//    pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어, 좀 더 RestFul API 디자인에 적합하다.
    public String helloScreenModelPath(@PathVariable int id, Model model) {
        model.addAttribute("myData", id);
        return "screen";
    }
}
