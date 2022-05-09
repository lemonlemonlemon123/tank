package tank1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TankTest extends JFrame {
    public static boolean isGameOver=false;
    protected static Socket socket;
    protected static ObjectInputStream input;
    protected static ObjectOutputStream output;
    public static boolean aBoolean1=false;
    public static boolean aBoolean2=false;
    ImageIcon icon11=new ImageIcon("src\\tank1\\userLeft.jpg");
    ImageIcon icon12=new ImageIcon("src\\tank1\\userUp.jpg");
    ImageIcon icon13=new ImageIcon("src\\tank1\\userRight.jpg");
    ImageIcon icon14=new ImageIcon("src\\tank1\\userDown.jpg");
    ImageIcon s1=new ImageIcon("src\\tank1\\user2Left.jpg");
    ImageIcon icon1=new ImageIcon("src\\tank1\\墙1.jpg");
    ImageIcon icon2=new ImageIcon("src\\tank1\\墙2.jpg");
    JPanel contentPane;
    UserTank userTank;
    Opponent1 opponent1;
    Opponent2 opponent2;
    Opponent3 opponent3;
    JLabel label1User;
    public TankTest(){
        super("坦克大战游戏");
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
        if(TankTest.aBoolean2){
            opponent1.setOutput(output);
            opponent2.setOutput(output);
            opponent3.setOutput(output);
            userTank.setOutput(output);
            userTank.xG=100;
            userTank.yG=100;
        }
        Thread thread=new Thread(userTank,"用户");
        thread.start();
        opponent1.setUserTank(userTank);
        opponent2.setUserTank(userTank);
        opponent3.setUserTank(userTank);
        JLabel labelOpponent1=opponent1.getLabel();
        JLabel labelOpponent2=opponent2.getLabel();
        JLabel labelOpponent3=opponent3.getLabel();
        JLabel labelUser=userTank.getLabel();
        contentPane.add(labelUser);
        if(TankTest.aBoolean2){
            label1User=userTank.getLabel1();
            contentPane.add(label1User);
        }
        contentPane.add(labelOpponent1);
        contentPane.add(labelOpponent2);
        contentPane.add(labelOpponent3);
        setContentPane(contentPane);
    }

    public static void main(String[] args) {
        ImageIcon icon=new ImageIcon("src\\tank1\\初界面.png");
        JFrame ap=new JFrame("开始游戏");
        Container container=ap.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label=new JLabel("坦克游戏",icon,JLabel.LEFT);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        container.add(label);
        JButton button1=new JButton("开始游戏（单人模式）");
        JButton button2=new JButton("开始游戏（双人模式）");
        container.add(button1);
        container.add(button2);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TankTest.aBoolean1=true;
                TankTest app=new TankTest();
                // app.setBackground(Color.BLUE);
                app.setSize(1600,850);
                app.setLocationRelativeTo(null);
                app.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                app.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        int i=JOptionPane.showConfirmDialog(app,"确定中断游戏吗？");
                        if(i==JOptionPane.YES_OPTION){
                                System.exit(0);
                        }
                    }
                });
                app.setVisible(true);
            }
        });
        button2.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                      TankTest.aBoolean2=true;
                                      UserTank.aBoolean=true;
                                      System.out.println("等待连接......");
                                      try {
                                          ServerSocket serverSocket = new ServerSocket(9999);
                                          socket = serverSocket.accept();
                                          System.out.println("连接成功!!!!!!");
                                          input = new ObjectInputStream(socket.getInputStream());
                                          output = new ObjectOutputStream(socket.getOutputStream());
                                          TankTest app=new TankTest();
                                          app.setBounds(0,0,1600,850);
                                          app.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                                          app.setVisible(true);
                                          Listener listener=new Listener(socket,input,app.label1User,app.contentPane,app.userTank);
                                          app.userTank.listener=listener;
                                          app.opponent1.listener=listener;
                                          app.opponent2.listener=listener;
                                          app.opponent3.listener=listener;
                                          app.userTank.userTank1=true;
                                          Thread thread1=new Thread(listener,"server");//app.label1User:对方图.
                                          thread1.start();
                                          app.userTank.thread=thread1;
                                          app.opponent1.thread=thread1;
                                          app.opponent2.thread=thread1;
                                          app.opponent3.thread=thread1;
                                      }catch(IOException ex){
                                          ex.printStackTrace();
                                      }
            }
       });
        ap.setBounds(200,115,1000,600);
        ap.setVisible(true);
        ap.setDefaultCloseOperation(3);
    }
}
