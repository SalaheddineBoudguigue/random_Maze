package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import map.Labyrinthe;
import utils.Collision;

public class Spider extends Enemy {

	private int x_direction; // -1 ; 0 ; 1
	private int y_direction; // -1 ; 0 ; 1

	public Spider(GamePanel a_gp) {
		super(a_gp, 0, 0, 2, 1, 3);
		Random rand = new Random();
		int directionRandom = rand.nextInt(4);

		if (directionRandom == 0) {
			this.x_direction = 0;
			this.y_direction = 1;
		}

		if (directionRandom == 1) {
			this.x_direction = 0;
			this.y_direction = -1;
		}

		if (directionRandom == 2) {
			this.x_direction = 1;
			this.y_direction = 0;
		}

		if (directionRandom == 3) {
			this.x_direction = -1;
			this.y_direction = 0;
		}

		// position
		randomPosition(a_gp);

		m_width = 27;
		m_height = 27;
		this.getSpiderImage();
	}

	public void randomPosition(GamePanel a_gp) {
		Random rand = new Random();
		int randomX = rand.nextInt(a_gp.MAX_SCREEN_COL);
		int randomY = rand.nextInt(a_gp.MAX_SCREE_ROW);

		while(a_gp.m_tileM.m_mapTileNum[randomX][randomY] == Labyrinthe.WALL) {
			randomX = rand.nextInt(a_gp.MAX_SCREEN_COL);
			randomY = rand.nextInt(a_gp.MAX_SCREE_ROW);
		}
		
		this.m_x = randomX*a_gp.TILE_SIZE;
		this.m_y = randomY*a_gp.TILE_SIZE;
	}

	public void update() {
		// L'araignée change de direction a chaque collision
		if (Collision.collisionObstacles(m_gp, this) || this.m_x < 0 || this.m_x > m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE/2 || this.m_y < 0 || this.m_y > m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE/2) {
			x_direction = -x_direction;
			y_direction = -y_direction;
		}

		if (x_direction == -1) {
			this.goLeft();
		}
		if (x_direction == 1) {
			this.goRight();
		}
		if (y_direction == -1) {
			this.goDown();
		}
		if (y_direction == 1) {
			this.goUp();
		}

	}

	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getSpiderImage() {
		// gestion des expections
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/enemy/spider.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImage;
		a_g2.drawImage(l_image, m_x, m_y, m_width, m_height, null);
	}
}
