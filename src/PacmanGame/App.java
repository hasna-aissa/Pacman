package PacmanGame;

import PacmanGame.Helpers.Counter;

import javax.swing.*;

class App {

    public static void main(String[] args) {
        Game game = new Game();
        new Labyrinthe(game);
        while (!game.isGameOver()){
            try{
                Counter.Start();
                Thread.sleep(100);
                game.step();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        Counter.Stop();
        int message = JOptionPane.showConfirmDialog(null, game.getStatus(), "Pacman", JOptionPane.DEFAULT_OPTION);
        if (message <= 0) {
            System.exit(0);
        }

    }
}
