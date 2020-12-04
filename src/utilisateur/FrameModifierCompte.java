package utilisateur;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class FrameModifierCompte{

	
	private JFrame frmModifierCompte;
	JPanel panel_inscription;
	JPanel panel_principal;
	JLabel message;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_8;
	JLabel lblNewLabel_10;
	JLabel creerCompte;
	
	private int posX = 0;  
    private int posY = 0;
   
	private HashMap<String, JTextField> listInput = new HashMap<String, JTextField>();
	private String[] input = {"mdpa", "mdp", "mdp1", "pseudo", "nom", "prenom", "ville"};
	private String[] texteInput = {"Mot de passe actuel*", "Nouveau Mot de passe*", "Confirmer MDP*", "Votre pseudo*","Votre Nom", "Votre prénom", "Votre ville"};
	private String[] imgInput = {"mdp", "mdp", "mdp", "user", "user", "user", "ville"};
	private boolean[] password = {true, true, true, false, false, false, false};
	private int[] pos = {40, 110, 180, 40, 110, 180, 250};
	
	Utilisateur user;
	
	Image img = null;

	/**
	 * Create the application.
	 */
	public FrameModifierCompte(Utilisateur user) {
		setUser(user);
		initialize();
		titrePrincipal();
		iconifie();
		sortir();
		titre();
		creerInput();
		avertissement();
		message();
		boutonSubmitMdp();
		boutonSubmitInfo();
		lien();;
		passageEnVisu();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifierCompte = new JFrame();
		frmModifierCompte.setTitle("Modifications");
	
		frmModifierCompte.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameConnexion.class.getResource("/utilisateur/images/user.png")));
		frmModifierCompte.setSize(660, 540);
		frmModifierCompte.getContentPane().setLayout(null);
		frmModifierCompte.setUndecorated(true);
		frmModifierCompte.setLocationRelativeTo(null);
		
		FrameDragListener frameDragListener = new FrameDragListener(frmModifierCompte);
		frmModifierCompte.addMouseListener(frameDragListener);
		frmModifierCompte.addMouseMotionListener(frameDragListener);
		
		panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 660, 540);
		panel_principal.setBackground(new Color(194, 194, 194));
		panel_principal.setLayout(null);
		frmModifierCompte.getContentPane().add(panel_principal);
		
	}
	
	private void sortir() {
		JButton sortir = new JButton("");
		sortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmModifierCompte.dispose();
			}
		});
		sortir.setBorder(null);
		sortir.setRequestFocusEnabled(false);
		sortir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sortir.setMargin(new Insets(0, 0, 0, 0));
		sortir.setBounds(620, 11, 31, 31);
		sortir.setBackground(new Color(194, 194, 194));
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/sortir2.png"));
			sortir.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
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
				frmModifierCompte.setState(Frame.ICONIFIED);
			}
		});
		reduit.setBounds(567, 11, 43, 31);
		panel_principal.add(reduit);
	}
	
	private void titrePrincipal() {
		JLabel lblNewLabel = new JLabel("NESTI");
		lblNewLabel.setBounds(85, 10, 499, 40);
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
		panel_inscription.setBounds(0, 53, 660, 540);
		panel_inscription.setLayout(null);
		panel_principal.add(panel_inscription);
		
		lblNewLabel_1 = new JLabel("MODIFICATIONS INFORMATIONS");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(98, 129, 159));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 5, 660, 40);
		panel_inscription.add(lblNewLabel_1);	
	}
	
	private void creerInput() {
		BlockInput inputObj;
		for (int i = 0; i < input.length; i++) {
			if(i < 3) {
				inputObj = new BlockInput(password[i], texteInput[i], imgInput[i], 30,  pos[i]);
			}else {
				inputObj = new BlockInput(password[i], texteInput[i], imgInput[i], 345,  pos[i]);
			}
			
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
		avertissement.setBounds(0, 250, 280, 32);
		panel_inscription.add(avertissement);
	}
	
	private void message() {
		message = new JLabel("");
		message.setVisible(false);
		message.setBorder(new LineBorder(Color.RED));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setInheritsPopupMenu(false);
		message.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		message.setBounds(0, 410, 660, 32);
		panel_inscription.add(message);
	}
	
	private void boutonSubmitMdp() {
		lblNewLabel_8 = new JLabel("MOFIFIER MDP");
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setBounds(40, 280, 290, 48);
		lblNewLabel_8.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				//efface toutes les bordures
				for(int i = 0; i < input.length; i++) {
					listInput.get(input[i]).setBorder(null);
				}
				//cahe les messages
				message.setVisible(false);
				verifierSaisieMdp();
			}
		});
		panel_inscription.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel();
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBorder(null);
		lblNewLabel_9.setBounds(40, 280, 290, 48);
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
	
	private void boutonSubmitInfo() {
		lblNewLabel_10 = new JLabel("MOFIFIER");
		lblNewLabel_10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblNewLabel_10.setForeground(Color.BLACK);
		lblNewLabel_10.setBounds(350, 340, 310, 48);
		lblNewLabel_10.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				//efface toutes les bordures
				for(int i = 0; i < input.length; i++) {
					listInput.get(input[i]).setBorder(null);
				}
				//cahe les messages
				message.setVisible(false);
				verifierSaisieInfo();
			}

			
		});
		panel_inscription.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel();
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBorder(null);
		lblNewLabel_11.setBounds(350, 340, 310, 48);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/bouton.png"));
			lblNewLabel_11.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel_11.setBackground(new Color(194, 194, 194));
		panel_inscription.add(lblNewLabel_11);
	}
	
	private void lien() {
		creerCompte = new JLabel("Voir mes informations");
		creerCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creerCompte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		creerCompte.setHorizontalAlignment(SwingConstants.CENTER);
		creerCompte.setBounds(0, 450, 660, 32);
		creerCompte.setForeground(new Color(98, 129, 159));
		creerCompte.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frmModifierCompte.dispose();
				new FrameVisuCompte(user).getfrmVisuCompte().setVisible(true);  
			}
		});
		panel_inscription.add(creerCompte);
	}
	
	private void verifierSaisieMdp() {
		if (listInput.get("mdpa").getText().equals("")){
			messageTexte("Merci d'indiquer votre mot de passe actuel !");
			listInput.get("mdpa").setBorder(new LineBorder(Color.RED));
		}else if (!listInput.get("mdpa").getText().equals(user.getMdp())) {
			messageTexte("Votre  mot de passe actuel n'est pas reconnu !");
			listInput.get("mdpa").setBorder(new LineBorder(Color.RED));
		}
		else if (listInput.get("mdp").getText().equals("")) {
			messageTexte("Merci d'indiquer votre nouveau mot de passe !");
			listInput.get("mdp").setBorder(new LineBorder(Color.RED));
		}else if(!listInput.get("mdp").getText().equals(listInput.get("mdp1").getText())) {
			messageTexte("Les deux mots de passe ne sont pas identiques !");
			listInput.get("mdp").setBorder(new LineBorder(Color.RED));
			listInput.get("mdp1").setBorder(new LineBorder(Color.RED));
		}else if (!user.mdpValide(listInput.get("mdp").getText())){
			messageTexte("Votre nouveau mot de passe n'est pas assez fort !");
			listInput.get("mdp").setBorder(new LineBorder(Color.RED));
			listInput.get("mdp1").setBorder(new LineBorder(Color.RED));
		}
		else {
			user.setMdp(listInput.get("mdp").getText());
			try {
				user.updateMdp();
				new FrameVisuCompte(user).getfrmVisuCompte().setVisible(true);
				frmModifierCompte.dispose();
			} catch (Exception e) {
				messageTexte("Erreur à la modification du mot de passe!");
			} 
		}
		
	}
	 
	private void verifierSaisieInfo() {
		
		if (listInput.get("pseudo").getText().equals("")){
			messageTexte("Merci d'indiquer un pseudo !");
			listInput.get("pseudo").setBorder(new LineBorder(Color.RED));
		}else {
			user.setPseudo(listInput.get("pseudo").getText());
			user.setNom(listInput.get("nom").getText());
			user.setPrenom(listInput.get("prenom").getText());
			user.setVille(listInput.get("ville").getText());
		
			try {
				user.creerUpdateUserSql();
				new FrameVisuCompte(user).getfrmVisuCompte().setVisible(true);
				frmModifierCompte.dispose();
			} catch (Exception e) {
				messageTexte("Erreur à la modification du compte !");
			}
		}	
	}
	
	private void passageEnVisu() {
		
		listInput.get("pseudo").setText(user.getPseudo());
		listInput.get("nom").setText(user.getNom());
		listInput.get("prenom").setText(user.getPrenom());
		listInput.get("ville").setText(user.getVille());
		
	}
	
	private void messageTexte(String texte) {
		message.setText(texte);
		message.setVisible(true);
	}
	
	public static class FrameDragListener extends MouseAdapter {

		private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }
        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }
        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }
        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }

	
	
	/**
	 * @return the user
	 */
	public Utilisateur getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Utilisateur user) {
		this.user = user;
	}

	/**
	 * @return the frmModifierCompte
	 */
	public JFrame getfrmModifierCompte() {
		return frmModifierCompte;
	}

	/**
	 * @param frmModifierCompte the frmModifierCompte to set
	 */
	public void setfrmModifierCompte(JFrame frmModifierCompte) {
		this.frmModifierCompte = frmModifierCompte;
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
