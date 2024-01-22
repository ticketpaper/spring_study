<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
    </style>
</head>
<body>
    <p> data(EL 문법) : ${myData}</p>
    <p> data(Java 코드) :<%
            String getData = (String) request.getAttribute("myData"); 
            out.print(getData);
        %>
    </p>
</body>
</html>