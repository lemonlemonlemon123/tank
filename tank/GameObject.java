package tank;

import java.awt.*;

public abstract class GameObject {
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public Image image;
    public int x;
    public  int y;
    public TankTest tankTest;
    public GameObject(String image,int x,int y,TankTest tankTest){
        this.image=Toolkit.getDefaultToolkit().getImage(image);
        this.x=x;
        this.y=y;
        this.tankTest=tankTest;
    }
    public abstract void painSelf(Graphics g);
}
