package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    //    사용자 입장에서 GET
    @RequestMapping(value = "string", method = RequestMethod.GET)
//    == @GetMapping("string")
    @ResponseBody
//    String이면 문자열을 반환, 객체면 json을 반환한다.
    public String helloString() {
        return "hello_string";
    }

    @GetMapping("json")
    @ResponseBody
    public Hello helloJson() {
//        ObjectMapper 사용함 스프링이 대신 해줌
        Hello hello = new Hello();
        hello.setName("존 시나");
        hello.setEmail("john@gmail.com");
        hello.setPassword("1234");
        System.out.println(hello);
        return hello;
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

    //    form 태그로 x-www 데이터 처리
    @GetMapping("form-screen")
    public String FormScreen() {
        return "form-screen";
    }

    @PostMapping("form-post-handle")
//    form 태그를 통한 body 데이터 형태가 key1=value1&key2=vlaue2
    @ResponseBody
    public String formPostHandle(@RequestParam(value = "name") String input_name,
                                 @RequestParam(value = "email") String input_email,
                                 @RequestParam(value = "password") String input_password) {
        System.out.println(input_name + " " + input_email + " " + input_password);
        return "정상 처리";
    }

    @PostMapping("form-post-handle2")
    @ResponseBody
//    Spring에서 Hello 클래스의 인스턴스를 자동 매핑하여 생성해준다. 이를 데이터 바인딩이라 부름 (setter 필수)
//    form-data 형식 즉, x-www-url인코딩 형식의 경우 사용
//    클래스를 집어넣으면 요소를 매핑해줌 -> 데이터 바인딩
    public String formPostHandle2(Hello hello) {
        System.out.println(hello);
        return "정상 처리";
    }


    //    json데이터 처리
    @GetMapping("json-screen")
    public String helloJsonScreen() {
        return "json-screen";
    }

    @PostMapping("json-post-handle")
    @ResponseBody
//    @RequestBody는 json으로 post요청이 들어왔을때 body에서 data를 꺼내기 위해 사용
    public String jsonPostHandle(@RequestBody Map<String, String> body) {
        System.out.println(body.get("name"));
        System.out.println(body.get("email"));
        System.out.println(body.get("password"));
        Hello hello = new Hello();
        hello.setName(body.get("name"));
        hello.setEmail(body.get("email"));
        hello.setPassword(body.get("password"));
        return "ok";
//        return "/hello/json-screen";
    }

    @PostMapping("json-post-handle2")
    @ResponseBody
    public String jsonPostHandle2(@RequestBody JsonNode body) {
        Hello hello = new Hello();
        hello.setName(body.get("name").asText());
        hello.setEmail(body.get("email").asText());
        hello.setPassword(body.get("password").asText());

        return "ok";
    }

    @PostMapping("json-post-handle3")
    @ResponseBody
    public String jsonPostHandle3(@RequestBody Hello hello) {
        System.out.println(hello);
        return "ok";
    }

}
