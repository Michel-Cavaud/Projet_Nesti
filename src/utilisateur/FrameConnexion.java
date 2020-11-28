package utilisateur;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class FrameConnexion {

	private JFrame frmConnexion;
	Image img = null;
	private JTextField pseudo;
	private JPasswordField mdp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameConnexion window = new FrameConnexion();
					window.frmConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameConnexion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConnexion = new JFrame();
		frmConnexion.setTitle("Connexion");
		frmConnexion.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FrameConnexion.class.getResource("/utilisateur/images/user.png")));
		frmConnexion.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// frame.setResizable(false);
		frmConnexion.setBounds(100, 100, 380, 597);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnexion.getContentPane().setLayout(null);
		frmConnexion.setUndecorated(true);
		frmConnexion.setLocationRelativeTo(null);
		
		

		JPanel panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 381, 597);
		panel_principal.setBackground(new Color(194, 194, 194));
		frmConnexion.getContentPane().add(panel_principal);

		JLabel lblNewLabel = new JLabel("NESTI");
		lblNewLabel.setBounds(73, 10, 219, 40);
		panel_principal.add(lblNewLabel);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNewLabel.setForeground(new Color(98, 129, 159));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		
		JLabel reduit = new JLabel("–");
		reduit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reduit.setHorizontalAlignment(SwingConstants.CENTER);
		reduit.setBorder(null);
		reduit.setBackground(new Color(240, 240, 240));
		reduit.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
		reduit.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				frmConnexion.setState(Frame.ICONIFIED);
			}
		});
		reduit.setBounds(287, 11, 43, 31);
		panel_principal.add(reduit);

		JButton sortir = new JButton("");
		sortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConnexion.dispose();
			}
		});
		sortir.setBorder(null);
		sortir.setRequestFocusEnabled(false);
		sortir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sortir.setMargin(new Insets(0, 0, 0, 0));
		sortir.setBounds(340, 11, 31, 31);
		sortir.setBackground(new Color(194, 194, 194));
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/sortir2.png"));
			sortir.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_principal.setLayout(null);
		panel_principal.add(sortir);

		JPanel panel_connexion = new JPanel();
		panel_connexion.setBackground(new Color(194, 194, 194));
		panel_connexion.setBorder(null);
		panel_connexion.setBounds(0, 53, 380, 544);
		panel_principal.add(panel_connexion);
		panel_connexion.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("CONNEXION");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(98, 129, 159));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 23, 380, 40);
		panel_connexion.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Pseudo ou Email");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblNewLabel_2.setForeground(new Color(98, 129, 159));
		lblNewLabel_2.setBounds(44, 88, 216, 32);
		panel_connexion.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(44, 130, 30, 30);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/user.png"));
			lblNewLabel_3.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_connexion.add(lblNewLabel_3);

		pseudo = new JTextField();
		pseudo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		pseudo.setBackground(new Color(240, 240, 240));
		pseudo.setBorder(null);
		pseudo.setBounds(92, 130, 230, 30);
		panel_connexion.add(pseudo);
		pseudo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setBounds(80, 126, 250, 38);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/input.png"));
			lblNewLabel_4.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_connexion.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Mot de passe");
		lblNewLabel_5.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblNewLabel_5.setForeground(new Color(98, 129, 159));
		lblNewLabel_5.setBounds(44, 188, 162, 32);
		panel_connexion.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel();
		lblNewLabel_6.setBounds(44, 230, 30, 30);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/mdp.png"));
			lblNewLabel_6.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_connexion.add(lblNewLabel_6);

		mdp = new JPasswordField();
		mdp.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		mdp.setBackground(new Color(240, 240, 240));
		mdp.setBorder(null);
		mdp.setBounds(92, 230, 230, 30);
		panel_connexion.add(mdp);
		mdp.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel();
		lblNewLabel_7.setBounds(80, 226, 250, 38);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/input.png"));
			lblNewLabel_7.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_connexion.add(lblNewLabel_7);

		JLabel message = new JLabel("");
		message.setVisible(false);
		message.setInheritsPopupMenu(false);
		message.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		message.setBounds(44, 287, 286, 32);
		panel_connexion.add(message);

		JLabel lblNewLabel_8 = new JLabel("CONNEXION");
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setBounds(0, 349, 380, 48);
		lblNewLabel_8.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				frmConnexion.dispose();
			}
		});
		panel_connexion.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel();
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBorder(null);
		lblNewLabel_9.setBounds(0, 349, 380, 48);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/bouton.png"));
			lblNewLabel_9.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel_9.setBackground(new Color(194, 194, 194));
		panel_connexion.add(lblNewLabel_9);

		JLabel creerCompte = new JLabel("Créer votre compte");
		creerCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creerCompte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		creerCompte.setHorizontalAlignment(SwingConstants.CENTER);
		creerCompte.setBounds(0, 443, 380, 32);
		creerCompte.setForeground(new Color(98, 129, 159));
		panel_connexion.add(creerCompte);

		

	}
}
