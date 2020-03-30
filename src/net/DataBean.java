package net;

public class DataBean {
    private String userName;
    private String password;
    private String fromText;
    private String toText;
    private String subject;
    private String message;

    public DataBean(String userName, String password, String fromText, String toText, String subject, String message) {
        this.userName = userName;
        this.password = password;
        this.fromText = fromText;
        this.toText = toText;
        this.subject = subject;
        this.message = message;
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
}
