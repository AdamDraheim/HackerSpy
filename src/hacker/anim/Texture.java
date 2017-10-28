package hacker.anim;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Texture implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Spritesheet ps, gs, rs, misc;
	
	private BufferedImage police_sheet = null;
	private BufferedImage guy_sheet = null;
	private BufferedImage rorschach_sheet = null;
	private BufferedImage misc_sheet = null;

	
	public BufferedImage[] police = new BufferedImage[12];
	public BufferedImage[] guy = new BufferedImage[6];
	public BufferedImage[] Rorschach = new BufferedImage[6];
	public BufferedImage[] msc = new BufferedImage[5];
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			police_sheet = loader.loadImage("/tex/Policeman.png");
			guy_sheet = loader.loadImage("/tex/Guy.png");
			rorschach_sheet = loader.loadImage("/tex/Main.png");
			misc_sheet = loader.loadImage("/tex/Misc.png");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ps = new Spritesheet(police_sheet);
		gs = new Spritesheet(guy_sheet);
		rs = new Spritesheet(rorschach_sheet);
		misc = new Spritesheet(misc_sheet);
		
		getTextures();
		
	}
	
	private void getTextures() {
		
		police[0] = ps.grabImage(1, 1, 32, 32);
		police[1] = ps.grabImage(1, 2, 32, 32);
		police[2] = ps.grabImage(1, 3, 32, 32);
		
		police[3] = ps.grabImage(1, 4, 32, 32);
		police[4] = ps.grabImage(1, 5, 32, 32);
		police[5] = ps.grabImage(1, 6, 32,32);
		
		police[6] = ps.grabImage(2, 1, 32, 32);
		police[7] = ps.grabImage(2, 2, 32, 32);
		police[8] = ps.grabImage(2, 3, 32, 32);
		
		police[9] = ps.grabImage(2, 4, 32, 32);
		police[10] = ps.grabImage(2, 5, 32, 32);
		police[11] = ps.grabImage(2, 6, 32,32);

		guy[0] = gs.grabImage(1, 1, 32, 32);
		guy[1] = gs.grabImage(1, 2, 32, 32);
		guy[2] = gs.grabImage(1, 3, 32, 32);
		
		guy[3] = gs.grabImage(2, 1, 32, 32);
		guy[4] = gs.grabImage(2, 2, 32, 32);
		guy[5] = gs.grabImage(2, 3, 32, 32);
		
		Rorschach[0] = rs.grabImage(1, 1, 32, 32);
		Rorschach[1] = rs.grabImage(1, 2, 32, 32);
		Rorschach[2] = rs.grabImage(1, 3, 32, 32);

		Rorschach[3] = rs.grabImage(2, 1, 32, 32);
		Rorschach[4] = rs.grabImage(2, 2, 32, 32);
		Rorschach[5] = rs.grabImage(2, 3, 32, 32);
		
		msc[0] = misc.grabImage(1, 1, 32, 32);
		msc[1] = misc.grabImage(1, 2, 32, 32);
		msc[2] = misc.grabImage(1, 3, 32, 32);
		msc[3] = misc.grabImage(1, 4, 32, 32);
		msc[4] = misc.grabImage(2, 1, 64, 32);

	}

}
