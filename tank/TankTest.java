package tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankTest extends JFrame {
    Image image=Toolkit.getDefaultToolkit().getImage("D:\\javacx\\src\\tank\\userRight.jpg");
    Image off=null;
    private boolean start=false;
    int y=140;
    int state=0;
    int a=0;
    Play play=new Play("D:\\javacx\\src\\tank\\userRight.jpg",125,510,this,"D:\\javacx\\src\\tank\\userUp.jpg","D:\\javacx\\src\\tank\\userDown.jpg",
            "D:\\javacx\\src\\tank\\userLeft.jpg","D:\\javacx\\src\\tank\\userRight.jpg");
    ArrayList<Bullet> bullets=new ArrayList<Bullet>();
    public void launch(){
        setTitle("坦克大战小游戏");
        setSize(1600,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setVisible(true);
        this.addKeyListener(new KeyMonitor());
        while(true){
            repaint();
            try{
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if(off==null){//双缓存机制。
            off=this.createImage(1600,800);
        }
        Graphics gImage=off.getGraphics();
        gImage.setColor(Color.lightGray);
        gImage.fillRect(0,0,1600,800);
        gImage.setColor(Color.BLACK);
        gImage.setFont(new Font("仿宋",Font.BOLD,50));
        if(state==0){
            gImage.drawString("选择游戏模式",500,100);
            gImage.drawString("单人模式",500,200);
            gImage.drawString("双人模式",500,300);
            gImage.drawImage(image,400,y,null);
        }
        else if(state==1||state==2){
            gImage.drawString("游戏开始",500,100);
            if(state==1){
                gImage.drawString("单人模式",500,200);
            }
            else{
                gImage.drawString("双人模式",500,200);
            }
            play.painSelf(gImage);
            for(Bullet bullet:bullets){
                bullet.painSelf(gImage);
            }
        }
        g.drawImage(off,0,0,null);
    }
     class KeyMonitor extends KeyAdapter{
         @Override
         public void keyPressed(KeyEvent e) {
             int key=e.getKeyCode();
             switch(key){
                 case KeyEvent.VK_1:
                     a=1;
                     y=140;
                     break;
                 case KeyEvent.VK_2:
                     a=2;
                     y=230;
                     break;
                 case KeyEvent.VK_ENTER:
                     state=a;
                     break;
                 default:
                     play.keyPressed(e);
             }
         }

         @Override
         public void keyReleased(KeyEvent e) {
             play.keyReleased(e);
         }
     }
    public static void main(String[] args) {
        new TankTest().launch();
    }
}
