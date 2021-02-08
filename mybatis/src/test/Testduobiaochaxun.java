package test;

import entity.Department;
import entity.Employee;
import mapper.Duobiaochaxun;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Testduobiaochaxun.java
 * @Description TODO
 * @createTime 2021年02月06日 18:09:00
 */
public class Testduobiaochaxun {
    public static void main(String[] args) throws IOException {
        //解析mybatis.xml
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        //获得session工厂
        SqlSessionFactory factor = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session对象
        SqlSession sqlSession = factor.openSession(true);//自动提交
        Duobiaochaxun mapper = sqlSession.getMapper(Duobiaochaxun.class);

        //N+1方法
        //查询所有员工所在的部门信息(1对1)
        List<Employee> list = mapper.selectAllDeptFromEmp();
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("=============");

        //查询所有班级的学生
        List<Department> departments = mapper.selectAllEmpFromDept();
        for (Department department : departments) {
            System.out.println(department);
        }
        System.out.println("=============");

        //多表查询
        //查询雇员和部门信息(类似左join)
        List<Employee> list2 = mapper.selectEandD();
        for (Employee employee : list2) {
            System.out.println(employee.getEmpno()+"\t"+employee.getEname()+"\t"+employee.getDeptno()+"\t"+employee.getDept().getDname());
        }
        System.out.println("=============");
        //查询雇员和部门信息(类似右join)
        List<Department> departments1 = mapper.selectDandE();
        for (Department department : departments1) {
            System.out.println(department.getDeptno()+"\t"+department.getDname()+"\t");
            for (int i = 0; i < department.getEmplist().size(); i++) {
                System.out.println(department.getEmplist().get(i).getEmpno()+"\t"
                        +department.getEmplist().get(i).getEname());
            }
        }
        System.out.println("==================");


        sqlSession.close();
    }
}
