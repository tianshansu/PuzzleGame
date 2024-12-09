package ui;

import database.DatabaseHelper;
import tools.DialogHelper;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterJFrame extends JFrame implements MouseListener {
    //JTextField of user id section
    JTextField userId = new JTextField();
    //JTextField of user password section
    JTextField userPw = new JTextField();
    //JTextField of user password confirm section
    JTextField userPwConf = new JTextField();

    JButton submit = new JButton(new ImageIcon("resource/puzzleImages/ui/submit.png"));
    JButton back = new JButton(new ImageIcon("resource/puzzleImages/ui/back.png"));


    //register related
    public RegisterJFrame() {
        this.setSize(488,500);

        //set title
        this.setTitle("Register");

        //set always on top
        this.setAlwaysOnTop(true);

        //set window location
        this.setLocationRelativeTo(null);

        //set game close(3=exit on close)
        this.setDefaultCloseOperation(3);

        initialiseUI();

        this.setVisible(true);
    }

    private void initialiseUI(){
        userId.setBounds(190, 148, 200, 30);
        this.getContentPane().add(userId);

        userPw.setBounds(190, 222, 200, 30);
        this.getContentPane().add(userPw);

        userPwConf.setBounds(190, 288, 200, 30);
        this.getContentPane().add(userPwConf);

        submit.setBounds(220, 370, 152, 42);
        this.getContentPane().add(submit);
        submit.addMouseListener(this);

        back.setBounds(100, 378, 89, 32);
        this.getContentPane().add(back);
        back.addMouseListener(this);

        JLabel background=new JLabel(new ImageIcon("resource/puzzleImages/ui/RegisterPage.png"));
        background.setBounds(0,0,488,500);
        this.getContentPane().add(background);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==submit){
            //only if the passwords are the same
            if(userPw.getText().equals(userPwConf.getText())){
                if(DatabaseHelper.checkUserIdExists(userId.getText())){
                    //if the current username is taken
                    DialogHelper.showDialog("The username is unavailable!");
                }else{
                    DatabaseHelper.registerUser(userId.getText(),userPw.getText());//add this account to database
                    this.setVisible(false);
                    new LoginJFrame();
                }

            }else{
                //if the passwords are not the same,show a new dialog
                DialogHelper.showDialog("The passwords are not the same!");
            }
        }else if(e.getSource()==back){
            this.setVisible(false);
            new LoginJFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
