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
    private ImageIcon icon=new ImageIcon("D:\\javacx\\src\\tank1\\98.png");
    public static int opp=0;
    public GameOver(){
        super("游戏结束");
        try{
            Container c=getContentPane();
            c.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel label1=new JLabel("游戏记录",icon,JLabel.LEFT);
            label1.setHorizontalTextPosition(JLabel.CENTER);
            label1.setVerticalTextPosition(JLabel.BOTTOM);
            c.add(label1);
            JButton button1=new JButton("结束游戏");
            Object[] colname={"游戏局数","打敌方数"};
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","lmj123456");
            Statement stmt=conn.createStatement();
            String sql="insert into tank values(1,"+opp+");";
            stmt.executeUpdate(sql);
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
                data[i][1]=rs.getInt(2);
                i++;
            }
            rs.close();
            conn.close();
            c.add(button1);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            pack();
            setVisible(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
