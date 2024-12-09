package tools;

import javax.swing.*;

public class DialogHelper {

    public static void showDialog(String content){
        JDialog jDialog=new JDialog();
        jDialog.setSize(300,80);
        JLabel text=new JLabel(content);
        text.setBounds(0,0,260,50);
        jDialog.getContentPane().add(text);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setVisible(true);
    }
}
