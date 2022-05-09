package ipdemo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class One1 {
    public static void main(String[] args) {
        MyFrame app=new MyFrame("123");
    }
}
class MyFrame extends JFrame implements KeyListener {
    protected Socket socket;
    protected ObjectInputStream input;
    protected ObjectOutputStream output;
    private ImageIcon icon,icon1;
    private JLabel lab;
    private JLabel label;

    public MyFrame(String s){
        super(s);
        setBounds(0,0,700,500);
//        setLocationRelativeTo(null);
        JPanel contentPane=new JPanel(null);
        setContentPane(contentPane);
        icon=new ImageIcon("src\\ipdemo\\图片2.jpg");
        icon1=new ImageIcon("src\\ipdemo\\98.png");
        label=new JLabel(icon1);
        label.setBounds(632,0,48,38);
        lab=new JLabel(icon);
        lab.setBounds(0,0,48,38);
        addKeyListener(this);
        contentPane.add(lab);
        contentPane.add(label);
        setVisible(true);
        setDefaultCloseOperation(3);
        try{
            ServerSocket serverSocket=new ServerSocket(9999);
            socket=serverSocket.accept();
            input=new ObjectInputStream(socket.getInputStream());
            output=new ObjectOutputStream(socket.getOutputStream());
//            new Thread(new Send(socket,output,play),"1").start();
            new Thread(new Listen(socket,input,label),"2").start();
//            String str=input.readUTF();
//            System.out.println(str);
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
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                x=x-50;
                try {
                    output.writeObject(new Play(x,y));
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lab.setBounds(x,y,48,38);
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                x=x+50;
                try {
                    output.writeObject(new Play(x,y));
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lab.setBounds(x,y,48,38);
            }
            if(e.getKeyCode()==KeyEvent.VK_UP){
                y=y-50;
                try {
                    output.writeObject(new Play(x,y));
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lab.setBounds(x,y,48,38);
            }
            if(e.getKeyCode()== KeyEvent.VK_DOWN){
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
//class Send implements Runnable{
//    Socket socket;
//    ObjectOutputStream output;
//    Play play;
//    public Send(Socket socket,ObjectOutputStream output,Play play){
//        this.output=output;
//        this.socket=socket;
//        this.play=play;
//    }
//
//    @Override
//    public void run() {
//        while(true){
//            try {
//                output.writeObject(play);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
class Listen implements Runnable{
    Socket socket;
    ObjectInputStream input;
    JLabel label;
    public Listen(Socket socket,ObjectInputStream input,JLabel label){
        this.input=input;
        this.socket=socket;
        this.label=label;
    }

    @Override
    public void run() {
        System.out.println("线程启动!!!!!!!");
        Play play;
        while(true){
            try {
                play=(Play) input.readObject();
                if(play==null){
                    socket.close();
                    break;
                }
                else{
                    label.setBounds(play.x,play.y,48,38);
                    System.out.println(play.x+"   "+ play.y);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
class Play implements Serializable {
    public int x;
    public int y;
    public Play(int x,int y){
        this.x=x;
        this.y=y;
    }
}