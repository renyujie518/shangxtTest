package entity;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021年01月24日 18:52:00
 */
public class User {
    private  String userId;
    private  String realName;
    private  String passsWord;
    private  double money;

    public User() {
    }

    public User(String userId, String realName, String passsWord, double money) {
        this.userId = userId;
        this.realName = realName;
        this.passsWord = passsWord;
        this.money = money;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPasssWord() {
        return passsWord;
    }

    public void setPasssWord(String passsWord) {
        this.passsWord = passsWord;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", realName='" + realName + '\'' +
                ", passsWord='" + passsWord + '\'' +
                ", money=" + money +
                '}';
    }
}
