package PacmanGame.pacgommes;
import PacmanGame.Helpers.Coordinate;
import PacmanGame.Game;

import java.awt.*;

public class GreenPacgomme extends Pacgomme {

    public GreenPacgomme(Coordinate coordinate, Game game){
        this.game = game;
        this.coordinate = coordinate;
        this.color = new Color(0x40FF33);
        this.scoreToAdd = 1000;
    }
    public void effect() {
        game.getPacman().addToScore(scoreToAdd);
        game.StructureModifier();
    }

}

