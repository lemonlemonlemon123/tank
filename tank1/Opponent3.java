package tank1;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Opponent3 implements Runnable, Serializable {
    public Socket socket;
    public Thread thread;
    public Listener listener;
    private ObjectOutputStream output;
    private UserTank userTank;
    private JPanel contentPane;
    private UserTankThread u;
    private ImageIcon icon1=new ImageIcon("src\\tank1\\opponentUp.jpg");
    private ImageIcon icon2=new ImageIcon("src\\tank1\\opponentDown.jpg");
    private ImageIcon icon3=new ImageIcon("src\\tank1\\opponentLeft.jpg");
    private ImageIcon icon4=new ImageIcon("src\\tank1\\opponentRight.jpg");
    private ImageIcon icon=new ImageIcon("src\\tank1\\坦克消失.jpg");
    private ImageIcon icon5=new ImageIcon("src\\tank1\\bomb.jpg");
    private ImageIcon icon6=new ImageIcon("src\\tank1\\吸收洞.png");
    private JLabel label=new JLabel();
    private int direct=3;
    private int x;
    private int y;
    private boolean aBoolean=true;
    public Opponent3(JPanel contentPane){
        this.contentPane=contentPane;
    }
    @Override
    public void run() {
        label.setIcon(icon3);
        label.setBounds(1600,240,80,80);
        x=label.getX();
        y=label.getY();
        while(aBoolean) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 1:if(y<=80||((y==720)&&(x>=320&&x<=400))||((y==480)&&(x>=960&&x<=1040))){
                    label.setIcon(icon4);
                    label.setBounds(x,y,80,80);
                    direct=4;
                    OpponentThread o1=new OpponentThread(userTank);
                    o1.set(x,y,direct);
                    Thread thread1=new Thread(o1,"敌方子弹9");
                    thread1.start();
                    JLabel jLabel=o1.getLabel();
                    contentPane.add(jLabel);
                }
                else{
                    y=y-80;
                    label.setBounds(x,y,80,80);
                }
                    break;
                case 2:if(y>=800-300||((y+80==240)&&(x>=320&&x<=400))||((y+80==80)&&(x>=960&&x<=1040))){
                    label.setIcon(icon3);
                    label.setBounds(x,y,80,80);
                    direct=3;
                    OpponentThread o1=new OpponentThread(userTank);
                    o1.set(x,y,direct);
                    Thread thread2=new Thread(o1,"敌方子弹10");
                    thread2.start();
                    JLabel jLabel=o1.getLabel();
                    contentPane.add(jLabel);
                }
                else{
                    y=y+80;
                    label.setBounds(x,y,80,80);
                }
                    break;
                case 3:if(x<=80||((x==1120)&&(y>=80&&y<=400))||((x==480)&&(y>=240&&y<=640))){
                    label.setIcon(icon1);
                    label.setBounds(x,y,80,80);
                    direct=1;
                    OpponentThread o1=new OpponentThread(userTank);
                    o1.set(x,y,direct);
                    Thread thread3=new Thread(o1,"敌方子弹11");
                    thread3.start();
                    JLabel jLabel=o1.getLabel();
                    contentPane.add(jLabel);
                }
                else{
                    x=x-80;
                    label.setBounds(x,y,80,80);
                }
                    break;
                case 4:if(x>=1600-80||((x+80==320)&&(y>=240&&y<=640))||((x+80==960)&&(y>=80&&y<=400))){
                    label.setIcon(icon2);
                    label.setBounds(x,y,80,80);
                    direct=2;
                    OpponentThread o1=new OpponentThread(userTank);
                    o1.set(x,y,direct);
                    Thread thread4=new Thread(o1,"敌方子弹12");
                    thread4.start();
                    JLabel jLabel=o1.getLabel();
                    contentPane.add(jLabel);
                }
                else{
                    x=x+80;
                    label.setBounds(x,y,80,80);
                }
            }
            //判断：子弹是否打中，若打中则结束进程，反之则不结束。
            if(u!=null){
                if(u.isD3()){
                    aBoolean=false;
                    label.setIcon(icon5);
                    label.setBounds(x,y,80,80);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    label.setIcon(icon);
                    label.setBounds(x,y,80,80);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    label.setIcon(icon6);
                    label.setBounds(x,y,120,120);
                    userTank.opp++;
                    if(UserTank.aBoolean){
                        if(listener.aBoolean){
                            listener.oppG++;
                            listener.aBoolean=false;
                        }
                    }
                    if(userTank.opp==3){
                        if(UserTank.aBoolean){//判断是否为双人模式.
                            listener.oppUserTank=userTank.opp-listener.oppG;
                            if(userTank.userTank2){
                                if(output!=null){
                                    Play play=new Play(x,y,icon,false);
                                    play.str="over";
                                    play.userCount2=userTank.opp-listener.oppG;
                                    try {
                                        output.writeObject(play);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                //结束客户端
                                thread.interrupt();
                                try {
                                    socket.close();
                                    System.exit(0);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(TankTest.isGameOver) {
                                GameOver.aBoolean1 = true;
                                new GameOver(listener.oppUserTank, Listener.oppU);
                            }
                        }else{
                            GameOver.aBoolean1=true;
                            new GameOver(userTank.opp,0);
                        }
                    }
                }
            }
        }
    }
    public JLabel getLabel() {
        return label;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setU(UserTankThread u) {
        this.u = u;
    }
    public void setUserTank(UserTank userTank) {
        this.userTank = userTank;
    }
    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }
}
