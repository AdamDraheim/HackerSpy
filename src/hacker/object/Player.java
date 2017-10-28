package hacker.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import hacker.anim.Animation;
import hacker.anim.Texture;
import hacker.block.Block;
import hacker.block.BlockID;
import hacker.game.Game;
import hacker.game.Handler;
import hacker.game.Game.STATE;

public class Player extends GameObject{

	public static boolean left = false, right = false;
	public static boolean isJumping;
	public static double velY = 0;
	private int speed = 5;
	Handler handler;
	int facing = 1;
	public static boolean activity = false;
	private double activityTimer = 0.2;
	public static boolean platformSolid = true;
	public static double pltfrmTimer = 0.3;
	
	public static int PlayerSuspicion;
	public static int money = 0;
	
	private Animation Aleft, Aright;
	Texture tex = Game.getInstance();
	
	public Player(double x, double y, int width, int height, ID id, int suspicion, Handler handler, boolean blind) {
		super(x, y, width, height, id, suspicion, blind);
		this.handler = handler;
		Aright = new Animation(5, tex.Rorschach[0], tex.Rorschach[2], tex.Rorschach[1], tex.Rorschach[2]);
		Aleft = new Animation(5, tex.Rorschach[3], tex.Rorschach[5], tex.Rorschach[4], tex.Rorschach[5]);
	}

	public void tick() {
		
		Aleft.runAnimation();
		Aright.runAnimation();
		if(suspicion < 0) {
			suspicion = 0;
		}
		if(suspicion > 100) {
			suspicion = 100;
		}
		
		if(!platformSolid) {
			pltfrmTimer -= Game.deltaTime;
			if(pltfrmTimer <= 0) {
				platformSolid = true;
			}
		}
		
		PlayerSuspicion = suspicion;
		move();
		collision();
		
		if(activity) {
			activityTimer -= Game.deltaTime;
			if(activityTimer <= 0) {
				activity = false;
				activityTimer = 2.0;
			}
		}
	}

	public void render(Graphics g) {

		if(facing == -1) {
			if(isJumping) {
				g.drawImage(tex.Rorschach[3], (int)x, (int)y, width, height, null);
			}else {
				if(!left) {
					g.drawImage(tex.Rorschach[5], (int)x, (int)y, width, height, null);
				}else {
					Aleft.drawAnimation(g, (int)x, (int)y, width, height);
				}
			}
		}else if(facing == 1) {
			if(isJumping) {
				g.drawImage(tex.Rorschach[0],(int)x, (int)y, width, height, null);
			}else {
				if(!right) {
					g.drawImage(tex.Rorschach[2],(int)x, (int)y, width, height, null);
				}else {
					Aright.drawAnimation(g, (int)x, (int)y, width, height);
				}
			}
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height * 3 / 4);
	}

	public void interact() {
		
	}
	
	public void move() {
		
		if(left && !right) {
			x -= speed;
			facing = -1;
		}
		if(right && !left) {
			x += speed;
			facing = 1;
		}
		
		if(isJumping) {
			y += velY;
			velY += (Game.deltaTime * (5));
		}
		
		
	}
	
	public Rectangle floorBounds() {
		return new Rectangle((int)x + 5, (int)y + (height * 3 / 4), width - 10, height / 4);
	}
	
	public void collision() {
		
		for(int i = 0; i < handler.blocks.size(); i++) {
			Block tempBlock = handler.blocks.get(i);
			if(tempBlock.getBid() == BlockID.floor) {
				if(this.floorBounds().intersects(tempBlock.getBounds())) {
					isJumping = false;
					velY = 0;
					y -= 1;
				}
				if(this.getBounds().intersects(tempBlock.getBounds())) {
					velY = 0;
					y += 1;
				}
			}
			if(tempBlock.getBid() == BlockID.platform) {
				
				if(this.floorBounds().intersects(tempBlock.getBounds())) {
					if(!platformSolid) {
						
					}else {
						isJumping = false;
						velY = 0;
						y -= 1;
					}
				}
			}
			if(tempBlock.getBid() == BlockID.wall || tempBlock.getBid() == BlockID.floor) {
				if(this.getBounds().intersects(tempBlock.getBounds())) {
					if(left) {
						x += 5;
						left = false;
					}
					if(right) {
						x -= 5;
						right = false;
					}
				}
			}
		}
		
	}
	
	public void die() {
		
		handler.clear();
		Game.gameState = STATE.over;
		
	}

}
