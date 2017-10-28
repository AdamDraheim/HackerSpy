package hacker.game;

import java.awt.Color;
import java.awt.Graphics;

public class Foreground {

	public static boolean floor1 = false, floor2 = false, floor3 = false;
	
	public Foreground() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		if(floor1) {
			
			g.fillRect(0, 0, Game.width, 352);
			
		}
		if(floor2) {
			g.fillRect(0, 352, Game.width, 322 - 64);

		}
		if(floor3) {
			g.fillRect(0, 352 + 322 - 64, Game.width, 352);

		}
		
	}
	
}
