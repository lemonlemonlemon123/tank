package tank1;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrameDemo {
    public static void main(String[] args) {
        MyFrame app=new MyFrame("123");
    }
}
class MyFrame extends JFrame implements KeyListener{
    private Icon icon,icon2;
    private JLabel lab;
    public MyFrame(String s){
        super(s);
        setSize(700,500);
        setLocationRelativeTo(null);
        JPanel contentPane=new JPanel(null);
        setContentPane(contentPane);
        icon=new ImageIcon("D:\\javacx\\src\\tank1\\图片2.jpg");
        lab=new JLabel(icon);
        lab.setBounds(0,0,48,38);
        addKeyListener(this);
        contentPane.add(lab);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        icon2=new ImageIcon("D:\\javacx\\src\\test3\\Inked图片1_LI.jpg");
        lab.setIcon(icon2);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        lab.setIcon(icon);
        int x=lab.getX();
        int y=lab.getY();
        if(x>700){
            lab.setBounds(0,y,48,38);
        }
        else if(x<0){
            lab.setBounds(700,y,48,38);
        }
        else if(y<0){
            lab.setBounds(x,500,48,38);
        }else if(y>500){
            lab.setBounds(x,0,48,38);
        }else{
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                lab.setBounds(x-50,y,48,38);
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                lab.setBounds(x+50,y,48,38);
            }
            if(e.getKeyCode()==KeyEvent.VK_UP){
                lab.setBounds(x,y-50,48,38);
            }
            if(e.getKeyCode()== KeyEvent.VK_DOWN){
                lab.setBounds(x,y+50,48,38);
            }
        }
    }
}
