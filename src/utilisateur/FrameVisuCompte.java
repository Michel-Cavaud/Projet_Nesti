package utilisateur;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import elementsFrame.BlockInput;
import elementsFrame.FrameDragListener;
import elementsFrame.LesBoutonsSortir;
import elementsFrame.LesIconifies;
import elementsFrame.LesLiens;


public class FrameVisuCompte {
	
	private JFrame frmVisuCompte;
	JPanel panel_inscription;
	JPanel panel_principal;
	JLabel message;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_8;
	JLabel creerCompte;
	
	private int posX = 0;  
    private int posY = 0;
   
	private HashMap<String, JTextField> listInput = new HashMap<String, JTextField>();
	private String[] input = {"email", "pseudo", "nom", "prenom", "ville"};
	private String[] texteInput = {"Votre email", "Votre pseudo", "Votre Nom", "Votre prénom", "Votre ville"};
	private String[] imgInput = {"email", "user", "user", "user", "ville"};
	private boolean[] password = {false, false, false, false, false};
	private int[] pos = {50, 120, 50, 120, 190};
	
	Utilisateur user;
	
	Image img = null;

	
	
	/**
	 * Create the application.
	 */
	public FrameVisuCompte(Utilisateur user) {
		setUser(user);
		initialize();
		titrePrincipal();
		iconifie();
		sortir();
		titre();
		creerInput();
		
		lien();
		if(getUser()!= null) {
			passageEnVisu();
		}
		
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisuCompte = new JFrame();
		if(getUser() == null) {
			frmVisuCompte.dispose();
			new FrameConnexion().getFrmConnexion().setVisible(true); 
		}else {
			frmVisuCompte.setTitle("Visualisation");
		}
		frmVisuCompte.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameConnexion.class.getResource("/utilisateur/images/user.png")));
		frmVisuCompte.setSize(660, 360);
		frmVisuCompte.getContentPane().setLayout(null);
		frmVisuCompte.setUndecorated(true);
		frmVisuCompte.setLocationRelativeTo(null);
		
		FrameDragListener frameDragListener = new FrameDragListener(frmVisuCompte);
		frmVisuCompte.addMouseListener(frameDragListener);
		frmVisuCompte.addMouseMotionListener(frameDragListener);
		
		panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 660, 360);
		panel_principal.setBackground(new Color(194, 194, 194));
		panel_principal.setLayout(null);
		frmVisuCompte.getContentPane().add(panel_principal);
		
		
	}
	
	private void sortir() {
		LesBoutonsSortir boutonsSortir = new LesBoutonsSortir(frmVisuCompte, 620);
		panel_principal.add(boutonsSortir);
	}
	
	private void iconifie() {
		LesIconifies boutonIconifie = new LesIconifies(frmVisuCompte, 567);
		panel_principal.add(boutonIconifie);
	}
	
	private void titrePrincipal() {
		JLabel lblNewLabel = new JLabel("NESTI");
		lblNewLabel.setBounds(85, 10, 499, 40);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNewLabel.setForeground(new Color(98, 129, 159));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panel_principal.add(lblNewLabel);
	}
	
	private void titre() {
		panel_inscription = new JPanel();
		panel_inscription.setBackground(new Color(194, 194, 194));
		panel_inscription.setBorder(null);
		panel_inscription.setBounds(0, 53, 660, 360);
		panel_inscription.setLayout(null);
		panel_principal.add(panel_inscription);
		
		lblNewLabel_1 = new JLabel("INFORMATIONS UTILISATEUR");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(98, 129, 159));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 5, 660, 40);
		panel_inscription.add(lblNewLabel_1);	
	}
	
	private void creerInput() {
		BlockInput inputObj;
		for (int i = 0; i < input.length; i++) {
			if(i < 2) {
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
				inputObj.getBlock_3().setEditable(false);  
				
				panel_inscription.add(inputObj.getBlock_3());
			}
			
			panel_inscription.add(inputObj.getBlock_4());
		}

	}
	
	private void lien() {
		LesLiens lien = new LesLiens("Modifier mes informations", 270, 660);
		lien.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				new FrameModifierCompte(user).getfrmModifierCompte().setVisible(true);
				frmVisuCompte.dispose();
			}
		});
		panel_inscription.add(lien);
	}
	
	private void passageEnVisu() {
		
		listInput.get("pseudo").setText(user.getPseudo());
		listInput.get("email").setText(user.getEmail());
		listInput.get("nom").setText(user.getNom());
		listInput.get("prenom").setText(user.getPrenom());
		listInput.get("ville").setText(user.getVille());
		
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
	 * @return the frmVisuCompte
	 */
	public JFrame getfrmVisuCompte() {
		return frmVisuCompte;
	}

	/**
	 * @param frmVisuCompte the frmVisuCompte to set
	 */
	public void setfrmVisuCompte(JFrame frmVisuCompte) {
		this.frmVisuCompte = frmVisuCompte;
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

