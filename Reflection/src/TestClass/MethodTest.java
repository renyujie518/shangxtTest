package TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MethodTest.java
 * @Description 反射执行方法
 * @createTime 2021年01月29日 14:03:00
 */
public class MethodTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("TestClass.MethodTest");
        Object o = clazz.getConstructor().newInstance();
        Method m1 = clazz.getMethod("shout");
        m1.invoke(o);

        Method m2 = clazz.getMethod("add", int.class, int.class);
        Object result = m2.invoke(o, 10, 20);
        System.out.println(result);


    }
    public  void  shout(){
        System.out.println("======shout");
    }

    public int add(int n1,int n2){
        return n1+n2;
    }
}
