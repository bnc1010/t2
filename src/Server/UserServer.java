package Server;

import Bean.DbBean;
import Bean.UserBean;
import Server.CommonServer;
import org.apache.catalina.User;

import java.sql.ResultSet;

public class UserServer {
    private DbBean dbBean = new DbBean();
    CommonServer commonServer = new CommonServer();
    private String sql = null;


    public boolean InsertUser(UserBean user)throws Exception {
        dbBean.openConnection();
        sql="Insert into users(name,pwd,email) values(?,?,?,?)";
        Object [] paras = new Object[8];
        paras[0] = user.getUserName();
        paras[1] = commonServer.getMd5Str(user.getPwd());
        paras[2] = user.getEmail();
        paras[3] = commonServer.GetTime();
        paras[4] = paras[5] = paras[6] = paras[7] = true;
        int n = dbBean.executeUpdate(sql, 4, paras);
        dbBean.closeConnection();
        return n > 0;
    }

    public boolean ExistUser(UserBean user) throws Exception {
        dbBean.openConnection();
        sql = "select * from users where name=?";
        Object[] paras = new Object[2];
        paras[0] = user.getUserName();
        paras[1] = true;
        ResultSet rs = dbBean.executeQuery(sql, 1, paras);
        boolean ret = rs.next();
        dbBean.closeConnection();
        return ret;
    }

    public int CheckValid(UserBean user)throws Exception {
        dbBean.openConnection();
        sql = "select * from users where name=? and pwd=?";
        Object [] paras = new Object[4];
        paras[0] = user.getUserName();
        paras[1] = commonServer.getMd5Str(user.getPwd());
        paras[2] = paras[3] = true;
        ResultSet rs = dbBean.executeQuery(sql, 2, paras);
        int ret = -1;
        if (rs.next()){
            ret = rs.getInt("sta");
        }
        dbBean.closeConnection();
        return ret;
    }


    public boolean ExistEmail(UserBean user)throws Exception {
        dbBean.openConnection();
        sql = "select * from users where email=?";
        Object[] paras = new Object[2];
        paras[0] = user.getEmail();
        paras[1] = true;
        ResultSet rs = dbBean.executeQuery(sql, 1, paras);
        boolean ret = rs.next();
        dbBean.closeConnection();
        return ret;
    }

    public boolean ExistEmail(String email)throws Exception {
        dbBean.openConnection();
        sql = "select * from users where email=?";
        Object[] paras = new Object[2];
        paras[0] = email;
        paras[1] = true;
        ResultSet rs = dbBean.executeQuery(sql, 1, paras);
        boolean ret = rs.next();
        dbBean.closeConnection();
        return ret;
    }

    public boolean ChangePassword(UserBean user, String npwd)throws Exception {
        user.setPwd(npwd);
        dbBean.openConnection();
        sql = "update users set pwd=? where name=?";
        Object[] paras = new Object[4];
        paras[0] = commonServer.getMd5Str(npwd);
        paras[1] = user.getUserName();
        paras[2] = paras[3] =true;
        int n = dbBean.executeUpdate(sql, 2, paras);
        dbBean.closeConnection();
        return n > 0;
    }

    public boolean SaveLog(UserBean user)throws Exception {
        dbBean.openConnection();
        sql = "insert into log(user, intime) values(?,?)";
        Object[] paras = new Object[4];
        paras[0] = user.getUserName();
        paras[1] = commonServer.GetTime();
        paras[2] = paras[3] = true;
        int n = dbBean.executeUpdate(sql, 2, paras);
        dbBean.closeConnection();
        return n > 0;
    }

    public UserBean FullUserMessage(UserBean user)throws Exception {
        dbBean.openConnection();
        sql = "select * from users where name=?";
        Object[] paras = new Object[2];
        paras[0] = user.getUserName();
        paras[1] = true;
        ResultSet rs = dbBean.executeQuery(sql, 1, paras);

        if (rs.next()){
            user.setUid(Integer.parseInt(rs.getString("id")));
            user.setEmail(rs.getString("email"));
            user.setNormal(1);
        }
        dbBean.closeConnection();
        return user;
    }


    public UserBean GetUserByEmail(String email)throws Exception{
        dbBean.openConnection();
        sql = "select * from users where email=?";
        Object[] paras = new Object[2];
        paras[0] = email;
        paras[1] = true;
        ResultSet rs = dbBean.executeQuery(sql, 1, paras);
        UserBean user = new UserBean();
        if (rs.next()){
            user.setUid(Integer.parseInt(rs.getString("id")));
            user.setEmail(rs.getString("email"));
            user.setPwd(rs.getString("pwd"));
            user.setUserName(rs.getString("name"));
            user.setNormal(rs.getInt("sta"));
        }
        dbBean.closeConnection();
        return user;
    }

    public boolean JudgeAuthor(UserBean user, String aid) throws Exception {
        dbBean.openConnection();
        sql = "select * from articals where uid=? and aid=?";
        Object[] paras = new Object[4];
        paras[0] = user.getUid();
        paras[1] = aid;
        paras[2] = false;
        paras[3] = true;
        ResultSet rs = dbBean.executeQuery(sql, 2, paras);
        boolean ret = rs.next();
        dbBean.closeConnection();
        return ret;
    }

    public boolean ChangeEmail(UserBean user, String newEmail) throws Exception {
        dbBean.openConnection();
        sql = "update users set email=? where name=?";
        Object[] paras = new Object[4];
        paras[0] = newEmail;
        paras[1] = user.getUserName();
        paras[2] = paras[3] = true;
        int n = dbBean.executeUpdate(sql, 2, paras);
        dbBean.closeConnection();
        return n > 0;
    }


    public boolean UpdateLast_t(UserBean user)throws Exception {
        dbBean.openConnection();
        sql = "update users set last_t=? where id=?";
        Object[] paras = new Object[4];
        paras[0] = commonServer.GetTime();
        paras[1] = user.getUid();
        paras[2] = true;
        paras[3] = false;
        int n = dbBean.executeUpdate(sql ,2 ,paras);
        return n > 0;
    }

    public boolean ChangeSta(String uid, int sta)throws Exception {
        dbBean.openConnection();
        sql = "update users set sta=? where id=?";
        Object[] paras = new Object[4];
        paras[0] = sta;
        paras[1] = Integer.parseInt(uid);
        paras[2] = paras[3] = false;
        int n = dbBean.executeUpdate(sql ,2, paras);
        return n > 0;
    }
}
