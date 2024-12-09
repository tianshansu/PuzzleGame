package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame();

        jFrame.setSize(488,430);

        //set title
        jFrame.setTitle("Log in");

        //set always on top
        jFrame.setAlwaysOnTop(true);

        //set window location
        jFrame.setLocationRelativeTo(null);

        //set game close(3=exit on close)
        jFrame.setDefaultCloseOperation(3);

        jFrame.setLayout(null);

        //new button
        JButton jButton=new JButton("confirm");
        jButton.setBounds(0,0,100,50);

        //add listener
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked!");
            }
        });

        jFrame.add(jButton);

        jFrame.setVisible(true);

    }
    //login related

}
