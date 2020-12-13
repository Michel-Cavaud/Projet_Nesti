package utilisateur;

import java.sql.ResultSet;

/**
 * Cette class représente l'utilisateur. Elle est complétée après la connexion de l'utilisateur avec les informations de la table utilisateur
 * Elle vérifie les données de l'utilisateur avant la création du compte et ajoute l'utilisateur dans la table.
 * Cette class permet de passer les informations de l'utilisateur entre les fenêtres
 */

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import elementsSQL.RequetesSQL;
import jbcrypt.BCrypt;

public class Utilisateur {
	private Integer id = null;
	private String pseudo;
	private String email;
	private String mdp;
	private String nom;
	private String prenom;
	private String ville;
	private boolean connexion = false;
	private  HashMap<String, Integer> listErreur = new HashMap<String, Integer>();
	
	//données de connexion à la base de données pour la table utilisateurs 
	private RequetesSQL requetesSQL = new RequetesSQL("163.172.232.206", "michelnesti", "c2Lrdhn0OlO3NtQD", "nesti");

	/**
	 * Vérifie les données d'incription de l'utilisateur avant l'ajout dans la base de données
	 * @param listDonneeUser
	 * @throws Exception
	 */
	public void ajouterModifierUtilisateur(HashMap<String, String> listDonneeUser) throws Exception {
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
	
	/**
	 * Mise à jour du mot de passe de l'utilisateur
	 * @throws Exception
	 */
	public void updateMdp() throws Exception {
		try {
			requetesSQL.openConnection();
		} catch (Exception e) {
			throw new Exception("connexion impossible");
		}
		try {
			requetesSQL.updateMdpUtilisateur(this);
		} catch (Exception e) {
			throw new Exception("close impossible");
		}
		try {
			requetesSQL.closeConnection();
		} catch (Exception e) {
			throw new Exception("close impossible");
		}
	}
	
	/**
	 * Insertion ou mise à jour de l'utilisateur
	 * Si l'identifiant est null il faut une création sinon une mise à jour
	 * @throws Exception
	 */
	public void creerUpdateUserSql() throws Exception {
			try {
				requetesSQL.openConnection();
			} catch (Exception e) {
				throw new Exception("connexion impossible");
			}
		if(getId() == null) {
			try {
				if(requetesSQL.doublonUser(this)) {
					requetesSQL.insertUtilisateur(this);
				}else {
					listErreur.clear();
					listErreur.put("doublon", 1);
					throw new Exception("doublon user");
				}
				
			} catch (Exception e) {
				throw new Exception("création impossible");
			}
		}else{
			try {	
				
				requetesSQL.updateInfoUtilisateur(this);
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
	
	/**
	 * Renvoie un tableau des information de l'utilisateur avec le mot de passe crypté
	 * @return tableau info utilisateur
	 */
	public String[] getInfoUserTabCrypte(){
		String[] tableau = {getPseudo(), getEmail(), BCrypt.hashpw(getMdp(), BCrypt.gensalt()), getNom(), getPrenom(), getVille()};
		return tableau;
	}
	/**
	 * Retour du mot de passe crypté
	 * @return mot de passe crypté
	 */
	public String getMdpCrypte(){
		return BCrypt.hashpw(getMdp(), BCrypt.gensalt());
	}
	/**
	 * retour des info utilisateur (sans mail et mot de passe)
	 * @return
	 */
	public String[] getInfoUserTab(){
		String[] tableau = {getPseudo(), getNom(), getPrenom(), getVille()};
		return tableau;
	}

	/**
	 * Vérification des données d'inscription de l'utilisateur. Inscrit les erreurs dans un tableau listErreur
	 * @param listDonneeUser
	 * @return False si erreur, true si pas d'erreur
	 */
	private boolean verifierDonnees(HashMap<String, String> listDonneeUser) {
		listErreur.clear();
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
		return retour;
	}
	
	/**
	 * Vérifie si l'utilisateur est connue dans la table après connexion
	 * @param pseudo  Pseudo ou mail saisie par l'utilisateur
	 * @param mdp  Mot de passe saisie par l'utilisateur
	 * @return l'utilisateur si la connexion est ok sinon retour de null
	 * @throws Exception
	 */
	public Utilisateur chercherUser(String pseudo, String mdp) throws Exception {
		try {
			requetesSQL.openConnection();
		} catch (Exception e) {
			throw new Exception("connexion impossible");
		}
		ResultSet resultat = requetesSQL.selectUsers(pseudo);
		while (resultat.next()) {
			if(BCrypt.checkpw(mdp, resultat.getString("mdp")) & pseudo.equals(resultat.getString("pseudo")) | pseudo.equals(resultat.getString("email"))) {
				HashMap<String, String> listDonneeUser = new HashMap<String, String>();
				listDonneeUser.put("pseudo", resultat.getString("pseudo"));
				listDonneeUser.put("email", resultat.getString("email"));
				listDonneeUser.put("mdp", mdp);
				listDonneeUser.put("nom", resultat.getString("nom"));
				listDonneeUser.put("prenom", resultat.getString("prenom"));
				listDonneeUser.put("ville", resultat.getString("ville"));
				this.ajouterModifierUtilisateur(listDonneeUser);
				setId(resultat.getInt("id_utilisateur"));
				return this;
			}
		}
		try {
			requetesSQL.closeConnection();
		} catch (Exception e) {
			throw new Exception("close impossible");
		}
		return null;
	}

	/**
	 * Vérifie le format de l'email
	 * @param email
	 * @return true si email ok
	 */
	private boolean emailValide(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}
	
	/**
	 * Vérifie la force du mot de passe
	 * @param mdp
	 * @return true si email ok
	 */
	public boolean mdpValide(String mdp) {
		Pattern p = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#-+!$@%])([&#-+!*$@%_\\w]{8,})$");
		Matcher m = p.matcher(mdp);
		return m.matches();
	}
	
	/**
	 * Cette fonction est accésible sans instance de l'utilisateur elle permet de vérifier les données du mot de passe a chaque saisie de l'utilisateur.
	 * @param texte la saisie de l'utilisateur
	 * @return un tableau de boolean 
	 */
	public static boolean[] analyseMdp(String texte) {
		 boolean[] retour = {false, false, false, false, false};
		
		if(texte.length() > 7) {
			retour[0] = true;
		}
		retour[1] = Pattern.compile("^.*[0-9].*").matcher(texte).matches();
		retour[2] = Pattern.compile("^.*[a-z].*").matcher(texte).matches();
		retour[3] = Pattern.compile("^.*[A-Z].*").matcher(texte).matches();
		retour[4] = Pattern.compile("^.*[&#-+!$@%].*").matcher(texte).matches();
		return retour;	
	}	
	
	
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
