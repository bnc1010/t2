package Bean;


public class UserBean {
    private String userName;
    private String pwd;
    private String email;
    private int uid;
    private String last_t;
    private int normal;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setLast_t(String last_t) { this.last_t = last_t; }

    public String getLast_t() { return last_t; }

    public int getNormal() { return normal; }

    public void setNormal(int normal) { this.normal = normal; }
}
