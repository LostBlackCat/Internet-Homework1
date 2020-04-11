package UI;

import net.NetControl;
import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MainPanel extends JPanel{
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel subjectLabel;
    private JLabel messageLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JTextField fromText;
    private JTextField toText;
    private JTextField subjectText;
    private JTextArea messageText;
    private JButton sendButton;
    private JButton clearButton;
    public JButton quitButton;
    private JButton helpButton;

    private NetControl control;



    private void initItems(){
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:  ");
        subjectLabel = new JLabel("Subject:");
        messageLabel = new JLabel("Message:");
        usernameLabel = new JLabel("UserName:");
        passwordLabel = new JLabel("PassWord:");

        fromText = new JTextField(36);
        toText = new JTextField(37);
        subjectText = new JTextField(35);
        messageText = new JTextArea(11,3);
        usernameText = new JTextField(18);
        passwordText = new JPasswordField(18);
        messageText.setLineWrap(true);


        sendButton = new JButton(" Send ");
        sendButton.setBorder(BorderFactory.createRaisedBevelBorder());
        clearButton = new JButton(" Clear ");
        clearButton.setBorder(BorderFactory.createRaisedBevelBorder());
        quitButton = new JButton(" Quit ");
        quitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        helpButton = new JButton("Help");
        helpButton.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public MainPanel(){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); //使用箱式布局
        initItems();

        JPanel row0_0 = new JPanel();
        row0_0.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        row0_0.add(usernameLabel);
        row0_0.add(usernameText);
        JPanel row0_1 = new JPanel();
        row0_1.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        row0_1.add(passwordLabel);
        row0_1.add(passwordText);


        JPanel row0 = new JPanel();
        row0.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        row0.add(fromLabel);
        row0.add(fromText);
        JPanel row1 = new JPanel();
        row1.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        row1.add(toLabel);
        row1.add(toText);
        JPanel row2 = new JPanel();
        row2.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        row2.add(subjectLabel);
        row2.add(subjectText);
        JPanel row3= new JPanel();
        row3.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
        row3.add(messageLabel);
        JScrollPane row4 = new JScrollPane(messageText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel row5 = new JPanel();
        row5.setLayout(new FlowLayout(FlowLayout.CENTER,50,2));
        row5.add(sendButton);
        row5.add(clearButton);
        row5.add(quitButton);
        row5.add(helpButton);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    control = new NetControl();
                    control.sendHELO();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "TCP Connection lost,please check your network state.",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                control.bean.setUserName(usernameText.getText());
                control.bean.setPassword(String.copyValueOf(passwordText.getPassword()));
                System.out.println(control.bean.getPassword());

                try{
                    control.sendUserName();
                    control.sendPassword();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "TCP Connection lost,\nplease check your network state.",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                System.out.println(control.bean.getRtn()+"**************");
                if(control.bean.getRtn().equals("535 Error: authentication failed")){
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "Authentication failed,\nplease check your username and password",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(fromText.getText().isEmpty() || toText.getText().isEmpty()){
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "From or To text can not be empty",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                control.bean.setFromText(fromText.getText());
                control.bean.setToText(toText.getText());
                control.bean.setSubject(subjectText.getText());

                try{
                    control.sendFrom();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "TCP Connection lost,please check your network state.",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(!control.bean.getRtn().equals("250 Mail OK")){
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "From email address must  equal with your username,\nplease check your username and from mail address",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try{
                    control.sendTo();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "TCP Connection lost,please check your network state.",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(!control.bean.getRtn().equals("250 Mail OK")){
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "User net found,\nplease check E-mail address",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }


                control.bean.setMessage(messageText.getText());
                try{
                    control.sendMessage();
                    control.sendMessageEnd();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(sendButton.getParent(),
                            "TCP Connection lost,please check your network state.",
                            "Failed",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try{
                    control.sendQUIT();
                }catch (IOException ioe){
                    ioe.printStackTrace();

                }
                JOptionPane.showMessageDialog(sendButton.getParent(),
                        "Send successfully",
                        "Success",
                        JOptionPane.WARNING_MESSAGE);
                return;

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageText.setText("");
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(sendButton.getParent(),
                        "This is a Email Client for 163 Email\n" +
                                "Please notice following issues:\n" +
                                "i.Before you use client,make sure you have already had a 163 email address and opened the SMTP sending function.\n" +
                                "ii.From text email address must equal to your username.\n" +
                                "iii.If receiver could not receive email,please check trash box.\n\n" +
                                "Powered by wxy(09017229),Computer Science faculty,SEU.\n" +
                                "Source Code:https://github.com/LostBlackCat/Internet-Homework1\n" +
                                "Contact address:634312391@qq.com",
                        "Help",
                        JOptionPane.WARNING_MESSAGE);
            }
        });


        this.add(row0_0);
        this.add(row0_1);
        this.add(row0);
        this.add(row1);
        this.add(row2);
        this.add(row3);
        this.add(row4);
        this.add(row5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String temp = "";
                while(true){
                    try{
                        if(!usernameText.getText().equals(temp)){
                            temp = usernameText.getText();
                            fromText.setText(temp + "@163.com");
                        }
                        sleep(200);
                    }catch(InterruptedException ite){
                        ite.printStackTrace();
                    }
                }

            }
        }).start();
    }

}
