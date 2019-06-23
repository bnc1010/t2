package Bean;

public class FileBean {
    private String fname;
    private String fid;
    private int uid;
    private String uname;
    private String size;
    private String type;

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
}
