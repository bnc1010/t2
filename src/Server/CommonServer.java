package Server;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonServer {
    private String randStr=null;
    private String md5Str=null;

    /*
     *获取随机6位验证码
    */
    private int getUpper() {//大写字母
        return (int)(Math.random()*26+65);
    }
    private int getLower() {//小写字母
        return (int)(Math.random()*26+97);
    }
    private int getNumber() {//得到数字
        return (int)(Math.random()*10);
    }
    private void Rand() {
        randStr="";
        for(int i=0; i<6; i++) {
            int res = (int)(Math.random()*3);
            if(res==0) {
                randStr=randStr + (char)getUpper();
            } else if(res==1) {
                randStr=randStr + (char)getLower();
            } else {
                randStr=randStr + getNumber();
            }
        }
    }
    public String getRandStr(){
        Rand();
        return randStr;
    }


    /*
     *MD5加密
     */
    public String getMd5Str(String s)throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5Str="";
        byte[] by = md5.digest(s.getBytes());
        for (int i = 0; i < by.length; i++) {
            md5Str += Byte.toString(by[i]);
        }
        return md5Str;
    }

    /*
     *获取时间
     */
    public String GetTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }


    /*
     *简单判断是否符合邮箱格式
     */
    private boolean JudgeChar(char c){
        if((c>='0'&&c<='9')||(c>='a'&&c<='z')||(c>='A'&&c<='Z')||c=='.'){
            return true;
        }
        else return false;
    }
    public boolean JudgeEmail(String str){
        char[] arr = str.toCharArray();
        int n = 0;
        for(int i = 0; i < str.length(); i++){
            if (arr[i] == '@'){
                n++;
                if(n > 1)return false;
            }
            else if(!JudgeChar(arr[i])){
                return false;
            }
        }
        return n==1;
    }
    private String [] dw = {"B","KB","MB","GB","TB"};
    public String FileSize(double sz){
        int cnt = 0;
        while (sz>1024){
            sz/=1024;
            cnt++;
        }
        return String.format("%.2f",sz) + dw[cnt];
    }
}
