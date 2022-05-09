package tank1;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Listener implements Runnable{
    public static int oppU=0;
    public int isGameOver=0;//1:己方游戏先结束，游戏失败 0：当对方游戏结束是，己方还未结束
    public boolean aBoolean=false;
    public int oppG=0;
    public int oppUserTank=0;
    Socket socket;
    ObjectInputStream input;
    JLabel label;
    JPanel contentPane;
    UserTank userTank;
    public Listener(Socket socket,ObjectInputStream input,JLabel label,JPanel contentPane,UserTank userTank){
        this.input=input;
        this.socket=socket;
        this.label=label;
        this.contentPane=contentPane;
        this.userTank=userTank;
    }
    @Override
    public void run() {
        System.out.println("启动!!!!!!!");
        Play play;
        while(true) {
            try {
                play = (Play) input.readObject();
                if (play.aBoolean) {
                    aBoolean = true;
                    Opponent1 o1 = userTank.getO1();
                    Opponent2 o2 = userTank.getO2();
                    Opponent3 o3 = userTank.getO3();
                    JLabel label1 = userTank.shot(play.x, play.y, play.direct, o1, o2, o3);
                    contentPane.add(label1);
                }
                if (play.isaBoolean) {
                    label.setIcon(play.icon);
                    label.setBounds(play.x, play.y, 80, 80);
                }
                //要将各个方的杀敌数给GameOver;
                if(isGameOver==1&&play.str.equals("over")){
                    //游戏成功，己方先结束游戏。
                    if(play.userTank2){
                        GameOver.aBoolean1=true;
                        new GameOver(oppUserTank,play.userCount2);
                        break;
                    }
                    //游戏失败，己方先结束游戏。
                    else{
                        GameOver.aBoolean2=true;
                        new GameOver(oppUserTank,play.userCount2);
                        break;
                    }
                }
                //对方游戏结束，但己方并未结束
                if(play.str.equals("over")&&isGameOver==0){
                    //敌方坦克消失完了
                    if(play.userTank2){
                        GameOver.aBoolean1=true;
                        new GameOver(oppUserTank,play.userCount2);
                        break;
                    }
                    else{
                        TankTest.isGameOver=true;
                        oppU=play.userCount2;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
