package service;

import pojo.User;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年04月07日 20:01:00
 */
public interface UserService {
    //登录业务方法(这里的方法以sel开头是因为事务管理)
    User selUserInfoService(String uname, String pwd);
}
