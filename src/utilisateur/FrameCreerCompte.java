package utilisateur;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class FrameCreerCompte {

	
	private JFrame frmCreerCompte;
	JPanel panel_inscription;
	JPanel panel_principal;
	JLabel message;
	private int posX = 0;  
    private int posY = 0;
	private HashMap<String, JTextField> listInput = new HashMap<String, JTextField>();
	private String[] input = {"email", "pseudo", "mdp", "mdp1", "nom", "prenom", "ville"};
	private String[] texteInput = {"Votre email*", "Votre pseudo*", "Mot de passe*", "Confirmer MDP*", "Votre Nom", "Votre prénom", "Votre ville"};
	private String[] imgInput = {"email", "user", "mdp", "mdp", "user", "user", "ville"};
	private boolean[] password = {false, false, true, true, false, false, false};
	private int[] pos = {30, 100, 170, 240, 310, 380, 450};
	
	Utilisateur user;
	
	Image img = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCreerCompte window = new FrameCreerCompte();
					window.frmCreerCompte.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameCreerCompte() {
		initialize();
		titrePrincipal();
		iconifie();
		sortir();
		titre();
		creerInput();
		avertissement();
		message();
		boutonSubmit();
		lien();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreerCompte = new JFrame();
		frmCreerCompte.setTitle("Connexion");
		frmCreerCompte.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameConnexion.class.getResource("/utilisateur/images/user.png")));
		//frmCreerCompte.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		//frmCreerCompte.setResizable(false);
		frmCreerCompte.setSize(380, 735);
		frmCreerCompte.getContentPane().setLayout(null);
		frmCreerCompte.setUndecorated(true);
		frmCreerCompte.setLocationRelativeTo(null);
		
		frmCreerCompte.addMouseListener(new MouseAdapter() {
            @Override
            //on recupere les coordonnées de la souris
            public void mousePressed(MouseEvent e) {
                posX = e.getX();    //Position X de la souris au clic
                posY = e.getY();    //Position Y de la souris au clic
            }
        });
         
		frmCreerCompte.addMouseMotionListener(new MouseMotionAdapter() {
            // A chaque deplacement on recalcul le positionnement de la fenetre
            @Override
            public void mouseDragged(MouseEvent e) {
                int depX = e.getX() + posX;
                int depY = e.getY() + posY;
                frmCreerCompte.setLocation(depX, depY);
            }
        });
		
		panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 380, 735);
		panel_principal.setBackground(new Color(194, 194, 194));
		frmCreerCompte.getContentPane().add(panel_principal);
	}
	
	private void sortir() {
		JButton sortir = new JButton("");
		sortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCreerCompte.dispose();
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
				frmCreerCompte.setState(Frame.ICONIFIED);
			}
		});
		reduit.setBounds(287, 11, 43, 31);
		panel_principal.add(reduit);
	}
	
	private void titrePrincipal() {
		JLabel lblNewLabel = new JLabel("NESTI");
		lblNewLabel.setBounds(73, 10, 219, 40);
		panel_principal.add(lblNewLabel);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNewLabel.setForeground(new Color(98, 129, 159));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
	}
	
	private void titre() {
		panel_inscription = new JPanel();
		panel_inscription.setBackground(new Color(194, 194, 194));
		panel_inscription.setBorder(null);
		panel_inscription.setBounds(0, 53, 380, 758);
		panel_inscription.setLayout(null);
		panel_principal.add(panel_inscription);
		
		JLabel lblNewLabel_1 = new JLabel("INSCRIPTION");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(98, 129, 159));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 5, 380, 40);
		panel_inscription.add(lblNewLabel_1);	
	}
	
	private void creerInput() {
		for (int i = 0; i < input.length; i++) {
			BlockInput inputObj = new BlockInput(password[i], texteInput[i], imgInput[i], pos[i]);
			panel_inscription.add(inputObj.getBlock_1());
			panel_inscription.add(inputObj.getBlock_2());
			
			if(password[i]) {
				listInput.put(input[i], inputObj.getBlock_3p());
				panel_inscription.add(inputObj.getBlock_3p());
			}else {
				listInput.put(input[i], inputObj.getBlock_3());
				panel_inscription.add(inputObj.getBlock_3());
			}
			
			panel_inscription.add(inputObj.getBlock_4());
		}

	}
	
	private void avertissement() {
		JLabel avertissement = new JLabel("* données obligatoires");
		//message.setVisible(false);
		avertissement.setHorizontalAlignment(SwingConstants.CENTER);
		avertissement.setInheritsPopupMenu(false);
		avertissement.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		avertissement.setBounds(0, 530, 380, 32);
		panel_inscription.add(avertissement);
	}
	
	private void message() {
		message = new JLabel("");
		message.setVisible(false);
		message.setBorder(new LineBorder(Color.RED));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setInheritsPopupMenu(false);
		message.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		message.setBounds(0, 557, 380, 32);
		panel_inscription.add(message);
	}
	
	private void boutonSubmit() {
		JLabel lblNewLabel_8 = new JLabel("INSCRIPTION");
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setBounds(0, 600, 380, 48);
		lblNewLabel_8.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				//efface toutes les bordures
				for(int i = 0; i < input.length; i++) {
					listInput.get(input[i]).setBorder(null);
				}
				//cahe les messages
				message.setVisible(false);
				verifierSaisie();
			}
		});
		panel_inscription.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel();
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBorder(null);
		lblNewLabel_9.setBounds(0, 600, 380, 48);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/bouton.png"));
			lblNewLabel_9.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel_9.setBackground(new Color(194, 194, 194));
		panel_inscription.add(lblNewLabel_9);
	}
	
	private void lien() {
		JLabel creerCompte = new JLabel("Vous avez déjà un compte");
		creerCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creerCompte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		creerCompte.setHorizontalAlignment(SwingConstants.CENTER);
		creerCompte.setBounds(0, 650, 380, 32);
		creerCompte.setForeground(new Color(98, 129, 159));
		creerCompte.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frmCreerCompte.dispose();
				new FrameConnexion().getFrmConnexion().setVisible(true);  
			}
		});
		panel_inscription.add(creerCompte);
	}
	
	 
	private void verifierSaisie() {
		if(!listInput.get("mdp").getText().equals(listInput.get("mdp1").getText())){
			listInput.get("mdp").setBorder(new LineBorder(Color.RED));
			listInput.get("mdp1").setBorder(new LineBorder(Color.RED));
			messageTexte("Les mots de passe sont différents !");
		}else {
			user = new Utilisateur();
			HashMap<String, String> listDonneeUser = new HashMap<String, String>();
			
			listDonneeUser.put("pseudo", listInput.get("pseudo").getText());
			listDonneeUser.put("email", listInput.get("email").getText());
			listDonneeUser.put("mdp", listInput.get("mdp").getText());
			listDonneeUser.put("nom", listInput.get("nom").getText());
			listDonneeUser.put("prenom", listInput.get("prenom").getText());
			listDonneeUser.put("ville", listInput.get("ville").getText());
			
			try {
				user.ajouterModifierUtilisateur(listDonneeUser);
			} catch (Exception e) {	
				for (Map.Entry<String, Integer> mapentry : user.getListErreur().entrySet()) {
					listInput.get(mapentry.getKey()).setBorder(new LineBorder(Color.RED));
					
					//System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
				}
				messageTexte("Erreurs dans la saisie des données !");
			}
			
			if(user.getListErreur().isEmpty()) {
				try {
					user.creerUpdateUserSql();
				} catch (Exception e) {
					if(user.getListErreur().containsKey("doublon")) {
						messageTexte("Le compte existe déjà !");
					}else {
						messageTexte("Erreur à la création du compte !");
					}
				}
			}
		}
	}
	
	private void messageTexte(String texte) {
		message.setText(texte);
		message.setVisible(true);
	}

	
	/**
	 * @return the frmCreerCompte
	 */
	public JFrame getFrmCreerCompte() {
		return frmCreerCompte;
	}

	/**
	 * @param frmCreerCompte the frmCreerCompte to set
	 */
	public void setFrmCreerCompte(JFrame frmCreerCompte) {
		this.frmCreerCompte = frmCreerCompte;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}



}
