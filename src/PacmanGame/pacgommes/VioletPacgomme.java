package PacmanGame.pacgommes;

import PacmanGame.Helpers.Coordinate;
import PacmanGame.Game;
import PacmanGame.Pacman.PacmanInvisibleState;
import java.awt.Color;

public class VioletPacgomme extends Pacgomme {

    public VioletPacgomme(Coordinate coordinate, Game game){
        this.game = game;
        this.coordinate = coordinate;
        this.color = Color.MAGENTA;
        this.scoreToAdd = 300;
    }

    public void effect() {
        game.getPacman().addToScore(scoreToAdd);
    	PacmanInvisibleState pacmanInvisibleState = new PacmanInvisibleState(game.getPacman());
    	pacmanInvisibleState.handle();
        ReturnToInitialState(5);
    }
}
