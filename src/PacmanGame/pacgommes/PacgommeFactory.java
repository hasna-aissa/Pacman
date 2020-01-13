package PacmanGame.pacgommes;

import PacmanGame.Helpers.Coordinate;
import PacmanGame.Game;

public class PacgommeFactory {
	
	private Coordinate coordinate;
	private Game game;
	
	public PacgommeFactory (Coordinate coordinate, Game game) {
		this.game = game;
		this.coordinate = coordinate;
		game.incNbpacgomme();
	}
	
	public Pacgomme createPacgomme(int type){
		switch (type) {
			case 1: 
				return new BluePacgomme(coordinate, game);
			case 2: 
				return new OrangePacgomme(coordinate, game);
			case 3: 
				return new VioletPacgomme(coordinate, game);
			case 4: 
				return new GreenPacgomme(coordinate, game);
			default: 
				return null;
		}
	}
	
}
