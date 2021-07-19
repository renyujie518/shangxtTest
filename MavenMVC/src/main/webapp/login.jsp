<%--
  Created by IntelliJ IDEA.
  User: renyujie
  Date: 2021/4/7
  Time: 7:32 下午
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath %>"/>
    <title>Title</title>
</head>
<body>
    <h3>SSM整合登录案例</h3>
    <hr>
    <form action="userLogin">
        用户名：<input type="text" name="uname" value=""><br>
        密码：<input type="password" name="pwd" value=""><br>
        <input type="submit" value="登录">
    </form>

</body>
</html>
