package PacmanGame.Fantome;

import PacmanGame.Helpers.Coordinate;

public class FantomeEvent
{

    public enum Change {
        MOVETO,
    }
    private Coordinate coordinate;
    private Change change;
    private Fantome fantome;

    public FantomeEvent(Coordinate coordinate, Change change, Fantome fantome){
        this.change = change;
        this.coordinate = coordinate;
        this.fantome = fantome;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Change getChange() {
        return change;
    }

    public Fantome getFantome() {
        return fantome;
    }
}
