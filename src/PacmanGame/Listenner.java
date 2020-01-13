package PacmanGame;

import PacmanGame.Helpers.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listenner implements KeyListener {

    private Game game;

    public Listenner(Game game){
        this.game = game;
    }


    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT : game.setDirection(Direction.Left);
                break;
            case KeyEvent.VK_RIGHT : game.setDirection(Direction.Right);
                break;
            case KeyEvent.VK_UP : game.setDirection(Direction.Up);
                break;
            case KeyEvent.VK_DOWN : game.setDirection(Direction.Down);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
