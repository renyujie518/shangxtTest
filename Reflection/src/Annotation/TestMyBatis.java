package Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestMyBatis.java
 * @Description TODO
 * @createTime 2021年02月02日 21:35:00
 */
public class TestMyBatis {
    public static void main(String[] args) throws NoSuchFieldException {
        //读取注解
        Class<ORMstudent> clazz = ORMstudent.class;
        Annotation[] annotations = clazz.getAnnotations();//返回所有的注解名字
        for (Annotation annotation : annotations) {
            System.out.println(annotation);//注意这里@SuppressWarnings("ALL")读不到，因为级别是代码级别，反射读不到
            
        }
        //Annotation annotation2 = clazz.getAnnotation(Table.class);  //获得指定注解
        Table annotation2 = (Table)clazz.getAnnotation(Table.class);  //强转后可以获得注解内定义的变量内容
        System.out.println(annotation2);
        System.out.println(annotation2.name());//tb_student

        Field f = clazz.getDeclaredField("name");//要想获得类内注解的内容，先获取字段
        //Annotation[] annotations1 = f.getAnnotations(); //获得这个字段的所有注解
        Column column = f.getAnnotation(Column.class);
        System.out.println(column);
        System.out.println(column.name());
        System.out.println(column.precision());
        System.out.println(column.type());
        System.out.println(column.length());

        
        //根据读取的数据信息创建表

        //读取注解对表增删改


    }
}
