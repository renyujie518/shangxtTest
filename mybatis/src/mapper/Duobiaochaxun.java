package mapper;

import entity.Department;
import entity.Employee;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Duobiaochaxun.java
 * @Description 多表查询
 * @createTime 2021年02月06日 18:07:00
 */
public interface Duobiaochaxun {
    //基础准备 多表查询可以分解为两个单表查询，先把这两个单表查询简单的实现一下
    //查询所有的部门
    List<Department> selectAllDept();
    //从部门查询指定编号的部门
    Department selectOneDept(int deptno);
    //查询所有员工
    List<Employee> selectAllEmp();
    //查询指定编号的员工
    Employee selectOneEmp(int empno);
    //用部门编号查到之下所有的员工
    List<Employee> selectoneEmpByDeptno(int deptno);

    //N+1方式
    //查询每个班级下的学生（1对多）
    List<Department> selectAllEmpFromDept();
    //查询所有员工所在的部门信息(1对1)
    List<Employee> selectAllDeptFromEmp();

    //多表查询
    //查询雇员和部门信息(类似左join)
    List<Employee> selectEandD();
    //查询雇员和部门信息(类似右join)
    List<Department> selectDandE();

}
