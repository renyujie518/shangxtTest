package entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -3369173952420828971L;//保证类内属性的变化时防止反序列化版本一致的异常
    private String userId;
    private String passWorld;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWorld() {
        return passWorld;
    }

    public void setPassWorld(String passWorld) {
        this.passWorld = passWorld;
    }

    public User() {
    }

    public User(String userId, String passWorld) {
        this.userId = userId;
        this.passWorld = passWorld;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", passWorld='" + passWorld + '\'' +
                '}';
    }
}
