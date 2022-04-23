package tank1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class TankTest extends JFrame {
    JPanel contentPane;
    UserTank userTank;
    Opponent1 opponent1;
    Opponent2 opponent2;
    public TankTest(){
        super("坦克大战游戏");
        contentPane=new JPanel(null);
//        setContentPane(contentPane);
        opponent1=new Opponent1(contentPane);
        Thread thread1=new Thread(opponent1,"敌方坦克1");
        thread1.start();
        opponent2=new Opponent2(contentPane);
        Thread thread2=new Thread(opponent2,"敌方坦克2");
        thread2.start();
        userTank=new UserTank(this,contentPane,opponent1,opponent2);
        Thread thread=new Thread(userTank,"用户");
        thread.start();
        opponent1.setUserTank(userTank);
        opponent2.setUserTank(userTank);
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//              //  super.keyReleased(e);
//               // userTank.setIcon(UserTank.icon1);
//                int x = userTank.getX();
//                int y = userTank.getY();
//                if (x <0)
//                    userTank.setBounds(1000, y, 80, 80);
//                else if (x > 1600)
//                    userTank.setBounds(0, y, 80, 80);
//                else if (y < 0)
//                    userTank.setBounds(x, 600, 80, 80);
//                else if (y > 800)
//                    userTank.setBounds(x, 0, 80, 80);
//                else {
//                    if (e.getKeyCode() == KeyEvent.VK_UP) {
//                        userTank.setDirect(1);
//                        userTank.setIcon(UserTank.icon2);
//                        userTank.setBounds(x, y - 50, 80, 80);
//                    }
//                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//                        userTank.setDirect(2);
//                        userTank.setIcon(UserTank.icon4);
//                        userTank.setBounds(x, y + 50, 80, 80);
//                    }
//                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                        userTank.setDirect(3);
//                        userTank.setIcon(UserTank.icon1);
//                        userTank.setBounds(x - 50, y, 80, 80);
//                    }
//                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                        userTank.setDirect(4);
//                        userTank.setIcon(UserTank.icon3);
//                        userTank.setBounds(x + 50, y, 80, 80);
//                    }
//                    if(e.getKeyCode()==KeyEvent.VK_1){
//                       // System.out.println("x="+userTank.getX()+"y="+getY());
//                        JLabel label=userTank.shot(userTank.getX(),userTank.getY(),userTank.getDirect(),opponent1,opponent2);
//                        contentPane.add(label);
//                    }
//                    }
//                }
//            });
        JLabel labelOpponent1=opponent1.getLabel();
        JLabel labelOpponent2=opponent2.getLabel();
        JLabel labelUser=userTank.getLabel();
        contentPane.add(labelUser);
        contentPane.add(labelOpponent1);
        contentPane.add(labelOpponent2);
        setContentPane(contentPane);
    }

    public static void main(String[] args) {
        ImageIcon icon=new ImageIcon("D:\\javacx\\src\\tank1\\111.png");
        JFrame ap=new JFrame("开始游戏");
        Container container=ap.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label=new JLabel("坦克游戏",icon,JLabel.LEFT);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        container.add(label);
        JButton button1=new JButton("开始新游戏");
        JButton button2=new JButton("继续游戏");
        container.add(button1);
        container.add(button2);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TankTest app=new TankTest();
                // app.setBackground(Color.BLUE);
                app.setSize(1600,800);
                app.setLocationRelativeTo(null);
                app.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                app.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        int i=JOptionPane.showConfirmDialog(app,"确定关闭吗？");
                        if(i==JOptionPane.YES_OPTION){
                            try{
                                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src\\tank1\\lastGame.txt"));
                                oos.writeObject(app);
                                oos.flush();
                                oos.close();
                            }catch(IOException a){
                                a.printStackTrace();
                            }
                            finally {
                                System.exit(0);
                            }
                        }

                    }
                });
                app.setVisible(true);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src\\tank1\\lastGame.txt"));
                    TankTest tankTest=(TankTest)ois.readObject();
                    tankTest.setSize(1600,800);
                    tankTest.setLocationRelativeTo(null);
                    tankTest.setDefaultCloseOperation(3);
                    tankTest.setVisible(true);
                    Thread thread1=new Thread(tankTest.userTank,"用户");
                    thread1.start();
                }catch(IOException b){
                    JOptionPane.showMessageDialog(ap,"无法继续游戏，请重新开始新游戏！");
                    b.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        ap.setBounds(200,115,1000,800);
        ap.setVisible(true);
        ap.setDefaultCloseOperation(3);
    }
}
