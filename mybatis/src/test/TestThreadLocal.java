package test;

import entity.Employee;
import mapper.EmpADUS;
import util.DbUtil;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestThreadLocal.java
 * @Description TODO
 * @createTime 2021年02月08日 13:32:00
 */
public class TestThreadLocal {
    public static void main(String[] args) {
        new TestThreadLocal().selectAll();//非static方法，不能直接通过类调用，所以创建对象后调用,这里模拟用户的一个请求

    }


    public void selectAll(){
        EmpADUS mapper = DbUtil.getSqlsession().getMapper(EmpADUS.class);
        List<Employee> list = mapper.selectAll();
        System.out.println(list);
        DbUtil.clossAll();
    }
}
