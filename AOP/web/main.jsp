<%--
  Created by IntelliJ IDEA.
  User: renyujie
  Date: 2021/4/5
  Time: 6:24 下午
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
<%--    引入jquery文件--%>
    <script type="text/javascript" src="js/jquery.min.js"></script>
<%--    声明js代码域--%>
    <script type="text/javascript">
<%--        声明页面加载事件--%>
    $(function () {
        //创建颜色数组
        var colors = ["red","yellow","black", "green"];
        //使用间隔执行
        window.setInterval(function () {//设置标题颜色
            $("#title").css("color",colors[Math.floor(Math.random()*colors.length)])
        },100)
    })
/*********************账户信息校验*********/
$(function () {
    $("#outdPwd").blur(function () {
        //发起ajax请求
        $.post("checkAccount",{outId:$("#outId").val(),outPwd:$("#outdPwd").val(),methodName:"checkOutInfo"},function (data) {
            if(eval(data)){//addClass是个span加样式
                $("#outSpan").html("√").css("color","green").addClass("success").removeClass("error");
            }else{
                $("#outSpan").html("X").css("color","red").addClass("error").removeClass("success");
            }
        })
    })
})
/*********************转账金额校验**************************************/
$(function () {
    $("#money").blur(function () {
        //发起ajax请求 请求serlert地址+请求数据，键值对（给后台）+回调函数(servlet中getWriter().write的字符串"true" : "false")
        $.post("checkAccount",{outId:$("#outId").val(),money:$("#money").val(),methodName:"checkMoneyInfo"},function (data) {
            if(eval(data)){
                $("#moneySpan").html("√").css("color","green").addClass("success").removeClass("error");
            }else{
                $("#moneySpan").html("X").css("color","red").addClass("error").removeClass("success");
            }
        })
    })
})
/*********************收款人信息校验**************************************/
$(function () {
    $("#inName").blur(function () {
        //发起ajax请求
        $.post("checkAccount",{inId:$("#inId").val(),inName:$("#inName").val(),methodName:"checkInInfo"},function (data) {
            if(eval(data)){
                $("#inNameSpan").html("√").css("color","green").addClass("success").removeClass("error");
            }else{
                $("#inNameSpan").html("X").css("color","red").addClass("error").removeClass("success");
            }
        })
    })
})
/*********************转账功能**************************************/
//点击开始转账时,保证前3个判断标志位（都是对勾）所有的转账信息是正确的
$(function () {
    $("#btn").click(function () {
        //校验转账信息是否正确
        if($(".success").length==3){//加.是按照类选择器加载所有的元素，取出所有样式为success的span
            //提及表单
            $("#fm").submit();
        }else{
            alert("请填写正确的账户信息")
        }
    })
})
    </script>
</head>
<body>
    <h3 align="center">
<%--        跑马灯效果--%>
        <marquee width="400" behavior="alternate" direction="left" align =middle>
            <font id="title">${sessionScope.user.uname}欢迎登陆610银行转账系统</font>
        </marquee>
    </h3>
    <%--水平线--%>
    <hr>
    <%--银行转账表单--%>
    <div style="width: 400px;margin: auto;">
        <form action="checkAccount" method="post" id="fm">
            <input type="hidden" name="methodName" value="transferInfo">
            <table style="margin: auto;margin-top: 30px;" cellpadding="10px">
                <tr>
                    <td>转账账户:</td>
                    <td>
                        <input type="text" id="outId" name="outId" value="">
                    </td>
                </tr>
                <tr>
                    <td>转账账户密码:</td>
                    <td>
                        <input type="password" id="outdPwd"  value="">
                        <span id="outSpan"></span>
                    </td>
                </tr>
                <tr>
                    <td>金额:</td>
                    <td>
                        <input type="text" name="money" id="money" value="">
                        <span id="moneySpan"></span>
                    </td>
                </tr>
                <tr>
                    <td>收款账号:</td>
                    <td>
                        <input type="text" id="inId" name="inId" value="">
                    </td>
                </tr>
                <tr>
                    <td>收款人姓名:</td>
                    <td>
                        <input type="text" id="inName"  value="">
                        <span id="inNameSpan"></span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" id="btn" value="开始转账">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>