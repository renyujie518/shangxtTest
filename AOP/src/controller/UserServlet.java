package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ${NAME}.java
 * @Description TODO
 * @createTime 2021年04月05日 17:13:00
 */
@WebServlet(value = "/userLogin",loadOnStartup = 1)
public class UserServlet extends HttpServlet {
    private UserService us;
    @Override
    public void init() throws ServletException {
        //获取Spring容器对象
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        //获取业务层方法
        us = (UserService) ac.getBean("us");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取请求信息
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        //处理请求
        User user = us.userLoginService(uname, pwd);
        //响应处理结果
        HttpSession session = req.getSession();
        if (user!= null){
            //将用户信息储存到session
            session.setAttribute("user", user);
            //重定向到主页
            resp.sendRedirect(req.getContextPath() + "/main.jsp");
        }else {
            //增加失败标记
            session.setAttribute("flag","userFail");
            //重定向到登录页面
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
