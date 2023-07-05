package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

/**
 * Entit� de base du jeu
 *
 */
public abstract class Entity {
	public GamePanel m_gp;
	public int m_x, m_y;				//position sur la map
	public BufferedImage m_idleImage;	//Une image de l'entit�
	public int m_width = 39;
	public int m_height = 39;
	
	public abstract void update();
	public abstract void draw(Graphics2D a_g2);
}
