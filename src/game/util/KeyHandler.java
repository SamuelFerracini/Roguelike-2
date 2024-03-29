package game.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import game.GamePanel;

public class KeyHandler implements KeyListener {

	public static List<Key> keys = new ArrayList<Key>();

	public class Key {
		public int presses, absorbs;
		public boolean down, clicked;

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
			if (pressed) {
				presses++;
			}
		}

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();
	public Key menu = new Key();
	public Key enter = new Key();
	public Key escape = new Key();

	public KeyHandler(GamePanel game) {
		game.addKeyListener(this);
	}

	public void releasedAll() {
		for (Key key : keys) {
			key.down = false;
		}
	}

	public void tick() {
		for (Key key : keys) {
			key.tick();
		}
	}

	private void toggle(KeyEvent e, boolean pressed) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
			up.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
			down.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
			left.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
			right.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			attack.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_E)
			menu.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			enter.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			escape.toggle(pressed);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }
}
