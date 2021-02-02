package dao.impl;

import dao.Employeedao;
import entity.Employee;
import util.Dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName EmployeedaoImpl.java
 * @Description 借助反射完善实现类
 * @createTime 2021年01月25日 13:39:00
 */
public class EmployeedaoImpl2 implements Employeedao {
    @Override
    public List<Employee> finaAll() {
        String sql = "select * from emp;";
        Object[] params = {};
        return Dbutil.executeQuery(sql,params,"entity.Employee");
    }

    @Override
    public Employee findById(int empNo) {
        String sql = "select * from emp where empno = ?";
        Object[] params = {empNo};
        List<Employee> list = Dbutil.executeQuery(sql, params,"entity.Employee");

        //如果找到了编号员工，该集合里只会有按照Id找到的第一条
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    //再设想一种可能，按照职位和员工编号查询,可能有多个人，所以还是返回一个list
    public List<Employee> findEmp(String cjob,int cempno){
        String sql = "select * from emp where job = ? and deptno = ?;";
        Object[] params = {cjob,cempno};
        List<Employee> list = Dbutil.executeQuery(sql, params,"entity.Employee");
        return list;
    }

    @Override
    public int save(Employee emp) {
        String sql = "insert into emp values(null,?,?,?,?,?,?,?);";
        Object[] params = {emp.getEname(), emp.getJob(), emp.getMgr(), new java.sql.Date(emp.getHiredate().getTime()),
                emp.getSal(), emp.getComm(), emp.getDeptno()};
        return Dbutil.Execute(sql, params);

    }

    @Override
    public int update(Employee emp) {
        String sql = "update emp set job = ?,sal = ?,deptno = ? where empno = ?;";
        Object[] params = {emp.getJob(),emp.getSal(),emp.getDeptno(),emp.getEmpno()};
        return Dbutil.Execute(sql, params);
    }

    @Override
    public int delete(int empno) {
        String sql = "delete from emp where empno = ?;";
        return Dbutil.Execute(sql,new Object[]{empno});
    }
}
