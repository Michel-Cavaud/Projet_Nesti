package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretListener;
import elementsFrame.BlockInput;
import elementsFrame.LesAvertissements;
import elementsFrame.LesBoutons;
import elementsFrame.LesBoutonsSortir;
import elementsFrame.LesIconifies;
import elementsFrame.LesLabelsTitres;
import elementsFrame.LesLiens;
import elementsFrame.LesMessages;
import elementsFrame.LesPanels;
import elementsFrame.LesFrames;
import elementsFrame.PanelMdp;
import utilisateur.Utilisateur;

public class FrameModifierCompte{

	
	private LesFrames frmModifierCompte;
	LesPanels panel_inscription;
	LesPanels panel_principal;
	LesMessages message;
	PanelMdp panelMdp;
	
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
		panelForceMdp();
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
		
		frmModifierCompte = new LesFrames("Modifications", 660, 580);
		
		panel_principal = new LesPanels(0, 0, 660, 580);
		frmModifierCompte.getContentPane().add(panel_principal);
		
		panel_inscription = new LesPanels(0, 53, 660, 540);
		panel_principal.add(panel_inscription);
	}
	
	private void sortir() {
		LesBoutonsSortir boutonsSortir = new LesBoutonsSortir(frmModifierCompte, 620);
		panel_principal.add(boutonsSortir);
	}
	
	private void iconifie() {
		LesIconifies boutonIconifie = new LesIconifies(frmModifierCompte, 567);
		panel_principal.add(boutonIconifie);
	}
	
	private void titrePrincipal() {
		LesLabelsTitres lblNewLabel = new LesLabelsTitres("NESTI", 85, 10, 499, 40);
		panel_principal.add(lblNewLabel);
	}
	
	private void titre() {
		LesLabelsTitres lblNewLabel_1 = new LesLabelsTitres("MODIFICATIONS INFORMATIONS", 0, 5, 660, 40);
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
		//Vérification MDP a chaque frappe
		listInput.get("mdp").addCaretListener(caretupdate);
	}
	
	private void panelForceMdp(){
		panelMdp = new PanelMdp(60, 280);
		panel_inscription.add(panelMdp.getPanelMdp());
	}
	
	private void avertissement() {
		
		
		LesAvertissements avertissement = new LesAvertissements(250);
		panel_inscription.add(avertissement);
	}
	
	private void message() {
		message = new LesMessages(450, 660);
		panel_inscription.add(message);
	}
	
	private void boutonSubmitMdp() {
		LesBoutons connexionMdp = new LesBoutons("MODIFIER MDP", 40, 400, 290);
		connexionMdp.getLabelTexte().addMouseListener(new MouseAdapter() {
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
		panel_inscription.add(connexionMdp.getLabelTexte());
		panel_inscription.add(connexionMdp.getLabelImage());
	}
	
	private void boutonSubmitInfo() {
		LesBoutons connexion = new LesBoutons("MODIFIER", 350, 340, 310);
		connexion.getLabelTexte().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//efface toutes les bordures
				for(int i = 0; i < input.length; i++) {
					listInput.get(input[i]).setBorder(null);
				}
				//cache les messages
				message.setVisible(false);
				verifierSaisieInfo();
			}	
		});
		panel_inscription.add(connexion.getLabelTexte());
		panel_inscription.add(connexion.getLabelImage());
	}
	
	private void lien() {
		LesLiens lien = new LesLiens("Voir mes informations", 480, 660);
		lien.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frmModifierCompte.dispose();
				new FrameVisuCompte(user).getfrmVisuCompte().setVisible(true);  
			}
		});
		panel_inscription.add(lien);
	}
	
	private void verifierSaisieMdp() {
		if (listInput.get("mdpa").getText().equals("")){
			messageTexte("Merci d'indiquer votre mot de passe actuel !");
			listInput.get("mdpa").setBorder(new LineBorder(Color.RED));
		}else if (!listInput.get("mdpa").getText().equals(user.getMdp())) {
			messageTexte("Votre  mot de passe actuel n'est pas reconnu !");
			listInput.get("mdpa").setBorder(new LineBorder(Color.RED));
		}else if (listInput.get("mdp").getText().equals("")) {
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
		}else {
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

	CaretListener caretupdate = new CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent e) {
           boolean[] retours = Utilisateur.analyseMdp(((JTextField) e.getSource()).getText());
           int toutVert = 0;
           
           for (int i = 0; i < retours.length; i++) {
           	
           	panelMdp.getCocheKO8CaractMdp()[i].setVisible(!retours[i]);
           	panelMdp.getCocheOK8CaractMdp()[i].setVisible(retours[i]);
           	
           	if(retours[i]) {
           		panelMdp.getLabelCaractMdp()[i].setForeground(new Color(46, 139, 87));
           		toutVert++;
           	}else {
           		panelMdp.getLabelCaractMdp()[i].setForeground(Color.RED);
           	}
           }

			if(toutVert == 5) {
           	panelMdp.getPanelMdp().setBorder(new LineBorder(new Color(46, 139, 87)));
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
	 * @return the frmModifierCompte
	 */
	public LesFrames getfrmModifierCompte() {
		return frmModifierCompte;
	}

	/**
	 * @param frmModifierCompte the frmModifierCompte to set
	 */
	public void setfrmModifierCompte(LesFrames frmModifierCompte) {
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
