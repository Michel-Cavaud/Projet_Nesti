package elementsFrame;

import java.awt.Color;

import javax.swing.JPanel;
/**
 * Class dui construit les panel de chaque fenêtre
 * @author CAVAUD
 *
 */
public class LesPanels extends JPanel {

	private static final long serialVersionUID = 1L;

	public LesPanels(int pos1, int pos2, int pos3, int pos4) {
		this.setBounds(pos1, pos2, pos3, pos4);
		this.setBorder(null);
		this.setLayout(null);
		this.setBackground(new Color(194, 194, 194));
	}
}
