package hacker.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import hacker.anim.Texture;
import hacker.object.Player;

public class HUD {

	Handler handler;
	Texture tex = Game.getInstance();
	public HUD(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(tex.msc[4], 5,10, 400, 150, null);
		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.BOLD, 30));
		g.drawString("Suspicion: " + Player.PlayerSuspicion, 50, 80);
		g.drawString("Money: $" + Player.money, 50, 110);

		
	}
	
}
