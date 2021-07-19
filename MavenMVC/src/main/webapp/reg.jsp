<%--
  Created by IntelliJ IDEA.
  User: zyp
  Date: 2020/3/13
  Time: 15:33
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
    <%--引入jquery文件--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <%--声明js代码域--%>
    <script type="text/javascript">
        /****************资源上传功能实现**********************************/
        $(function () {
            //给上传按钮增加单击事件
            $("#btnUpload").click(function () {
                //获取要上传的文件资源 这里的【0】是根据console.log(file)在web检查中看到的结构
                var file=$("#file")[0].files[0];
                //创建FormData对象存储要上传的资源
                var formData=new FormData();
                formData.append("photo",file);
                //发起ajax请求完成资源上传
                $.ajax({
                    type:"post",//使用post类型的请求
                    data:formData,//请求数据
                    url:"regUpload",//请求地址
                    processData:false,
                    contentType:false,
                    success:function (data) {//回调函数
                        //将响应数据转换为json对象
                        eval("var obj="+data);
                        //判断
                        if(obj.status==true){
                            alert("上传成功");
                            //将用户的头像的url地址信息记录下来
                            $("#img").val(obj.url);
                            //在页面中显示上传的头像
                            $("#myImg").attr("src","img/"+obj.url).css("display","");
                        }else{
                            alert(obj.msg);
                        }
                    }
                })

            })
        })
    </script>

</head>
<body>
<%--创建注册页面--%>
<h3 align="center">欢迎注册610宿舍系统</h3>
<hr>
<%--创建注册表单--%>
<div style="width:600px;margin: auto;">
    <form action="userReg" method="post">
        <table cellpadding="10px" style="margin: auto;margin-top:20px;">
            <tr>
                <td>用户名:</td>
                <td>
                    <input type="text" name="uname" value=""><br>
                </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td>
                    <input type="password" name="pwd" value=""><br>
                </td>
            </tr>
            <tr>
                <td>头像:</td>
                <td>
                    <input type="file" id="file" value="点击选择头像">&nbsp;<a id="btnUpload" href="javascript:void(0)">点击上传</a>
                    <input type="hidden" name="img" id="img" value=""><%--隐藏标签记录上传成功的头像的url地址--%>
                    <img src="" style="display: none" id="myImg" width="100px">
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                    <input type="submit" value="完成注册">
                </td>
            </tr>
        </table>
    </form>
</div>



</body>
</html>
