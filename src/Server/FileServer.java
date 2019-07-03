package Server;


import Bean.BlogBean;
import Bean.DbBean;
import Bean.FileBean;
import Bean.UserBean;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.UUID;

public class FileServer {
    private DbBean dbBean = new DbBean();
    private String sql = null;
    private CommonServer cs = new CommonServer();
    /*
     *获取某用户第x页的y个文件
     * paras[3]每页文件数
     */
    public FileBean[] GetFiles(UserBean user,String fa, int index) throws Exception{
        FileBean [] ret = new FileBean[11];
        dbBean.openConnection();
        sql = "SELECT * from files where uid=? and fa=? order by type desc, fname asc limit ?, ? ";
        Object [] paras = new Object[8];
        paras[0] = user.getUid();
        paras[1] = fa;
        paras[2] = (index-1)*10;
        paras[3] = 10;
        paras[5] = true;
        paras[4] = paras[6] = paras[7] = false;
        ResultSet rs = dbBean.executeQuery(sql, 4, paras);
        int pos = 0;
        while (rs.next()){
            ret[pos] = new FileBean();
            ret[pos].setFname(rs.getString("fname"));
            ret[pos].setFid(rs.getString("fid"));
            ret[pos].setUid(rs.getInt("uid"));
            ret[pos].setSubmit_t(rs.getString("submit_t"));
            ret[pos].setFa(fa);
            ret[pos].setIsdir(rs.getBoolean("type"));
            ret[pos].setSize(rs.getString("sz"));
            if (!ret[pos].getIsdir()){
                String tmp = ret[pos].getFname().substring(ret[pos].getFname().lastIndexOf('.'));
                ret[pos].setType(tmp);
            }
            pos++;
        }
        dbBean.closeConnection();
        return ret;
    }


    public ArrayList<FileBean> GetFiles(UserBean user, String fa) throws Exception{
        ArrayList<FileBean> ret = new ArrayList<>();
        dbBean.openConnection();
        sql = "SELECT * from files where uid=? and fa=?";
        Object [] paras = new Object[4];
        paras[0] = user.getUid();
        paras[1] = fa;
        paras[2] = paras[3] = true;
        ResultSet rs = dbBean.executeQuery(sql, 2, paras);
        int pos = 0;
        FileBean tp;
        while (rs.next()){
            tp = new FileBean();
            tp.setFname(rs.getString("fname"));
            tp.setFid(rs.getString("fid"));
            tp.setUid(rs.getInt("uid"));
            tp.setSubmit_t(rs.getString("submit_t"));
            tp.setFa(fa);
            tp.setIsdir(rs.getBoolean("type"));
            tp.setSize(rs.getString("sz"));
            if (!tp.getIsdir()){
                String tmp = tp.getFname().substring(tp.getFname().lastIndexOf('.'));
                tp.setType(tmp);
            }
            ret.add(tp);
        }
        dbBean.closeConnection();
        return ret;
    }


    public String FindFaFolder(String nowFolder)throws Exception{
        dbBean.openConnection();
        sql = "select fa from files where fid=?";
        Object [] paras = new Object[2];
        paras[0] = nowFolder;
        paras[1] = true;
        ResultSet rs = dbBean.executeQuery(sql, 1, paras);
        String ret ="";
        if (rs.next()){
            ret = rs.getString("fa");
        }
        return ret;
    }


    public int GetUserPageFileCount(UserBean user, String fa) throws Exception{
        dbBean.openConnection();
        sql = "select count(*) from files where uid=? and fa=?";
        Object [] paras = new Object[4];
        paras[0] = user.getUid();
        paras[1] = fa;
        paras[2] = false;
        paras[3] = true;
        ResultSet rs = dbBean.executeQuery(sql,2 ,paras);
        int ret = 0;
        if (rs.next()){
            ret = rs.getInt("count(*)");
        }
        dbBean.closeConnection();
        return (ret - 1)/10 + 1;
    }

    public FileBean GetFile(String fid)throws Exception {
        dbBean.openConnection();
        sql = "select * from files where fid =?";
        Object[] paras = new Object[2];
        paras[0] = fid;
        paras[1] = true;
        ResultSet rs = dbBean.executeQuery(sql, 1, paras);
        FileBean ret = new FileBean();
        ret.setFid(fid);
        while (rs.next()){
            ret.setUid(rs.getInt("uid"));
            ret.setFname(rs.getString("fname"));
            ret.setSize(rs.getString("sz"));
            ret.setIsdir(rs.getBoolean("type"));
            if (!ret.getIsdir()) {
                String tmp = ret.getFname().substring(ret.getFname().lastIndexOf('.'));
                ret.setType(tmp);
            }
            ret.setSubmit_t(rs.getString("submit_t"));
            ret.setFa(rs.getString("fa"));
            ret.setIsdir(rs.getBoolean("type"));
        }
        dbBean.closeConnection();
        return ret;
    }


    public boolean CheckFolderValid(String fname,String fa, UserBean user)throws Exception {
        dbBean.openConnection();
        sql = "select * from files where fa=? and fname=? and uid=?";
        Object[] paras = new Object[6];
        paras[0] = fa;
        paras[1] = fname;
        paras[2] = user.getUid();
        paras[3] = paras[4] = true;paras[5] = false;
        ResultSet rs = dbBean.executeQuery(sql ,3, paras);
        boolean ret = !rs.next();
        dbBean.closeConnection();
        return ret;
    }

    public boolean SaveFolder(String fname,String fa,UserBean user)throws Exception {
        dbBean.openConnection();
        sql = "insert into files values(?,?,?,?,?,?,?)";
        Object[] paras = new Object[14];
        paras[0] = user.getUid();
        paras[1] = fname;
        paras[2] = UUID.randomUUID().toString();;
        paras[3] = "0";
        paras[4] = 1;
        paras[5] = cs.GetTime();
        paras[6] = fa;
        for (int i = 7; i < 14; i++){
            paras[i] = true;
        }
        int n = 0;
        n = dbBean.executeUpdate(sql, 7,paras);
        dbBean.closeConnection();
        return n > 0;
    }


    /*
     *保存文件
     */
    public boolean SaveFile(UserBean user, String file, String fid, String sz,String fa)throws Exception{
        dbBean.openConnection();
        sql = "insert into files values(?,?,?,?,?,?,?)";
        Object[] paras = new Object[14];
        paras[0] = user.getUid();
        paras[1] = file;
        paras[2] = fid;
        paras[3] = sz;
        paras[4] = 0;
        paras[5] = cs.GetTime();
        paras[6] = fa;
        for (int i = 7; i < 14; i++){
            paras[i] = true;
        }
        int n = dbBean.executeUpdate(sql, 7, paras);
        dbBean.closeConnection();
        return n > 0;
    }

    /*
     *删除文件
     */
    public boolean DeleteFile(String fid)throws Exception {
        dbBean.openConnection();
        sql = "delete from files where fid=?;";
        Object[] paras = new Object[2];
        paras[0] = fid;
        paras[1] = true;
        int n = dbBean.executeUpdate(sql, 1, paras);
        dbBean.closeConnection();
        return n > 0;
    }


    public boolean SaveBlog(String aid, UserBean user, String artical, int self)throws Exception{
        dbBean.openConnection();
        sql = "insert into articals values(?,?,?,?,?,?)";
        Object[] paras = new Object[12];
        paras[0] = aid;
        paras[1] = artical;
        paras[2] = self;
        paras[3] = 0;
        paras[4] = user.getUid();
        paras[5] = cs.GetTime();
        paras[6] = paras[7] = paras[11] = true;
        paras[8] = paras[9] = paras[10] = false;
        int n = dbBean.executeUpdate(sql, 6, paras);
        dbBean.closeConnection();
        return n > 0;
    }

    public BlogBean [] GetArticals(UserBean user, int index) throws Exception{
        BlogBean [] ret = new BlogBean[11];
        dbBean.openConnection();
        sql = "SELECT * from articals where uid=? limit ?, ?";
        Object [] paras = new Object[6];
        paras[0] = user.getUid();
        paras[1] = (index-1)*10;
        paras[2] = 10;
        paras[3] = paras[4] = paras[5] = false;
        ResultSet rs = dbBean.executeQuery(sql, 3, paras);
        int pos = 0;
        while (rs.next()){
            ret[pos] = new BlogBean();
            ret[pos].setAname(rs.getString("aname"));
            ret[pos].setLast_t(rs.getString("last_t"));
            ret[pos].setAid(rs.getString("aid"));
            pos++;
        }
        dbBean.closeConnection();
        return ret;
    }


    public BlogBean[] GetPublicArticals(int index) throws Exception{
        BlogBean [] ret = new BlogBean[11];
        dbBean.openConnection();
        sql = "SELECT aid,aname,articals.last_t,uid,name from articals,users where self=1 and users.id=articals.uid limit ?, ?";
        Object [] paras = new Object[4];
        paras[0] = (index-1)*10;
        paras[1] = 10;
        paras[2] = paras[3] = false;
        ResultSet rs = dbBean.executeQuery(sql, 2, paras);
        int pos = 0;
        while (rs.next()){
            ret[pos] = new BlogBean();
            ret[pos].setAname(rs.getString("aname"));
            ret[pos].setLast_t(rs.getString("articals.last_t"));
            ret[pos].setUid(rs.getInt("uid"));
            ret[pos].setUn(rs.getString("name"));
            ret[pos].setAid(rs.getString("aid"));
            pos++;
        }
        dbBean.closeConnection();
        return ret;
    }

    public boolean VisitBlog(BlogBean blog) throws Exception {
        dbBean.openConnection();
        sql = "update articals set visit=visit+1 where aname=? and uid=?";
        Object [] paras = new Object[4];
        paras[0] = blog.getAname();
        paras[1] = blog.getUid();
        paras[2] = true;
        paras[3] = false;
        int n = dbBean.executeUpdate(sql, 2, paras);
        dbBean.closeConnection();
        return n > 0;
    }

    public BlogBean getArtical(String aid)throws Exception{
        dbBean.openConnection();
        sql = "select aid,aname,articals.last_t,uid,name from articals,users where aid=? and users.id=uid";
        Object[] paras = new Object[2];
        paras[0] = aid;
        paras[1] = true;
        BlogBean ret = new BlogBean();
        ResultSet rs = dbBean.executeQuery(sql, 1, paras);
        if (rs.next()){
            ret.setAname(rs.getString("aname"));
            ret.setLast_t(rs.getString("articals.last_t"));
            ret.setUid(rs.getInt("uid"));
            ret.setUn(rs.getString("name"));
            ret.setAid(rs.getString("aid"));
        }
        dbBean.closeConnection();
        return ret;
    }

    public boolean UpdateBlog(String aid, String newname, int self)throws Exception{
        dbBean.openConnection();
        sql = "update articals set last_t=?,aname=?,self=? where aid=?";
        Object[] paras = new Object[8];
        paras[0] = cs.GetTime();
        paras[1] = newname;
        paras[2] = self;
        paras[3] = aid;
        paras[4] = paras[5] = paras[7] = true;
        paras[6] = false;
        int n = dbBean.executeUpdate(sql, 4, paras);
        dbBean.closeConnection();
        return n > 0;
    }

    public boolean DeleteBlog(String aid)throws Exception{
        dbBean.openConnection();
        sql = "delete from articals where aid=?";
        Object[] paras = new Object[2];
        paras[0] = aid;
        paras[1] = true;
        int n = dbBean.executeUpdate(sql, 1, paras);
        dbBean.closeConnection();
        return n > 0;
    }

}
