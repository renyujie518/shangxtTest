package pojo;

import java.util.Objects;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName StudentDi.java
 * @Description TODO
 * @createTime 2021年04月02日 21:10:00
 */
public class StudentDI {
    private Integer sid;
    private String sname;
    private String fav;
    private Teacher teacher;

    public StudentDI(Integer sid, String sname, String fav, Teacher teacher) {
        this.sid = sid;
        this.sname = sname;
        this.fav = fav;
        this.teacher = teacher;
    }

    public StudentDI() {
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDI studentDI = (StudentDI) o;
        return Objects.equals(sid, studentDI.sid) &&
                Objects.equals(sname, studentDI.sname) &&
                Objects.equals(fav, studentDI.fav) &&
                Objects.equals(teacher, studentDI.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, sname, fav, teacher);
    }

    @Override
    public String toString() {
        return "StudentDI{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", fav='" + fav + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
