package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import utils.Collision;

public class Arrow extends Player {

	public Arrow(GamePanel a_gp, KeyHandler a_keyH, int x, int y) {
		super(a_gp, a_keyH);
		p_x_arrow = x;
		p_y_arrow = y;
		this.m_width = 30;
		this.m_height = 30;
	}

	int p_x_arrow;
	int p_y_arrow;
	int arrow_speed = 20;
	int x_direction = 0;
	int y_direction = 0;
	boolean killArrow = false;
	public BufferedImage m_idleImagea1;
	public BufferedImage m_idleImagea2;
	public BufferedImage m_idleImagea3;
	public BufferedImage m_idleImagea4;

	
	public void updateArrow() {
		if (!killArrow) {
			this.m_x = p_x_arrow;
			this.m_y = p_y_arrow;
			deplacementArrow(m_keyH);
			getArrowImage();
		}

	}

	public void deplacementArrow(KeyHandler k) {
		for (Entity e : m_gp.listeEntity) {
			if (!Collision.collisionObstacles(m_gp, this) && !Collision.collisionEntity(this, e)) {
				if (x_direction == -1 && y_direction == 0) {
					p_x_arrow -= arrow_speed;
				} else if (x_direction == 0 && y_direction == 1) {
					p_y_arrow += arrow_speed;
				} else if (x_direction == 1 && y_direction == 0) {
					p_x_arrow += arrow_speed;
				} else if (x_direction == 0 && y_direction == -1) {
					p_y_arrow -= arrow_speed;
				}
			} else if (e.getClass().getName().equals("entity.Spider") && Collision.collisionEntity(this, e)) {
				m_gp.listeEntity.remove(e);
				killArrow = true;
				break;
			}
			else if (Collision.collisionObstacles(m_gp, this) || Collision.collisionEntity(this, e) || this.m_x < 0 || this.m_x > m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE/2 || this.m_y < 0 || this.m_y > m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE/2) {
				killArrow = true;
				break;
			}
		}
	}

	public void getArrowImage() {
		// gestion des expections
		try {
			if (x_direction == 1) {
				m_idleImagea1 = ImageIO.read(getClass().getResource("/fleche/right.png"));
			} else if (x_direction == -1) {
				m_idleImagea1 = ImageIO.read(getClass().getResource("/fleche/left.png"));
			} else if (y_direction == 1) {
				m_idleImagea1 = ImageIO.read(getClass().getResource("/fleche/up.png"));
			} else if (y_direction == -1) {
				m_idleImagea1 = ImageIO.read(getClass().getResource("/fleche/down.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawArrow(Graphics2D a_g2) {
		BufferedImage l_imagea1 = m_idleImagea1;
		a_g2.drawImage(l_imagea1, this.p_x_arrow, this.p_y_arrow, 25, 25, null);
	}

}
