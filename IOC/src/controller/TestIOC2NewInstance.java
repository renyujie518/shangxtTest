package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Student;
import pojo.StudentDI;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestIOC.java
 * @Description TODO
 * @createTime 2021年04月02日 19:39:00
 */
public class TestIOC2NewInstance {
    public static void main(String[] args) {
        //获取容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
        //获取对象
        //无参构造器(默认) 容器对象在创建的时候，就完成了配置文件的对象资源的初始化创建 直接从容器中获取使用
        Student stu1 = (Student) ac.getBean("stu1");
        System.out.println("无参构造器"+stu1);
        //有参数的构造器
        Student stu2 = (Student) ac.getBean("stu2");
        System.out.println("有参构造器"+stu2);
        //属性注入 set 注意 这里无参构造器会打印两次 这代表是先创建无参 在通过property注入
        Student stu3 = (Student) ac.getBean("stu3");
        System.out.println("属性注入"+stu3);
        //工厂模式
        Student stu4 = (Student) ac.getBean("stu4");
        System.out.println("动态工厂模式"+stu4);
        //DI
        StudentDI stu5 = (StudentDI) ac.getBean("stu5");
        System.out.println("DI"+stu5);
    }
}
