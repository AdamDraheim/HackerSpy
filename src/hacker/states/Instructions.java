package hacker.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import hacker.object.Player;

public class Instructions {

	public Instructions() {
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 1600, 900);
		
		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.BOLD, 70));
		g.drawString("Instructions", 550, 200);
		g.setFont(new Font("Courier new", Font.BOLD, 30));
		
		g.drawString("Hack money away from the corrupt people", 350, 320);
		g.drawString("The more you steal, the more suspicious you will be" + Player.money + "!", 350, 370);
		g.drawString("But, the people around you are suspects as well!",350, 420);
		g.drawString("Hack a person to take their money", 350,  470);
		g.drawString("Hack a cop to see how suspcious people around you appear", 350, 520);
		g.drawString("Hack the computer to cause a blackout on the floor you are on", 350, 570);
		g.drawString("Steal as much money as you can!", 350, 620);
		g.drawString("A/D to move, W or SPACE to jump, S drop a floor, and E to interact", 350, 670);
		g.drawString("Press 'Escape' to return to the menu", 350, 750);
		
	}
	
}
