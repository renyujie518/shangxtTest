package TestClass;

import why.Animal;
import why.Dog;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName FieldTest.java
 * @Description 反射操作属性
 * @createTime 2021年01月29日 13:36:00
 */
public class FieldTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Dog dog = new Dog();
        dog.nickName = "馒头";
        System.out.println(dog.getNickName());

        //使用反射操作属性，即使private修饰也可以操作
        Class<?> clazz = Class.forName("why.Dog");
        Constructor<?> constructor = clazz.getConstructor();
        Object o = constructor.newInstance();
        Field f = clazz.getDeclaredField("type");//private修饰
        f.setAccessible(true);
        f.set(o,"拉布拉多");
        System.out.println(o);
        Object resulttype = f.get(o);
        System.out.println(resulttype);


    }
}
