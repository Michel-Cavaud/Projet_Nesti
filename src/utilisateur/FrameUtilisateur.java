package utilisateur;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;

public class FrameUtilisateur {

	private JFrame frame;
	Image img = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUtilisateur window = new FrameUtilisateur();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameUtilisateur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.setResizable(false);
		frame.setBounds(100, 100, 652, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		
		JButton sortir = new JButton("");
		sortir.setBorder(null);
		sortir.setRequestFocusEnabled(false);
		sortir.setSize(new Dimension(30, 30));
		sortir.setPreferredSize(new Dimension(89, 30));
		sortir.setMinimumSize(new Dimension(89, 30));
		sortir.setMaximumSize(new Dimension(89, 30));
		sortir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sortir.setMargin(new Insets(0, 0, 0, 0));
		sortir.setAlignmentY(0.0f);
		sortir.setBounds(601, 10, 30, 30);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/sortir.png"));
			sortir.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		frame.getContentPane().add(sortir);
		
		
		

	}
}
