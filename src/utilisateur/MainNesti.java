package utilisateur;

import java.awt.EventQueue;

public class MainNesti {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameConnexion windowConnexion = new FrameConnexion();
					windowConnexion.getFrmConnexion().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
