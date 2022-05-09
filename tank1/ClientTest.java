package tank1;


import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientTest extends JFrame {
    protected static Socket socket;
    protected static ObjectInputStream input1;
    protected static ObjectOutputStream output1;
    ImageIcon icon11=new ImageIcon("src\\tank1\\user2Left.jpg");//对方的坦克图。icon11~icon14
    ImageIcon icon12=new ImageIcon("src\\tank1\\user2Up.jpg");
    ImageIcon icon13=new ImageIcon("src\\tank1\\user2Right.jpg");
    ImageIcon icon14=new ImageIcon("src\\tank1\\user2Down .jpg");
    ImageIcon s1=new ImageIcon("src\\tank1\\userLeft.jpg");//服务端的图，用于同步信息。
    ImageIcon icon1=new ImageIcon("src\\tank1\\墙1.jpg");
    ImageIcon icon2=new ImageIcon("src\\tank1\\墙2.jpg");
    JPanel contentPane;
    UserTank userTank;
    Opponent1 opponent1;
    Opponent2 opponent2;
    Opponent3 opponent3;
    JLabel label1User;
    public ClientTest(){
        super("坦克大战游戏(客户端)");
        contentPane=new JPanel(null);
        JLabel label1=new JLabel(icon1);
        JLabel label2=new JLabel(icon2);
        label1.setBounds(320,240,160,480);
        label2.setBounds(960,80,160,400);
        contentPane.add(label1);
        contentPane.add(label2);
//        setContentPane(contentPane);
        opponent1=new Opponent1(contentPane);
        Thread thread1=new Thread(opponent1,"敌方坦克1");
        thread1.start();
        opponent2=new Opponent2(contentPane);
        Thread thread2=new Thread(opponent2,"敌方坦克2");
        thread2.start();
        opponent3=new Opponent3(contentPane);
        Thread thread3=new Thread(opponent3,"敌方坦克3");
        thread3.start();
        userTank=new UserTank(this,contentPane,opponent1,opponent2,opponent3,icon11,icon12,icon13,icon14,s1);
        userTank.setOutput(output1);
        userTank.xG=600;
        userTank.yG=600;
        opponent1.setOutput(output1);
        opponent2.setOutput(output1);
        opponent3.setOutput(output1);
        Thread thread=new Thread(userTank,"用户");
        thread.start();
        opponent1.setUserTank(userTank);
        opponent2.setUserTank(userTank);
        opponent3.setUserTank(userTank);
        JLabel labelOpponent1=opponent1.getLabel();
        JLabel labelOpponent2=opponent2.getLabel();
        JLabel labelOpponent3=opponent3.getLabel();
        JLabel labelUser=userTank.getLabel();
        label1User=userTank.getLabel1();
        contentPane.add(labelUser);
        contentPane.add(label1User);
        contentPane.add(labelOpponent1);
        contentPane.add(labelOpponent2);
        contentPane.add(labelOpponent3);
        setContentPane(contentPane);
    }
    public static void main(String[] args) {
        UserTank.aBoolean=true;
        try{
            socket=new Socket("127.0.0.1",9999);
            output1=new ObjectOutputStream(socket.getOutputStream());
            input1=new ObjectInputStream(socket.getInputStream());
            ClientTest app=new ClientTest();
            app.setBounds(200,115,1600,850);
            app.setVisible(true);
            app.setDefaultCloseOperation(3);
            Listener listener=new Listener(socket,input1,app.label1User,app.contentPane,app.userTank);
            app.userTank.listener=listener;
            app.opponent1.listener=listener;
            app.opponent2.listener=listener;
            app.opponent3.listener=listener;
            app.userTank.userTank2=true;
            Thread thread2=new Thread(listener,"client");
            thread2.start();
            app.userTank.thread=thread2;
            app.opponent1.thread=thread2;
            app.opponent2.thread=thread2;
            app.opponent3.thread=thread2;
            app.userTank.socket=socket;
            app.opponent1.socket=socket;
            app.opponent2.socket=socket;
            app.opponent3.socket=socket;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
