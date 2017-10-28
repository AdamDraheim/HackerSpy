package hacker.game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	public Window(String name, int width, int height, Game game) {
		
		game.setPreferredSize(new Dimension(width, height));
		game.setMaximumSize(new Dimension(width, height));
		game.setMinimumSize(new Dimension(width, height));

		
		JFrame frame = new JFrame(name);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();

		
	}
	
}
