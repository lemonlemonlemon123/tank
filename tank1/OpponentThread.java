package tank1;

import javax.swing.*;
import java.io.Serializable;

public class OpponentThread implements Runnable, Serializable {
    private UserTank userTank;
    private ImageIcon icon=new ImageIcon("src\\tank1\\opponentBullet.jpg");//子弹图片;
    private ImageIcon icon1=new ImageIcon("src\\tank1\\子弹消失.jpg");
    private int direct;
    private int x;
    private int y;
    private JLabel label=new JLabel();
    private boolean aBoolean=true;
    private boolean isaBoolean=true;
    private boolean isHit=false;
    public OpponentThread(UserTank userTank){
        this.userTank=userTank;
    }
    @Override
    public void run() {
        while(aBoolean){
            try {
                Thread.sleep(80);
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
            //判断子弹是否打中，若打中则结束进程，反之不结束进程。
            isHit=isDisappear(x,y,userTank.getX(),userTank.getY(),direct);
            if(isHit){
                UserTank.time--;
            }
            if((x<-48||y<-38||x>1648||y>838)||isHit){
                label.setIcon(icon1);
                label.setBounds(x,y,30,30);
                aBoolean=false;
                isaBoolean=true;
            }
            if(((x>=350&&x<=450)&&(y>=270&&y<=690))||((x>=990&&x<=1120)&&(y>=110&&y<=480))){
                label.setIcon(icon1);
                label.setBounds(x,y,30,30);
                aBoolean=false;
                isaBoolean=true;
            }
        }
    }
    public void set(int x,int y,int direct){
        this.x=x;
        this.y=y;
        this.direct=direct;
    }
    public JLabel getLabel() {
        return label;
    }
    public boolean isDisappear(int x1,int y1,int x2,int y2,int direct){
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
    public boolean isHit() {
        return isHit;
    }
}
