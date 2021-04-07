package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Account;
import service.CheckAccountService;

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
 * @createTime 2021年04月05日 21:27:00
 */
@WebServlet(value = "/checkAccount",loadOnStartup = 2)
public class ServletCheckAccountServlet extends HttpServlet {
    private CheckAccountService checkAccountService;
    @Override
    public void init() throws ServletException {
        //获取Spring对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
        checkAccountService = (CheckAccountService) ac.getBean("checkAccount");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取请求数据的方法名（在 service方法中,根据请求传来的"方法名"调用对应的封装方法处理请求。）
        String methodName = req.getParameter("methodName");

        if ("checkOutInfo".equals(methodName)){//根据请求调用 检验转账账户信息
            checkOutInfo(req, resp);
        }else if ("checkMoneyInfo".equals(methodName)){//检验金额信息
            checkMoneyInfo(req, resp);
        }else if ("checkInInfo".equals(methodName)){//检验收款人信息
            checkInInfo(req, resp);
        }else if ("transferInfo".equals(methodName)){ //转账
            transferInfo(req,resp);
        }else {
            //methodName 前端拼错了
            System.out.println("没有对应的逻辑方法" + methodName);
        }
    }


//检验转账账户信息
    private void checkOutInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取请求信息
        String outId = req.getParameter("outId");
        String outPwd = req.getParameter("outPwd");
        //处理请求
        Account account = checkAccountService.checkOutAccountInfoService(outId, outPwd);
        //结束响应
        resp.getWriter().write(account != null ? "true" : "false");
    }
//检验金额信息  余额足够则在金额框后显示√,否则显示Ⅹ
    private void checkMoneyInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取请求信息
        String outId = req.getParameter("outId");
        String money= req.getParameter("money");
        //处理请求
        Account account = checkAccountService.checkMoneyInfoService(outId, money);
        //结束响应
        resp.getWriter().write(account != null ? "true" : "false");
    }

    //检验收款人信息 用户在收款人姓名失去焦点时,校验收款人信息。存在,则在收款人姓名框后显示
    private void checkInInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取请求信息
        String inId = req.getParameter("inId");
        String inName= req.getParameter("inName");
        //处理请求
        Account account = checkAccountService.checkInInfoService(inId, inName);
        //结束响应
        resp.getWriter().write(account != null ? "true" : "false");

    }

    //转账
    private void transferInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取请求信息
        String outId = req.getParameter("outId");
        String inId= req.getParameter("inId");
        String money= req.getParameter("money");

        //处理请求
        int result = checkAccountService.transferInfoService(outId, inId, money);
        //结束响应
        if (result>=0){
            resp.sendRedirect(req.getContextPath()+"/sucess.jsp");//一定是重定向，不能用请求转发，因为请求转发地址栏不变，刷新一次就会转涨一次，重定向会再刷一个转账成功的结果页
        }else {
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }
    }
}
