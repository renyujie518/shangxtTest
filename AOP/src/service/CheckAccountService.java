package service;

import pojo.Account;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CheckAccountService.java
 * @Description TODO
 * @createTime 2021年04月05日 21:31:00
 */
public interface CheckAccountService {
    //检验转账信息
    Account checkOutAccountInfoService(String outId, String outPwd);
    //检验金额信息
    Account checkMoneyInfoService(String outId,String money);
    //检验收款人信息
    Account checkInInfoService(String inId, String inName);
    //转账
    int transferInfoService(String outId, String inId, String money);
}
