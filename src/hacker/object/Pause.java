package hacker.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Pause {

	public static int selected = 0;
	public Pause() {
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.gray);
		g.fillRect(600, 300, 400, 200);
		
		g.setColor(Color.black);
		g.setFont(new Font("Courier New", Font.BOLD, 60));
		g.drawString("PAUSED", 680, 350);
		
		g.setFont(new Font("Courier New", Font.BOLD, 40));
		if(selected == 0) {
			g.drawString("-Continue-", 660, 420);
		}
		
	}
	
}
