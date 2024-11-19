package main;

import entity.*;


public class CollisionChecker{

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp=gp;
        
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX= entity.worldX+ entity.solidArea.x+ entity.solidArea.width;
        int entityTopY= entity.worldY+entity.solidArea.y;
        int entityBottomY=entity.worldY+entity.solidArea.y+ entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol= entityRightWorldX/gp.tileSize;
        int entityTopRow= entityTopY/gp.tileSize;
        int entityBottomRow= entityBottomY/gp.tileSize;

        int tileNum1,tileNum2;

        switch (entity.direction) {
            case "up" :
                entityTopRow= (entityTopY - entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if(gp.tileM.tile[tileNum1].collision==true ||
                   gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                   }
                break;
            case "down":
               entityBottomRow=(entityBottomY + entity.speed)/gp.tileSize;
               tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
               tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

               if(gp.tileM.tile[tileNum1].collision==true ||
               gp.tileM.tile[tileNum2].collision==true){
                   entity.collisionOn=true;
               }
                break;

            case "left":
                entityLeftCol=(entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true ||
                    gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
               }
                break;


            case "right":
                entityRightCol=(entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true ||
                    gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
               }
                break;
        
        }
    }
    public int checkObject(Entity entity,boolean player){
        int idx= 999;

        for(int i =0;i< gp.obj.length;i++){
           
            if(gp.obj[i]!= null){
                //get entity's solid area
                entity.solidArea.x= entity.worldX+ entity.solidArea.x;
                entity.solidArea.y= entity.worldY + entity.solidArea.y;

                //get the objects solid area position
                gp.obj[i].solidArea.x=  gp.obj[i].worldX+ gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y= gp.obj[i].worldY +gp.obj[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y-= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn= true;
                            }
                            if(player ==true){
                                idx= i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn= true;
                            }
                            if(player ==true){
                                idx= i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x-= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn= true;
                            }
                            if(player ==true){
                                idx= i;
                            }
                        }
                         break;
                     case "right":
                         entity.solidArea.y += entity.speed;
                         if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn= true;
                            }
                            if(player ==true){
                                idx= i;
                            }
                            }
                         break;
                }
                entity.solidArea.x = entity.solidAreaDefX;
                entity.solidArea.y= entity.solidAreaDefY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefX;
                gp.obj[i].solidArea.y= gp.obj[i].solidAreaDefY;
            }
        }
        return idx;
    }
}