package service;

import mapper.UserMapper;
import pojo.User;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021年04月05日 17:24:00
 */
public class UserServiceImpl implements UserService {
    //声明mapper层属性
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User userLoginService(String uname, String pwd) {
        User user = userMapper.userLoginMapper(uname, pwd);
        return user;
    }
}
