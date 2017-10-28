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

public class Guard extends GameObject{

	Handler handler;
	private int facing = -1;
	private int speed = 3;
	private double moveTimer = 3;
	private double refresh = 10;
	private boolean showSuspicion = false;
	private double suspicionTimer = 3;
	
	int oldFacing = -1;
	
	private Animation left, right, ALeft, ARight;
	
	private GameObject focus = null;
	
	Texture tex = Game.getInstance();
	
	public Guard(double x, double y, int width, int height, ID id, int suspicion, Handler handler, boolean blind) {
		super(x, y, width, height, id, suspicion, blind);
		this.handler = handler;
		facing = (int)(Math.random() * 3) - 1;
		
		right = new Animation(5, tex.police[0], tex.police[2], tex.police[1], tex.police[2]);
		ARight = new Animation(5, tex.police[4], tex.police[3], tex.police[5], tex.police[3]);
		left = new Animation(5, tex.police[6], tex.police[8], tex.police[7], tex.police[8]);
		ALeft = new Animation(5, tex.police[10], tex.police[9], tex.police[11], tex.police[9]);


	}

	public void tick() {
		
		if(blind) {
			focus = null;
		}
		

		right.runAnimation();
		ARight.runAnimation();
		left.runAnimation();
		ALeft.runAnimation();
		
		if(focus == null) {
			move();
		}else {
			attack(focus);
		}
		if(this.getId() == ID.GuardYes) {

			refresh -= Game.deltaTime;
			if(refresh <= 0) {
				this.setId(ID.GuardNo);
				refresh = 10;
			}
		}
		
		if(showSuspicion) {
			suspicionTimer -= Game.deltaTime;
			if(suspicionTimer <= 0) {
				suspicionTimer = 3;
				showSuspicion = false;
			}
		}
	}

	public void render(Graphics g) {

		if(facing == -1 && focus == null) {
			left.drawAnimation(g, (int)x, (int)y, width, height);
		}else if(facing == -1) {
			ALeft.drawAnimation(g, (int)x, (int)y, width, height);
		}else if(facing == 1 && focus == null) {
			right.drawAnimation(g, (int)x, (int)y, width, height);
		}else if(facing == 1) {
			ARight.drawAnimation(g, (int)x, (int)y, width, height);
		}else if(facing == 0) {
			if(oldFacing == -1) {
				g.drawImage(tex.police[8], (int)x, (int)y, width, height, null);
			}else if(oldFacing == 1) {
				g.drawImage(tex.police[2], (int)x, (int)y, width, height, null);
			}else if(oldFacing == 0) {
				oldFacing = 1;
			}
		}

		if(this.getId() == ID.GuardNo) {

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
		
		if(showSuspicion) {
			for(int i = 0; i < handler.objects.size(); i++) {
				GameObject tempObject = handler.objects.get(i);
				if(tempObject.getId() == ID.NPCNo || tempObject.getId() == ID.NPCYes) {
					if(Math.abs(tempObject.getX() - this.getX()) < 300) {
						g.setColor(Color.red);
						g.setFont(new Font("Courier New", Font.BOLD, 20));
						g.drawString(tempObject.getSuspicion() + "", (int)(tempObject.getX() + (width / 2) - 7) - (("" + tempObject.suspicion).length() * 5), (int)((tempObject.getY() - 38)));
					}
					
					
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public Rectangle sideBounds() {
		if(facing == -1) {
			return 	new Rectangle((int)x - (width * 3), (int)y, width, height);

		}else {
			return new Rectangle((int)x + (width * 4), (int)y, width, height);
		}
	}

	public void interact() {

		showSuspicion = true;
		
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
		
		for(int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			if(tempObject.getId() == ID.player) {
				if(this.sideBounds().intersects(tempObject.getBounds())) {
					if(Player.activity) {
						tempObject.setSuspicion(tempObject.getSuspicion() + 20);
						Player.activity = false;
					}
				}
			}
			if(focus == null) {
				if(tempObject.getId() == ID.player || tempObject.getId() == ID.NPCNo || tempObject.getId() == ID.NPCYes) {
					if(this.sideBounds().intersects(tempObject.getBounds())) {
						if(tempObject.getSuspicion() >= 50) {
							if(!blind) {
								focus = tempObject;
							}
						}
					}
				}
			}
		}
		return check;
		
	}
	
	public void attack(GameObject target) {
		
		if(Math.abs(target.x - this.x) <= 400) {
			if(target.x < this.x) {
				facing = -1;
			}else {
				facing = 1;
			}
			
			x += facing * speed;
			
			if(Math.abs(this.getY() - target.getY()) > 50) {
				focus = null;
			}
			
			if(this.getBounds().intersects(target.getBounds())) {
				target.die();
				focus = null;
			}
			
		}else {
			focus = null;
		}
		
	}
	
	public void die()
	{
		
	}
	
	
}
