package game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by kiret0 on 02/11/2015.
 */
public class InputHandler implements KeyListener {

    public InputHandler(Display display){
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}