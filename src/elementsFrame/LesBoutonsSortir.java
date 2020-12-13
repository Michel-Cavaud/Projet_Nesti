package elementsFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Construction des bouton de fermeture des fenêtres
 * @author CAVAUD
 *
 */
public class LesBoutonsSortir extends JLabel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Le constructeur avec la position du bouton et la fenêtre a fermer
	 * @param frmCreerCompte
	 * @param pos
	 */
	public LesBoutonsSortir(JFrame frmCreerCompte, int pos) {
		this.setBorder(null);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setBounds(pos, 11, 31, 31);
		this.setBackground(new Color(194, 194, 194));
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("../images/sortir2.png"));
			this.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frmCreerCompte.dispose();
			}
		});
	}

}
