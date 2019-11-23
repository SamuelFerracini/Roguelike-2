package game.tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.util.AABB;
import game.util.Vector2f;

public class ObjBlock extends Block{

	public ObjBlock(BufferedImage img, Vector2f pos, int w, int h) {
		super(img, pos, w, h);
	}

	public boolean update(AABB p) {
		return true;
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		g.setColor(Color.white);
		g.drawRect((int)pos.getWorldVar().x, (int)pos.getWorldVar().y, w,h);
	}

	
}
