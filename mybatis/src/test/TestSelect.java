package test;

import entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestA.java
 * @Description TODO
 * @createTime 2021年02月04日 16:35:00
 */
public class TestSelect {
    public static void main(String[] args) throws IOException {
        //解析mybatis.xml
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        //获得session工厂
        SqlSessionFactory factor = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session对象
        SqlSession sqlSession = factor.openSession();
        //调用方法
        //结果集返回多条数据
        List<Employee> list = sqlSession.selectList("mapper.EMPMapper.selectAll");
        System.out.println(list);
        System.out.println("======");

        //结果集最多返回1条数据
        Employee e = sqlSession.selectOne("mapper.EMPMapper.selectOne");
        System.out.println(e);
        System.out.println("======");

        //查询返回map ,希望可以通过key快速的找到结果
        Map<Object, Object> empnoMap = sqlSession.selectMap("mapper.EMPMapper.selectMap", "EMPNO");
        System.out.println(empnoMap);
        Object o = empnoMap.get(7844);
        System.out.println(o);
        System.out.println("======");

        //参数传递
        Employee e2 = sqlSession.selectOne("mapper.EMPMapper.selectbyID", 7844);
        System.out.println(e2);
        System.out.println("======");

        //单表传递多个参数，可以传入一个对象
        Employee employee = new Employee();
        employee.setMgr(7839);
        employee.setJob("manager");
        List<Object> list2 = sqlSession.selectList("mapper.EMPMapper.selectby2Param", employee);
        System.out.println(list2);
        System.out.println("======");

        //假如多表传递多个参数，就不能传入一个对象，因为这可能不是一个对象
        //可以通过传入一个map的键值对，本例中仍然用单表做示例，意思到位了
        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("a",7839);//这个a要传入EMPMapper.xml占位符的key
        map1.put("b","manager");
        List<Object> list3 = sqlSession.selectList("mapper.EMPMapper.selectBy2ParamInMap", map1);
        System.out.println(list2);    
        System.out.println("======");


        //关闭sqlSession
         sqlSession.close();
    }
}
