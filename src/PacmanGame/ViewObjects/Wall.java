package PacmanGame.ViewObjects;

import PacmanGame.Helpers.Coordinate;

import java.awt.*;

public class Wall extends MazeObject {

    public Wall(Coordinate coordinate){
        this.coordinate = coordinate;
    }
    @Override
    public void drawMe(Graphics g) {
        g.setColor(new Color(48,110,160));
        g.fill3DRect(coordinate.getX(),coordinate.getY(),19,19,true);
    }

    @Override
    public String Collision() {
        return "Wall";
    }
}
