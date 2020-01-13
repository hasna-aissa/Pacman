package PacmanGame.pacgommes;

import PacmanGame.Helpers.Coordinate;
import PacmanGame.Game;

import java.awt.*;

public class BluePacgomme extends Pacgomme {


    public BluePacgomme(Coordinate coordinate, Game game){
        this.game = game;
        this.coordinate = coordinate;
        this.color = new Color(60, 126, 255);
        this.scoreToAdd = 100;
    }

    public void effect() {
        game.getPacman().addToScore(scoreToAdd);
    }

}
