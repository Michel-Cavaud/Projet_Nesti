package frames;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import elementsFrame.BlockInput;
import elementsFrame.LesBoutons;
import elementsFrame.LesBoutonsSortir;
import elementsFrame.LesIconifies;
import elementsFrame.LesLabelsTitres;
import elementsFrame.LesLiens;
import elementsFrame.LesMessages;
import elementsFrame.LesPanels;
import utilisateur.Utilisateur;
import elementsFrame.LesFrames;

/**
 * Cette class construit la fenetre de connexion
 * @author CAVAUD
 *
 */

public class FrameConnexion {

	private LesFrames frmConnexion;
	LesPanels panel_principal;
	LesPanels panel_connexion;
	
	LesMessages message;
	Image img = null;
	
	private HashMap<String, JTextField> listInput = new HashMap<String, JTextField>();
	private String[] input = {"pseudo", "mdp"};
	private String[] texteInput = {"Pseudo ou Email", "Mot de passe"};
	private String[] imgInput = { "user", "mdp"};
	private boolean[] password = {false, true,};
	private int[] pos = {50, 120};
	
	Utilisateur user;

	/**
	 * l'ensemble de la construition de la fenetre
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
	 * Contruction de la Frame
	 */
	private void initialize() {
		frmConnexion = new LesFrames("Connexion", 380, 370);
		panel_principal = new LesPanels(0, 0, 380, 370);
		frmConnexion.getContentPane().add(panel_principal);
		panel_connexion = new LesPanels(0, 53, 380, 317);
		panel_principal.add(panel_connexion);
	}
	/**
	 * le titre de la fenetre
	 */
	private void titrePrincipal() {
		LesLabelsTitres lblNewLabel = new LesLabelsTitres("NESTI", 95, 11, 182, 40);
		panel_principal.add(lblNewLabel);
	}	
	/**
	 * L'icone pour mettre la fenetre en icone
	 */
	private void iconifie() {
		LesIconifies boutonIconifie = new LesIconifies(frmConnexion, 287);
		panel_principal.add(boutonIconifie);
	}
	/**
	 * L'icone pour fermer la fenetre
	 */
	private void sortir() {
		LesBoutonsSortir boutonsSortir = new LesBoutonsSortir(frmConnexion, 340);
		panel_principal.add(boutonsSortir);
	}
	/**
	 * Le titre de la fen^tre
	 */
	private void titre() {
		LesLabelsTitres lblNewLabel_1 = new LesLabelsTitres("CONNEXION", 0, 10, 380, 40);
		panel_connexion.add(lblNewLabel_1);
	}
	/**
	 * Création des zones de saisie de la fenetre
	 */
	private void creerInput() {
		BlockInput inputObj;
		for (int i = 0; i < input.length; i++) {
			inputObj = new BlockInput(password[i], texteInput[i], imgInput[i], 44,  pos[i]);
			panel_connexion.add(inputObj.getBlock_1());
			panel_connexion.add(inputObj.getBlock_2());
			if(password[i]) {
				listInput.put(input[i], inputObj.getBlock_3p());
				panel_connexion.add(inputObj.getBlock_3p());
			}else {
				listInput.put(input[i], inputObj.getBlock_3());
				panel_connexion.add(inputObj.getBlock_3());
			}
			panel_connexion.add(inputObj.getBlock_4());
		}
	}
	/**
	 * La zone de message d'erreur
	 */
	private void message() {
		message = new LesMessages(200, 380);
		panel_connexion.add(message);
	}
	/**
	 * Bouton de connexion
	 */
	private void boutonSubmit() {
		LesBoutons connexion = new LesBoutons("CONNEXION", 0, 240, 380);
		connexion.getLabelTexte().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (listInput.get("pseudo").getText().equals("")) {
					listInput.get("pseudo").setBorder(new LineBorder(Color.RED));
					message.setVisible(true);
					message.setText("Il manque les données de connexion !");
				}else if(listInput.get("mdp").getText().equals("")){
					listInput.get("mdp").setBorder(new LineBorder(Color.RED));
					message.setVisible(true);
					message.setText("Il manque les données de connexion !");
				}else {
					listInput.get("mdp").setBorder(null);
					listInput.get("pseudo").setBorder(null);
					user = new Utilisateur();
					try {
						user = user.chercherUser(listInput.get("pseudo").getText(), listInput.get("mdp").getText());	
						if (user != null) {
							new FrameVisuCompte(user).getfrmVisuCompte().setVisible(true);
							frmConnexion.dispose();					
						}else {
							ImageIcon icon = new ImageIcon(getClass().getResource("../images/ko.png"));
							JOptionPane.showMessageDialog(frmConnexion, "L'utilisateur n'est pas connue", "Erreur de connexion.", JOptionPane.WARNING_MESSAGE, icon);
							message.setText(" ");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_connexion.add(connexion.getLabelTexte());
		panel_connexion.add(connexion.getLabelImage());
	}
	/**
	 * lien pour accéder à la création d'une compte
	 */
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
	 * @return the frmConnexion
	 */
	public LesFrames getFrmConnexion() {
		return frmConnexion;
	}

	/**
	 * @param frmConnexion the frmConnexion to set
	 */
	public void setFrmConnexion(LesFrames frmConnexion) {
		this.frmConnexion = frmConnexion;
	}
	

	
	
}
