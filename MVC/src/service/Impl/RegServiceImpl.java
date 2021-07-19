package service.Impl;

import mapper.RegMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.RegService;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RegServiceImpl.java
 * @Description TODO
 * @createTime 2021年04月13日 00:27:00
 */
@Service
public class RegServiceImpl implements RegService {
    //声明mapper属性
    @Autowired
    private RegMapper regMapper;
    @Override
    public int insUserInfoService(User user) {
        return regMapper.insUserInfoMapper(user);
    }
}
