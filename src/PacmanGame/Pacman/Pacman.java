package PacmanGame.Pacman;

import PacmanGame.*;
import PacmanGame.Helpers.Coordinate;
import PacmanGame.Helpers.Direction;
import PacmanGame.ViewObjects.MazeObject;
import PacmanGame.ViewObjects.Personnage;
import PacmanGame.pacgommes.Pacgomme;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pacman extends MazeObject implements Personnage {

    private Direction direction;
    private int score;
    private int nbLives = 3;
    private List<PacmanObserver> pacmanObserverList = new ArrayList<>();
    private Coordinate pacmanPosition;
    private PacmanEvent event;
    private PacmanState state;
    private boolean lifeAdded = false;

    public Pacman(Game game, Coordinate coordinate){
        this.color = new Color(255, 228, 0);
        this.game = game;
        this.direction = Direction.Stop;
        this.pacmanPosition = coordinate;
        addOberver(game);
        this.state = new NormalPacmanState(this);
    }
    public void addToScore(int value){
        this.score+=value;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public Coordinate getPosition() {
        return pacmanPosition;
    }

    public int getScore() {
        return score;
    }

    public PacmanState getState() {
        return state;
    }

    public void setState(PacmanState state) {
        this.state = state;
    }

    public int getNbLives(){
        return nbLives;
    }

    public void DecreaseNbLives() {
        nbLives--;
        if(nbLives <=0) {
        	game.gameOver();
        }
    }

    private void IncreaseNbLives() {
        nbLives++;
    }

    @Override
    public void move() {
        if(score >= 5000 && !lifeAdded){
            IncreaseNbLives();
            lifeAdded = true;
        }
        int coordinateX = pacmanPosition.getX() + direction.getX() * Game.CASE_UNITE;
        int coordinateY = pacmanPosition.getY() + direction.getY() * Game.CASE_UNITE;
        Coordinate newCoordinates = new Coordinate(coordinateX, coordinateY);
        if(newCoordinates.getX() < 0 || newCoordinates.getX() / Game.CASE_UNITE >= game.getMaze()[0].length) wraparound();
        else{
            if(!game.isWall(newCoordinates)){
                pacmanPosition = newCoordinates;
            }else{
                setDirection(direction);
            }
            if(game.isPacgomme(newCoordinates)){
                Coordinate boxCoordinates = new Coordinate((newCoordinates.getY() / Game.CASE_UNITE),(newCoordinates.getX() / Game.CASE_UNITE));
                game.eat((Pacgomme) game.getMazeBox(boxCoordinates));
                game.setMazeBox(boxCoordinates.getX(),boxCoordinates.getY(),this);
            }
        }
        event = new PacmanEvent(newCoordinates, PacmanEvent.Change.MOVETO,state.getPacmanState());
        update(event);
    }

    @Override
    public void wraparound(){
        pacmanPosition = Wraparound(pacmanPosition);
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(color);
        g.fillOval(getPosition().getX()+2, getPosition().getY()+2,15,15);
    }

    @Override
    public String Collision() {
        return "Pacman";
    }

    // Methods Observer

    public void addOberver(PacmanObserver pacmanObserver){
        pacmanObserverList.add(pacmanObserver);
    }

    public void deleteOberver(PacmanObserver pacmanObserver){
        pacmanObserverList.remove(pacmanObserver);
    }

    public void update(PacmanEvent event){
        for (PacmanObserver pacmanObserver : pacmanObserverList){
            pacmanObserver.notifyPacman(event);
        }
    }
}