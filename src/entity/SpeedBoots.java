package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class SpeedBoots extends Objet{
	
	private static BufferedImage image_speedboots;
	
	
	public SpeedBoots(GamePanel a_gp, int p_x, int p_y) {
		super(a_gp,p_x,p_y,image_speedboots);
		this.getHeartImage();
		
	}
	 
	
	public void getHeartImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/player/speedboots.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D a_g2) {
		BufferedImage l_image = m_idleImage;
		a_g2.drawImage(l_image, m_x, m_y, m_width, m_height, null);
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
}

