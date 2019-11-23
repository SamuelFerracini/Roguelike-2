package game.util;

import game.entity.Entity;
import game.tiles.TileMapObj;

public class AABB {

	private Vector2f pos;
	private float xOffset = 0;
	private float yOffset = 0;
	private float w;
	private float h;
	private float r;
	private int size;
	private Entity e;

	public AABB(Vector2f pos, int w, int h) {
		this.pos = pos;
		this.w = w;
		this.h = h;

		size = Math.max(w, h);
	}

	public AABB(Vector2f pos, int r) {
		this.pos = pos;
		this.r = r;

		size = r;

	}

	public Vector2f getPos() {
		return pos;
	}

	public float getRadius() {
		return r;
	}

	public float getWidth() {
		return w;
	}

	public float getHeight() {
		return h;
	}

	public void setBox(Vector2f pos, int w, int h) {
		this.pos = pos;
		this.w = w;
		this.h = h;

		size = Math.max(w, h);
	}

	public void setCircle(Vector2f pos, int r) {
		this.pos = pos;
		this.r = r;

		size = r;
	}

	public void setWidth(float f) {
		w = f;
	}

	public void setHeight(float f) {
		h = f;
	}

	public void setXOffset(float f) {
		xOffset = f;
	}

	public float getXOffset() {
		return xOffset;
	}

	public void setYOffset(float f) {
		yOffset = f;
	}

	public float getYOffset() {
		return yOffset;
	}

	public boolean collides(AABB bBox) {
		float ax = (pos.getWorldVar().x + (xOffset)) + (w / 2);
		float ay = (pos.getWorldVar().y + (yOffset)) + (h / 2);
		float bx = (bBox.pos.getWorldVar().x + (bBox.xOffset)) + (w / 2);
		float by = (bBox.pos.getWorldVar().y + (bBox.yOffset)) + (h / 2);

		if (Math.abs(ax - bx) < (this.w / 2) + (bBox.w / 2)) {
			if (Math.abs(ay - by) < (this.h / 2) + (bBox.h / 2)) {
				return true;
			}
		}
		return false;
	}

	public boolean colCircleBox(AABB aBox) {
		float cx = (float) (pos.getWorldVar().x + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));
		float cy = (float) (pos.getWorldVar().y + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));

		float xDelta = cx
				- Math.max(aBox.pos.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().x));
		float yDelta = cx
				- Math.max(aBox.pos.getWorldVar().y + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().y));

		if ((xDelta * xDelta + yDelta * yDelta) < ((this.r / Math.sqrt(2)) * (this.r / Math.sqrt(2)))) {
			return true;
		}

		return false;
	}

	public boolean collisionTile(float ax, float ay) {
		for (int i = 0; i < 4; i++) {
			int xt = (int) ((pos.x + ax) + (i % 2) * w + xOffset) / 64;
			int yt = (int) ((pos.y + ay) + ((int)(i / 2)) * h + yOffset) / 64;
			
			
			if (TileMapObj.tmp_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
				System.out.println("teste");
				return TileMapObj.tmp_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt)).update(this);
			}
		}
		return false;
	}

}
