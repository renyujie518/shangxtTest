package service;

import mapper.CheckAccountMapper;
import pojo.Account;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CheckAccountServiceImpl.java
 * @Description TODO
 * @createTime 2021年04月05日 21:33:00
 */
public class CheckAccountServiceImpl implements CheckAccountService{

    private CheckAccountMapper checkAccountMapper;

    public CheckAccountMapper getCheckAccountMapper() {
        return checkAccountMapper;
    }

    public void setCheckAccountMapper(CheckAccountMapper checkAccountMapper) {
        this.checkAccountMapper = checkAccountMapper;
    }
    //校验转账账户
    @Override
    public Account checkOutAccountInfoService(String outId, String outPwd) {
        return checkAccountMapper.checkAccountOutInfoMapper(outId, outPwd);

    }

    //检验金额信息
    @Override
    public Account checkMoneyInfoService(String outId, String money) {
        return checkAccountMapper.checkMoneyInfoMapper(outId, money);
    }
    //检验收款人信息
    @Override
    public Account checkInInfoService(String inId, String inName) {
        return checkAccountMapper.checkInInfoMapper(inId, inName);
    }

    //转账
    @Override
    public int transferInfoService(String outId, String inId, String money) {
        //出账
        int out = checkAccountMapper.transferOut(outId, money);
        //入账
        out += checkAccountMapper.transferIn(inId, money);
        return out;
    }
}
