package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
public class Entity {
    
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;

    public int spriteCount= 0;
    public int spriteNum=1;

    public Rectangle solidArea;
    public int solidAreaDefX, solidAreaDefY;
    public boolean collisionOn= false;
}
