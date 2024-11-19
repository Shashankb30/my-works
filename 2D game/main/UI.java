package main;

import java.awt.image.*;
import java.text.DecimalFormat;

import object.*;

import java.awt.*;
public class UI {
    
    GamePanel gp;
    Font arial_40,arial_80B;
    BufferedImage keyImg;
    public boolean messageOn = false;
    public String message= "";
    int messcount=0;
    public boolean gameFinished= false; 
    double playTime;
    DecimalFormat dFormat= new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;

        arial_40= new Font("Arail",Font.PLAIN,40);
        arial_80B= new Font("Arail",Font.BOLD,40);
        OBJ_Key key= new OBJ_Key(gp);
        keyImg= key.image;
    }
    public void ShowMessage(String text){

        message = text;
        messageOn=true;
    }

    public void draw(Graphics2D g2){

        if(gameFinished== true){

            g2.setFont(arial_40);
            g2.setColor(Color.white);
            int textLen,x,y;
            String text;

            text= "You Found the Treasure";
            textLen= (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x= gp.screenWidth/2 - textLen/2;
            y= gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text= "You Have Finished this Game in "+ dFormat.format(playTime)+ " !";
            textLen= (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x= gp.screenWidth/2 - textLen/2;
            y= gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text= "Congratulations!";
            textLen= (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x= gp.screenWidth/2 - textLen/2;
            y= gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread= null;

             
        }else{
            
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImg,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("x"+gp.player.hasKey, 74, 65);
        //TIME
        playTime+= (double)1/60;
        g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65);

        //MESSAGE
        if(messageOn== true){

            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messcount++;

            if(messcount >120){
                messcount= 0;
                messageOn= false;
            }
        }
    }
        }

}
