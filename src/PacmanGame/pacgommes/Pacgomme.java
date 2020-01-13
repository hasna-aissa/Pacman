package PacmanGame.pacgommes;


import PacmanGame.Helpers.Counter;
import PacmanGame.ViewObjects.MazeObject;

import java.awt.*;

public abstract class Pacgomme extends MazeObject {

    protected int scoreToAdd;
    private Counter counter = null;

    public abstract void effect();

    public void drawMe(Graphics g){
        g.setColor(color);
        g.fillOval(coordinate.getX() + 7, coordinate.getY() + 7,6, 6);
    }
    public void ReturnToInitialState(int seconds){
        counter = new Counter(seconds*1000,game);
        counter.effectTimer();
    }

    public String Collision() {
        return "Pacgomme";
    }
}