package PacmanGame.ViewObjects;

import PacmanGame.Game;
import PacmanGame.Helpers.Coordinate;

import java.awt.*;

public abstract class MazeObject  {

    protected Game game;
    protected Coordinate coordinate;
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void drawMe(Graphics g);
    public abstract String Collision();

    public Coordinate Wraparound(Coordinate ObjectCoordinates){
        if(ObjectCoordinates.getX() <= 0){
            return new Coordinate((game.getMaze()[0].length-1)*Game.CASE_UNITE,ObjectCoordinates.getY());
        }
        else if(ObjectCoordinates.getX() >= (game.getMaze()[0].length-1)*Game.CASE_UNITE){
            return new Coordinate(0,ObjectCoordinates.getY());
        }
        if(ObjectCoordinates.getY() < 0){
            return new Coordinate(ObjectCoordinates.getX(),(game.getMaze().length-1)*Game.CASE_UNITE);
        }
        else if(ObjectCoordinates.getY() >= (game.getMaze().length-1)*Game.CASE_UNITE){
             return new Coordinate(ObjectCoordinates.getX(),0);
        }
        return ObjectCoordinates;
    }
}
