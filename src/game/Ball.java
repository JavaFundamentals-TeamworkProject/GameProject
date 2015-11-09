package game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball {

    public Rectangle boundingBox;
    private BufferedImage ballImg;
    private int ballWidth, ballHeight, gameWidth, gameHeight, speed, XPosition = gameWidth / 2, YPosition = gameHeight / 2;
    private Rectangle playerBoundingBox;
    private boolean[] movement;

    public Ball(BufferedImage img, Rectangle playerBoundingBox, int gameWidth, int gameHeight, int speed){
        this.ballImg = img;
        this.ballWidth = img.getWidth();
        this.ballHeight = img.getHeight();
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.boundingBox = new Rectangle(this.ballWidth,this.ballHeight, this.XPosition + this.ballWidth, this.YPosition + this.ballHeight);
        this.playerBoundingBox = playerBoundingBox;
        this.speed = speed;
        //Movement is an array holding information whether ball is moving right and downwards
        this.movement = new boolean[2];
        this.movement[0] = true; this.movement[1] = true;
    }

    private void moveLeft() {
        this.XPosition -= speed;
    }
    private void moveRight() {
        this.XPosition += speed;
    }
    private void moveUp() {
        this.YPosition -= speed;
    }
    private void moveDown() {
        this.YPosition += speed;
    }

    private void move() {
        //Deciding where to go to
        if(movement[0]) {
            moveRight();
        } else {
            moveLeft();
        }
        if(movement[1]) {
            moveDown();
        } else {
            moveUp();
        }

        //Checking boundaries of the game
        if(this.XPosition + this.ballWidth > gameWidth) {
            movement[0] = false;
        } else if(XPosition <= 0) {
            movement[0] = true;
        } else if(YPosition <= 0) {
            movement[1] = true;
        } else if(YPosition + ballHeight >= gameHeight) {
            //TODO => Losing state
            System.out.println("You died");
            movement[1] = false;
        }

        //Checking for collisions with the player
        if(boundingBox.intersects(playerBoundingBox)) {
            this.movement[1] = false;
        }

    }

    public void tick(){
        this.move();
        this.boundingBox.setBounds(XPosition,YPosition,ballWidth,ballHeight);
    }

    public void render(Graphics g){
        g.drawImage(this.ballImg,this.XPosition,this.YPosition,this.ballWidth, this.ballHeight, null);
    }
}
