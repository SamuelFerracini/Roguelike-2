package game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.Animation;
import game.graphics.Sprite;
import game.util.AABB;
import game.util.KeyHandler;
import game.util.MouseHandler;
import game.util.Vector2f;

public abstract class Entity {

    public int UP = 3;
    public int DOWN = 2;
    public int RIGHT = 0;
    public int LEFT = 1;
    
	protected int currentAnimation;

	protected Animation ani;
	protected Sprite sprite;
	protected Vector2f pos;
	protected int size;

	protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left;
	protected boolean attack;
	protected int attackSpeed;
	protected int AttackDuration;

	protected float dx;
	protected float dy;

	protected float maxSpeed = 3f;
	protected float acc = 3f;
	protected float deacc = 1f;

	protected AABB hitBounds;
	protected AABB bounds;

	public Entity(Sprite sprite, Vector2f origin, int size) {
		this.sprite = sprite;
		pos = origin;
		this.size = size;
		bounds = new AABB(origin, size, size);
		hitBounds = new AABB(new Vector2f(origin.x + (size/2), origin.y), size ,size);
		ani = new Animation();
		setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
	}

	public void setAnimation(int i, BufferedImage[] frames, int delay) {
		currentAnimation = i;
		ani.setFrames(frames);
		ani.setDelay(delay);
	}

	public float getSize() {
		return this.size;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setSize(int i) {
		size = i;
	}

	public void setMaxSpeed(float f) {
		maxSpeed = f;
	}

	public Animation getAnimation() {
		return ani;
	}

	public void setAcc(float f) {
		acc = f;
	}

	public void setDeacc(float f) {
		deacc = f;
	}

	public AABB getBounds() {
		return bounds;
	}

	public void setHitBoxDirection() {
		if (up) {
			hitBounds.setYOffset(-size / 2);
			hitBounds.setXOffset(-size / 2);
		}
		else if (down) {
			hitBounds.setYOffset(size / 2);
			hitBounds.setXOffset(-size / 2);
		}
		else if (left) {
			hitBounds.setYOffset(-size);
			hitBounds.setXOffset(0);
		}
		else if (right) {
			hitBounds.setYOffset(0);
			hitBounds.setXOffset(0);
		}
	}

	public void update() {
		animate();
		setHitBoxDirection();
		ani.update();
	}

	public void animate() {
		if (up) {
			if (currentAnimation != UP || ani.getDelay() == -1) {
				setAnimation(UP, sprite.getSpriteArray(UP), 5);
			}
		}

		else if (down) {
			if (currentAnimation != DOWN || ani.getDelay() == -1) {
				setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
			}
		}

		else if (left) {
			if (currentAnimation != LEFT || ani.getDelay() == -1) {
				setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
			}
		}

		else if (right) {
			if (currentAnimation != RIGHT || ani.getDelay() == -1) {
				setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
			}
		} else {
			setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
		}
	}

	public abstract void render(Graphics2D g);

	public void input(KeyHandler key, MouseHandler mouse) {

	}

}
