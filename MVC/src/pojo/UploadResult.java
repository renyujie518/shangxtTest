package pojo;

import java.util.Objects;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UploadResult.java
 * @Description TODO
 * @createTime 2021年04月12日 23:09:00
 */
public class UploadResult {
    private Boolean status;
    private String msg;
    private String url;  //谁上传的信息和谁绑定起来

    public UploadResult() {
    }

    public UploadResult(Boolean status, String msg, String url) {
        this.status = status;
        this.msg = msg;
        this.url = url;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadResult that = (UploadResult) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, msg, url);
    }

    @Override
    public String toString() {
        return "UploadResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
