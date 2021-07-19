package pojo;

import java.util.Objects;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021年04月07日 20:02:00
 */
public class User {
    private Integer uid;
    private String uname;
    private String pwd;
    private String img;

    public User(Integer uid, String uname, String pwd) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;

    }

    public User() {
    }

    public User(Integer uid, String uname, String pwd, String img) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;
        this.img = img;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) &&
                Objects.equals(uname, user.uname) &&
                Objects.equals(pwd, user.pwd) &&
                Objects.equals(img, user.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, uname, pwd, img);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
