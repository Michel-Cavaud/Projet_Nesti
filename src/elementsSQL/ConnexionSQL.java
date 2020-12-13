package elementsSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Cette class ouvre et ferme la connection à la base de données
 * En abstract elle est intencier par une class fiche 
 * @author CAVAUD
 *
 */
abstract class ConnexionSQL  {
	static Connection accessDataBase = null;
	private String url;
	private String login;
	private String mdp;
	private String bdd;
	
	/**
	 * Constructeur de la class 
	 * @param url
	 * @param login
	 * @param mdp
	 * @param bdd
	 */
	public ConnexionSQL(String url, String login, String mdp, String bdd){
			setUrl("jdbc:mysql://" + url +"/" + bdd);
			setLogin(login);
			setMdp(mdp);
	}
	/**
	 * Ouvrir une connexion
	 * @throws Exception
	 */
	public void openConnection() throws Exception {
		if(!testConnection()) {
			try {
				accessDataBase = DriverManager.getConnection(getUrl(), getLogin(), getMdp());
			} catch (SQLException ex) {
				throw new Exception("Erreur connexion");
			}
		}
	}
	/**
	 * Teste si la connexion est ouverte
	 * @return true si connexion existe sinon false 
	 * @throws Exception
	 */
	public boolean testConnection() throws Exception {
		boolean flag = false;
		try {
			if (accessDataBase != null) {
				if (!accessDataBase.isClosed()) {
					flag = true;
				}
			}
		} catch (SQLException ex) {
			throw new Exception("Erreur connexion");
		}
		return flag;
	}
	
	/**
	 * Fermeture de la connexion
	 * @throws Exception
	 */
	public void closeConnection() throws Exception {
		if (accessDataBase != null) {
			try {
				accessDataBase.close();
			} catch (SQLException e) {
				throw new Exception("Erreur close connexion");
			}
		}
	}

	

	/**
	 * @return the bdd
	 */
	public String getBdd() {
		return bdd;
	}

	/**
	 * @param bdd the bdd to set
	 */
	public void setBdd(String bdd) {
		this.bdd = bdd;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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

}
