package hacker.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import hacker.object.Player;

public class GameOver {

	public static int selected = 0;
	
	public GameOver() {
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 1600, 900);
		
		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.BOLD, 70));
		g.drawString("GAME OVER", 550, 200);
		g.setFont(new Font("Courier new", Font.BOLD, 50));
		
		g.drawString("You were caught by the police!", 350, 320);
		g.drawString("You had $" + Player.money + "!", 580, 370);

		if(selected == 0) {
			g.drawString("-Restart-", 600, 450);
		}else {
			g.drawString("Restart", 630, 450);
		}
		if(selected == 1) {
			g.drawString("-Return to Menu-",500, 550);
		}else {
			g.drawString("Return to Menu",530, 550);

		}
		
	}
	
}
