package PacmanGame.ViewObjects;

import PacmanGame.Game;
import PacmanGame.Helpers.Coordinate;
import PacmanGame.pacgommes.PacgommeFactory;

public class MazeObjectFactory {
	
	private Coordinate coordinate;
	private Game game;
	
	public MazeObjectFactory(Coordinate coordinate, Game game) {
		this.game = game;
		this.coordinate = coordinate;
	}
	
	public MazeObject createMazeObject(int type) {
		if(type == 0) {
			return new Wall(coordinate);
		}
		else {
			PacgommeFactory pacgommeFactory = new PacgommeFactory(coordinate, game);
			return  pacgommeFactory.createPacgomme(type);
		}
	}
}
