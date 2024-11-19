package main;

import java.awt.*;
import javax.swing.*;

import TILE.TileManger;
import entity.*;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable{
     
    
    //SCREEN SETTINGS
    final int orgTilesize= 16;
    final int scale=3;

    public final int tileSize= orgTilesize*scale;//16*3=48px
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth=tileSize*maxScreenCol;//768px
    public final int screenHeight=tileSize*maxScreenRow;//576px

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow=50;
  
    //FPS
    int FPS = 60;

    //SYSTEM
    public TileManger tileM = new TileManger(this);
    KeyHandler key= new KeyHandler();
    Sound music =new Sound();
    Sound se= new Sound();
    public CollisionChecker checker= new CollisionChecker(this);
    public AssetSetter setter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, key);
    public SuperObject obj[] = new SuperObject[10];

    

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        
    }



    public void startGThread(){

        gameThread=new Thread(this);
        gameThread.start();
    }
    public void setUpGame(){
        setter.setObject();

        playMusic(0);
    }

    @Override
    public void run() {
        double drawInterval= 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;
        long timer=0;
        
        while(gameThread!= null){

            currTime = System.nanoTime();
            timer+=(currTime-lastTime);
            delta +=(currTime - lastTime)/drawInterval;

            lastTime=currTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
                
            }
           if(timer>=1000000000){
           
            
            timer=0;
           }
        }
        
    } 
    public void update(){
          player.update();
    }   
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;

        //TILE
         tileM.draw(g2);

         //OBJECT
        for(int i = 0;i <obj.length;i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

         //PLAYER
         player.draw(g2);

         //UI
         ui.draw(g2);

        g2.dispose();
    } 
     
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
} 
    

