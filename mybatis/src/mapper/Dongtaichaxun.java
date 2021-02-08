package mapper;

import entity.Employee;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Dongtaichaxun.java
 * @Description TODO
 * @createTime 2021年02月05日 21:24:00
 */
public interface Dongtaichaxun {
    //按照条件查询 输入empno按照empno查询，输入eneme按照ename查询，两个都输两个都要，什么都不输查询所有
    List<Employee> selectIf(int empno, String ename);

    //where标签会自动增加where关键字（相当于再加一组条件判断，如果where标签前符合，标签后就不会考虑），会把第一个多余的and去掉
    List<Employee> selectWhere(int empno, String ename);

    //when
    List<Employee> selectWhen(int empno, String ename);

    //set适合修改,自动增加set关键字同时去除最后一个逗号,ename不传就不改，job不传就不改
    int updateSet(Employee employee);

    //trim 可以添加（去除）前缀，添加（去除）后缀
    int updateTrim(Employee employee);

    //foreach 用于遍历，比如一次查出empno = 1,2,3的信息 类比于 select* from emp where empno in(1,2,3)
    List<Employee> selectForeach(List<Integer> list);

    //bind  模糊查询 比如名字中包含S的 select* from emp where ename like '%S%'
    List<Employee> selectBind(String jobin, String enamein);

    //include sql定义公共片段

}
