package utilisateur;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilisateur {

	private String pseudo;
	private String email;
	private String mdp;
	private String nom;
	private String prenom;
	private String ville;

	private boolean connexion = false;
	private  HashMap<String, Integer> listErreur = new HashMap<String, Integer>();

	public void ajouterUtilisateur(HashMap<String, String> listDonneeUser) throws Exception {

		for (Map.Entry mapentry : listDonneeUser.entrySet()) {
			System.out.println("cl�: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
		}
		if(this.verifierDonnees(listDonneeUser)) {
			setPseudo(listDonneeUser.get("pseudo"));
			setEmail(listDonneeUser.get("email"));
			setMdp(listDonneeUser.get("mdp"));
			setNom(listDonneeUser.get("nom"));
			setPrenom(listDonneeUser.get("prenom"));
			setVille(listDonneeUser.get("ville"));
		} else {
			throw new Exception("User incorrecte");
		}
	}

	private boolean verifierDonnees(HashMap<String, String> listDonneeUser) {
		
		boolean retour = true;
		if(listDonneeUser.get("pseudo").equals("")) {
			listErreur.put("pseudo", 1);
			retour = false;
		}
		if(listDonneeUser.get("email").equals("") | !emailValide(listDonneeUser.get("email"))) {
			listErreur.put("email", 1);
			retour = false;
		}
		if(listDonneeUser.get("mdp").equals("") | !mdpValide(listDonneeUser.get("mdp"))) {
			listErreur.put("mdp", 1);
			retour = false;
		}
		//System.out.println(retour);
		return retour;
	}

	public static boolean emailValide(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}
	
	public static boolean mdpValide(String mdp) {
		Pattern p = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#-+!*$@%_])([&#-+!*$@%_\\w]{8,15})$");
		Matcher m = p.matcher(mdp);
		return m.matches();
	}

	/**
	 * @return the listErreur
	 */
	public HashMap<String, Integer> getListErreur() {
		return listErreur;
	}

	/**
	 * @param listErreur the listErreur to set
	 */
	public void setListErreur(HashMap<String, Integer> listErreur) {
		this.listErreur = listErreur;
	}

	/**
	 * @return the connexion
	 */
	public boolean isConnexion() {
		return connexion;
	}

	/**
	 * @param connexion the connexion to set
	 */
	public void setConnexion(boolean connexion) {
		this.connexion = connexion;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

}
