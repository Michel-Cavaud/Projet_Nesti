package elementsSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import utilisateur.Utilisateur;
/**
 * Cette class contient les requètes pour les données utilisateurs
 * @author CAVAUD
 *
 */
public class RequetesSQL extends ConnexionSQL {
	/**
	 * Le constructeur est le constructeur de la class Connexion SQL
	 * @param url
	 * @param login
	 * @param mdp
	 * @param bdd
	 */
	public RequetesSQL(String url, String login, String mdp, String bdd) {
		super(url, login, mdp, bdd);
	}
	
	/**
	 * Insertion d'un nouvel utilisateur dans la table utilisateurs
	 * @param user
	 * @return retour True si l'insertion c'est bien passé sinon false
	 * @throws Exception
	 */
	public boolean insertUtilisateur(Utilisateur user) throws Exception{
		boolean flag = false;
		try {
			String query = "INSERT INTO utilisateurs (pseudo, email, mdp, nom, prenom, ville) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement declaration = accessDataBase.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			String[] donnees = user.getInfoUserTabCrypte();
			for (int i = 0; i< donnees.length; i++) {
				declaration.setString(i+1, donnees[i]);
			}
			int executeUpdate = declaration.executeUpdate();
			ResultSet resultat = declaration.getGeneratedKeys();
			resultat.next();
			user.setId(resultat.getInt(1));
			flag = (executeUpdate == 1);
		} catch (Exception e) {
			user.setListErreur("insertUser", 1);
			throw new Exception("Erreur connexion");
		}
		return flag;
	}
	
	/**
	 * Mise à jour des données personnelles utilisateur dans la table utilisateurs
	 * @param user
	 * @return retour True si l'insertion c'est bien passé sinon false
	 * @throws Exception
	 */
	public  boolean updateInfoUtilisateur(Utilisateur user) throws Exception{
		boolean flag = false;
		try {
			String query = "UPDATE utilisateurs SET pseudo = ?, nom = ?, prenom = ?, ville = ? WHERE id_utilisateur = ?";
			PreparedStatement declaration = accessDataBase.prepareStatement(query);
			
			String[] donnees = user.getInfoUserTab();
			
			for (int i = 0; i< donnees.length; i++) {
				declaration.setString(i+1, donnees[i]);
			}
			declaration.setInt(5, user.getId());
			int executeUpdate = declaration.executeUpdate();
			flag = (executeUpdate == 1);
		} catch (Exception e) {
			user.setListErreur("update", 1);
			throw new Exception("Erreur connexion");
		}
		return flag;
	}
	/**
	 * Mise à jour du mot de passe utilisateur dans la table utilisateurs
	 * @param user
	 * @return retour True si l'insertion c'est bien passé sinon false
	 * @throws Exception
	 */
	public  boolean updateMdpUtilisateur(Utilisateur user) throws Exception{
		boolean flag = false;
		try {
			String query = "UPDATE utilisateurs SET mdp = ? WHERE id_utilisateur = ?";
			PreparedStatement declaration = accessDataBase.prepareStatement(query);

			declaration.setString(1, user.getMdpCrypte());
			declaration.setInt(2, user.getId());
			int executeUpdate = declaration.executeUpdate();
			flag = (executeUpdate == 1);
		} catch (Exception e) {
			user.setListErreur("updateMdp", 1);
			throw new Exception("Erreur connexion");
		}
		return flag;
	}
	/**
	 * Vérification si l'utilsateur n'est pas en doublon dans la table utilisateur avant insertion
	 * @param user
	 * @return retourne false si un résultat est trouvé dans la table sinon true
	 * @throws Exception
	 */
	public  boolean doublonUser(Utilisateur user) throws Exception{
		boolean flag = true;
		try {
			Statement declaration = accessDataBase.createStatement();
			String query = "SELECT * FROM utilisateurs WHERE pseudo = '" + user.getPseudo() + "' OR email = '" + user.getEmail() + "'";
			ResultSet resultat = declaration.executeQuery(query);
			if(resultat.next()) {
				flag = false;
			}
		} catch (Exception e) {
			throw new Exception("Erreur doublon");
		}
		return flag;
	}
	
	/**
	 * Recherche utilisateur avec soit le pseudo ou l'email de connexion pour vérification du mot de passe
	 * @param pseudo
	 * @return retourne le résultat de la requète
	 */
	public ResultSet selectUsers(String pseudo) {
		ResultSet resultat = null;
		try {
			String query = "SELECT * FROM utilisateurs WHERE pseudo = '" + pseudo + "' OR email = '" + pseudo + "'";
			Statement declaration = accessDataBase.createStatement();
			resultat = declaration.executeQuery(query);
		} catch (Exception e) {
			System.err.println("Erreur d'affichage d'ing: " + e.getMessage());
		}
		return resultat;

	}
}

