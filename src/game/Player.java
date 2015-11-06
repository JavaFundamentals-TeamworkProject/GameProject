package game;

import java.awt.*;


public class Player {
    private int playerWidth;
    private int playerHeight;
    private int x, y;
    private Rectangle boundingBox;


    //Constructor
    public Player(int x, int y, int width, int height){
        this.playerWidth = width;
        this.playerHeight = height;

        this.x = x;
        this.y = y;

        this.boundingBox = new Rectangle(this.playerWidth, this.playerHeight);
    }

    //update
    public void tick(){

        //set box bounds
        this.boundingBox.setBounds(this.x, this.y, this.playerWidth, this.playerWidth);

        //move pad
        if (Game.isMovingLeft){
            this.x += -4;
            if (this.x < 0) this.x = 0;
        }else if (Game.isMovingRight){
            this.x += 4;
            if (this.x >  Game.width - this.playerWidth)
                this.x = Game.width - this.playerWidth;
        }

    }

    //draw
    public void render(Graphics g){
        g.drawImage(gfx.ImageLoader.loadImage("/bat2.png"), this.x, this.y, null);
    }
}
