package advice;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName after2Log.java
 * @Description 后置通知 针对登录成成功后 Object o是切点返回的User对象  objects数组得到用户名和密码
 * @createTime 2021年04月06日 13:15:00
 */
public class after2Log implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        //获取log4j对象
        Logger logger = Logger.getLogger(after2Log.class);
        //日志输出
        if (o!=null){
            logger.debug(objects[0]+"登录成功");
        }

    }
}
