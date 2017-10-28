package hacker.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import hacker.anim.Texture;
import hacker.game.Foreground;
import hacker.game.Game;
import hacker.game.Handler;

public class Lights extends GameObject {

	Handler handler;
	int floor;
	private double recharge = 20;
	private double activeTimer = 5;
	private double maxRecharge = 20;
	private boolean active = true;
	
	Texture tex = Game.getInstance();
	public Lights(double x, double y, int width, int height, ID id, int suspicion, Handler handler, int floor, boolean blind) {
		super(x, y, width, height, id, suspicion, blind);
		this.handler = handler;
		this.floor = floor;
	}

	public void tick() {

		if(!active) {
			recharge -= Game.deltaTime;
			activeTimer -= Game.deltaTime;
			if(activeTimer <= 0) {
				if(floor == 1) {
					Foreground.floor1 = false;
				}
				if(floor == 2) {
					Foreground.floor2 = false;
				}
				if(floor == 3) {
					Foreground.floor3 = false;
				}
				
				for (int i = 0; i < handler.objects.size(); i++) {
					GameObject tempObject = handler.objects.get(i);
					if(this.lightBounds().intersects(tempObject.getBounds())) {
						tempObject.setBlind(false);
					}
				}
				
			}
			
			if(recharge <= 0) {
				active = true;
				recharge = maxRecharge;
				activeTimer = 5;
				this.setId(ID.lightNo);
			}
		}
		
		
	}

	public void render(Graphics g) {
		if(active) {
			g.drawImage(tex.msc[0], (int)x, (int)y, width, height, null);
		}else {
			g.drawImage(tex.msc[1], (int)x, (int)y, width, height, null);
		}
		
		if (this.getId() == ID.lightNo) {
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject tempObject = handler.objects.get(i);
				if (tempObject.getId() == ID.player) {

					if (this.getBounds().intersects(tempObject.getBounds())) {
						g.setColor(Color.BLACK);
						g.drawRect((int) (x + (width / 2) - 9), (int) (y - 33), 18, 18);
						g.setColor(Color.WHITE);
						g.fillRect((int) (x + (width / 2) - 8), (int) (y - 32), 16, 16);

						g.setColor(Color.black);
						g.setFont(new Font("Courier New", Font.BOLD, 20));
						g.drawString("E", (int) (x + (width / 2) - 7), (int) (y - 18));

					}

				}
			}
		}

	}
	
	public Rectangle lightBounds() {
		
		Rectangle bounds = new Rectangle(0, 0, 1, 1);
		
		if(floor == 1) {
			bounds = new Rectangle(0, 0, Game.width, 352);
		}else if(floor == 2) {
			bounds = new Rectangle(0, 362, Game.width, 322 - 34);
		}else if(floor == 3) {
			bounds = new Rectangle(0, 352 + 382 - 64, Game.width, 322);
		}
		
		return bounds;
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public void interact() {
		if(active) {
			active = false;
			if(floor == 1) {
				Foreground.floor1 = true;
			}
			if(floor == 2) {
				Foreground.floor2 = true;
			}
			if(floor == 3) {
				Foreground.floor3 = true;
			}
			maxRecharge += 10;
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject tempObject = handler.objects.get(i);
				if(this.lightBounds().intersects(tempObject.getBounds())) {
					tempObject.setSuspicion(tempObject.getSuspicion() - 20);
					tempObject.setBlind(true);
				}
			}
		}
		
	}

	public void move() {

	}

	public void die() {

	}

}
