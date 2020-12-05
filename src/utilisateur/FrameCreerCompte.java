package utilisateur;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.event.CaretListener;

public class FrameCreerCompte {

	
	private JFrame frmCreerCompte;
	JPanel panel_inscription;
	JPanel panel_principal;
	JLabel message;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_8;
	JLabel creerCompte;
	PanelMdp panelMdp;
	
	private int posX = 0;  
    private int posY = 0;
   
	private HashMap<String, JTextField> listInput = new HashMap<String, JTextField>();
	private String[] input = {"email", "pseudo", "mdp", "mdp1", "nom", "prenom", "ville"};
	private String[] texteInput = {"Votre email*", "Votre pseudo*", "Mot de passe*", "Confirmer MDP*", "Votre Nom", "Votre prénom", "Votre ville"};
	private String[] imgInput = {"email", "user", "mdp", "mdp", "user", "user", "ville"};
	private boolean[] password = {false, false, true, true, false, false, false};
	private int[] pos = {30, 100, 170, 240, 30, 100, 170};
	
	Utilisateur user;
	
	Image img = null;

	/**
	 * Create the application.
	 */
	public FrameCreerCompte(Utilisateur user) {
		setUser(user);
		initialize();
		titrePrincipal();
		iconifie();
		sortir();
		titre();
		creerInput();
		panelForceMdp();
		avertissement();
		message();
		boutonSubmit();
		lien();
		if(getUser()!= null) {
			passageEnVisu();
		}
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreerCompte = new JFrame();
		if(getUser() == null) {
			frmCreerCompte.setTitle("Inscription");
		}else {
			frmCreerCompte.setTitle("Visualisation");
		}
		frmCreerCompte.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameConnexion.class.getResource("/utilisateur/images/user.png")));
		frmCreerCompte.setSize(660, 540);
		frmCreerCompte.getContentPane().setLayout(null);
		frmCreerCompte.setUndecorated(true);
		frmCreerCompte.setLocationRelativeTo(null);
		
		FrameDragListener frameDragListener = new FrameDragListener(frmCreerCompte);
		frmCreerCompte.addMouseListener(frameDragListener);
		frmCreerCompte.addMouseMotionListener(frameDragListener);
		
		panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 660, 540);
		panel_principal.setBackground(new Color(194, 194, 194));
		panel_principal.setLayout(null);
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
				frmCreerCompte.setState(Frame.ICONIFIED);
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
		
		lblNewLabel_1 = new JLabel("INSCRIPTION");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(98, 129, 159));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 5, 660, 40);
		panel_inscription.add(lblNewLabel_1);	
	}
	
	private void creerInput() {
		BlockInput inputObj;
		for (int i = 0; i < input.length; i++) {
			if(i < 4) {
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
				if(user != null & i == 0) {
					inputObj.getBlock_3().setEditable(false);
					inputObj.getBlock_1().setText("Votre email");
				}
			}
			
			panel_inscription.add(inputObj.getBlock_4());
		}
		//Vérification MDP a chaque frappe
		listInput.get("mdp").addCaretListener(caretupdate);
	}
	
	private void panelForceMdp(){
		
		panelMdp = new PanelMdp(320, 247);
		panel_inscription.add(panelMdp.getPanelMdp());
		
		
	}
	
	private void avertissement() {
		JLabel avertissement = new JLabel("* données obligatoires");
		//message.setVisible(false);
		avertissement.setHorizontalAlignment(SwingConstants.CENTER);
		avertissement.setInheritsPopupMenu(false);
		avertissement.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		avertissement.setBounds(0, 320, 280, 32);
		panel_inscription.add(avertissement);
	}
	
	private void message() {
		message = new JLabel("");
		message.setVisible(false);
		message.setBorder(new LineBorder(Color.RED));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setInheritsPopupMenu(false);
		message.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		message.setBounds(0, 365, 660, 32);
		panel_inscription.add(message);
	}
	
	private void boutonSubmit() {
		lblNewLabel_8 = new JLabel("INSCRIPTION");
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setBounds(0, 400, 660, 48);
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
		lblNewLabel_9.setBounds(0, 400, 660, 48);
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
		creerCompte = new JLabel("Vous avez déjà un compte");
		creerCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creerCompte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		creerCompte.setHorizontalAlignment(SwingConstants.CENTER);
		creerCompte.setBounds(0, 450, 660, 32);
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
			if(user == null) {
				user = new Utilisateur();
			}
			HashMap<String, String> listDonneeUser = new HashMap<String, String>();
			
			System.out.println(listInput.get("pseudo").getText());
			
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
					
					System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
				}
				messageTexte("Erreurs dans la saisie des données !");
			}
			
			if(user.getListErreur().isEmpty()) {
				try {
					user.creerUpdateUserSql();
					new FrameVisuCompte(user).getfrmVisuCompte().setVisible(true);
					frmCreerCompte.dispose();
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
	
	private void passageEnVisu() {
		lblNewLabel_1.setText("MODIFICATIONS INFORMATIONS");
		lblNewLabel_8.setText("MODIFIER");
		creerCompte.setVisible(false);
		
		listInput.get("pseudo").setText(user.getPseudo());
		listInput.get("email").setText(user.getEmail());
		listInput.get("nom").setText(user.getNom());
		listInput.get("prenom").setText(user.getPrenom());
		listInput.get("ville").setText(user.getVille());
		
	}
	
	private void messageTexte(String texte) {
		message.setText(texte);
		message.setVisible(true);
	}
	

	 CaretListener caretupdate = new CaretListener() {
         public void caretUpdate(javax.swing.event.CaretEvent e) {
             
            boolean[] retours = Utilisateur.analyseMdp(((JTextField) e.getSource()).getText());
            int toutVert = 0;
            
            for (int i = 0; i < retours.length; i++) {
            	
            	panelMdp.getCocheKO8CaractMdp()[i].setVisible(!retours[i]);
            	panelMdp.getCocheOK8CaractMdp()[i].setVisible(retours[i]);
            	
            	if(retours[i]) {
            		panelMdp.getLabelCaractMdp()[i].setForeground(Color.GREEN);
            		toutVert++;
            	}else {
            		panelMdp.getLabelCaractMdp()[i].setForeground(Color.RED);
            	}
            }

			if(toutVert == 5) {
            	panelMdp.getPanelMdp().setBorder(new LineBorder(Color.GREEN));
            }else {
            	panelMdp.getPanelMdp().setBorder(new LineBorder(Color.RED));
            }
            
         }
     };

	
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
