package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.User;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2021年04月07日 20:07:00
 */
public interface UserMapper {
    //用户登录
    @Select("select * from room_user where uname=#{uname} and pwd=#{pwd}" )
    User userLoginMapper(@Param("uname") String uname, @Param("pwd") String pwd);
}
