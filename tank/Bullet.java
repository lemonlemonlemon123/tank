package tank;

import java.awt.*;

public class Bullet extends GameObject{
    int width=30;
    int height=30;
    int speed=7;
    Direction direction;
    public Bullet(String image, int x, int y, TankTest tankTest,Direction direction){
        super(image,x,y,tankTest);
        this.direction=direction;
    }
    public void leftward(){
        x-=speed;
    }
    public void rightward(){
        x+=speed;
    }
    public void upward(){
        y-=speed;
    }
    public void downward(){
        y+=speed;
    }
    public void go(){
        switch(direction){
            case UP:
                upward();
                break;
            case DONE:
                downward();
                break;
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
        }
    }
    @Override
    public void painSelf(Graphics g) {
        g.drawImage(image,x,y,null);
        this.go();
    }
}
