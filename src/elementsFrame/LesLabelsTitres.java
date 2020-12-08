package elementsFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LesLabelsTitres extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LesLabelsTitres(String texte, int pos1, int pos2, int pos3, int pos4) {
		
		this.setText(texte);
		this.setBounds(pos1, pos2, pos3, pos4);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.setForeground(new Color(98, 129, 159));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
	}

}
