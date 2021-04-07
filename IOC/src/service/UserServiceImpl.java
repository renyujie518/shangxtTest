package service;

import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021年04月03日 13:51:00
 */
public class UserServiceImpl implements UserService {
    //声明数据库mapper层的属性 因为serices业务层和数据库层天然有依赖关系，可以统一成1个bean放在容器里，而属性注入之前必须类中有该属性
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User userLoginService(String uname, String pwd) throws IOException {
        //1.获取sqlSession对象,通过流对象读取配置文件里的数据
//        InputStream is = Resources.getResourceAsStream("mybatis.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
//        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.获取Mappper接口的实例化对象
        //UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //以上的方法导致srvice和mybatiss耦合性过高，下面采用IOC创建mapper对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
//        UserMapper mapper = (UserMapper) ac.getBean("userMapper");
            //注意！！这里是Spring容器对象在完成 mybatis相关对象的创建后,
            //会自动的调用其完成 mapper层的扫描,将扫描结果
            //也就是 mapper接口的实例化对象,再次存储到 Spring
            //容器对象中,资源的键名默认为 mapper接口的首字母小写  之前是UserMapper 这填入userMapper
            //看容器中所有bead的键名id可以使用如下函数 String[] names =ac. getBeanDefinitionNames ();

        //在优化 防止多线程的时候创建2次容器导致程序挂了
        //优化手段就是 serices业务层和数据库层天然有依赖关系 在bean里配好 直接调用userMapper即可



        //3.完成数据库查询
        //User user = mapper.userLoginMapper(uname, pwd);
        User user = userMapper.userLoginMapper(uname, pwd);
        //4.返回用户信息
        return user;
    }
}
