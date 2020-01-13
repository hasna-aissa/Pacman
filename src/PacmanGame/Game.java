package PacmanGame;

import PacmanGame.Fantome.*;
import PacmanGame.Helpers.*;
import PacmanGame.Pacman.*;
import PacmanGame.ViewObjects.*;
import PacmanGame.pacgommes.*;

public class Game implements FantomeObserver, PacmanObserver {

    public static final int CASE_UNITE = 20;
    private int nbPacgommes = 0;
    private final Pacman pacman;
    private final Fantome[] fantomes = new Fantome[4];
    private final MazeObject[][] Maze = new MazeObject[30][25];
    private boolean gameOver;
    private View view;
    private int status = -1;

    public Game(){
        view = new View(this);
        generateMaze(Map.getMap());
        pacman = new Pacman(this, new Coordinate(240,480));
        putFantomes();
        gameOver = false;
    }

    private void putFantomes(){
        for(int i=0 ; i <= 3 ; i++){
            fantomes[i] = new Fantome(this,new Coordinate(Game.CASE_UNITE*(i+10),Game.CASE_UNITE*14));
            pacman.addOberver(fantomes[i]);
        }
    }

    private void generateMaze(int[][] map){
        for (int i=0 ; i < map.length; i++){
            for (int j=0; j < map[i].length; j++){
            	MazeObjectFactory mazeObjectFactory = new MazeObjectFactory(new Coordinate((CASE_UNITE * j),(CASE_UNITE * i)),this);
            	setMazeBox(i,j,mazeObjectFactory.createMazeObject(map[i][j]));
            }
        }
    }
    
    public void StructureModifier(){
        int[][] map1 = Map.getAltMap();
        for (int i=0 ; i < map1.length; i++){
            for (int j=0; j < map1[i].length; j++){
                if(map1[i][j] == 5)
                {
                    if(getMazeBox(new Coordinate(i,j)).Collision() == "Pacgomme") {
                    	nbPacgommes--;
                    }
                    setMazeBox(i,j,new Wall(new Coordinate((CASE_UNITE * j),(CASE_UNITE * i))));
                }
            }
        }
    }

    public void setMazeBox(int indexX,int indexY, MazeObject mazeObject){
        try {
            this.Maze[indexX][indexY] = mazeObject;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public MazeObject getMazeBox(Coordinate coordinate){
        try {
            return Maze[coordinate.getX()][coordinate.getY()];
        }catch (Exception a){
            return null;
        }
    }
    
    public void incNbpacgomme(){
    	nbPacgommes++;
    }

    public MazeObject[][] getMaze() {
        return Maze;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Fantome[] getFantomes() {
        return fantomes;
    }

    public String getStatus(){
        if(status == 0){
            return "Game Over, You LOST";
        }
        return "Congratulations, You WON!";
    }

    public View getView() {
        return view;
    }

    public Boolean isGameOver(){
        return gameOver;
    }

    public void step(){
        pacman.move();
        fantomes[0].getFantomeState().move();
        fantomes[1].getFantomeState().move();
        fantomes[2].getFantomeState().move();
        fantomes[3].getFantomeState().move();
    }

    public void setDirection(Direction direction){
        int cordX = direction.getX()*CASE_UNITE + pacman.getPosition().getX();
        int cordY = direction.getY()*CASE_UNITE + pacman.getPosition().getY();
        if(!isWall(new Coordinate(cordX,cordY))){
            pacman.setDirection(direction);
        }
    }

    public void eat(Pacgomme pacgommeToEat){
        nbPacgommes--;
        pacgommeToEat.effect();
        if(nbPacgommes <= 0){
            status = 1;
            gameOver = true;
        }
    }

    public boolean isWall(Coordinate newCoordinates){
        Coordinate boxCoordinates = new Coordinate((newCoordinates.getY()/CASE_UNITE),(newCoordinates.getX()/CASE_UNITE));
        return getMazeBox(boxCoordinates).Collision().equals("Wall");
    }
    public boolean isPacgomme(Coordinate newCoordinates){
        Coordinate boxCoordinates = new Coordinate((newCoordinates.getY()/CASE_UNITE),(newCoordinates.getX()/CASE_UNITE));
        return getMazeBox(boxCoordinates).Collision().equals("Pacgomme");
    }
    
    public void gameOver() {
	    status = 0;
	    gameOver = true;
    }

    private void PauseGame(){
        try{
            Thread.sleep(1000);
        }catch (Exception e) {
            return;
        }
    }

    //After receiving events from observers we handle any possible collisions between PACMAN & Fantomes.
    public void CollisionGhostPacman(Fantome fantome){
    	pacman.getState().collisionWithGhost();
    	PauseGame();
    	fantome.getFantomeState().collisionWithPacman();
    }

    // Observing Fantome Method
    @Override
    public void notifyFantome(FantomeEvent event) {
        if(event.getCoordinate().getX() == pacman.getPosition().getX() && event.getCoordinate().getY() == pacman.getPosition().getY()){
            if(pacman.getState().getPacmanState() != PacmanStates.Invisible) {
            	CollisionGhostPacman(event.getFantome());
            }
        }
    }

    // Observing Pacman Method
    @Override
    public void notifyPacman(PacmanEvent event) {
        if(event.getChange() == PacmanEvent.Change.MOVETO){
            view.repaint();
        }
    }
}
