package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DbUtil.java
 * @Description Threadlocal 保证在同一个进程中共享一个sqlsession
 * @createTime 2021年02月08日 13:00:00
 */
public class DbUtil {
    private static  SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();//本质上是一个map集合，key是进程号，value是sqlsession
    //但是这个map里要么处于只存一个元素，要么为null

    static {
        InputStream inputStream = null;

        try {
            inputStream = Resources.getResourceAsStream("mybatis.xml");
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //获得sqlsession对象
    public static SqlSession getSqlsession(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null){//当前线程第一次获得sqlsession对象
            sqlSession = factory.openSession(true);//啥都没有就新创建,创建完放入map里.一旦创建完后，这个if就不满足了，会调用上一条get
            //这样就保证了在同一个进程中共享一个sqlsession
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    //关闭sqlsession
    public static  void clossAll(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession!=null){
            sqlSession.close();
        }
        threadLocal.set(null);//闭环
    }
}
