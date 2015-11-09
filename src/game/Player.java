package game;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player {
    private int playerWidth;
    private int playerHeight;
    private int x, y;

    public Rectangle boundingBox;

    private BufferedImage pad;


    //Constructor
    public Player(BufferedImage img, int x, int y, int width, int height){
        this.playerWidth = width;
        this.playerHeight = height;

        this.x = x;
        this.y = y;

        this.pad = img;

        this.boundingBox = new Rectangle(this.playerWidth, this.playerHeight);
    }

    //update
    public void tick(){

        //move pad
        if (Game.isMovingLeft){
            this.x += -4;
            if (this.x < 0) this.x = 0;
        }else if (Game.isMovingRight){
            this.x += 4;
            if (this.x >  Game.getWidth() - this.playerWidth)
                this.x = Game.getWidth() - this.playerWidth;
        }

        //set box bounds
        this.boundingBox.setBounds(this.x, this.y, this.playerWidth, this.playerWidth);
    }

    //draw
    public void render(Graphics g){
        g.drawImage(this.pad, this.x, this.y, null);
    }
}
