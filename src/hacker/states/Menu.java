package hacker.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import hacker.anim.BufferedImageLoader;

public class Menu {

	public static int selected = 0;
	
	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage mask;
	
	public Menu() {
		
		try {
			mask = loader.loadImage("/tex/Menu.png");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 1600, 900);
		g.drawImage(mask, -50, 0, 1600, 900, null);
		
		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.BOLD, 70));
		g.drawString("Hacker", 660, 200);
		g.setFont(new Font("Courier new", Font.BOLD, 50));
		
		if(selected == 0) {
			g.drawString("-Start-", 670, 380);
		}else {
			g.drawString("Start", 700, 380);
		}
		
		if(selected == 1) {
			g.drawString("-Credits-", 650, 450);
		}else {
			g.drawString("Credits", 680, 450);
		}
		
		if(selected == 2) {
			g.drawString("-Instructions-", 580, 520);
		}else {
			g.drawString("Instructions", 610, 520);
		}
		
		
	}
	
}
