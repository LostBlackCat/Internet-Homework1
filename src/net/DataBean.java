package net;

import javax.xml.crypto.Data;

public class DataBean {
    private String userName;
    private String password;
    private String fromText;
    private String toText;
    private String subject;
    private String message;
    private String rtn;

    public DataBean(String userName, String password, String fromText, String toText, String subject, String message) {
        this.userName = userName;
        this.password = password;
        this.fromText = fromText;
        this.toText = toText;
        this.subject = subject;
        this.message = message;
    }
    public DataBean(){
        userName = null;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFromText() {
        return fromText;
    }

    public String getToText() {
        return toText;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFromText(String fromText) {
        this.fromText = fromText;
    }

    public void setToText(String toText) {
        this.toText = toText;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
