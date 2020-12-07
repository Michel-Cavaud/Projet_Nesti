package utilisateur;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Toolkit;

public class FrameConnexion {

	private JFrame frmConnexion;
	JPanel panel_principal;
	JPanel panel_connexion;
	
	JLabel message;
	HashMap<String, JTextField> listInput = new HashMap<String, JTextField>();
	Image img = null;
	
	private String messageText;
	
	Utilisateur user;

	/**
	 * Create the application.
	 */
	public FrameConnexion() {
		initialize();
		titrePrincipal();
		iconifie();
		sortir();
		titre();
		creerInput();
		message();
		boutonSubmit();
		lien();
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
		frmConnexion.setResizable(false);
		frmConnexion.setBounds(100, 100, 380, 370);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnexion.getContentPane().setLayout(null);
		frmConnexion.setUndecorated(true);
		frmConnexion.setLocationRelativeTo(null);
		
		FrameDragListener frameDragListener = new FrameDragListener(frmConnexion);
		frmConnexion.addMouseListener(frameDragListener);
		frmConnexion.addMouseMotionListener(frameDragListener);
		
		panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 380, 370);
		panel_principal.setBackground(new Color(194, 194, 194));
		frmConnexion.getContentPane().add(panel_principal);
	}
	private void titrePrincipal() {
		panel_principal.setLayout(null);
		JLabel lblNewLabel = new JLabel("NESTI");
		lblNewLabel.setBounds(113, 11, 83, 40);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNewLabel.setForeground(new Color(98, 129, 159));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panel_principal.add(lblNewLabel);
	}	
	
	private void iconifie() {
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
		reduit.setBounds(287, 12, 43, 31);
		panel_principal.add(reduit);
	}
	
	private void sortir() {
		LesBoutonsSortir boutonsSortir = new LesBoutonsSortir(frmConnexion, 340);
		panel_principal.add(boutonsSortir);
	}
	
	private void titre() {

		panel_connexion = new JPanel();
		panel_connexion.setBackground(new Color(194, 194, 194));
		panel_connexion.setBorder(null);
		panel_connexion.setBounds(0, 53, 380, 317);
		panel_connexion.setLayout(null);
		panel_principal.add(panel_connexion);
		

		JLabel lblNewLabel_1 = new JLabel("CONNEXION");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(98, 129, 159));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 10, 380, 40);
		panel_connexion.add(lblNewLabel_1);
	}
	
	private void creerInput() {
		BlockInput pseudo = new BlockInput(false, "Pseudo ou Email", "user", 44, 50);
		panel_connexion.add(pseudo.getBlock_1());
		panel_connexion.add(pseudo.getBlock_2());
		panel_connexion.add(pseudo.getBlock_3());
		panel_connexion.add(pseudo.getBlock_4());
		listInput.put("pseudo", pseudo.getBlock_3());
		listInput.get("pseudo").addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
            	listInput.get("pseudo").setBorder(null);
            	listInput.get("mdp").setBorder(null);
            }
            public void focusLost(FocusEvent e)
            {
            }
        });
		
		BlockInput mdp = new BlockInput(true, "Mot de passe", "mdp", 44, 120);
		panel_connexion.add(mdp.getBlock_1());
		panel_connexion.add(mdp.getBlock_2());
		panel_connexion.add(mdp.getBlock_3p());
		panel_connexion.add(mdp.getBlock_4());
		listInput.put("mdp", mdp.getBlock_3p());
		listInput.get("mdp").addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
            	listInput.get("mdp").setBorder(null);
            	listInput.get("pseudo").setBorder(null);
            }
            public void focusLost(FocusEvent e)
            {
            }
        });

	}
	private void message() {
		message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		message.setBounds(0, 200, 380, 32);
		panel_connexion.add(message);
	}
	
	private void boutonSubmit() {
		LesBoutons connexion = new LesBoutons("CONNEXION", 0, 240, 380);
		connexion.getLabelTexte().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (listInput.get("pseudo").getText().equals("")) {
					listInput.get("pseudo").setBorder(new LineBorder(Color.RED));
					message.setText("Il manque les données de connexion !");
				}else if(listInput.get("mdp").getText().equals("")){
					listInput.get("mdp").setBorder(new LineBorder(Color.RED));
					message.setText("Il manque les données de connexion !");
				}else {
					listInput.get("mdp").setBorder(null);
					listInput.get("pseudo").setBorder(null);
					user = new Utilisateur();
					System.out.println("coucou 1");
					try {
						user = user.chercherUser(listInput.get("pseudo").getText(), listInput.get("mdp").getText());	
						if (user != null) {
							System.out.println("coucou 2");
							new FrameVisuCompte(user).getfrmVisuCompte().setVisible(true);
							frmConnexion.dispose();					
						}else {
							System.out.println("coucou 3");
							ImageIcon icon = new ImageIcon(getClass().getResource("./images/ko.png"));
							JOptionPane.showMessageDialog(frmConnexion, "L'utilisateur n'est pas connue", "Erreur de connexion.", JOptionPane.WARNING_MESSAGE, icon);
							message.setText(" ");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel_connexion.add(connexion.getLabelTexte());
		panel_connexion.add(connexion.getLabelImage());
	}
	
	private void lien() {
		LesLiens lien = new LesLiens("Créer votre compte", 285, 380);
		lien.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				new FrameCreerCompte().getFrmCreerCompte().setVisible(true);
				frmConnexion.dispose();
			}
		});
		panel_connexion.add(lien);
		
	}
	
	

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * @return the frmConnexion
	 */
	public JFrame getFrmConnexion() {
		return frmConnexion;
	}

	/**
	 * @param frmConnexion the frmConnexion to set
	 */
	public void setFrmConnexion(JFrame frmConnexion) {
		this.frmConnexion = frmConnexion;
	}
	
}
