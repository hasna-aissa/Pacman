package PacmanGame.Pacman;

import java.awt.Color;

public class NormalPacmanState implements PacmanState {
	
	private Pacman pacman;
	
	public NormalPacmanState(Pacman pacman) {
		this.pacman = pacman;
	}

	@Override
	public void handle() {
		pacman.setState(this);
		pacman.setColor(new Color(255, 228, 0));
	}

	@Override
	public void collisionWithGhost() {
		pacman.DecreaseNbLives();
	}

	@Override
	public PacmanStates getPacmanState() {
		return PacmanStates.Normal;
	}

}
