package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.User;
import service.UserService;
import service.UserServiceImpl;

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
 * @createTime 2021年04月03日 13:40:00
 */
@WebServlet("/userLogin")
public class LoginServlet extends HttpServlet {
    //声明变量 便于在下面"调用业务层方法"使用
    private UserService userService;
    //初始化方法 在Servlet在初始化的时候创建
    @Override
    public void init() throws ServletException {
        //创建业务层对象
        //        在业务层使用 Spring容器对象获取 Mapper接口实例化对象后实现了
        //        service层和 mybatis层的解耦但是在 controller层我们依然在 Servlet
        //        中直接创建 Service对象,耦合性过高.
        //UserServiceImpl userService = new UserServiceImpl();
        //        将 service对象配置为bean对象 Servlet中从 Spring容器中
        //        获取 Service对象完成功能开发

        //优化 放在servletcontext对象里去拿，就不用写死
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
//        userService = (UserService) ac.getBean("us");
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        userService = (UserService) ac.getBean("us");

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


        //调用业务层方法
        User user = userService.userLoginService(uname, pwd);
        //响应结果
        //获取session对象
        HttpSession session = req.getSession();
        if (user!=null){
            session.setAttribute("user",user);
            resp.sendRedirect(req.getContextPath() + "/main.jsp");//登录成功重定向到主页
        }else {
            session.setAttribute("flag", "loginFail");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");//登录失败重定向到登录页面
        }

    }
}
