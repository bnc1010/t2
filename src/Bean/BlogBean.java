package Bean;

public class BlogBean {
    private int uid;
    private String aid;
    private String un;
    private String last_t;
    private String aname;
    private int sf;

    public void setUid(int uid){
        this.uid=uid;
    }

    public int getUid(){
        return uid;
    }

    public void setUn(String un){
        this.un = un;
    }

    public String getAname() {
        return aname;
    }

    public String getLast_t() {
        return last_t;
    }

    public String getUn() {
        return un;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setLast_t(String last_t) {
        this.last_t = last_t;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public int getSf() {
        return sf;
    }

    public void setSf(int sf) {
        this.sf = sf;
    }
}
