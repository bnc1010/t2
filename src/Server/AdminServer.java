package Server;

import Bean.BlogBean;
import Bean.DbBean;
import Bean.UserBean;
import Bean.FileBean;
import java.sql.ResultSet;

public class AdminServer {
    private DbBean dbBean = new DbBean();
    private String sql = null;
    private CommonServer cs = new CommonServer();

    public int SumBlog()throws Exception {
        dbBean.openConnection();
        sql = "select count(*) from articals";
        ResultSet rs = dbBean.executeQuery(sql, 0 );
        int n = 0;
        if (rs.next()){
            n = rs.getInt("count(*)");
        }
        dbBean.closeConnection();
        return n;
    }

    public int SumFile()throws Exception {
        dbBean.openConnection();
        sql = "select count(*) from files";
        ResultSet rs = dbBean.executeQuery(sql, 0 );
        int n = 0;
        if (rs.next()){
            n = rs.getInt("count(*)");
        }
        dbBean.closeConnection();
        return n;
    }

    public int GetAllArticalPageCount()throws Exception {
        dbBean.openConnection();
        sql = "select count(*) from articals";
        ResultSet rs = dbBean.executeQuery(sql, 0 );
        int n = 0;
        if (rs.next()){
            n = rs.getInt("count(*)");
        }
        dbBean.closeConnection();
        return (n - 1) / 10 + 1;
    }


    public BlogBean[] GetAllArtical(int index)throws Exception {
        BlogBean [] ret = new BlogBean[11];
        dbBean.openConnection();
        sql = "SELECT aid,aname,articals.last_t,uid,name,self from articals,users where users.id=articals.uid limit ?, ?";
        Object [] paras = new Object[4];
        paras[0] = (index-1)*10;
        paras[1] = 10;
        paras[2] = paras[3] = false;
        ResultSet rs = dbBean.executeQuery(sql, 2, paras);
        int pos = 0;
        while (rs.next()){
            ret[pos] = new BlogBean();
            ret[pos].setSf(rs.getInt("self"));
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

    public int GetAllUserPageCount()throws Exception {
        dbBean.openConnection();
        sql = "select count(*) from users where id!=1";
        ResultSet rs = dbBean.executeQuery(sql, 0 );
        int n = 0;
        if (rs.next()){
            n = rs.getInt("count(*)");
        }
        dbBean.closeConnection();
        return (n - 1) / 10 + 1;
    }

    public UserBean[] GetAllUser(int index)throws Exception {
        UserBean [] ret = new UserBean[11];
        dbBean.openConnection();
        sql = "SELECT id,name,email,last_t,sta from users where id!=1 limit ?, ?";
        Object [] paras = new Object[4];
        paras[0] = (index-1)*10;
        paras[1] = 10;
        paras[2] = paras[3] = false;
        ResultSet rs = dbBean.executeQuery(sql, 2, paras);
        int pos = 0;
        while (rs.next()){
            ret[pos] = new UserBean();
            ret[pos].setUserName(rs.getString("name"));
            ret[pos].setEmail(rs.getString("email"));
            ret[pos].setUid(rs.getInt("id"));
            ret[pos].setLast_t(rs.getString("last_t"));
            ret[pos].setNormal(rs.getInt("sta"));
            pos++;
        }
        dbBean.closeConnection();
        return ret;
    }

    public boolean ResetPwd(String uid)throws Exception {
        dbBean.openConnection();
        sql = "update users set pwd=? where id=?";
        Object [] paras = new Object[4];
        paras[0] = cs.getMd5Str("123456");
        paras[1] = Integer.parseInt(uid);
        paras[2] = true;
        paras[3] = false;
        int n = dbBean.executeUpdate(sql, 2, paras);
        return n > 0;
    }

    public int SumUsers()throws Exception {
        dbBean.openConnection();
        sql = "select count(*) from users";
        ResultSet rs = dbBean.executeQuery(sql, 0 );
        int n = 0;
        if (rs.next()){
            n = rs.getInt("count(*)");
        }
        dbBean.closeConnection();
        return n;
    }

    public FileBean[] GetAllFile(int index)throws Exception {
        FileBean [] ret = new FileBean[11];
        dbBean.openConnection();
        sql = "SELECT uid,fid,fname,sz,name from files,users where uid=id limit ?, ?";
        Object [] paras = new Object[4];
        paras[0] = (index-1)*10;
        paras[1] = 10;
        paras[2] = paras[3] = false;
        ResultSet rs = dbBean.executeQuery(sql, 2, paras);
        int pos = 0;
        while (rs.next()){
            ret[pos] = new FileBean();
            ret[pos].setUid(rs.getInt("uid"));
            ret[pos].setFid(rs.getString("fid"));
            ret[pos].setFname(rs.getString("fname"));
            ret[pos].setSize(rs.getString("sz"));
            ret[pos].setUname(rs.getString("name"));
            String tmp = ret[pos].getFname().substring(ret[pos].getFname().lastIndexOf('.'));
            ret[pos].setType(tmp);
            pos++;
        }
        dbBean.closeConnection();
        return ret;
    }

    public int GetAllFilePageCount()throws Exception {
        dbBean.openConnection();
        sql = "select count(*) from files";
        ResultSet rs = dbBean.executeQuery(sql, 0 );
        int n = 0;
        if (rs.next()){
            n = rs.getInt("count(*)");
        }
        dbBean.closeConnection();
        return (n - 1) / 10 + 1;
    }

}
