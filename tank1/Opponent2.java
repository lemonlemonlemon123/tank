package tank1;

import javax.swing.*;
import java.io.Serializable;

public class Opponent2 implements Runnable, Serializable {
    private UserTank userTank;
    private JPanel contentPane;
    private UserTankThread u;
    private ImageIcon icon1=new ImageIcon("D:\\javacx\\src\\tank1\\opponentUp.jpg");
    private ImageIcon icon2=new ImageIcon("D:\\javacx\\src\\tank1\\opponentDown.jpg");
    private ImageIcon icon3=new ImageIcon("D:\\javacx\\src\\tank1\\opponentLeft.jpg");
    private ImageIcon icon4=new ImageIcon("D:\\javacx\\src\\tank1\\opponentRight.jpg");
    private ImageIcon icon=new ImageIcon("D:\\javacx\\src\\tank1\\2.jpg");
    private ImageIcon icon5=new ImageIcon("D:\\javacx\\src\\tank1\\bomb.jpg");
    private ImageIcon icon6=new ImageIcon("D:\\javacx\\src\\tank1\\吸收洞.png");
    private JLabel label=new JLabel();
    private int direct=1;
    private int x;
    private int y;
    private boolean aBoolean=true;
    public Opponent2(JPanel contentPane){
        this.contentPane=contentPane;
    }
    @Override
    public void run() {
        label.setIcon(icon1);
        label.setBounds(500,800,80,80);
        x=label.getX();
        y=label.getY();
        while(aBoolean) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 1:if(y<=0){
                    label.setIcon(icon4);
                    label.setBounds(x,y,80,80);
                    direct=4;
                    OpponentThread o1=new OpponentThread(userTank);
                    o1.set(x,y,direct);
                    Thread thread1=new Thread(o1,"敌方子弹5");
                    thread1.start();
                    JLabel jLabel=o1.getLabel();
                    contentPane.add(jLabel);
                }
                else{
                    y=y-80;
                    label.setBounds(x,y,80,80);
                }
                    break;
                case 2:if(y>=800-300){
                    label.setIcon(icon3);
                    label.setBounds(x,y,80,80);
                    direct=3;
                    OpponentThread o1=new OpponentThread(userTank);
                    o1.set(x,y,direct);
                    Thread thread2=new Thread(o1,"敌方子弹6");
                    thread2.start();
                    JLabel jLabel=o1.getLabel();
                    contentPane.add(jLabel);
                }
                else{
                    y=y+80;
                    label.setBounds(x,y,80,80);
                }
                    break;
                case 3:if(x<=80){
                    label.setIcon(icon1);
                    label.setBounds(x,y,80,80);
                    direct=1;
                    OpponentThread o1=new OpponentThread(userTank);
                    o1.set(x,y,direct);
                    Thread thread3=new Thread(o1,"敌方子弹7");
                    thread3.start();
                    JLabel jLabel=o1.getLabel();
                    contentPane.add(jLabel);
                }
                else{
                    x=x-50;
                    label.setBounds(x,y,80,80);
                }
                    break;
                case 4:if(x>=1600-300){
                    label.setIcon(icon2);
                    label.setBounds(x,y,80,80);
                    direct=2;
                    OpponentThread o1=new OpponentThread(userTank);
                    o1.set(x,y,direct);
                    Thread thread4=new Thread(o1,"敌方子弹8");
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
                if(u.isD2()){
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
                    GameOver.opp++;
                    if(GameOver.opp==2){
                        new GameOver();
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
}
