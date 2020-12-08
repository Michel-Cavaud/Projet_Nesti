package elementsFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LesLiens extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LesLiens(String texte, int pos1, int pos2) {
		
		this.setText(texte);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBounds(0, pos1, pos2, 32);
		this.setForeground(new Color(98, 139, 159));
		
	}
	
	

}
