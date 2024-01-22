package com.encore.basic.servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-jsp-post")
public class HelloServletJspPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("name = " + name);
        System.out.println("email = " + email);
        System.out.println("password = " + password);

//        응답
//        응답 헤더
        resp.setContentType("text/plain"); // text로 보낸다
        resp.setCharacterEncoding("UTF-8");
//        응답 바디
        PrintWriter out = resp.getWriter();
        out.print("OK");

//        버퍼를 통해 조립이 이루어지므로, 버퍼를 비우는 과정.
        out.flush();
    }
}
