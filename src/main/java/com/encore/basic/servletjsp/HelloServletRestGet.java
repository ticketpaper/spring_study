package com.encore.basic.servletjsp;


import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-get")
public class HelloServletRestGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = new Hello();
        hello.setName("dlfma");
        hello.setEmail("dlapdlf");
        hello.setPassword("votmdnjem");

        resp.setContentType("application/json"); // json으로 보낸다.
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        printWriter.print(mapper.writeValueAsString(hello));


    }
}
