package game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball {

    public Rectangle boundingBox;
    private BufferedImage ballImg;
    private int ballWidth, ballHeight, speed, XPosition, YPosition;
    private Rectangle playerBoundingBox;
    private boolean[] movement;
    private Game instance;

    public Ball(Game inst, BufferedImage img, Rectangle playerBoundingBox, int speed){
        this.instance = inst;
        this.ballImg = img;
        this.ballWidth = img.getWidth();
        this.ballHeight = img.getHeight();
        this.boundingBox = new Rectangle(this.ballWidth,this.ballHeight, this.XPosition + this.ballWidth, this.YPosition + this.ballHeight);
        this.playerBoundingBox = playerBoundingBox;
        this.speed = speed;
        //Movement is an array holding information whether ball is moving right and downwards
        this.movement = new boolean[2];
        this.movement[0] = true; this.movement[1] = true;
        this.XPosition = Game.getWidth() / 2;
        this.YPosition = Game.getHeight() / 3;
    }

    private void moveLeft() {
        this.XPosition -= this.speed;
    }
    private void moveRight() {
        this.XPosition += this.speed;
    }
    private void moveUp() {
        this.YPosition -= this.speed;
    }
    private void moveDown() {
        this.YPosition += this.speed;
    }

    private void move() {
        //Deciding where to go to
        if(this.movement[0]) {
            moveRight();
        } else {
            moveLeft();
        }
        if(this.movement[1]) {
            moveDown();
        } else {
            moveUp();
        }

        //Checking boundaries of the game
        if(this.XPosition + this.ballWidth > Game.getWidth()) {
            this.movement[0] = false;
        } else if(XPosition <= 0) {
            this.movement[0] = true;
        } else if(YPosition <= 0) {
            this.movement[1] = true;
        } else if(YPosition + ballHeight >= Game.getHeight()) {
            instance.missedBall();
            System.out.println("You lost a life");
            this.XPosition = Game.getWidth() / 2;;
            this.YPosition = Game.getHeight() / 3;
        }

        if (this.boundingBox.intersects(this.playerBoundingBox)){
            this.movement[1] = false;
        }

        // Check if ball collides with brick;
        for (Bricks[] bricks : Game.getBricks()){
            for (Bricks brick: bricks){
                if (brick.collidesWith(new Rectangle(this.XPosition , this.YPosition , this.ballWidth , this.ballHeight))){
                    brick.destroy();
                    // make logic
                    this.movement[1] = true;

                }
            }
        }

    }

    public void setXPosition(int XPosition) {
        this.XPosition = XPosition;
    }

    public void setYPosition(int YPosition) {
        this.YPosition = YPosition;
    }

    public void tick(){
        this.move();
        this.boundingBox.setBounds(this.XPosition,this.YPosition,this.ballWidth,this.ballHeight);
    }

    public void render(Graphics g){
        g.drawImage(this.ballImg,this.XPosition,this.YPosition,this.ballWidth, this.ballHeight, null);
    }
}
