package entity;

import main.GamePanel;

public abstract class EntityMovable extends Entity{

	public int m_speed;
	public int m_pv;
	public GamePanel m_gp;
	public int m_damage;
	
	/**
	 * Deplacement vers le haut
	 */
	public void goUp(){
		m_y -= m_speed;
	}
	
	/**
	 * Deplacement vers le bas
	 */
	public void goDown() {
		m_y += m_speed;
	}
	
	/**
	 * Deplacement vers la droite
	 */
	public void goRight() {
		m_x += m_speed;
	}
	
	/**
	 * Deplacement vers la gauche
	 */
	public void goLeft() {
		m_x -= m_speed;
	}
}
