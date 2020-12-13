package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
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

/**
 * Cette class construit le fenêtre d'inscription de l'utilisateur
 * @author CAVAUD
 *
 */

public class FrameCreerCompte {
	private LesFrames frmCreerCompte;
	LesPanels panel_inscription;
	LesPanels panel_principal;
	LesMessages message;
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
	 * Construction de la fenêtre
	 */
	public FrameCreerCompte() {	
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
	}

	/**
	 * La Frame
	 */
	private void initialize() {	
		frmCreerCompte = new LesFrames("Inscriptions", 660, 540);
		panel_principal = new LesPanels(0, 0, 660, 540);
		frmCreerCompte.getContentPane().add(panel_principal);
		panel_inscription = new LesPanels(0, 53, 660, 487);
		panel_principal.add(panel_inscription);
	}
	/**
	 * Bouton fermeture de la fen^tre
	 */
	private void sortir() {
		LesBoutonsSortir boutonsSortir = new LesBoutonsSortir(frmCreerCompte, 620);
		panel_principal.add(boutonsSortir);
	}
	/**
	 * Bouton pour réduire le fenêtre en icone
	 */
	private void iconifie() {
		LesIconifies boutonIconifie = new LesIconifies(frmCreerCompte, 567);
		panel_principal.add(boutonIconifie);
	}
	/**
	 * Affichage titre principal
	 */
	private void titrePrincipal() {
		LesLabelsTitres lblNewLabel = new LesLabelsTitres("NESTI", 85, 10, 499, 40);
		panel_principal.add(lblNewLabel);
	}
	/**
	 * Affichage titre
	 */
	private void titre() {
		LesLabelsTitres lblNewLabel_1 = new LesLabelsTitres("INSCRIPTION", 0, 5, 660, 40);
		panel_inscription.add(lblNewLabel_1);
	}
	
	/**
	 * Création des saisies pour l'utilisateur
	 */
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
			}
			panel_inscription.add(inputObj.getBlock_4());
		}
		//Vérification MDP a chaque frappe
		listInput.get("mdp").addCaretListener(caretupdate);
	}
	/**
	 * Panneau de force du mot de passe
	 */
	private void panelForceMdp(){
		panelMdp = new PanelMdp(320, 247);
		panel_inscription.add(panelMdp.getPanelMdp());
	}
	/**
	 *  Affichage information obligatoire
	 */
	private void avertissement() {
		LesAvertissements avertissement = new LesAvertissements(320);
		panel_inscription.add(avertissement);
	}
	/**
	 * Les messages d'erreur de saisie
	 */
	private void message() {
		message = new LesMessages(365, 660);
		panel_inscription.add(message);
	}
	/**
	 * Bouton création compte
	 */
	private void boutonSubmit() {
		LesBoutons connexion = new LesBoutons("INSCRIPTION",0, 400, 660);
		connexion.getLabelTexte().addMouseListener(new MouseAdapter() {
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
		panel_inscription.add(connexion.getLabelTexte());
		panel_inscription.add(connexion.getLabelImage());
	}
	/**
	 * Lien pour aller à la fenêtre de connexion
	 */
	private void lien() {
		LesLiens lien = new LesLiens("Vous avez déjà un compte", 450, 660);
		lien.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frmCreerCompte.dispose();
				new FrameConnexion().getFrmConnexion().setVisible(true);  
			}
		});
		panel_inscription.add(lien);
	}
	/*
	 * Vérification des saisies et envoie des info à la class utilisateur pour création d'un utilisateur et l'ajout dans la base de donnée
	 * Sinon affichage des erreurs
	 */
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
	
	/**
	 * Affichage message d'erreur
	 * @param texte
	 */
	private void messageTexte(String texte) {
		message.setText(texte);
		message.setVisible(true);
	}
	
	/**
	 * Vérification de la saisie du mot de passe a chaque caractère pour indiquer la force du mot de passe
	 */
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
	 * @return the frmCreerCompte
	 */
	public LesFrames getFrmCreerCompte() {
		return frmCreerCompte;
	}

	/**
	 * @param frmCreerCompte the frmCreerCompte to set
	 */
	public void setFrmCreerCompte(LesFrames frmCreerCompte) {
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
