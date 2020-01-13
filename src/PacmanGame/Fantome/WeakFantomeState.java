package PacmanGame.Fantome;

import java.awt.Color;
import PacmanGame.Helpers.Counter;

public class WeakFantomeState implements FantomeState {
	
	private Fantome fantome;
	
	public WeakFantomeState(Fantome fantome) {
		this.fantome = fantome;
	}

	@Override
	public void handle() {
		fantome.setState(this);
		fantome.setColor(Color.BLUE);
	}

	@Override
	public void move() {
		if(Counter.count % 2 == 0){
			fantome.move();
		}
	}

	@Override
	public void collisionWithPacman() {
		fantome.moveToCenter();
	}

	
	
}

