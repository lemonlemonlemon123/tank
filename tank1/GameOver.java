package tank1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GameOver extends JFrame {
    private ImageIcon icon=new ImageIcon("src\\tank1\\游戏结束.png");
    private ImageIcon icon1=new ImageIcon("src\\tank1\\游戏成功.jpg");
    private ImageIcon icon2=new ImageIcon("src\\tank1\\游戏失败.jpg");
    private int opp1;
    private int opp2;
    public static boolean aBoolean1=false;
    public static boolean aBoolean2=false;
    public GameOver(int opp1,int opp2){
        this.opp1=opp1;
        this.opp2=opp2;
        try{
            setName("游戏结束");
            Container c=getContentPane();
            c.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel label1=new JLabel("游戏记录",icon,JLabel.LEFT);
            label1.setHorizontalTextPosition(JLabel.CENTER);
            label1.setVerticalTextPosition(JLabel.BOTTOM);
            c.add(label1);
            JButton button1=new JButton("结束游戏");
            Object[] colname={"游戏模式","玩家一（打敌数）","玩家二（打敌数）"};
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","lmj123456");
            Statement stmt=conn.createStatement();
            String sql;
            if(TankTest.aBoolean1){//单人模式下，向数据库输入信息。
                String str=opp1+"个";
                sql="insert into tank values ('单人模式',"+"'"+str+"'"+","+"'无');";
                stmt.executeUpdate(sql);
            }
            if(TankTest.aBoolean2){//双人模式下，向数据库输入信息。
                String str1=opp1+"个";
                String str2=opp2+"个";
                sql="insert into tank values('双人模式',"+"'"+str1+"'"+","+"'"+str2+"');";
                stmt.executeUpdate(sql);
            }
            ResultSet rs=stmt.executeQuery("select * from tank;");
            rs.last();
            int n=rs.getRow();
            Object[][] data=new Object[n][10];
            JTable table=new JTable(data,colname);
            c.add(new JScrollPane(table));
            int i=0;
            rs.beforeFirst();
            while(rs.next()){
                data[i][0]=rs.getString(1);
                data[i][1]=rs.getString(2);
                data[i][2]=rs.getString(3);
                i++;
            }
            rs.close();
            conn.close();
            c.add(button1);
            if(aBoolean1){
                JLabel label=new JLabel(icon1);//游戏成功.
                c.add(label);
            }
            if(aBoolean2){
                JLabel label=new JLabel(icon2);//游戏失败.
                c.add(label);
            }
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            setBounds(500,200,800,650);
            setVisible(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
