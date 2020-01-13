package PacmanGame.Pacman;

import PacmanGame.Helpers.Coordinate;

public class PacmanEvent {

    public enum Change {
        MOVETO, ATE, DIE
    }
    private Coordinate coordinate;
    private Change change;
    private PacmanStates state;

    public PacmanEvent(Coordinate coordinate, Change change, PacmanStates state){
        this.change = change;
        this.coordinate = coordinate;
        this.state = state;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Change getChange() {
        return change;
    }

    public PacmanStates getState() {
        return state;
    }
}