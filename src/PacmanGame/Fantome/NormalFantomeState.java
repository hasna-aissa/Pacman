package PacmanGame.Fantome;

import java.awt.Color;

public class NormalFantomeState implements FantomeState {
	
	private Fantome fantome;
	
	public NormalFantomeState(Fantome fantome) {
		this.fantome = fantome;
	}

	@Override
	public void handle() {
		fantome.setState(this);
		fantome.setColor(new Color(0xDB9565));
	}

	@Override
	public void move() {
		fantome.move();
	}

	@Override
	public void collisionWithPacman() {
		//Eat it!
	}

}
