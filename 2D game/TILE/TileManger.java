package TILE;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import javax.imageio.ImageIO;

import main.*;

public class TileManger {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManger(GamePanel gp){
        this.gp=gp;

        tile= new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world02.txt");
        

        
        
    }

    public void getTileImage(){
    
        // setUp(0, "grass", false);
        // setUp(1, "wall", true);
        // setUp(2, "water", true);
        // setUp(3, "earth", false);
        // setUp(4, "tree00", true);
        // setUp(5, "sand", false);
        for(int i=0;i<10;i++){
            setUp(i, "grass00", false);
        }
        setUp(10, "grass00", false);
        setUp(11, "grass01", false);
        setUp(12, "water00", false);
        setUp(13, "water01", false);
        setUp(14, "water02", true);
        setUp(15, "water03", true);
        setUp(16, "water04", true);
        setUp(17, "water05", true);
        setUp(18, "water06", true);
        setUp(19, "water07", true);
        setUp(20, "water08", true);
        setUp(21, "water09", true);
        setUp(22, "water10", true);
        setUp(23, "water11", true);
        setUp(24, "water12", true);
        setUp(25, "water13", true);
        setUp(26, "road00", false);
        setUp(27, "road01", false);
        setUp(28, "road02", false);
        setUp(29, "road03", false);
        setUp(30, "road04", false);
        setUp(31, "road05", false);
        setUp(32, "road06", false);
        setUp(33, "road07", false);
        setUp(34, "road08", false);
        setUp(35, "road09", false);
        setUp(36, "road10", false);
        setUp(37, "road11", false);
        setUp(38, "road12", false);
        setUp(39, "earth00", false);
        setUp(40, "wall00", true);
        setUp(41, "tree00", true);
        setUp(42, "grass00", false);
        setUp(43, "grass00", false);
        setUp(44, "grass00", false);
        setUp(45, "grass00", false);
        setUp(46, "grass00", false);
        setUp(47, "grass00", false);
        setUp(48, "grass00", false);
        setUp(49, "grass00", false);
        
        

    }

           
    
    public void setUp(int index,String imgPath,boolean collision){

       

        Utility uTool = new Utility();

        try{
            tile[index]= new Tile();
            tile[index].image= ImageIO.read(getClass().getResourceAsStream("/res/newtiles/"+ imgPath +".png"));
            tile[index].image=uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision= collision;
          
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row= 0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line = br.readLine();

                while(col<gp.maxWorldCol ){
                    String numbers[]= line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            
        }
    }
    public void draw(Graphics2D g2){

       int WorldCol=0;
       int WorldRow=0;
       

       while(WorldCol < gp.maxWorldCol && WorldRow < gp.maxWorldRow){

        int tileNum =mapTileNum[WorldCol][WorldRow];

        int worldX= WorldCol * gp.tileSize;
        int worldY= WorldRow * gp.tileSize;
        int screenX= worldX - gp.player.worldX + gp.player.screenX;
        int screenY= worldY - gp.player.worldY +gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize <gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && 
           worldY - gp.tileSize<gp.player.worldY + gp.player.screenY)   {
        
            g2.drawImage(tile[tileNum].image,screenX,screenY,null);
        }
        WorldCol++;
       

        if(WorldCol == gp.maxWorldCol){
            WorldCol= 0;
            WorldRow++;
            
        }
       }
    }
}
