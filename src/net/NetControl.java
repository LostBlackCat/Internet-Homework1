package net;

import java.io.*;
import java.net.*;
import java.util.Base64;

public class NetControl {
    public DataBean bean;  //前后端的通信类
    private Socket mySocket;   //TCP socket
    private PrintWriter writer;  //与socket相关的输入缓存
    private BufferedReader reader;  //与socket相关的输出缓存
    private static final String SMTP_IP_ADDRESS = "220.181.12.16";  // 163邮件服务器的地址 使用final字段修饰
    private static final int SMTP_PORT = 25;  //163邮件服务器的SMTP端口
    private final Base64.Decoder decoder = Base64.getDecoder();  //Base64解封器
    private final Base64.Encoder encoder = Base64.getEncoder();  //Base64封装器 用来封装用户名与密码以通信


    /**
     *Public
     *构造函数
     *初始化了Socket与输入输出缓存队列
     */
    public NetControl() throws IOException {
        mySocket = new Socket(SMTP_IP_ADDRESS, SMTP_PORT);
        mySocket.setSoTimeout(10000);
        bean = new DataBean();
        writer = new PrintWriter(mySocket.getOutputStream(),true);
        reader = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        receiveLineToBean();
    }

    /**
     *Private
     *工具函数
     *将缓存中的信息（邮件服务器返回）存到bean中的rtn内
     */
    private void receiveLineToBean() throws IOException{
        String temp = reader.readLine();
        bean.setRtn(temp);
        System.out.println(temp);
    }

    /**
     *Public
     *共有函数
     *将HELO信息写入发送缓存队列，同时返回信息
     */
    public void sendHELO() throws IOException{
        String temp = "HELO 163.com";
        writer.println(temp);
        System.out.println(temp);
        receiveLineToBean();
    }

    /**
     *Public
     *共有函数
     *将用户名信息写入发送缓存队列，同时返回信息
     */
    public void sendUserName() throws IOException{
        String temp = encode(bean.getUserName());
        writer.println("AUTH LOGIN");
        System.out.println("AUTH LOGIN");
        receiveLineToBean();
        writer.println(temp);
        System.out.println(temp);
        receiveLineToBean();
    }

    /**
     *Public
     *共有函数
     *将密码信息写入发送缓存队列，同时返回信息
     */
    public void sendPassword() throws IOException{
        String temp = encode(bean.getPassword());
        writer.println(temp);
        System.out.println(temp);
        receiveLineToBean();
    }

    /**
     *Public
     *共有函数
     *将退出信息写入发送缓存队列，同时返回信息
     */
    public void sendQUIT() throws IOException{
        String temp = "QUIT";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();
    }

    /**
     *Public
     *共有函数
     *将From邮件地址信息写入发送缓存队列，同时返回信息
     */
    public void sendFrom() throws IOException{
        String temp = "MAIL FROM:<" + bean.getFromText()+">";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();
    }

    /**
     *Public
     *共有函数
     *将To邮件地址信息写入发送缓存队列，同时返回信息
     */
    public void sendTo() throws IOException{
        String temp = "RCPT TO:<" + bean.getToText() + ">";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();
    }

    /**
     *Public
     *共有函数
     *将主邮件信息写入发送缓存队列，同时返回信息
     *邮件报文格式与局部变量headLine一致
     */
    public void sendMessage() throws IOException{
        String temp = "DATA";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();

        String headLine = "From:" + bean.getFromText() + "\n" + "To:" + bean.getToText() + "\n" + "Subject:"
                + bean.getSubject() +"\n" +"\n" + bean.getMessage() + "\n";
        writer.print(headLine);
        System.out.print(headLine);
    }

    /**
     *Public
     *共有函数
     *将主邮件结束信息写入发送缓存队列，同时返回信息
     *邮件报文结束一般是单独的一行包含'.'的字符
     */
    public void sendMessageEnd() throws IOException{
        String temp = ".";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();
    }

    /**
     *Private
     *工具函数
     *使用base64算法加密
     *输入源字符串 orig  输出封装后的字符串 encoded
     */
    private String encode(String orig) throws UnsupportedEncodingException{
        byte[] testBytes;
        String encoded;
        testBytes  = orig.getBytes("UTF-8");
        encoded = encoder.encodeToString(testBytes);
        return encoded;
    }


    public static void main(String args[]){
        try{
            NetControl control = new NetControl();
            control.sendHELO();
            control.bean.setUserName("");
            control.bean.setPassword("");
            control.bean.setFromText("@163.com");
            control.bean.setToText("@163.com");
            control.bean.setMessage("");
            control.bean.setSubject("TEST MAIL");
            control.sendUserName();
            control.sendPassword();
            control.sendFrom();
            control.sendTo();
            control.sendMessage();
            control.sendMessageEnd();
            control.sendQUIT();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
