package object;

import java.io.*;

import javax.imageio.*;

import main.GamePanel;

public class OBJ_Chest extends SuperObject {


     GamePanel gp;

     public OBJ_Chest(GamePanel gp){
          this.gp=gp;
         name = "Chest";
         try{
              image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
              uTool.scaleImage(image, gp.tileSize, gp.tileSize);
         }catch(IOException e){
            e.printStackTrace();
         }
    }
}
