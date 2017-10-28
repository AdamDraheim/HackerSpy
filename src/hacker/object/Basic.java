package hacker.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import hacker.anim.Animation;
import hacker.anim.Texture;
import hacker.block.Block;
import hacker.block.BlockID;
import hacker.game.Game;
import hacker.game.Handler;

public class Basic extends GameObject{

	Handler handler;
	private int facing = -1;
	private int speed = 3;
	private double moveTimer = 3;
	private double refresh = 10;
	private int moneyIncrease = 0;
	private boolean showMoneyIncrease = false;
	private double showMoneyTimer = 2;
	Texture tex = Game.getInstance();
	private Animation left, right;
	int oldFacing = -1;
	
	public Basic(double x, double y, int width, int height, ID id, int suspicion, Handler handler, boolean blind) {
		super(x, y, width, height, id, suspicion, blind);
		this.handler = handler;
		facing = (int)(Math.random() * 3) - 1;
		right = new Animation(5, tex.guy[0], tex.guy[2], tex.guy[1], tex.guy[2]);
		left = new Animation(5, tex.guy[3], tex.guy[5], tex.guy[4], tex.guy[5]);


	}

	public void tick() {
		
		left.runAnimation();
		right.runAnimation();
		if(suspicion < 0) {
			suspicion = 0;
		}
		
		move();
		
		if(this.getId() == ID.NPCYes) {

			refresh -= Game.deltaTime;
			if(refresh <= 0) {
				this.setId(ID.NPCNo);
				refresh = 10;
			}
		}
	}

	public void render(Graphics g) {
		if(facing == -1) {
			left.drawAnimation(g, (int)x, (int)y, width, height);
		}else if(facing == 1) {
			right.drawAnimation(g, (int)x, (int)y, width, height);
		}else if(facing == 0) {
			if(oldFacing == -1) {
				g.drawImage(tex.guy[2], (int)x, (int)y, width, height, null);
			}else if(oldFacing == 1) {
				g.drawImage(tex.guy[5], (int)x, (int)y, width, height, null);
			}else if(oldFacing == 0) {
				oldFacing = 1;
			}
		}
		
		if(this.getId() == ID.NPCNo) {
			for(int i = 0; i < handler.objects.size(); i++) {
				GameObject tempObject = handler.objects.get(i);
				if(tempObject.getId() == ID.player) {
					
					if(this.getBounds().intersects(tempObject.getBounds())) {
						g.setColor(Color.BLACK);
						g.drawRect((int) (x + (width / 2) - 9), (int) (y - 33), 18, 18);
						g.setColor(Color.WHITE);
						g.fillRect((int)(x + (width / 2) - 8) , (int)(y - 32), 16, 16);
						
						g.setColor(Color.black);
						g.setFont(new Font("Courier New", Font.BOLD, 20));
						g.drawString("E", (int)(x + (width / 2) - 7), (int)(y - 18));
						
					}
					
				}
			}
		}
		
		if(showMoneyIncrease) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("Courier New", Font.BOLD, 20));
			g.drawString("$" + moneyIncrease, (int)(x + (width / 2) - 7) - (("$" + moneyIncrease).length() * 5), (int)((y - 18)));
			showMoneyTimer -= Game.deltaTime;
			if(showMoneyTimer <= 0) {
				showMoneyIncrease = false;
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public void interact() {

		moneyIncrease = ((int)(Math.random() * 10) * ((int)(Player.PlayerSuspicion / 10) + 1));
		Player.money += moneyIncrease;
		Handler.distanceToGoal += moneyIncrease;
		showMoneyIncrease = true;
		showMoneyTimer = 2;
		
	}

	public void move() {
		
		moveTimer -= Game.deltaTime;
		if(moveTimer <= 0 || collision()) {
			oldFacing = facing;
			facing = (int)(Math.random() * 3) - 1;
			moveTimer = 2;
		}
		
		x += (facing * speed);
		
	}
	
	public boolean collision() {
		
		boolean check = false;
		
		for(int i = 0; i < handler.blocks.size(); i++) {
			Block tempBlock = handler.blocks.get(i);
			if(tempBlock.getBid() == BlockID.wall) {
				if(this.getBounds().intersects(tempBlock.getBounds())) {
					if(facing == -1) {
						x += 5;
						check = true;
					}
					if(facing == 1) {
						x -= 5;
						check = true;
					}
				}
			}
		}
		return check;
		
	}

	public void die() {
		handler.removeObject(this);
	}
	
}
