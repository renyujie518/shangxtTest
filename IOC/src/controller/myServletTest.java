package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.myservice;
import service.myserviceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ${NAME}.java
 * @Description TODO
 * @createTime 2021年04月02日 15:29:00
 */
@WebServlet("/test")
public class myServletTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
//        resp.getContentType("text/html;charset=utf-8");
        //设置响应编码格式
        //获取请求
        //处理请求
            //获取业务层对象  传统方法，耦合性高
//        myservice myService = new myserviceImpl();
//        myService.testService();
        //IOC方式
        //获取容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
        myservice myService = (myservice) ac.getBean("myService");//通过键值对的key，即ID获得
        myService.testService();

//响应请求
    }
}
