package TestClass;

import why.Dog;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestClass1.java
 * @Description TODO
 * @createTime 2021年01月26日 14:16:00
 */
public class TestClass1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        //获取一个类的类对象(3种)
        //Class<?> clazz = Class.forName("why.Dog");
        //Class<Dog> clazz = Dog.class;//类名获得

        Dog dog = new Dog();
        Class<? extends Dog> clazz = dog.getClass(); //对象名获得


        //获取类的结构信息
        System.out.println(clazz.getName());
        System.out.println(Arrays.toString(clazz.getInterfaces())); //接口
        System.out.println(clazz.getSuperclass());//父类的类对象
        System.out.println(clazz.getSimpleName());//类名
        System.out.println(Modifier.toString(clazz.getModifiers()));//修饰符 0默认 1public

        //获取成员变量
        Field[] fields1 = clazz.getFields();//得到包括上级的public的变量
        for (Field field : fields1) {
            System.out.println(field);
            System.out.println();
        }
        Field[] fields2 = clazz.getDeclaredFields();//得到所有属性，但不包括父类
        for (Field field : fields2) {
            System.out.println(field);
            System.out.println();
        }
        Field f = clazz.getField("nickName");//获取公共属性的指定信息
        System.out.println(f);
        System.out.println(f.getName()+f.getType()+Modifier.toString(f.getModifiers()));
        System.out.println();

        Field f2 = clazz.getDeclaredField("type"); //获取所有属性指定信息，包括protect,private,但不包括父类
        Field f3 = clazz.getSuperclass().getDeclaredField("age");//获取父类的所有属性指定信息

        System.out.println("============");
        //获取成员方法
        Method[] methods = clazz.getMethods();//得到包括上级的public的方法
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("============");
        Method[] methods2 = clazz.getDeclaredMethods();//得到包括本类的所有方法，包括protect,private。,但不包括父类
        for (Method method : methods2) {
            System.out.println(method);
        }
        System.out.println("============");
        //Method shouts = clazz.getMethod("shout"); //不加参数默认无参构造方法
        //Method shouts = clazz.getDeclaredMethod("guard"); //只能获得本类的方法，否则会报错
        Method shouts = clazz.getMethod("shout",String.class,int.class);
        System.out.println(shouts);

        System.out.println("============");
        //构造方法
        Constructor<?>[] constructors = clazz.getConstructors();//构造方法肯定只能是本类的，但只是public的
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        Constructor<?>[] constructors2 = clazz.getDeclaredConstructors();//构造方法肯定只能是本类的，加Declared获取全部类型
        for (Constructor<?> constructor : constructors2) {
            System.out.println(constructor);
        }

        System.out.println("============");
        //Constructor<?> constructor = clazz.getConstructor();//不加参数默认无参构造方法
        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class,int.class,String.class);//少参数会报错
        System.out.println(constructor);


    }
}
