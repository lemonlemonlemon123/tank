package tank1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserTank implements Runnable {
    public Socket socket;
    public boolean userTank1=false;
    public boolean userTank2=false;
    private boolean isaBoolean=true;
    public Listener listener;
    public Thread thread;
    public static boolean aBoolean=false;
    public static int time=3;
    private ObjectOutputStream output;
    private OpponentThread opponentThread;
    public int opp=0;
    JFrame jFrame;
    private int x;
    private int y;
    private JPanel contentPane;
    private Opponent1 o1;
    private Opponent2 o2;
    private Opponent3 o3;
    private JLabel label=new JLabel();
    private JLabel label1=new JLabel();
    private int direct=1;
    public  ImageIcon icon1;//=new ImageIcon("src\\tank1\\userLeft.jpg");
    public  ImageIcon icon2;//=new ImageIcon("src\\tank1\\userUp.jpg");
    public  ImageIcon icon3;//=new ImageIcon("src\\tank1\\userRight.jpg");
    public  ImageIcon icon4;//=new ImageIcon("src\\tank1\\userDown.jpg");
    public  ImageIcon s1;
    private ImageIcon icon=new ImageIcon("src\\tank1\\坦克消失.jpg");//坦克消失图。
    private ImageIcon icon5=new ImageIcon("src\\tank1\\bomb.jpg");//坦克爆炸图。
    public int xG;
    public int yG;
    public UserTank(JFrame jFrame,JPanel contentPane,Opponent1 o1,Opponent2 o2,Opponent3 o3,ImageIcon icon1,ImageIcon icon2,ImageIcon icon3,ImageIcon icon4,ImageIcon s1){
        this.jFrame=jFrame;
        this.contentPane=contentPane;
        this.o1=o1;
        this.o2=o2;
        this.o3=o3;
        this.icon1=icon1;
        this.icon2=icon2;
        this.icon3=icon3;
        this.icon4=icon4;
        this.s1=s1;
    }
    public JLabel shot(int x, int y, int direct,Opponent1 o,Opponent2 o2,Opponent3 o3){
        UserTankThread userTankThread=new UserTankThread();
        o.setU(userTankThread);
        o2.setU(userTankThread);
        o3.setU(userTankThread);
        userTankThread.setO1(o);
        userTankThread.setO2(o2);
        userTankThread.setO3(o3);
        userTankThread.setDirect(direct);
        userTankThread.setX(x);
        userTankThread.setY(y);
        Thread thread=new Thread(userTankThread,"子弹");
        thread.start();
        return userTankThread.getLabel();
    }
    @Override
    public void run() {
            label.setIcon(icon1);
            if(aBoolean){
                label1.setIcon(s1);//到后期把icon1换图，上面的icon用构造器初始化不同的图片，不直接定义好.
                label1.setBounds(0,0,80,80);
            }
            label.setBounds(0, 0, 80, 80);
            jFrame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    x = label.getX();
                    y = label.getY();
                    if (x < 0){
                        x=1600;
                        if(aBoolean){
                            try {
                                if(output!=null){
                                    output.writeObject(new Play(x,y,icon1,true));
                                    output.flush();
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        label.setBounds(x, y, 80, 80);
                    }
                    else if (x > 1600){
                        x=0;
                        if(aBoolean){
                            try {
                                if(output!=null){
                                    output.writeObject(new Play(x,y,icon1,true));
                                    output.flush();
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        label.setBounds(x, y, 80, 80);
                    }
                    else if (y < 0){
                        y=800;
                        if(aBoolean){
                            try {
                                if(output!=null){
                                    output.writeObject(new Play(x,y,icon1,true));
                                    output.flush();
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        label.setBounds(x, y, 80, 80);
                    }
                    else if (y > 800){
                        y=0;
                        if(aBoolean){
                            try {
                                if(output!=null){
                                    output.writeObject(new Play(x,y,icon1,true));
                                    output.flush();
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        label.setBounds(x, y, 80, 80);
                    }
                    else {
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            direct = 1;
                            y=y-80;
                            if(((y==720)&&(x>=320&&x<=400))||((y==480)&&(x>=960&&x<=1040))){
                                direct=3;
                                label.setIcon(icon1);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon1,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(x,y,80,80);
                            }
                            if(((y<720&&y==640)&&(x>=320&&x<=400))||((y<480&&y==400)&&(x>=960&&x<=1040))){
                                x=0;
                                y=0;
                                label.setIcon(icon1);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon1,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(0,0,80,80);
                            }
                            else{
                                label.setIcon(icon2);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon2,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(x, y, 80, 80);
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            direct = 2;
                            y=y+80;
                            if(((y+80==240)&&(x>=320&&x<=400))||((y+80==80)&&(x>=960&&x<=1040))){
                                direct=4;
                                label.setIcon(icon3);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon3,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(x,y,80,80);
                            }
                            if(((y+80>240&&y+80==320)&&(x>=320&&x<=400))||((y+80>80&&y+80==160)&&(x>=960&&x<=1040))){
                                x=0;
                                y=0;
                                label.setIcon(icon3);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon3,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(0,0,80,80);
                            }
                            else{
                                label.setIcon(icon4);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon4,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(x, y, 80, 80);
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            direct = 3;
                            x=x-80;
                            if(((x==1120)&&(y>=80&&y<=400))||((x==480)&&(y>=240&&y<=640))){
                                direct=2;
                                label.setIcon(icon4);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon4,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(x,y,80,80);
                            }
                            if(((x<1120&&x==1040)&&(y>=80&&y<=400))||((x<480&&x==400)&&(y>=240&&y<=640))){
                                x=0;
                                y=0;
                                label.setIcon(icon1);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon1,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(0,0,80,80);
                            }
                            else{
                                label.setIcon(icon1);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon1,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(x, y, 80, 80);
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            direct = 4;
                            x=x+80;
                            if(((x+80==320)&&(y>=240&&y<=640))||((x+80==960)&&(y>=80&&y<=400))){
                                direct=1;
                                label.setIcon(icon2);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon2,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(x,y,80,80);
                            }
                            if(((x+80>320&&x+80==400)&&(y>=240&&y<=640))||((x+80>960&&x+80==1040)&&(y>=80&&y<=400))){
                                x=0;
                                y=0;
                                label.setIcon(icon1);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon1,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(0,0,80,80);
                            }
                            else{
                                label.setIcon(icon3);
                                if(aBoolean){
                                    try {
                                        if(output!=null){
                                            output.writeObject(new Play(x,y,icon3,true));
                                            output.flush();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                label.setBounds(x, y, 80, 80);
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_1) {
                            // System.out.println("x="+userTank.getX()+"y="+getY());
                            if(output!=null){
                                Play play=new Play(x,y,icon1,false);
                                play.aBoolean=true;
                                play.direct=direct;
                                try {
                                    output.writeObject(play);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            JLabel label = shot(x, y, direct, o1, o2,o3);
                            contentPane.add(label);
                        }
                    }
                }
            });
        while(isaBoolean){
            System.out.println(time);
            if(time==0){
                isaBoolean=false;
            }
        }
        if(time==0){
            label.setIcon(icon5);
            label.setBounds(x,y,80,80);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            label.setIcon(icon);
            label.setBounds(x,y,80,80);
            if(aBoolean){
                if(output!=null) {
                    try {
                        output.writeObject(new Play(x, y, icon5, true));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(output!=null){
                    try {
                        output.writeObject(new Play(x,y,icon,true));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //判断服务器端的坦克是否死亡。
                if(userTank1){
                    listener.oppUserTank=opp-listener.oppG;
                    if(TankTest.isGameOver){
                        GameOver.aBoolean2=true;
                        new GameOver(listener.oppUserTank,Listener.oppU);
                    }else {
                        listener.isGameOver=1;
                    }
                }
                //只允许对方坦克发送信息.
                if(userTank2){
                    if(output!=null){
                        Play play=new Play(x,y,icon,false);
                        play.str="over";
                        play.userTank2=false;
                        play.userCount2=opp-listener.oppG;
                        try {
                            output.writeObject(play);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //终止对方监听，结束客户端
                    thread.interrupt();
                    try {
                        socket.close();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(!TankTest.isGameOver){
                    ImageIcon iconG=new ImageIcon("src\\tank1\\联机.jpg");
                    JFrame frame=new JFrame("结束游戏!");
                    Container cG=frame.getContentPane();
                    cG.setLayout(new FlowLayout(FlowLayout.CENTER));
                    JLabel labelG=new JLabel(iconG);
                    cG.add(labelG);
                    frame.setBounds(xG,yG,550,300);
                    frame.setVisible(true);
                }
            }
            else{
                GameOver.aBoolean2=true;
                new GameOver(opp,0);
            }
        }

    }

    public JLabel getLabel() {
        return label;
    }
    public JLabel getLabel1(){
        return label1;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    public void setOpponentThread(OpponentThread opponentThread) {
        this.opponentThread = opponentThread;}

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

    public Opponent1 getO1() {
        return o1;
    }

    public Opponent2 getO2() {
        return o2;
    }

    public Opponent3 getO3() {
        return o3;
    }
}
