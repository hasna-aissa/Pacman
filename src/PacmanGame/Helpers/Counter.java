package PacmanGame.Helpers;

import PacmanGame.Game;
import PacmanGame.Pacman.NormalPacmanState;
import java.util.Timer;
import java.util.TimerTask;

public class Counter {

    public static int count = 0;
    public int counter;
    private Game game;

    public Counter(int counter,Game game){
        this.counter = counter;
        this.game = game;
    }

    public static void Start(){
        count++;
    }
    public static void Stop(){
        count = 0;
    }

    public void effectTimer(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                NormalPacmanState normalPacmanState = new NormalPacmanState(game.getPacman());
                normalPacmanState.handle();
            }
        };
        timer.schedule(timerTask,counter);
    }
}
