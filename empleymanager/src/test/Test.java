package test;

import dao.Employeedao;
import dao.impl.EmployeedaoImpl;
import entity.Employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test.java
 * @Description 前台
 * @createTime 2021年01月25日 13:36:00
 */
public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("");
            System.out.println("*****欢迎进入员工管理系统*****");
            System.out.println("\t1.查询所有员工信息");
            System.out.println("\t2.查询指定编号员工");
            System.out.println("\t3.添加员工信息");
            System.out.println("\t4.修改员工信息");
            System.out.println("\t5.删除员工信息");
            System.out.println("\t6.退出");
            System.out.println("***************************");
            System.out.print("请选择菜单：");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    findAll();
                    break;//掉出循环
                case 2:
                    findById();
                    break;

                case 3:
                    save();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    System.out.println("谢谢使用");
                    return; //main方法结束
                default:
                    System.out.println("输入错误");
            }
            System.out.println("请输入任意键继续");
            //input.next();
            input.nextLine();//回车也算字符，继续
            input.nextLine();//真正的回车
        }while(true);
    }


    public static  void findAll(){
        //调用后台，在前台输出
        Employeedao employeedao = new EmployeedaoImpl(); //多态
        List<Employee> emlist = employeedao.finaAll();
        System.out.println("编号\t姓名\t职位\t上级编号\t入职日期\t薪水\t奖金\t所属 部门编号");
        for (Employee emp : emlist) {
            System.out.println(emp.getEmpno()+"\t"+emp.getEname()+"\t"+emp.getJob()+"\t"
                    +emp.getMgr()+"\t"+emp.getHiredate()+"\t"+emp.getSal()+"\t"+emp.getComm()+"\t"+emp.getDeptno());
        }

    }

    public static  void findById(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int empno = sc.nextInt();
        //调用后台，在前台输出
        Employeedao employeedao = new EmployeedaoImpl(); //多态
        Employee emp = employeedao.findById(empno);
        if (emp == null){
            System.out.println("查无此人");
        }else {
            System.out.println("编号\t姓名\t职位\t上级编号\t入职日期\t薪水\t奖金\t所属 部门编号");
            System.out.println(emp.getEmpno()+"\t"+emp.getEname()+"\t"+emp.getJob()+"\t"
                    +emp.getMgr()+"\t"+emp.getHiredate()+"\t"+emp.getSal()+"\t"+emp.getComm()+"\t"+emp.getDeptno());
        }
    }

    //添加员工
    public static void save(){
        EmployeedaoImpl employeedao = new EmployeedaoImpl();
        //从键盘输入要添加的员工信息11
        Scanner input = new Scanner(System.in);
        System.out.println("请输入员工姓名");
        String  ename =  input.next();
        System.out.println("请输入员工岗位");
        String  job =  input.next();
        System.out.println("请输入员工上级编号");
        int  mgr =  input.nextInt();
        System.out.println("请输入员工入职时间(yyyy-MM-dd)");
        String sdate = input.next(); //"1999-12-23"
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hiredate = null;
        try {
            hiredate = sdf.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入员工薪水");
        double  sal =  input.nextDouble();
        System.out.println("请输入员工津贴");
        double  comm =  input.nextDouble();
        System.out.println("请输入员工部门编号");
        int  deptno =  input.nextInt();

        Employee emp = new Employee(ename, job, mgr, hiredate, sal, comm, deptno);
        int n = employeedao.save(emp);
        if (n>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    //修改员工信息
    public static void update() {
        EmployeedaoImpl employeedao = new EmployeedaoImpl();
        //从键盘输入要添加的员工信息
        Scanner input = new Scanner(System.in);
        System.out.println("请输入员工岗位");
        String job = input.next();
        System.out.println("请输入员工薪水");
        double sal = input.nextDouble();
        System.out.println("请输入员工部门编号");
        int deptno = input.nextInt();
        System.out.println("请输入员工编号");
        int empno = input.nextInt();
        Employee emp = new Employee(empno, job,sal,deptno);
        int n = employeedao.update(emp);
        if (n > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    //删除员工信息
    public static void delete() {
        EmployeedaoImpl employeedao = new EmployeedaoImpl();
        //从键盘输入要添加的员工信息
        Scanner input = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int empno = input.nextInt();
        int n = employeedao.delete(empno);
        if (n > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }
}
