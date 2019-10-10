package game.states;

import java.awt.Color;
import java.awt.Graphics2D;

import game.util.KeyHandler;
import game.util.MouseHandler;

public class PlayState extends GameState{

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public void update() {
		
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		if(key.up.down) {
			System.out.println("W IS BEING PRESSED");
		}
	}

	public void render(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.drawRect(100, 100, 200, 200);
	}
	

}
