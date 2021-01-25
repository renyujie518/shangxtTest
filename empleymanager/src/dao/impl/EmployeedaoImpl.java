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
 * @Description TODO
 * @createTime 2021年01月25日 13:39:00
 */
public class EmployeedaoImpl implements Employeedao {
    @Override
    public List<Employee> finaAll() {
        Connection coon = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Employee> list = new ArrayList<>();
        try {
            coon = Dbutil.getConnection();
            stmt = coon.createStatement();
             String sql = "select * from emp;";
             rs = stmt.executeQuery(sql);
             while (rs.next()){
                 int empno = rs.getInt("empno"); //不区分大小写
                 String ename = rs.getString("ename");
                 String job = rs.getString("job");
                 int mgr = rs.getInt("mgr");
                 Date hiredate = rs.getDate("hiredate");
                 double sal = rs.getDouble("sal");
                 double comm = rs.getDouble("comm");
                 int deptno = rs.getInt("deptno");
                 Employee employee = new Employee(empno, ename, job, mgr, hiredate, sal, comm, deptno);
                 list.add(employee);
             }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            Dbutil.closeAll(rs,stmt,coon);
        }
        return list;

    }

    @Override
    public Employee findById(int empno) {
        Connection coon = null;
        Statement stmt = null;
        ResultSet rs = null;
        Employee emp = null;//返回值，默认查询不到

        try {
            coon = Dbutil.getConnection();
            stmt = coon.createStatement();
            String sql = "select * from emp where empno ="+empno+";";
            rs = stmt.executeQuery(sql);
            if (rs.next()){
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptno = rs.getInt("deptno");
                emp = new Employee(empno, ename, job, mgr, hiredate, sal, comm, deptno);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            Dbutil.closeAll(rs,stmt,coon);
        }
        return emp;
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
