package mapper;

import entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName EmpADUS.java
 * @Description TODO
 * @createTime 2021年02月05日 16:58:00
 */
public interface EmpADUS {
    List<Employee> selectAll();
    int insert(Employee employee);

    Employee selectBy2p(String job,int mgr);
    //List<Employee> selectBy2p(@Param("param1") String job, @Param("param2") int mgr);

    List<Employee> selectByObject(Employee employee);
}
