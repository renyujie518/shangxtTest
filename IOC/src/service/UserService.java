package service;

import pojo.User;

import java.io.IOException;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年04月03日 13:49:00
 */


public interface UserService {
    //用户登录
    User userLoginService(String uname, String pwd) throws IOException;
}
