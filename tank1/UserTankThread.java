package tank1;

import javax.swing.*;
import java.io.Serializable;

public class UserTankThread implements Runnable, Serializable {
    private Opponent1 o1;
    private Opponent2 o2;
    private ImageIcon icon=new ImageIcon("D:\\javacx\\src\\tank1\\userBullet.jpg");
    private ImageIcon icon1=new ImageIcon("D:\\javacx\\src\\tank1\\1.jpg");//子弹消失图片。
    private int direct;
    private int x;
    private int y;
    private boolean isD1=false;
    private boolean isD2=false;
    private boolean isaBoolean=true;
    private boolean aBoolean=true;
    private JLabel label=new JLabel();
    @Override
    public void run() {
        while(aBoolean){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            label.setIcon(icon);
            if(direct==1){
                if(isaBoolean){
                    y=y-30;
                    isaBoolean=false;
                }
                else{
                    y=y-30;
                }
                label.setBounds(x+30,y,30,30);
            }
            if(direct==2){
                if(isaBoolean){
                    y=y+80+30;
                    isaBoolean=false;
                }
                else{
                    y=y+30;
                }
                label.setBounds(x+30,y,30,30);
            }
            if(direct==3){
                if(isaBoolean){
                    x=x-30;
                    isaBoolean=false;
                }
                else{
                    x=x-30;
                }
                label.setBounds(x,y+30,30,30);
            }
            if(direct==4){
                if(isaBoolean){
                    x=x+80;
                    isaBoolean=false;
                }
                else{
                    x=x+30;
                }
                label.setBounds(x,y+30,30,30);
            }
            System.out.println("x="+x+"y="+y);
            //判断：子弹是否打中，若打中则结束进程，反之不结束。
            boolean isHit1=isDisappear(x,y,o1.getX(),o1.getY());
            if(isHit1){
                isD1=true;
            }
            boolean isHit2=isDisappear(x,y,o2.getX(),o2.getY());
            if(isHit2){
                isD2=true;
            }
            if((x<-48||y<-38||x>1648||y>838)||isHit1||isHit2){
                label.setIcon(icon1);
                label.setBounds(x,y,30,30);
                aBoolean=false;
                isaBoolean=true;
            }
        }
    }
    public void setDirect(int direct) {
        this.direct = direct;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setO1(Opponent1 o1) {
        this.o1 = o1;
    }

    public void setO2(Opponent2 o2) {
        this.o2 = o2;
    }

    public boolean isDisappear(int x1, int y1, int x2, int y2){
        switch(direct){
            case 1:if((x1<=x2+50&&x1>=x2)&&(y1<=y2+80&&y1>=y2+20)){
                return true;
            }
            else return false;
            case 2:if((x1>=x2&&x1<=x2+50)&&(y1+30>=y2&&y1+30<=y2+60)){
                return true;
            }
            else return false;
            case 3:if((y1>=y2&&y1<=y2+50)&&(x1+30>=x2&&x1+30<=x2+60)){
                return true;
            }
            else return false;
            case 4:if((y1>=y2&&y1<=y2+50)&&(x1>=x2+20&&x1<=x2+80)){
                return true;
            }
            else return false;
        }
        return false;
    }

    public boolean isD1() {
        return isD1;
    }

    public boolean isD2() {
        return isD2;
    }
}
