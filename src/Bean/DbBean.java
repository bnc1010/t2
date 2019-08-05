package Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbBean {
    private PreparedStatement state=null;
    private Connection conn=null;
    private ResultSet rs=null;


    /*
     *打开数据库连接
     */
    public void openConnection() throws Exception{
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
            String url="jdbc:mysql://101.132.160.250:3306/htmluser?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF-8";
        String user="bnc";
        String pwd="ze!15958231764";
        conn= DriverManager.getConnection(url, user, pwd);
    }


    /*
     *查询语句 参数：sql语句，sql参数数量，sql参数表
     */
    public ResultSet executeQuery(String sql, int pnum, Object...paras) throws Exception{
        rs=null;
        state=conn.prepareStatement(sql);
        for(int i=0; i<pnum; i++){
            if ((boolean)paras[i + pnum]){
                state.setString(i+1, paras[i].toString());
            }
            else {
                state.setInt(i+1, (int)paras[i]);
            }

        }
        rs=state.executeQuery();
        return rs;
    }


    /*
     *更新语句 参数：sql语句，sql参数数量，sql参数表
     */
    public int executeUpdate(String sql, int pnum, Object...paras)throws Exception{
        int ret=0;
        state=conn.prepareStatement(sql);
        for(int i=0; i<pnum; i++){
            if ((boolean)paras[i + pnum]){
                state.setString(i+1, paras[i].toString());
            }
            else {
                state.setInt(i+1, (int)paras[i]);
            }
        }
        ret=state.executeUpdate();
        return ret;
    }

    /*
     *关闭数据库
     */
    public void closeConnection()throws Exception{
        if (rs!=null)
            rs.close();
        if (state!=null)
            state.close();
        if (conn!=null)
            conn.close();
    }
}
