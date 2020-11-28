package utilisateur;

import java.util.HashMap;

import junit.framework.TestCase;

public class UtilisateurTest extends TestCase {

	private Utilisateur user = new Utilisateur();
	private HashMap<String, String> listDonneeUser = new HashMap<String, String>();

	public void testajouterUtilisateur() throws Exception {

		// ajouter un user avec bonne information
		user.ajouterModifierUtilisateur(listDonneeUser);

		// ajouter user sans email
		listDonneeUser.put("email", "");
		try {
			user.ajouterModifierUtilisateur(listDonneeUser);
			fail("Erreur non d�tect�e : mail absent");
		} catch (Exception e) {
			// erreur attendue
		}

		// ajouter user sans pseudo
		listDonneeUser.put("email", "monemain@mail.com");
		listDonneeUser.put("pseudo", "");
		try {
			user.ajouterModifierUtilisateur(listDonneeUser);
			fail("Erreur non d�tect�e : pseudo absent");
		} catch (Exception e) {
			// erreur attendue
		}
		// ajouter user sans pseudo sans mail
		listDonneeUser.put("pseudo", "");
		listDonneeUser.put("email", "");
		try {
			user.ajouterModifierUtilisateur(listDonneeUser);
			fail("Erreur non d�tect�e : pseudo et mail absent");
		} catch (Exception e) {
			// erreur attendue
		}

		// ajouter user avec mail erron�
		listDonneeUser.put("pseudo", "monpseudo");
		listDonneeUser.put("email", "sfsffdfdf.fg");
		try {
			user.ajouterModifierUtilisateur(listDonneeUser);
			fail("Erreur non d�tect�e : mail absent");
		} catch (Exception e) {
			// erreur attendue
		}

		// ajouter user sans mot de passe
		listDonneeUser.put("email", "sfsf@fdfdf.fg");
		listDonneeUser.put("mdp", "");
		try {
			user.ajouterModifierUtilisateur(listDonneeUser);
			fail("Erreur non d�tect�e : mdp absent");
		} catch (Exception e) {
			// erreur attendue
		}

		// ajouter user avec mot de passe faible
		listDonneeUser.put("mdp", "monmdp&30");
		try {
			user.ajouterModifierUtilisateur(listDonneeUser);
			fail("Erreur non d�tect�e : mdp absent");
		} catch (Exception e) {
			// erreur attendue
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		listDonneeUser.put("pseudo", "monpseudo");
		listDonneeUser.put("email", "monemain@mail.com");
		listDonneeUser.put("mdp", "monmdpAA&30");
		listDonneeUser.put("nom", "monnom");
		listDonneeUser.put("prenom", "monprenom");
		listDonneeUser.put("ville", "maville");
	}

}
