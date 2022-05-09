package tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Play extends Tank{
    public Play(String image,int x,int y,TankTest tankTest,String imagUp,String imagDown,String imagLeft,String imagRight){
        super(image,x,y,tankTest,imagUp,imagDown,imagLeft,imagRight);
    }

    @Override
    public void painSelf(Graphics g) {
        g.drawImage(image,x,y,null);
    }
    public void move(){
        if(left){
            leftward();
        }
        else if(right){
            rightward();
        }
        else if(up){
            upward();
        }else if(down){
            downward();
        }
    }
    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode();
        switch(key){
            case KeyEvent.VK_A:
                left=true;
                move();
                break;
            case KeyEvent.VK_S:
                down=true;
                move();
                break;
            case KeyEvent.VK_D:
                right=true;
                move();
                break;
            case KeyEvent.VK_W:
                up=true;
                move();
                break;
            case KeyEvent.VK_SPACE:
                attack();
            default:
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        int key=e.getKeyCode();
        switch(key){
            case KeyEvent.VK_A:
                left=false;
                break;
            case KeyEvent.VK_S:
                down=false;
                break;
            case KeyEvent.VK_D:
                right=false;
                break;
            case KeyEvent.VK_W:
                up=false;
                break;
            default:
                break;
        }
    }
}
