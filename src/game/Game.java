package game;

import display.Display;
import gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game implements Runnable{
    private Display display;

    public  static int width,height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;

    public static Player player;
    public static Ball ball;
    public static Bricks bricks;

    public static boolean isMovingLeft;
    public static boolean isMovingRight;

    public Game(String title, int height, int width) {

        this.title = title;
        this.height = height;
        this.width = width;

    }


    public Player getPlayer(){
        return this.player;
    }

    private void init(){
        this.display = new Display(this.title, this.width, this.height);
        img = gfx.ImageLoader.loadImage("/background.png");

        this.inputHandler = new InputHandler(this.display);


        player = new Player((width - 111) / 2, height - 19, 111, 19);
        ball = new Ball();
        bricks = new Bricks();
    }

    private  void tick(){
        //update player
        player.tick();
        //update ball
        ball.tick();
        //update bricks
        bricks.tick();
    }
    private  void render(){
        this.bs = display.getCanvas().getBufferStrategy();

        if(bs == null){
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }

        this.g = bs.getDrawGraphics();
        g.clearRect(0,0, this.width, this.height);
        g.drawImage(img,0,0,this.width, this.height, null);
        //Start draw
        player.tick();
        player.render(g);


        //End draw
        this.bs.show();

        this.g.dispose();
    }

    @Override
    public void run() {
        this.init();

        int fps = 60;
        double timePerTick = 1_000_000_000.0 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();

                ticks++;
                delta--;
            }
            if(timer >= 1_000_000_000){

                timer = 0;
                ticks = 0;
            }
        }
        stop();
    }
    public synchronized void start(){
        if(running){
            return;
        }

        running = true;
        this.thread = new Thread(this);

        this.thread.start();
    }
    public synchronized void stop() {
        if(!running){
            return;
        }
        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


