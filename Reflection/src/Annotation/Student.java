package Annotation;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Student.java
 * @Description TODO
 * @createTime 2021年02月02日 20:15:00
 */

@MyAnnotation(id=1,name = "sss",scors = {10,21,3.21})


public class Student {

    @Myanotation2(value = {"java"},key = 12)
    private  int sno;

    @Myanotation2(value = {"java,Data.AI"})
    public Student(){

    }

    @MyAnnotation(id=1,name = "asad")
    public void method1(@MyAnnotation String name,int age ){

    }

    public static void main(String[] args) {
        System.out.println("=====");
    }
}
