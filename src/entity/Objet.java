package entity;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Objet extends Entity{
	
	public Objet(GamePanel a_gp,int x, int y, BufferedImage idleImage) {
		m_gp=a_gp;
		m_x = x;
		m_y = y;
		m_idleImage = idleImage;
		this.m_height = 30;
		this.m_width = 30;
		
	}
	

}
