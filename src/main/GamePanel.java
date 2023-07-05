package main;

import java.awt.Dimension;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Entity;
import entity.Heart;
import entity.Player;
import entity.SpeedBoots;
import entity.Spider;
import map.Labyrinthe;
import tile.Obstacle;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Panel principal du jeu contenant la map principale
 *
 */
public class GamePanel extends JPanel implements Runnable {

	// Param tres de l' cran
	final int ORIGINAL_TILE_SIZE = 16; // une tuile de taille 16x16
	final int SCALE = 3; // chelle utilis e pour agrandir l'affichage
	public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48
	public final int MAX_SCREEN_COL = 16;
	public final int MAX_SCREE_ROW = 12; // ces valeurs donnent une r solution 4:3
	public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
	public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREE_ROW; // 576 pixels

	// FPS : taux de rafraichissement
	int m_FPS;

	// Cr ation des diff rentes instances (Player, KeyHandler, TileManager,
	// GameThread ...)
	KeyHandler m_keyH;
	Thread m_gameThread;
	Player m_player;
	public TileManager m_tileM;
	public List<Obstacle> m_listeObstacleCollisionnables = new ArrayList<>();;
	public List<Entity> listeEntity = new ArrayList<>();;
	public BufferedImage win_screen;
	public BufferedImage loose_screen;

	/**
	 * Initialiser les entity (enemy, objects, ...)
	 */
	public void initialize() {
		// Initialisation de la liste des obstacles collisionnables
		int[][] mapNum = m_tileM.m_mapTileNum;
		for (int i = 0; i < mapNum.length; i++) {
			for (int j = 0; j < mapNum[i].length; j++) {
				if (mapNum[i][j] == 1) {
					Obstacle obstacle = new Obstacle(i * TILE_SIZE, j * TILE_SIZE);
					m_listeObstacleCollisionnables.add(obstacle);
				}
			}
		}
		
		// Création des spiders

		Spider spider1 = new Spider(this);
		Spider spider2 = new Spider(this);
		Spider spider3 = new Spider(this);
		Spider spider4 = new Spider(this);
		listeEntity.add(spider1);
		listeEntity.add(spider2);
		listeEntity.add(spider3);
		listeEntity.add(spider4);
		
		// Création des objets
		
		Random r =new Random();
		int randomX = r.nextInt(MAX_SCREEN_COL);
		int randomY = r.nextInt(MAX_SCREE_ROW);
		Heart h = new Heart(this, randomX*TILE_SIZE, randomY*TILE_SIZE);
		listeEntity.add(h);
		
		Random r1 =new Random();
		int randomX1 = r1.nextInt(MAX_SCREEN_COL);
		int randomY1 = r1.nextInt(MAX_SCREE_ROW);	
		SpeedBoots sb = new SpeedBoots(this, randomX1*TILE_SIZE, randomY1*TILE_SIZE);
		listeEntity.add(sb);
		
		while(m_tileM.m_mapTileNum[randomX][randomY] == Labyrinthe.WALL) {
			randomX = r.nextInt(MAX_SCREEN_COL);
			randomY = r.nextInt(MAX_SCREE_ROW);
			
		}
		h.m_x = randomX*TILE_SIZE;
		h.m_y = randomY*TILE_SIZE;
		
		while(m_tileM.m_mapTileNum[randomX1][randomY1] == Labyrinthe.WALL) {
			randomX1 = r1.nextInt(MAX_SCREEN_COL);
			randomY1 = r1.nextInt(MAX_SCREE_ROW);
		}
		sb.m_x = randomX1*TILE_SIZE;
		sb.m_y = randomY1*TILE_SIZE;
	}

	/**
	 * Constructeur
	 */
	public GamePanel() {
		m_FPS = 60;
		m_keyH = new KeyHandler();
		m_player = new Player(this, m_keyH);
		m_tileM = new TileManager(this);
		this.initialize();

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(m_keyH);
		this.setFocusable(true);
	}

	/**
	 * Lancement du thread principal
	 */
	public void startGameThread() {
		m_gameThread = new Thread(this);
		m_gameThread.start();
	}

	public void run() {

		double drawInterval = 1000000000 / m_FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (m_gameThread != null) { // Tant que le thread du jeu est actif

			// Permet de mettre jour les diff rentes variables du jeu
			this.update();

			// Dessine sur l' cran le personnage et la map avec les nouvelles informations.
			// la m thode "paintComponent" doit obligatoirement tre appel e avec
			// "repaint()"
			this.repaint();

			// Calcule le temps de pause du thread
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mise jour des donn es des entit s
	 */
	public void update() {
		m_player.update();
		for (Entity e : listeEntity) {
			e.update();
		}
	}

	/**
	 * Affichage des l ments
	 */
	public void paintComponent(Graphics g) {
		
		try {
			win_screen = ImageIO.read(getClass().getResource("/loose_win_screens/win_screen.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			loose_screen = ImageIO.read(getClass().getResource("/loose_win_screens/game_over.jpeg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// Si le joueur a visité 2 salles il a gagné
		if(m_player.win) {
			g2.drawImage(win_screen, 0, 0, this.SCREEN_WIDTH, this.SCREEN_HEIGHT, null);
		}
		else if(m_player.loose) {
			g2.drawImage(loose_screen, 0, 0, this.SCREEN_WIDTH, this.SCREEN_HEIGHT, null);
		}
		else {
			m_tileM.draw(g2);
			m_player.draw(g2);
			for (Entity e : listeEntity) {
				e.draw(g2);
			}
		}
		
		g2.dispose();
	}

	/**
	 * Reset la salle lorsque l'on change de salle
	 */
	public void resetSalle() {
		listeEntity.clear();
		m_listeObstacleCollisionnables.clear();
		this.initialize();
	}

}
