package utilisateur;

import java.util.HashMap;

public class Maintest {

	public static void main(String[] args) {


		Utilisateur user = new Utilisateur();
		
		HashMap<String, String> listDonneeUser = new HashMap<String, String>();
		
		/*listDonneeUser.put("pseudo", "monpseudo");
		listDonneeUser.put("email", "monemain@mail.com");
		listDonneeUser.put("mdp", "monmdpAA&30");
		listDonneeUser.put("nom", "monnom");
		listDonneeUser.put("prenom", "monprenom");
		listDonneeUser.put("ville", "maville");
		
		try {
			user.ajouterModifierUtilisateur(listDonneeUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("1"+user.getListErreur());
		}
		
		if(user.getListErreur().isEmpty()) {
		
			try {
				user.creerUpdateUserSql();
			} catch (Exception e) {
				System.out.println("3"+user.getListErreur());
			}
		}
		
		if(user.getListErreur().isEmpty()) {
			System.out.println("tout ok");
		}*/
		
		
		listDonneeUser.put("pseudo", "monpseudo");
		listDonneeUser.put("email", "monemain@mail.fz");
		listDonneeUser.put("mdp", "monmdpAA&23");
		listDonneeUser.put("nom", "monnomjk");
		listDonneeUser.put("prenom", "monprenomjk");
		listDonneeUser.put("ville", "mavillejk");
		
		try {
			user.ajouterModifierUtilisateur(listDonneeUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("1"+user.getListErreur());
		}
		
		if(user.getListErreur().isEmpty()) {
			try {
				user.creerUpdateUserSql();
			} catch (Exception e) {
				System.out.println("3"+user.getListErreur());
			}
		}
		
		if(user.getListErreur().isEmpty()) {
			System.out.println("tout ok");
		}
		
	}

}
