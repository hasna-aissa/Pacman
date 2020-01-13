package PacmanGame.Fantome;

public interface FantomeState {
    public void handle();
    public void move();
    public void collisionWithPacman();
}
