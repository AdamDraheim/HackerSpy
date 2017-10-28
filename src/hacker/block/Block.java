package hacker.block;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Block {

	protected int x, y, width, height;
	protected BlockID bid;

	public Block(int x, int y, int width, int height, BlockID bid) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bid = bid;
	}
	
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BlockID getBid() {
		return bid;
	}

	public void setBid(BlockID bid) {
		this.bid = bid;
	}
	
	
	
}
