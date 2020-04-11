package UI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {
    private MainPanel myPannel;

    private MainUI(){
        this.setTitle("163 Email Client");
        myPannel = new MainPanel();
        this.add(myPannel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(430,380);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        myPannel.quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String args[]){
        MainUI main = new MainUI();

    }
}
