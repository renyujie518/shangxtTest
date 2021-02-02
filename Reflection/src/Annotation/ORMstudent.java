package Annotation;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ORMstudent.java
 * @Description 模仿mybatis的映射，利用注解和反射
 * @createTime 2021年02月02日 21:24:00
 */


@Deprecated
@SuppressWarnings("ALL")
@Table(name = "tb_student")
public class ORMstudent {
    @Column(name= "sno",type = "int",length = 10)
    private int sno;
    @Column(name= "sname",type = "varchar",length = 15)
    private String name;
    @Column(name= "credit",type = "double",length = 7,precision = 2)
    private double scroe;
}
