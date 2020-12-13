package elementsFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Cette class construit les boutons de validation
 * Chaque bouton est composé d'une image de fond et d'un texte
 * @author CAVAUD
 *
 */
public class LesBoutons  extends JLabel{
	
	private static final long serialVersionUID = 1L;
	JLabel labelTexte;
	JLabel labelImage;
	
	/**
	 * Le constructeur prend en paramètre le texte du bouton, la position et la taille du bouton
	 * @param texte
	 * @param pos1
	 * @param pos2
	 * @param pos3
	 */
	public LesBoutons(String texte, int pos1, int pos2, int pos3){
		
		labelTexte = new JLabel(texte);
		labelTexte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelTexte.setHorizontalAlignment(SwingConstants.CENTER);
		labelTexte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		labelTexte.setForeground(Color.BLACK);
		labelTexte.setBounds(pos1, pos2, pos3, 48);
		setLabelTexte(labelTexte);
		labelImage = new JLabel();
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		labelImage.setBorder(null);
		labelImage.setBounds(pos1, pos2, pos3, 48);
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("../images/bouton.png"));
			labelImage.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		labelImage.setBackground(new Color(194, 194, 194));
		setLabelImage(labelImage);
	}

	/**
	 * @return the labelTexte
	 */
	public JLabel getLabelTexte() {
		return labelTexte;
	}
	/**
	 * @param labelTexte the labelTexte to set
	 */
	public void setLabelTexte(JLabel labelTexte) {
		this.labelTexte = labelTexte;
	}
	/**
	 * @return the labelImage
	 */
	public JLabel getLabelImage() {
		return labelImage;
	}
	/**
	 * @param labelImage the labelImage to set
	 */
	public void setLabelImage(JLabel labelImage) {
		this.labelImage = labelImage;
	}
}
