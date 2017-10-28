package hacker.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import hacker.anim.Texture;
import hacker.game.Game;

public class Floor extends Block{

	Texture tex = Game.getInstance();
	
	public Floor(int x, int y, int width, int height, BlockID bid) {
		super(x, y, width, height, bid);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		if(this.getBid() == BlockID.platform) {
			g.drawImage(tex.msc[2], x, y, width, height, null);
		}else {
			g.drawImage(tex.msc[3], x, y, width, height, null);
		}
	}

	public Rectangle getBounds() {
		if(this.getBid() == BlockID.platform) {
			return new Rectangle(x, y, width, height / 4);
		}
		return new Rectangle(x, y, width, height);
	}

}
