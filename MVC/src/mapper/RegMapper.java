package mapper;

import org.apache.ibatis.annotations.Insert;
import pojo.User;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RegMapper.java
 * @Description TODO
 * @createTime 2021年04月13日 00:28:00
 */
public interface RegMapper {
    //用户注册
    @Insert("insert into room_user values(default,#{uname},#{pwd},#{img})")
    int insUserInfoMapper(User user);
}
