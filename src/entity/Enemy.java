package entity;

import main.GamePanel;

public abstract class Enemy extends EntityMovable{
	
	/**
	 * Constructeur
	 */
	public Enemy(GamePanel a_gp, int p_x, int p_y, int speed, int damage, int pv) {
		m_gp = a_gp;
		m_x = p_x;
		m_y = p_x;
		m_speed = speed;
		m_damage = damage;
		m_pv = pv;
	}
}
