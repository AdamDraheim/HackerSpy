package hacker.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import hacker.anim.BufferedImageLoader;
import hacker.block.Block;
import hacker.block.BlockID;
import hacker.block.Floor;
import hacker.object.Basic;
import hacker.object.GameObject;
import hacker.object.Guard;
import hacker.object.ID;
import hacker.object.Lights;
import hacker.object.Player;
public class Handler {

	BufferedImageLoader loader = new BufferedImageLoader();
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public static int distanceToGoal = 0;
	
	BufferedImage background;
	
	public Handler(Game game) {
		try {
			background = loader.loadImage("/tex/Background.png");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.tick();
		}
		
		addNPC();

	}
	
	public void render(Graphics g) {
		
		g.drawImage(background, 0, 0, 1600, 900, null);
	
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.render(g);
		}
		for(int i = 0; i < blocks.size(); i++) {
			Block tempBlock = blocks.get(i);

			tempBlock.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		objects.remove(object);
	}
	
	public void addBlock(Block block) {
		blocks.add(block);
	}
	public void removeBlock(Block block) {
		blocks.remove(block);
	}
	
	public void clear() {
		objects.clear();
		blocks.clear();
	}
	
	public void addNPC() {
		
		if(distanceToGoal >= 100) {
			
			distanceToGoal -= 100;
			int location = (int)(Math.random() * 3);
			
			if(location == 0) {
				addObject(new Guard(300, 800, 64, 64, ID.GuardNo, 0, this, false));
			}else if(location == 1) {
				addObject(new Guard(300, 544, 64, 64, ID.GuardNo, 0, this, false));
			}else if(location == 2) {
				addObject(new Guard(300, 288, 64, 64, ID.GuardNo, 0, this, false));
			}
			
		}
		
	}
	
	public void load() {
		Player.money = 0;
		addObject(new Player(200, 800, 64, 64, ID.player, 0, this, false));
		addObject(new Basic(540, 800, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(620, 800, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(680, 800, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(720, 800, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(680, 800, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(620, 800, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Guard(500, 800, 64, 64, ID.GuardNo, 0, this, false));
		addObject(new Lights(1500, 800, 64, 64, ID.lightNo, 0, this, 3, false));

		
		addObject(new Basic(540, 544, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(620, 544, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(680, 544, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(120, 544, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(680, 544, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(120, 544, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Guard(300, 544, 64, 64, ID.GuardNo, 0, this, false));
		addObject(new Lights(1500, 544, 64, 64, ID.lightNo, 0, this, 2, false));


		addObject(new Basic(540, 288, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(620, 288, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(680, 288, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(120, 288, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(680, 288, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Basic(120, 288, 64, 64, ID.NPCNo, 0, this, false));
		addObject(new Guard(300, 288, 64, 64, ID.GuardNo, 0, this, false));
		addObject(new Lights(1500, 288, 64, 64, ID.lightNo, 0, this, 1, false));



		Player.isJumping = true;
		for(int i = 0; i < 25; i++) {
			addBlock(new Floor(i * 64, 864, 64, 64, BlockID.floor));
		}
		for(int i = 0; i < 14; i++) {
			addBlock(new Floor(-64, i * 64, 64, 64, BlockID.wall));
			addBlock(new Floor(64 * 25, i * 64, 64, 64, BlockID.wall));

		}
		
		for(int i = 0; i < 5; i++) {
			addBlock(new Floor(i * 64, 608, 64, 64, BlockID.floor));
			addBlock(new Floor((64 * 25) - ( i * 64), 608, 64, 64, BlockID.floor));
			
			addBlock(new Floor(i * 64, 352, 64, 64, BlockID.floor));
			addBlock(new Floor((64 * 25) - ( i * 64), 352, 64, 64, BlockID.floor));

		}
		
		for(int i = 0; i < 16; i++) {
			addBlock(new Floor((5 + i) * 64, 608, 64, 64, BlockID.platform));
			addBlock(new Floor((5 + i) * 64,352, 64, 64, BlockID.platform));

		}
	}
	
}
