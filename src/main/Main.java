package main;

import javax.swing.JFrame;

/**
 * 
 * Classe principale du jeu
 *
 */
public class Main {

	public static void main(String[] args) {

		//Fen�tre de lancement du jeu
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("ESIR1 - Projet Prog");
		
		//Ajout du panel du jeu et d�marrage du thread principal
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.startGameThread();	

	}

}
