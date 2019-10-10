package game.states;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.GamePanel;
import game.util.KeyHandler;
import game.util.MouseHandler;
import game.util.Vector2f;

public class GameStateManager {

	private ArrayList<GameState> states;
	public static Vector2f map;
	public static final int PLAY = 0;
	public static final int MENU = 0;
	public static final int PAUSE = 0;
	public static final int GAMEOVER = 0;

	public GameStateManager() {
		map = new Vector2f(GamePanel.width, GamePanel.height);
		Vector2f.setWorldVar(map.x, map.y);
		states = new ArrayList<GameState>();
		states.add(new PlayState(this));
	}

	public void pop(int state) {
		states.remove(state);
	}

	public void addAndPop(int state) {
		states.remove(0);
		add(state);
	}

	public void add(int state) {
		if (state == PLAY) {
			states.add(new PlayState(this));
		}
		if (state == MENU) {
			states.add(new MenuState(this));
		}
		if (state == PAUSE) {
			states.add(new PauseState(this));
		}
		if (state == GAMEOVER) {
			states.add(new GameOverState(this));
		}

	}

	public void update() {
		Vector2f.setWorldVar(map.x, map.y);
		for (GameState gameState : states) {
			gameState.update();
		}
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		for (GameState gameState : states) {
			gameState.input(mouse, key);
		}
	}

	public void render(Graphics2D g) {
		for (GameState gameState : states) {
			gameState.render(g);
		}
	}
}
