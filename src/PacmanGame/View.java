package PacmanGame;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{

    private Game game;

    public View(Game game){
        this.game = game;
        setOpaque(true);
        setSize(WIDTH, HEIGHT);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        //Drawing the PACMAN
        game.getPacman().drawMe(g);
        //Vider la place du Pacman au niveau du labyrinth
        game.setMazeBox(game.getPacman().getPosition().getY()/Game.CASE_UNITE,game.getPacman().getPosition().getX()/Game.CASE_UNITE,game.getPacman());
        drawMaze(g);
        //Les affichages du Score et des Vies du Pacman
        g.setColor(Color.YELLOW);
        g.drawString("Score : "+game.getPacman().getScore(),5,613);
        g.drawString("Pacman State : "+game.getPacman().getState().getPacmanState(),170,613);
        g.drawString("Vies : ",387,613);
        for (int i = 1 ; i<= game.getPacman().getNbLives(); i++){
            g.fillOval(i*17+410,604,10,10);
        }
        //Drawing The Ghosts
        game.getFantomes()[0].drawMe(g);
        game.getFantomes()[1].drawMe(g);
        game.getFantomes()[2].drawMe(g);
        game.getFantomes()[3].drawMe(g);
    }

    public void drawMaze(Graphics graphics){
        for (int i=0 ; i < game.getMaze().length ; i++){
            for (int j=0 ; j < game.getMaze()[i].length ; j++){
                if(game.getMaze()[i][j] != null){
                    game.getMaze()[i][j].drawMe(graphics);
                }
            }
        }
    }
}
