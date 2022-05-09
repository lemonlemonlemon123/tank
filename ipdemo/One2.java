package ipdemo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class One2 {
    public static void main(String[] args) {
        MyFrame1 app=new MyFrame1("123");
    }
}
class MyFrame1 extends JFrame implements KeyListener {
    protected Socket socket;
    protected ObjectInputStream input;
    protected ObjectOutputStream output;
    private ImageIcon icon,icon1;
    private JLabel lab;
    private JLabel label;
    public MyFrame1(String s){
        super(s);
        setSize(700,500);
        setLocationRelativeTo(null);
        JPanel contentPane=new JPanel(null);
        setContentPane(contentPane);
        icon1=new ImageIcon("src\\ipdemo\\图片2.jpg");
        label=new JLabel(icon1);
        label.setBounds(0,0,48,38);
        icon=new ImageIcon("src\\ipdemo\\98.png");
        lab=new JLabel(icon);
        lab.setBounds(632,0,48,38);
        addKeyListener(this);
        contentPane.add(lab);
        contentPane.add(label);
        setVisible(true);
        setDefaultCloseOperation(3);
        try{
            socket=new Socket("127.0.0.1",9999);
            output=new ObjectOutputStream(socket.getOutputStream());
            input=new ObjectInputStream(socket.getInputStream());
//            new Thread(new Send(socket,output,play1),"3").start();
            new Thread(new Listen(socket,input,label),"4").start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        lab.setIcon(icon);
        int x=lab.getX();
        int y=lab.getY();
        if(x>700){
            x=0;
            try {
                output.writeObject(new Play(x,y));
                output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            lab.setBounds(x,y,48,38);
        }
        else if(x<0){
            x=700;
            try {
                output.writeObject(new Play(x,y));
                output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            lab.setBounds(x,y,48,38);
        }
        else if(y<0){
            y=500;
            try {
                output.writeObject(new Play(x,y));
                output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            lab.setBounds(x,y,48,38);
        }else if(y>500){
            y=0;
            try {
                output.writeObject(new Play(x,y));
                output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            lab.setBounds(x,y,48,38);
        }else{
            if(e.getKeyCode()==KeyEvent.VK_A){
                x=x-50;
                try {
                    output.writeObject(new Play(x,y));
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lab.setBounds(x,y,48,38);
            }
            if(e.getKeyCode()==KeyEvent.VK_D){
                x=x+50;
                try {
                    output.writeObject(new Play(x,y));
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lab.setBounds(x,y,48,38);
            }
            if(e.getKeyCode()==KeyEvent.VK_W){
                y=y-50;
                try {
                    output.writeObject(new Play(x,y));
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lab.setBounds(x,y,48,38);
            }
            if(e.getKeyCode()== KeyEvent.VK_S){
                y=y+50;
                try {
                    output.writeObject(new Play(x,y));
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lab.setBounds(x,y,48,38);
            }
        }
    }
}
