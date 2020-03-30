package UI;

import net.DataBean;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private DataBean bean;



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

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageText.setText("");
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
    }

}
