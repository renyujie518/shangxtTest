package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.User;
import service.UserService;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2021年04月07日 19:49:00
 */
@Controller
public class UserController {
    //声明业务层属性
    @Autowired
    private UserService userService;
    //声明单元方法
    @RequestMapping("userLogin")
    public String userLogin(String uname,String pwd){
        //处理请求
        User user = userService.selUserInfoService(uname, pwd);
        //响应结果
        if (user!=null){
            return "redirect:/main.jsp";
        }else {
            return "redirect:/login.jsp";
        }


    }
}
