package tank1;

import javax.swing.*;
import java.io.Serializable;

public class Play implements Serializable {
    public String str="continue";
    public boolean userTank1=true;
    public boolean userTank2=true;
    public int userCount2=0;
    public ImageIcon icon;
    public boolean isaBoolean;//正常信号，便于监听者监听到信息时便于处理.
    public int x;
    public int y;
    public boolean aBoolean=false;//信号：是否发射子弹.
    public int direct;
    public Play(int x,int y,ImageIcon icon,boolean isaBoolean){
        this.x=x;
        this.y=y;
        this.icon=icon;
        this.isaBoolean=isaBoolean;
    }
}
