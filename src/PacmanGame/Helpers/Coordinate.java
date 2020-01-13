package PacmanGame.Helpers;

public class Coordinate {
    private int coordinateX;
    private int coordinateY;

    public Coordinate(int coordinateX, int coordinateY){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getX() {
        return coordinateX;
    }

    public int getY() {
        return coordinateY;
    }

    @Override
    public String toString() {
        return "("+coordinateX+","+coordinateY+")";
    }
}
