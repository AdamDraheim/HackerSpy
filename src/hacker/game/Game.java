package hacker.game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import Input.KeyInput;
import hacker.anim.Texture;
import hacker.object.Pause;
import hacker.states.Credits;
import hacker.states.GameOver;
import hacker.states.Instructions;
import hacker.states.Menu;


public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread;
	public static int width = 1600, height = 900;
	public boolean running = false;
	public static double deltaTime = 0;
	Handler handler;
	HUD hud;
	Foreground fg;
	Menu menu;
	Credits credits;
	GameOver over;
	Instructions instruct;
	Pause pause;
		
	public static Texture tex;
	
	public static enum STATE{
		
		menu(),
		over(),
		game(),
		credits(),
		instructions()
		
	};
	
	public static STATE gameState;
	
	public Game() {
		
	}
	
	/**
	 * 
	 * Starts running
	 */
	public synchronized void start() {
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * 
	 * stops running
	 * 
	 */
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Occurs at start-up
	 */
	private void init() {
		
		gameState = STATE.menu;
		tex = new Texture();
		handler = new Handler(this);
		hud = new HUD(handler);
		fg = new Foreground();
		menu = new Menu();
		credits = new Credits();
		over = new GameOver();
		instruct = new Instructions();
		pause = new Pause();

		this.addKeyListener(new KeyInput(handler));
		
	}
	
	/**
	 * 
	 * handles tick and render methods
	 * 
	 */
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				
				deltaTime = 1.0 / updates;
				
				timer += 1000;
				System.out.println("FPS: " + frames + " Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	/**
	 * 
	 * what occurs in game
	 */
	public void tick() {
		
		if(gameState == STATE.game) {
			if(!KeyInput.paused) {
				handler.tick();
				hud.tick();
			}
		}
		
	}
	
	/**
	 * 
	 * Renders onto the screen
	 * @param g
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		
		
		if(gameState == STATE.game) {
			handler.render(g);
			fg.render(g);
			hud.render(g);
			if(KeyInput.paused) {
				pause.render(g);
			}
			
		}else if(gameState == STATE.menu) {
			menu.render(g);
		}else if(gameState == STATE.credits) {
			credits.render(g);
		}else if(gameState == STATE.over) {
			over.render(g);
		}else if(gameState == STATE.instructions) {
			instruct.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static Texture getInstance() {
		return tex;
	}
	
	public static void main(String[] args) {

		new Window("Hacker", width - 12, height - 12, new Game());
		
	}

}
