package PacmanGame.Pacman;

import java.awt.Color;

public class PacmanInvisibleState implements PacmanState {
	
	private Pacman pacman;
	
	public PacmanInvisibleState(Pacman pacman) {
		this.pacman = pacman;
	}

	@Override
	public void handle() {
		pacman.setState(this);
		pacman.setColor(new Color(245, 255, 135));
	}

	@Override
	public void collisionWithGhost() {
		//Do nothing, you're invisible!
	}

	@Override
	public PacmanStates getPacmanState() {
		return PacmanStates.Invisible;
	}

}
