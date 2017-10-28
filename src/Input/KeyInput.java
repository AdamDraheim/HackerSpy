package Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import hacker.game.Game;
import hacker.game.Handler;
import hacker.game.Game.STATE;
import hacker.object.GameObject;
import hacker.object.ID;
import hacker.object.Pause;
import hacker.object.Player;
import hacker.states.GameOver;
import hacker.states.Menu;

public class KeyInput extends KeyAdapter {

	Handler handler;
	public static boolean paused = false;

	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (Game.gameState == STATE.game) {

			if (key == KeyEvent.VK_ESCAPE) {
				paused = !paused;
			}
			if (paused) {
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					Pause.selected--;
					if (Pause.selected == -1) {
						Pause.selected = 1;
					}
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					Pause.selected++;
					if (Pause.selected == 2) {
						Pause.selected = 0;
					}
				}
				if (key == KeyEvent.VK_ENTER) {
					if (Pause.selected == 0) {
						paused = !paused;
					}
				}
			}

			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject tempObject = handler.objects.get(i);
				if (tempObject.getId() == ID.player) {

					if (key == KeyEvent.VK_D) {
						Player.right = true;
					}
					if (key == KeyEvent.VK_A) {
						Player.left = true;
					}

					if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W) {
						if (!Player.isJumping) {
							Player.isJumping = true;
							Player.velY = -7;
						}
					}

					if (key == KeyEvent.VK_S) {
						Player.platformSolid = false;
						Player.pltfrmTimer = 1;
						Player.isJumping = true;
					}

					for (int j = 0; j < handler.objects.size(); j++) {
						GameObject tempObject2 = handler.objects.get(j);
						if (tempObject2.getId() == ID.NPCNo || tempObject2.getId() == ID.GuardNo) {

							if (tempObject.getBounds().intersects(tempObject2.getBounds())) {
								if (key == KeyEvent.VK_E) {
									if (tempObject2.getId() == ID.NPCNo) {
										tempObject2.setId(ID.NPCYes);
									}
									if (tempObject2.getId() == ID.GuardNo) {
										tempObject2.setId(ID.GuardYes);
									}

									tempObject2.interact();

									Player.activity = true;

									tempObject.setSuspicion(tempObject.getSuspicion() + 5);

									for (int ii = 0; ii < handler.objects.size(); ii++) {
										GameObject tempObject3 = handler.objects.get(ii);
										if (tempObject3.getId() == ID.NPCNo || tempObject3.getId() == ID.NPCYes) {
											if (Math.abs(tempObject.getX() - tempObject3.getX()) < 300) {

												tempObject3.setSuspicion(tempObject3.getSuspicion() + 5);

											}
										}
									}
								}
							}
						} else if (tempObject2.getId() == ID.lightNo) {
							if (tempObject.getBounds().intersects(tempObject2.getBounds())) {
								if (key == KeyEvent.VK_E) {
									tempObject2.setId(ID.lightYes);
									tempObject2.interact();
								}
							}
						}
					}
				}
			}
		}
		if (Game.gameState == STATE.menu) {

			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				Menu.selected--;
				if (Menu.selected == -1) {
					Menu.selected = 2;
				}
			}
			if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				Menu.selected++;
				if (Menu.selected == 3) {
					Menu.selected = 0;
				}
			}
			if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
				if (Menu.selected == 0) {
					handler.load();
					Game.gameState = STATE.game;
				} else if (Menu.selected == 1) {
					Game.gameState = STATE.credits;
				} else if (Menu.selected == 2) {
					Game.gameState = STATE.instructions;
				}
			}

		}

		if (Game.gameState == STATE.credits) {
			if (key == KeyEvent.VK_ESCAPE) {
				Game.gameState = STATE.menu;
			}
		}
		if (Game.gameState == STATE.instructions) {
			if (key == KeyEvent.VK_ESCAPE) {
				Game.gameState = STATE.menu;
			}
		}

		if (Game.gameState == STATE.over) {

			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				GameOver.selected--;
				if (GameOver.selected == -1) {
					GameOver.selected = 1;
				}
			}
			if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				GameOver.selected++;
				if (GameOver.selected == 2) {
					GameOver.selected = 0;
				}
			}
			if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
				if (GameOver.selected == 0) {
					handler.load();
					Game.gameState = STATE.game;
				} else if (GameOver.selected == 1) {
					Game.gameState = STATE.menu;
				}

			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (Game.gameState == STATE.game) {

			if (key == KeyEvent.VK_D) {
				Player.right = false;
			}
			if (key == KeyEvent.VK_A) {
				Player.left = false;
			}
		}

	}

	public void keyTyped(KeyEvent e) {

	}

}
