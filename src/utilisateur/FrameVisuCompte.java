package utilisateur;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JTextField;
import elementsFrame.BlockInput;
import elementsFrame.LesBoutonsSortir;
import elementsFrame.LesIconifies;
import elementsFrame.LesLabelsTitres;
import elementsFrame.LesLiens;
import elementsFrame.LesPanels;
import elementsFrame.LesFrames;


public class FrameVisuCompte {
	
	private LesFrames frmVisuCompte;
	LesPanels panel_inscription;
	LesPanels panel_principal;
	
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
		
		frmVisuCompte = new LesFrames("Visualisation", 660, 360);
		if(getUser() == null) {
			frmVisuCompte.dispose();
			new FrameConnexion().getFrmConnexion().setVisible(true); 
		}
		
		panel_principal = new LesPanels(0, 0, 660, 360);
		frmVisuCompte.getContentPane().add(panel_principal);
		
		panel_inscription = new LesPanels(0, 53, 660, 360);
		panel_principal.add(panel_inscription);
		
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
		LesLabelsTitres lblNewLabel = new LesLabelsTitres("NESTI", 85, 10, 499, 40);
		panel_principal.add(lblNewLabel);
	}
	
	private void titre() {
		LesLabelsTitres lblNewLabel_1 = new LesLabelsTitres("INFORMATIONS UTILISATEUR", 0, 5, 660, 40);
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
	public LesFrames getfrmVisuCompte() {
		return frmVisuCompte;
	}

	/**
	 * @param frmVisuCompte the frmVisuCompte to set
	 */
	public void setfrmVisuCompte(LesFrames frmVisuCompte) {
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

