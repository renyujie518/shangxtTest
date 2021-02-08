package entity;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Department.java
 * @Description TODO
 * @createTime 2021年02月06日 18:12:00
 */
public class Department {
    private int deptno;
    private String dname;
    private String loc;
    private List<Employee> emplist;


    public Department() {
    }

    public Department(int deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Department(int deptno, String dname, String loc, List<Employee> emplist) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
        this.emplist = emplist;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public List<Employee> getEmplist() {
        return emplist;
    }

    public void setEmplist(List<Employee> emplist) {
        this.emplist = emplist;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                ", emplist=" + emplist +
                '}';
    }
}
