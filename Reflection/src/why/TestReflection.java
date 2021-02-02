package why;

import java.lang.reflect.InvocationTargetException;

public class TestReflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //如果编码/编译的时候已经知道要操作的类或者对象，不需要使用反射
        Dog dog = new Dog();//创建对象
        dog.age = 13;//操作属性
        dog.eat();//执行方法
        dog.shout();

        //如果编码/编译的时候不知道要操作的类或者对象（运行的时候才知道），需要使用反射
        String className = "why.Dog";

        //根据字符串来创建对象
        //Dog dog2 = new "why.Dog"();
        Class clazz = Class.forName(className);
        //使用反射创建对象
        clazz.getConstructor().newInstance();

        //根据字符串来调用方法


        //根据字符串来操作属性

    }
}
