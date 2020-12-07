package utilisateur;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LesIconifies  extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LesIconifies(JFrame frame, int pos) {
		
		this.setText("–");
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBorder(null);
		this.setBackground(new Color(240, 240, 240));
		this.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		this.setBounds(pos, 11, 43, 31);
	}

}
