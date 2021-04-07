<%--
  Created by IntelliJ IDEA.
  User: renyujie
  Date: 2021/4/5
  Time: 4:52 下午
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="<%=basePath %>"/>
    <title>Title</title>
</head>
<body>
    <h3 align="center">欢迎登陆610转账系统</h3>
    <hr>
    <div style="width: 400px;margin: auto;margin-top: 40px" >
        <c:if test="${sessionScope.flag == 'userFail'}">
            <font color="red" size="20px;">用户名或密码错误</font>
        </c:if>
        <c:remove var="flag" scope="session"></c:remove>
        <form action="userLogin" method="post">
            <table style="margin: auto" cellpadding="10px">
                <tr>
                    <td>用户名：</td>
                    <td>
                        <input type="text" name="uname" value="">
                    </td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td>
                        <input type="password" name="pwd" value="">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="点击登陆">
                    </td>
                </tr>
            </table>
        </form>
    </div>


</body>
</html>
