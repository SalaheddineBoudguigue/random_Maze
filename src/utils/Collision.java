package utils;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import tile.Obstacle;

public class Collision {
	
	public static Rectangle getBounds(Entity e) {
        return new Rectangle(e.m_x, e.m_y, e.m_width, e.m_height);
    }
	
	public static Rectangle getBounds(Obstacle o) {
        return new Rectangle(o.m_x, o.m_y, o.m_width, o.m_height);
    }
	
	/**
	 * Vérifie si une entity est en collision avec un obstacle
	 * @param e une entity (héros, enemy, ...)
	 * @param o un obstacle
	 * @return si l'entity est en collision avec la tile
	 */
	public static boolean collisionObstacle(Entity e, Obstacle o) {
		Rectangle r_entity = getBounds(e);
		Rectangle r_obstacle = getBounds(o);
		return r_entity.intersects(r_obstacle);
	}
	
	/**
	 * Vérifie si une entity est en collision avec un obstacle parmi tous les obstacles
	 * @param panel le GamePanel
	 * @param e une entity (héros, enemy, ...)
	 * @return si l'entity est en collision avec n'importe quel obstacle collisionable
	 */
	public static boolean collisionObstacles(GamePanel panel, Entity e) {
		for(Obstacle o : panel.m_listeObstacleCollisionnables){
			if(collisionObstacle(e,o)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Vérifie si une entity est en collision avec une autre entity
	 * @param e1 une entity (héros, enemy, ...)
	 * @param e2 une entity (héros, enemy, ...)
	 * @return si l'entity est en collision avec l'autre entity
	 */
	public static boolean collisionEntity(Entity e1, Entity e2) {
		Rectangle r_entity_1 = getBounds(e1);
		Rectangle r_entity_2 = getBounds(e2);
		return r_entity_1.intersects(r_entity_2);
	}
}
