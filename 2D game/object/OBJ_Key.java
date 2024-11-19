package object;

import java.io.*;
import javax.imageio.*;

import main.*;

public class OBJ_Key extends SuperObject {

     GamePanel gp;

    public OBJ_Key(GamePanel gp){
     this.gp=gp;
         name = "Key";
         try{
              image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
              uTool.scaleImage(image, gp.tileSize, gp.tileSize);


         }catch(IOException e){
            e.printStackTrace();
         }
    }

    
}
