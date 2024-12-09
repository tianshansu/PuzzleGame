package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[3][3];
    int x=0;//x is row
    int y=0;//y is column
    boolean showFullImage=false;
    int step=0;

    //JMenu Item
    JMenuItem changeImageItem = new JMenuItem("Change Image");
    JMenuItem replayItem = new JMenuItem("Replay");
    JMenuItem reLoginItem = new JMenuItem("Re-login");
    JMenuItem closeItem = new JMenuItem("Close game");

    JMenuItem websiteItem = new JMenuItem("Website");

    JMenuItem animalItem=new JMenuItem("Animal");
    JMenuItem natureItem=new JMenuItem("Nature");

    //all file paths
    String[] animals =new String[]{"resource\\puzzleImages\\images_after\\animal1\\","resource\\puzzleImages\\images_after\\animal2\\","resource\\puzzleImages\\images_after\\animal3\\"};
    String[] nature=new String[]{"resource\\puzzleImages\\images_after\\nature1\\","resource\\puzzleImages\\images_after\\nature2\\","resource\\puzzleImages\\images_after\\nature3\\"};

    //actual file path
    String path="resource\\puzzleImages\\images_after\\animal1\\";

    //game main menu
    public GameJFrame() {
        //initialise JFrame
        initialiseJFrame();

        //initialise JMenu bar
        initialiseJMenuBar();

        //initialise data
        initialData();

        //initialise image
        initialiseImage();

        this.setVisible(true);
    }

    private void initialData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[index];
            tempArr[index] = tempArr[i];
            tempArr[i] = temp;
        }

        //add numbers into 2d array
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if(tempArr[index]!=0)
                    data[i][j] = tempArr[index];
                else{
                    data[i][j] =0;
                    x=i;
                    y=j;
                }
                index++;
            }
        }
    }

    private void initialiseImage() {
        //remove previous images
        this.getContentPane().removeAll();

        JLabel steps=new JLabel(new String("Steps: "+step));
        steps.setBounds( 5, 5, 100, 20);
        this.getContentPane().add(steps);

        //if player wants to see full image
        if(showFullImage){
            JLabel jLabel = new JLabel(new ImageIcon(path+"full.jpg"));

            jLabel.setBounds( 84, 136, 420, 420);
            //add border to images
            jLabel.setBorder(new BevelBorder(0));
            this.getContentPane().add(jLabel);
        } else{
            if(winOrNot()){
                JLabel jLabel = new JLabel(new ImageIcon("resource/puzzleImages/ui/victory1.png"));
                jLabel.setBounds( 203, 283, 197, 73);
                this.getContentPane().add(jLabel);

                removeKeyListener(this);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //create JLabel
                    JLabel jLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));

                    jLabel.setBounds(140 * j + 84, 140 * i + 136, 140, 140);
                    //add border to images
                    jLabel.setBorder(new BevelBorder(0));
                    this.getContentPane().add(jLabel);

                }
            }
    }

        //add background image
        JLabel background = new JLabel(new ImageIcon("resource\\puzzleImages\\Background1.png"));
        background.setBounds(40, 40, 508, 560);
        //add to content pane
        this.getContentPane().add(background);

        //repaint content pane to show new images
        this.getContentPane().repaint();

    }

    private void initialiseJMenuBar() {
        //initialise menu
        //whole menu(JMenuBar)
        JMenuBar jMenuBar = new JMenuBar();
        //JMenu
        JMenu functionMenu = new JMenu("Setting");
        JMenu aboutMenu = new JMenu("About");

        JMenu imageMenu=new JMenu("Change Image");


        //link item to JMenu
        functionMenu.add(imageMenu);
        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);

        aboutMenu.add(websiteItem);

        imageMenu.add(animalItem);
        imageMenu.add(natureItem);

        //add events to the labels
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        websiteItem.addActionListener(this);
        animalItem.addActionListener(this);
        natureItem.addActionListener(this);

        //link menu to menu bar
        jMenuBar.add(functionMenu);
        jMenuBar.add(aboutMenu);

        //add menu bar to window
        this.setJMenuBar(jMenuBar);

    }

    private void initialiseJFrame() {
        //set page size
        this.setSize(603, 680);

        //set title
        this.setTitle("Puzzle Game");

        //set always on top
        //this.setAlwaysOnTop(true);

        //set window location
        this.setLocationRelativeTo(null);

        //set game close(3=exit on close)
        this.setDefaultCloseOperation(3);

        //remove auto centralise setting
        this.setLayout(null);

        //add key listener
        this.addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //if press A - show full image
        if(e.getKeyCode()==65){
            showFullImage=true;
            initialiseImage();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode=e.getKeyCode();

        switch(keyCode){
            //if press up key
            case 38:
                if(x+1<data.length){
                    data[x][y]=data[x+1][y];
                    data[x+1][y]=0;
                    x++;//set new location of the blank image

                    step++;
                    //initialise image again using the new 2d array
                    initialiseImage();
                }
                break;
            //if press down key
            case 40:
                if(0<=x-1 && x-1<data.length){
                    data[x][y]=data[x-1][y];
                    data[x-1][y]=0;
                    x--;//set new location of the blank image

                    step++;

                    //initialise image again using the new 2d array
                    initialiseImage();
                }
                break;
            //if press left key
            case 37:
                if(y+1<data.length){
                    data[x][y]=data[x][y+1];
                    data[x][y+1]=0;
                    y++;//set new location of the blank image

                    step++;

                    //initialise image again using the new 2d array
                    initialiseImage();
                }
                break;
            //if press right key
            case 39:
                if(y-1<data.length && y-1>=0){
                    data[x][y]=data[x][y-1];
                    data[x][y-1]=0;
                    y--;//set new location of the blank image

                    step++;

                    //initialise image again using the new 2d array
                    initialiseImage();
                }
                break;
            //if release A - finish display full image
            case 65:
                showFullImage=false;
                initialiseImage();
                break;
            //if press w, cheat game and complete game immediately
            case 87:
                cheat();
                initialiseImage();
                break;

            default:
                System.out.println("Invalid Key!");
                break;
        }


    }

    //cheat game
    private void cheat(){
        int index = 1;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = index;
                index++;
            }
        }

    }

    private boolean winOrNot(){
        int index=1;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if(data[i][j]==index)
                    index++;
                else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionItem=e.getSource();

         if(actionItem==replayItem){ //if click replay, reshuffle the game
             resetImage();
         }else if(actionItem==reLoginItem){
             this.setVisible(false);//set this gameJFrame to invisible
             new LoginJFrame();//new a login frame to show login page
         }else if(actionItem==closeItem){
             System.exit(0);//exit game
         }else if(actionItem==websiteItem){
             try {
                 URI uri = new URI("https://www.tianshansu.com");//website wants to be opened

                 if (Desktop.isDesktopSupported()) {
                     Desktop.getDesktop().browse(uri);
                 }
             } catch (URISyntaxException | java.io.IOException ex) {
                 ex.printStackTrace();
             }
         }else if(actionItem==animalItem){
             path=animals[randomInt(animals)];
             resetImage();
         }else if(actionItem==natureItem){
             path=nature[randomInt(nature)];
             resetImage();
         }
    }

    private void print(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
    }

    private int randomInt(String[] array){
        int currentInt=0;
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            if(array[i]==path){
                currentInt=i;
            }
        }
        while(true){
            if(array.length==1)
                return 0;
            int nextInt=r.nextInt(array.length);
            if(nextInt!=currentInt)
                return nextInt;
        }
    }

    private void resetImage(){
        this.removeKeyListener(this);//remove the previous key listener
        initialData();
        initialiseImage();
        step=0;//restart counting
        addKeyListener(this);
    }
}
