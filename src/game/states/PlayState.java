package game.states;

import java.awt.Graphics2D;

import game.GamePanel;
import game.entity.Player;
import game.graphics.Font;
import game.graphics.Sprite;
import game.tiles.TileManager;
import game.util.KeyHandler;
import game.util.MouseHandler;
import game.util.Vector2f;

public class PlayState extends GameState{
	
	private Font font;
	private Player player;
	private TileManager tm;
	public static Vector2f map;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		map = new Vector2f();
		Vector2f.setWorldVar(map.x, map.y);
		tm = new TileManager("tilemap.xml");
		font = new Font("font.png", 10,10);
		player = new Player(new Sprite("linkFormatted.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 64);
	}

	public void update() {
		Vector2f.setWorldVar(map.x, map.y);
		player.update();
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}

	public void render(Graphics2D g) {
		tm.render(g);
		Sprite.drawArray(g, font, Integer.toString(GamePanel.oldFrameCount), new Vector2f(GamePanel.width - 60,10), 32,32,24,0); // FPS COUNTER
		player.render(g);
	}
	

}
