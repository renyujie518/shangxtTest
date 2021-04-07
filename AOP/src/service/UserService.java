package service;

import pojo.User;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserSerivice.java
 * @Description TODO
 * @createTime 2021年04月05日 17:23:00
 */
public interface UserService {
    User userLoginService(String uname,String pwd);
}
