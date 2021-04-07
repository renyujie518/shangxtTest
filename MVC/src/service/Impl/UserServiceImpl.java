package service.Impl;

import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.UserService;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021年04月07日 20:05:00
 */
@Service
public class UserServiceImpl implements UserService {
    //声明mapper层属性
    @Autowired
    private UserMapper userMapper;
    //登录业务方法
    @Override
    public User selUserInfoService(String uname, String pwd) {
        return userMapper.userLoginMapper(uname, pwd);
    }
}
