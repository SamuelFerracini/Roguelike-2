package game.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import game.graphics.Sprite;
import game.states.PlayState;
import game.util.KeyHandler;
import game.util.MouseHandler;
import game.util.Vector2f;

public class Player extends Entity{

	public Player(Sprite sprite, Vector2f origin, int size) {
		super(sprite, origin, size);
		acc = 2f;
		maxSpeed = 3f;
		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setXOffset(12);
		bounds.setYOffset(40);
	}
	
	public void move() {
		if(up) {
			dy -= acc;
			if(dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		}else {
			if(dy < 0) {
				dy += deacc;
				if(dy > 0) {
					dy = 0;
				}
			}
				
		}
		
		if(down) {
			dy += acc;
			if(dy > maxSpeed) {
				dy = maxSpeed;
			}
		}else {
			if(dy > 0) {
				dy -= deacc;
				if(dy < 0) {
					dy = 0;
				}
			}
			
		}
		
		if(left) {
			dx -= acc;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}else {
			if(dx < 0) {
				dx += deacc;
				if(dx > 0) {
					dx = 0;
				}
			}
			
		}
		
		if(right) {
			dx += acc;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}else {
			if(dx > 0) {
				dx -= deacc;
				if(dx < 0) {
					dx = 0;
				}
			}
			
		}
	}
	
	public void update() {
		super.update();
		move();
		if(!bounds.collisionTile(dx, 0)) {
			PlayState.map.x += dx;
			pos.x += dx;
		}
		if(!bounds.collisionTile(0, dy)) {
			PlayState.map.y += dy;
			pos.y += dy;
		}
		
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.blue);
		g.drawRect((int)(pos.getWorldVar().x + bounds.getXOffset()), (int)(pos.getWorldVar().y + bounds.getYOffset()),(int)bounds.getWidth(),(int)bounds.getHeight());
		g.drawImage(ani.getImage(), (int)(pos.getWorldVar().x), (int)(pos.getWorldVar().y), size,size,null);
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		if(mouse.getButton() == 1) {
			System.out.println("Player:" + pos.x + ", " + pos.y);
		}
		if(key.up.down) {
			up = true;
		}else {
			up = false;
		}
		if(key.down.down) {
			down = true;
		}else {
			down = false;
		}
		if(key.left.down) {
			left = true;
		}else {
			left = false;
		}
		if(key.right.down) {
			right = true;
		}else {
			right = false;
		}
		if(key.attack.down) { 
			attack = true;
		}else {
			attack = false;
		}
	}
	

}
