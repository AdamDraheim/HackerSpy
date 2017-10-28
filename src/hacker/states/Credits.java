package hacker.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Credits {

	public static int selected = 0;
	
	public Credits() {
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 1600, 900);
		
		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.BOLD, 70));
		g.drawString("Credits", 600, 200);
		g.setFont(new Font("Courier new", Font.BOLD, 50));
		
		g.drawString("Created by Adam Draheim", 400, 320);
		g.setFont(new Font("Courier New", 0, 30));
		g.drawString("At the University of Utah \"Hack the U\" Event", 350, 400);
		g.drawString("Press 'ESCAPE' to return to menu",460, 500);
		
		
	}
	
}
