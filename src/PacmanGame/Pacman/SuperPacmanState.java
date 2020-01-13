package PacmanGame.Pacman;

import java.awt.Color;

public class SuperPacmanState implements PacmanState {
	
	private Pacman pacman;
	
	public SuperPacmanState(Pacman pacman) {
		this.pacman = pacman;
	}

	@Override
	public void handle() {
		pacman.setState(this);
		pacman.setColor(new Color(255, 80, 66));
	}

	@Override
	public void collisionWithGhost() {
		//Do nothing, you're Super, and ghosts will retrun to the center!
	}

	@Override
	public PacmanStates getPacmanState() {
		return PacmanStates.Superpacman;
	}

}
