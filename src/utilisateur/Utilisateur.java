package utilisateur;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jbcrypt.BCrypt;



public class Utilisateur {

	private Integer id = 8;
	private String pseudo;
	private String email;
	private String mdp;
	private String nom;
	private String prenom;
	private String ville;

	private boolean connexion = false;
	private  HashMap<String, Integer> listErreur = new HashMap<String, Integer>();
	
	private RequetesSQL requetesSQL = new RequetesSQL("163.172.232.206", "michel", "sqlmichel", "nesti");

	public void ajouterModifierUtilisateur(HashMap<String, String> listDonneeUser) throws Exception {

		for (Map.Entry mapentry : listDonneeUser.entrySet()) {
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
		}
		if(this.verifierDonnees(listDonneeUser)) {
			setPseudo(listDonneeUser.get("pseudo"));
			setEmail(listDonneeUser.get("email"));
			setMdp(listDonneeUser.get("mdp"));
			setNom(listDonneeUser.get("nom"));
			setPrenom(listDonneeUser.get("prenom"));
			setVille(listDonneeUser.get("ville"));	
		} else {
			//listErreur.put("user", 1);
			throw new Exception("User incorrecte");
		}
	}
	
	
	public void creerUpdateUserSql() throws Exception {
		try {
			requetesSQL.openConnection();
		} catch (Exception e) {
			throw new Exception("connexion impossible");
		}
		System.out.println(getId());
		if(getId() == null) {
			try {
				if(RequetesSQL.doublonUser(this)) {
					RequetesSQL.insertUtilisateur(this);
				}else {
					listErreur.put("doublon", 1);
					throw new Exception("doublon user");
				}
				
			} catch (Exception e) {
				throw new Exception("création impossible");
			}
		}else{
			try {		
				RequetesSQL.updateUtilisateur(this);
			} catch (Exception e) {
				throw new Exception("création impossible");
			}
		}
		
		try {
			requetesSQL.closeConnection();
		} catch (Exception e) {
			throw new Exception("close impossible");
		}
	}
	
	public String[] getInfoUserTabCrypte(){
		String[] tableau = {getPseudo(), getEmail(), BCrypt.hashpw(getMdp(), BCrypt.gensalt()), getNom(), getPrenom(), getVille()};
		return tableau;
	}
	
	public String[] getInfoUserTab(){
		String[] tableau = {getPseudo(), getEmail(), getMdp(), getNom(), getPrenom(), getVille()};
		return tableau;
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

	/*if (BCrypt.checkpw(candidate, hashed))
		System.out.println("It matches");
	else
		System.out.println("It does not match");*/
	
	
	
	/**
	 * @return the listErreur
	 */
	public HashMap<String, Integer> getListErreur() {
		return listErreur;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @param listErreur the listErreur to set
	 */
	public void setListErreur(String key, int val) {
		this.listErreur.put(key, val);
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
