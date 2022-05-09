package tank;

import java.awt.*;

public abstract class Tank extends GameObject{
    public int width=80;
    public int height=80;
    private int speed=3;
    private Direction direction=Direction.UP;
    private String imagUp;
    private String imagDown;
    private String imagLeft;
    private String imagRight;
    public Tank(String image,int x,int y,TankTest tankTest,String imagUp,String imagDown,String imagLeft,String imagRight){
        super(image,x,y,tankTest);
        this.imagUp=imagUp;
        this.imagDown=imagDown;
        this.imagLeft=imagLeft;
        this.imagRight=imagRight;
    }
    @Override
    public abstract void painSelf(Graphics g);
    public void leftward(){
            direction=Direction.LEFT;
            setImg(imagLeft);
            this.x-=speed;
    }
    public void rightward(){
            direction=Direction.RIGHT;
            setImg(imagRight);
            this.x+=speed;
    }
    public void upward(){
            direction=Direction.UP;
            setImg(imagUp);
            this.y-=speed;
    }
    public void downward(){
            direction=Direction.DONE;
            setImg(imagDown);
            this.y+=speed;
    }
    public void setImg(String img){
        this.image=Toolkit.getDefaultToolkit().getImage(img);
    }
    public Point getHeadPoint(){
        switch(direction){
            case LEFT:
                return new Point(x,y+height/2);
            case RIGHT:
                return new Point(x+width,y+height/2);
            case UP:
                return new Point(x+width/2,y);
            case DONE:
                return new Point(x+width/2,y+height);
            default:
                return null;
        }
    }
    public void attack(){
        Point p=this.getHeadPoint();
        Bullet bullet=new Bullet("D:\\javacx\\src\\tank\\userBullet.jpg",p.x,p.y,this.tankTest,direction);
        this.tankTest.bullets.add(bullet);
    }
}
