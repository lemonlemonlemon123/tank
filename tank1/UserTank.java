package tank1;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

//public class UserTank extends JLabel {
//    private int direct=1;
//    public static Icon icon1=new ImageIcon("D:\\javacx\\src\\tank1\\userLeft.jpg");
//    public static Icon icon2=new ImageIcon("D:\\javacx\\src\\tank1\\userUp.jpg");
//    public static Icon icon3=new ImageIcon("D:\\javacx\\src\\tank1\\userRight.jpg");
//    public static Icon icon4=new ImageIcon("D:\\javacx\\src\\tank1\\userDown.jpg");
//    public UserTank(){
//        super(icon1);
//    }
//    public JLabel shot(int x, int y, int direct,Opponent1 o,Opponent2 o2){
//        UserTankThread userTankThread=new UserTankThread();
//        o.setU(userTankThread);
//        o2.setU(userTankThread);
//        userTankThread.setO1(o);
//        userTankThread.setO2(o2);
//        userTankThread.setDirect(direct);
//        userTankThread.setX(x);
//        userTankThread.setY(y);
//        Thread thread=new Thread(userTankThread,"子弹");
//        thread.start();
//        return userTankThread.getLabel();
//    }
//    public void setDirect(int direct) {
//        this.direct = direct;
//    }
//
//    public int getDirect() {
//        return direct;
//    }
//}
public class UserTank implements Runnable, Serializable {
    public static int time=3;
    private OpponentThread opponentThread;
    JFrame jFrame;
    private int x;
    private int y;
    private JPanel contentPane;
    private Opponent1 o1;
    private Opponent2 o2;
    private JLabel label=new JLabel();
    private int direct=1;
    public static Icon icon1=new ImageIcon("D:\\javacx\\src\\tank1\\userLeft.jpg");
    public static Icon icon2=new ImageIcon("D:\\javacx\\src\\tank1\\userUp.jpg");
    public static Icon icon3=new ImageIcon("D:\\javacx\\src\\tank1\\userRight.jpg");
    public static Icon icon4=new ImageIcon("D:\\javacx\\src\\tank1\\userDown.jpg");
    private ImageIcon icon=new ImageIcon("D:\\javacx\\src\\tank1\\2.jpg");//坦克消失图。
    private ImageIcon icon5=new ImageIcon("D:\\javacx\\src\\tank1\\bomb.jpg");//坦克爆炸图。
    public UserTank(JFrame jFrame,JPanel contentPane,Opponent1 o1,Opponent2 o2){
        this.jFrame=jFrame;
        this.contentPane=contentPane;
        this.o1=o1;
        this.o2=o2;
    }
    public JLabel shot(int x, int y, int direct,Opponent1 o,Opponent2 o2){
        UserTankThread userTankThread=new UserTankThread();
        o.setU(userTankThread);
        o2.setU(userTankThread);
        userTankThread.setO1(o);
        userTankThread.setO2(o2);
        userTankThread.setDirect(direct);
        userTankThread.setX(x);
        userTankThread.setY(y);
        Thread thread=new Thread(userTankThread,"子弹");
        thread.start();
        return userTankThread.getLabel();
    }
//    public void setDirect(int direct) {
//        this.direct = direct;
//    }
//
//    public int getDirect() {
//        return direct;
//    }

    @Override
    public void run() {
//        if(opponentThread!=null){
//            opponentThread.setUserTank(this);
//            System.out.println("");
//        }
            label.setIcon(icon1);
            label.setBounds(0, 0, 80, 80);
            jFrame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    x = label.getX();
                    y = label.getY();
                    if (x < 0)
                        label.setBounds(1000, y, 80, 80);
                    else if (x > 1600)
                        label.setBounds(0, y, 80, 80);
                    else if (y < 0)
                        label.setBounds(x, 600, 80, 80);
                    else if (y > 800)
                        label.setBounds(x, 0, 80, 80);
                    else {
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            direct = 1;
                            label.setIcon(UserTank.icon2);
                            label.setBounds(x, y - 50, 80, 80);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            direct = 2;
                            label.setIcon(UserTank.icon4);
                            label.setBounds(x, y + 50, 80, 80);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            direct = 3;
                            label.setIcon(UserTank.icon1);
                            label.setBounds(x - 50, y, 80, 80);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            direct = 4;
                            label.setIcon(UserTank.icon3);
                            label.setBounds(x + 50, y, 80, 80);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_1) {
                            // System.out.println("x="+userTank.getX()+"y="+getY());
                            JLabel label = shot(x, y, direct, o1, o2);
                            contentPane.add(label);
                        }
                    }
                }
            });
        while(time!=0){
            //System.out.println(time);
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
            new GameOver();
        }

    }

    public JLabel getLabel() {
        return label;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    public void setOpponentThread(OpponentThread opponentThread) {
        this.opponentThread = opponentThread;}
}
