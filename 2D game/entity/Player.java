package entity;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import main.*;


public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler key;

    public final int screenX;
    public final int screenY;

    public int hasKey= 0;
    public int standCount;

    public Player(GamePanel g,KeyHandler key){
        this.gp=g;
        this.key=key;

        screenX= gp.screenWidth/2- (gp.tileSize/2);
        screenY= gp.screenHeight/2- (gp.tileSize/2);

        solidArea =new Rectangle();
        solidArea.x= 8;
        solidArea.y=16;
        solidAreaDefX= solidArea.x;
        solidAreaDefY= solidArea.y;
        solidArea.width=24;
        solidArea.height=24;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        
        worldX= gp.tileSize * 23;
        worldY= gp.tileSize * 21;
        direction="down";
        speed=4;
        
    }


    public void getPlayerImage(){

        
        up1 = setUp("up1");
        up2 = setUp("up2");
        down1 = setUp("down1");
        down2 = setUp("down2");
        left1 = setUp("left1");
        left2 = setUp("left2");
        right1 = setUp("right1");
        right2 = setUp("right2");
        
        
    }
    public BufferedImage setUp(String imagename){
        Utility utool = new Utility();
        BufferedImage img= null;
        try {
            img= ImageIO.read(getClass().getResourceAsStream("/res/player/"+imagename+".png"));
           img=utool.scaleImage(img, gp.tileSize,gp.tileSize );
        } catch (IOException e) {
           e.printStackTrace();
        }
        return img;
    }

    public void update(){
        if(key.up==true || key.down==true||key.left==true||key.right==true){
            if (key.up== true){
                direction="up";
                
              }else if(key.down== true){
                direction="down";
                
              }else if(key.left== true){
                direction="left";
               
              }else if(key.right==true){
                direction="right";
               
              }
     //CHECK TILE COLLISION
              collisionOn=false;
              gp.checker.checkTile(this);

     //CHECK OBJECT COLLISION
             int objIndex= gp.checker.checkObject(this, true);
             pickUpObj(objIndex);


     //IF COLLISON IS FALSE CAN MOVE 
             if(collisionOn== false){
                 if(direction == "up"){
                    worldY-= speed;
                 }else if(direction== "down"){
                    worldY += speed;
                 }else if(direction== "left"){
                    worldX -= speed;
                 }else if(direction== "right"){
                    worldX += speed;
                 }
                
     }
              spriteCount++;
              if(spriteCount>10){
                if(spriteNum == 1){
                    spriteNum= 2;
                }else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCount = 0;
              }
        }else{ //TO MAKE THE SPRITE IN STANDING POS WHEN NOT MOVING
            standCount++;
            if(standCount == 20){
                spriteNum= 1;
                standCount= 0;
            }
        }
    
        }
    
    public void pickUpObj(int i){
        if (i!=999){
           String objName = gp.obj[i].name;
           switch(objName){
            case "Key":
              gp.playSE(1);
              hasKey++;
              gp.obj[i]= null;
              gp.ui.ShowMessage("You got a Key");
              break;
            case "Door":
              gp.playSE(3);
              if(hasKey>0){
                gp.obj[i]=null;
                hasKey--;
                gp.ui.ShowMessage("You opened the door!");
              }else{
                gp.ui.ShowMessage("I dont like this sound.GO find a key,Bastard :<");
              }
              break;
            case "Boots":
              gp.playSE(2);
              speed+= 2;
              gp.obj[i]= null;
              gp.ui.ShowMessage("Your faster than ever :)");
              break;
            case "Chest":
              gp.ui.gameFinished = true;
              gp.stopMusic();
              gp.playSE(4);
              break;
           }
        }

    }
        
    public void draw(Graphics2D g2){
        
        // g2.setColor(Color.white);
        //  g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image= up2;
 
        if(direction== "up"){
            if(spriteNum==1){
                image= up1;
            }
            if(spriteNum==2){
                image=up2;
            }
           
        }else if(direction== "down"){
            if(spriteNum==1){
                image=down1;
            }
            if(spriteNum==2){
                image=down2;
            }
        }else if(direction== "left"){
            if(spriteNum==1){
                image = left1;
            }
            if(spriteNum==2){
                image=left2;
            }
        }else if(direction== "right"){
            if(spriteNum== 1){
                image =right1;
            }
            if(spriteNum== 2){
                image =right2;
            }
        }

        
        g2.drawImage(image, screenX,screenY,null);
    }
}
