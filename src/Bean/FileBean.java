package Bean;

public class FileBean {
    private String fname;
    private String fid;
    private int uid;
    private String uname;
    private String size;
    private String type;
    private boolean isdir;
    private String submit_t;
    private String fa;

    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getFid() {
        return fid;
    }

    public String getFname() {
        return fname;
    }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }

    public String getUname() { return uname; }

    public void setUname(String uname) { this.uname = uname; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public boolean getIsdir() { return isdir; }

    public void setIsdir(boolean isdir) { this.isdir = isdir; }

    public String getSubmit_t() { return submit_t; }

    public void setSubmit_t(String submit_t) { this.submit_t = submit_t; }

    public String getFa() { return fa; }

    public void setFa(String fa) { this.fa = fa; }
}
