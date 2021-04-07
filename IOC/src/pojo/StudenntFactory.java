package pojo;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName StudenntFactory.java
 * @Description TODO
 * @createTime 2021年04月02日 20:53:00
 */
public class StudenntFactory {
    public Student newInstance(){
        System.out.println("动态工厂");
        return new Student();
    }
}
