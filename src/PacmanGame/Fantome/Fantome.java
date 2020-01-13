package PacmanGame.Fantome;

import PacmanGame.*;
import PacmanGame.Helpers.*;
import PacmanGame.Pacman.PacmanEvent;
import PacmanGame.Pacman.PacmanObserver;
import PacmanGame.Pacman.PacmanStates;
import PacmanGame.ViewObjects.MazeObject;
import PacmanGame.ViewObjects.Personnage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Fantome extends MazeObject implements Personnage, PacmanObserver {

    private Coordinate fantomePosition;
    private final Coordinate initialCoordinate;
    private Direction direction;
    private FantomeEvent event;
    private FantomeState fantomeState;
    private List<FantomeObserver> fantomeObservers = new ArrayList<>();
    
    public Fantome(Game game, Coordinate coordinate){
        this.game = game;
        color = new Color(0xDB9565);
        initialCoordinate = coordinate;
        fantomePosition = initialCoordinate;
        direction = generateDirection();
        addOberver(game);
        fantomeState = new NormalFantomeState(this);
    }
    @Override
    public void move() {
        Coordinate nextMove = new Coordinate(fantomePosition.getX() + direction.getX() * Game.CASE_UNITE, fantomePosition.getY() + direction.getY() * Game.CASE_UNITE);
        if(nextMove.getX() < 0 || nextMove.getX()/Game.CASE_UNITE >= game.getMaze()[0].length){
           wraparound();
       }
       else {
           if (game.isWall(nextMove)) {
               this.direction = generateDirection();
               fantomePosition = new Coordinate(fantomePosition.getX() + direction.getX() * Game.CASE_UNITE,fantomePosition.getY() + direction.getY() * Game.CASE_UNITE);
           }else{
               fantomePosition = nextMove;
           }
       }
       event = new FantomeEvent(fantomePosition, FantomeEvent.Change.MOVETO,this);
       update(event);
    }

    public void moveToCenter(){
        fantomePosition = initialCoordinate;
        event = new FantomeEvent(fantomePosition, FantomeEvent.Change.MOVETO,this);
    }

    @Override
    public void wraparound() {
        fantomePosition = Wraparound(fantomePosition);
    }

    private Direction generateDirection(){
        List<Direction> AllDirections = new ArrayList<>();
        List<Direction> randomDirections = new ArrayList<>();
        AllDirections.add(Direction.Down);
        AllDirections.add(Direction.Up);
        AllDirections.add(Direction.Left);
        AllDirections.add(Direction.Right);
        for (Direction directionLoop : AllDirections) {
            Coordinate coordinatesGhost = new Coordinate(fantomePosition.getX()  + directionLoop.getX() * Game.CASE_UNITE , fantomePosition.getY() + directionLoop.getY() * Game.CASE_UNITE );
            if (!game.isWall(coordinatesGhost)) {
                randomDirections.add(directionLoop);
            }
        }
        return randomDirections.get((int) (Math.random() * randomDirections.size()));
    }
    
    public void setState(FantomeState state) {
    	this.fantomeState = state;
    }
    public FantomeState getFantomeState() {
		return fantomeState;
	}

   @Override
    public void drawMe(Graphics g) {
		g.setColor(color);
        g.fillOval(fantomePosition.getX(), fantomePosition.getY(), 18, 10);
		g.fillRect(fantomePosition.getX(), fantomePosition.getY()+5, 18, 10);

    }

    @Override
    public String Collision() {
        return "Ghost";
    }

    // Observers Methods

    public void addOberver(FantomeObserver fantomeObserver){
        fantomeObservers.add(fantomeObserver);
    }
    public void deleteOberver(FantomeObserver fantomeObserver){
        fantomeObservers.remove(fantomeObserver);
    }

    public void update(FantomeEvent event){
        for (FantomeObserver fantomeObserver : fantomeObservers){
            fantomeObserver.notifyFantome(event);
        }
    }

    // Observing Pacman
    @Override
    public void notifyPacman(PacmanEvent event) {
    	if(event.getState() == PacmanStates.Superpacman) {
    		WeakFantomeState weakFantomeState = new WeakFantomeState(this);
    		weakFantomeState.handle();
    	}
    	if(event.getState() == PacmanStates.Normal) {
    		NormalFantomeState normalFantomeState = new NormalFantomeState(this);
    		normalFantomeState.handle();
    	}
    }
}
