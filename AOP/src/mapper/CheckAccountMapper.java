package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pojo.Account;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CheckAccountMapper.java
 * @Description TODO
 * @createTime 2021年04月05日 21:34:00
 */
public interface CheckAccountMapper {
    //根据账户ID和密码获取账户信息
    @Select("select * from t_account where aid=#{outId} and apwd=#{outPwd}; ")
    Account checkAccountOutInfoMapper(@Param("outId") String outId,@Param("outPwd") String outPwd);

    //检验金额信息
    @Select("select * from t_account where aid=#{outId} and money>=#{money}; ")//前一个money是查出来的（余额）>= 前台传进来的
    Account checkMoneyInfoMapper(@Param("outId")String outId, @Param("money")String money);

    //检验收款人信息
    @Select("select a.* from t_account a join room_user u on a.uid=u.uid where a.aid=#{inId} and u.uname=#{inName}; ")
    Account checkInInfoMapper(@Param("inId") String inId,@Param("inName") String inName);

    //转出
    @Update("update t_account set money=money-#{money} where aid=#{outId}")
    int transferOut(@Param("outId") String outId,@Param("money") String money);
    //转入
    @Update("update t_account set money=money+#{money} where aid=#{inId}")
    int transferIn(@Param("inId") String inId,@Param("money") String money);
}
