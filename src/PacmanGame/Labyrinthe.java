package PacmanGame;

import javax.swing.*;

public class Labyrinthe extends JFrame {

    public Labyrinthe(Game game){
        setTitle("Pacman");
        //Controllers
        addKeyListener(new Listenner(game));
        //Configs
        add(game.getView());
        setSize(519,678);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
