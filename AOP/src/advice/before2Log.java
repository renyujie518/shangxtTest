package advice;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName before2Log.java
 * @Description before前置通知 针对的是用户登录校验 object得到用户名和密码
 * @createTime 2021年04月06日 13:11:00
 */
public class before2Log implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        //获取log4j的对象
        Logger logger = Logger.getLogger(before2Log.class);
        //日志输出
        logger.debug(objects[0]+"发起了登录请求");
    }
}
