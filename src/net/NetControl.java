package net;

import java.io.*;
import java.net.*;
import java.util.Base64;

public class NetControl {
    public DataBean bean;
    private Socket mySocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private static final String SMTP_IP_ADDRESS = "220.181.12.16";
    private static final int SMTP_PORT = 25;
    private final Base64.Decoder decoder = Base64.getDecoder();
    private final Base64.Encoder encoder = Base64.getEncoder();


    public NetControl() throws IOException {
        mySocket = new Socket(SMTP_IP_ADDRESS, SMTP_PORT);
        mySocket.setSoTimeout(10000);
        bean = new DataBean();
        writer = new PrintWriter(mySocket.getOutputStream(),true);
        reader = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        receiveLineToBean();
    }

    private void receiveLineToBean() throws IOException{
        String temp = reader.readLine();
        bean.setRtn(temp);
        System.out.println(temp);
    }

    public void sendHELO() throws IOException{
        String temp = "HELO 163.com";
        writer.println(temp);
        System.out.println(temp);
        receiveLineToBean();
    }

    public void sendUserName() throws IOException{
        String temp = encode(bean.getUserName());
        writer.println("AUTH LOGIN");
        System.out.println("AUTH LOGIN");
        receiveLineToBean();
        writer.println(temp);
        System.out.println(temp);
        receiveLineToBean();
    }

    public void sendPassword() throws IOException{
        String temp = encode(bean.getPassword());
        writer.println(temp);
        System.out.println(temp);
        receiveLineToBean();
    }

    public void sendQUIT() throws IOException{
        String temp = "QUIT";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();
    }

    public void sendFrom() throws IOException{
        String temp = "MAIL FROM:<" + bean.getFromText()+">";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();
    }

    public void sendTo() throws IOException{
        String temp = "RCPT TO:<" + bean.getToText() + ">";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();
    }

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

    public void sendMessageEnd() throws IOException{
        String temp = ".";
        System.out.println(temp);
        writer.println(temp);
        receiveLineToBean();
    }

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
