<%--
  Created by IntelliJ IDEA.
  User: zyp
  Date: 2020/3/14
  Time: 10:23
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
    <h3>SpringMVC的下载</h3>
    <hr>
    <a href="downFile?filename=text.jpg">点击下载</a>
</body>
</html>
