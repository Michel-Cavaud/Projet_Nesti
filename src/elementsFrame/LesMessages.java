package elementsFrame;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
/**
 * Class qui construit les zone de message d'erreur
 * @author CAVAUD
 *
 */
public class LesMessages extends JLabel{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructeur qui prend en paramètre la possition du message
	 * @param pos1
	 * @param pos2
	 */
	public LesMessages(int pos1, int pos2) {
		this.setVisible(false);
		this.setBorder(new LineBorder(Color.RED));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setInheritsPopupMenu(false);
		this.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		this.setBounds(0, pos1, pos2, 32);
	}
}
