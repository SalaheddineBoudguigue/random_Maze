package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestionnaire d'�v�nements (touche clavier)
 *
 */
public class KeyHandler implements KeyListener {

	public int keyP;

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyP = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyP = 0;
	}

}
