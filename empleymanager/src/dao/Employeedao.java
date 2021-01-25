package dao;

import entity.Employee;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Employeedao.java
 * @Description TODO
 * @createTime 2021年01月25日 13:38:00
 */
public interface Employeedao {
    //查询所有员工
    public List<Employee> finaAll();
    //查询指定编号的员工
    public Employee findById(int empni);
    //添加员工
    public int save(Employee emp);
    //修改员工
    public int update(Employee emp);
    //删除员工
    public int delete(int empno);

}
