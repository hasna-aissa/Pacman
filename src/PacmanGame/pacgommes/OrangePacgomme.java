package PacmanGame.pacgommes;

import PacmanGame.Helpers.Coordinate;
import PacmanGame.Game;
import PacmanGame.Pacman.SuperPacmanState;

import java.awt.*;

public class OrangePacgomme extends Pacgomme {

    public OrangePacgomme(Coordinate coordinate, Game game){
        this.game = game;
        this.coordinate = coordinate;
        this.color = new Color(255, 80, 66);
        this.scoreToAdd = 500;
    }

    public void effect() {
    	game.getPacman().addToScore(scoreToAdd);
    	SuperPacmanState superPacmanState = new SuperPacmanState(game.getPacman());
    	superPacmanState.handle();
        ReturnToInitialState(5);
    }
}
