package utilisateur;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class LesBoutonsSortir extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LesBoutonsSortir (int pos) {
		this.setBorder(null);
		//this.setRequestFocusEnabled(false);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//this.setMargin(new Insets(0, 0, 0, 0));
		this.setBounds(pos, 11, 31, 31);
		this.setBackground(new Color(194, 194, 194));
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/sortir2.png"));
			this.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
