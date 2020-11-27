package connexion;

import java.util.HashMap;

public class Maintest {

	public static void main(String[] args) {


		Utilisateur user = new Utilisateur();
		
		HashMap<String, String> listDonneeUser = new HashMap<String, String>();
		
		listDonneeUser.put("pseudo", "monpseudo");
		listDonneeUser.put("email", "monemain@mail.com");
		listDonneeUser.put("mdp", "monmdpAA&30");
		listDonneeUser.put("nom", "monnom");
		listDonneeUser.put("prenom", "monprenom");
		listDonneeUser.put("ville", "maville");
		
		try {
			user.ajouterUtilisateur(listDonneeUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(user.getListErreur());
		}
		System.out.println(user.getListErreur());
	}

}
