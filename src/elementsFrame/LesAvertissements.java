package elementsFrame;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Cette class construit la zone de texte pour indiquer les donnée obligatoire
 * @author CAVAUD
 *
 */
public class LesAvertissements extends JLabel{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Le contructeur prend en paramètre la possition du texte
	 * @param pos
	 */
	public LesAvertissements(int pos) {
		this.setText("* données obligatoires");
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setInheritsPopupMenu(false);
		this.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		this.setBounds(0, pos, 280, 32);
	}

}
