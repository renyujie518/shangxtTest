package TestClass;

import why.Dog;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ConstructorTest.java
 * @Description TODO
 * @createTime 2021年01月28日 12:58:00
 */
public class ConstructorTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //Dog dog = new Dog();
        String classname = "why.Dog";
        Class<?> clazz = Class.forName(classname);
        Constructor<?> cons = clazz.getConstructor();
        //使用反射创建对象
        Object o = cons.newInstance();
        System.out.println(o);

        Object o1 = clazz.newInstance();//只针对无参
        System.out.println(o1);

        //得到有参数的构造方法
        Constructor<?> cons2 = clazz.getConstructor(String.class, int.class, String.class);
        //cons2.setAccessible(true);  //构造方法若是默认（什么也不写）或者是private,此语句允许访问
        Object o2 = cons2.newInstance("旺财", 2, "拉布拉多");
        System.out.println(o2);


    }
}
