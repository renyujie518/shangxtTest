package pojo;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Student.java
 * @Description TODO
 * @createTime 2021年04月02日 19:33:00
 */
public class Student {
    private Integer sid;
    private String sname;
    private String fav; //爱好

    public Student(Integer sid, String sname, String fav) {
        this.sid = sid;
        this.sname = sname;
        this.fav = fav;
        System.out.println("sid = " + sid + ", sname = " + sname + ", fav = " + fav);
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", fav='" + fav + '\'' +
                '}';
    }

    public Student() {
        System.out.println("我是学生的无参构造器");
    }
}
