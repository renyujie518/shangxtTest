package pojo;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Teacher.java
 * @Description TODO
 * @createTime 2021年04月02日 21:09:00
 */
public class Teacher {
    private Integer tid;
    private String tname;

    public Teacher(Integer tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public Teacher() {
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                '}';
    }
}
