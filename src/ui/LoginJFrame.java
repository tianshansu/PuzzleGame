package ui;

import database.DataBaseHelper;
import tools.DialogHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginJFrame extends JFrame implements MouseListener {

    JButton login = new JButton(new ImageIcon("resource/puzzleImages/ui/login.png"));
    JButton register = new JButton(new ImageIcon("resource/puzzleImages/ui/register.png"));

    //JTextField of user id section
    JTextField userId = new JTextField();
    //JTextField of user password section
    JTextField userPw = new JTextField();

    //login related
    public LoginJFrame() {
        this.setSize(488, 430);

        //set title
        this.setTitle("Log in");

        //set always on top
        this.setAlwaysOnTop(true);

        //set window location
        this.setLocationRelativeTo(null);

        //set game close(3=exit on close)
        this.setDefaultCloseOperation(3);

        initialiseUI();

        this.setVisible(true);
    }

    private void initialiseUI() {

        userId.setBounds(190, 160, 200, 30);
        this.getContentPane().add(userId);

        userPw.setBounds(190, 230, 200, 30);
        this.getContentPane().add(userPw);

        login.setBounds(60, 310, 152, 42);
        this.getContentPane().add(login);
        login.addMouseListener(this);

        register.setBounds(270, 310, 152, 42);
        this.getContentPane().add(register);
        register.addMouseListener(this);

        JLabel background = new JLabel(new ImageIcon("resource/puzzleImages/ui/LoginPage.png"));
        background.setBounds(0, 0, 100, 100);
        this.getContentPane().add(background);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == login) {
            if(DataBaseHelper.validateUser(userId.getText(),userPw.getText())){
                //if the user enters the correct userId and userPw, set current login JFrame to invisible and make a new GameJFrame Object
                this.setVisible(false);
                new GameJFrame();
            }else{
                //if the userId and userPw is incorrect, show a new dialog
                DialogHelper.showDialog("Incorrect username or password!");
            }

        } else if (e.getSource() == register) {
            //if click register, set current login JFrame to invisible and make a new RegisterJFrame Object
            this.setVisible(false);
            new RegisterJFrame();
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
