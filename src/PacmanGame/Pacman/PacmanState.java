package PacmanGame.Pacman;

public interface PacmanState {
	public void handle();
	public void collisionWithGhost();
	public PacmanStates getPacmanState();
}
