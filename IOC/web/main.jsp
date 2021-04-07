<%--
  Created by IntelliJ IDEA.
  User: renyujie
  Date: 2021/4/3
  Time: 2:26 下午
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
    ${sessionScope.user.uname}登录成功 这是主界面
</body>
</html>
