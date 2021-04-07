<%--
  Created by IntelliJ IDEA.
  User: renyujie
  Date: 2021/4/2
  Time: 11:16 下午
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="<%=basePath %>"/>
    <title>Title</title>
</head>
<body>
    <h3>欢迎访问610宿舍</h3>
    <hr>
    <c:if test="${sessionScope.flag == 'loginFail'}">
        <font size="20px" color="red">用户名密码错误</font>
    </c:if>
    <c:remove var="flag" scope="session"/>
<%--    删掉flag的原因是session的数据是存在服务器端的，只要失败一次，session就有这个标记，避免下次重新请求还有这个标记--%>
    <form action="userLogin" method="post">
        用户名：<input type="text" name="uname" value=""><br>
        密码： <input type="password" name="pwd" value=""><br>
        <input type="submit" value="登录">
    </form>
L

</body>
</html>
