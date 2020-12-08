package utilisateur;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import elementsFrame.BlockInput;
import elementsFrame.FrameDragListener;
import elementsFrame.LesBoutons;
import elementsFrame.LesBoutonsSortir;
import elementsFrame.LesIconifies;
import elementsFrame.LesLiens;
import elementsFrame.LesMessages;

import java.awt.Toolkit;

public class FrameConnexion {

	private JFrame frmConnexion;
	JPanel panel_principal;
	JPanel panel_connexion;
	
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
	 * Create the application.
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConnexion = new JFrame();
		frmConnexion.setTitle("Connexion");
		frmConnexion.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FrameConnexion.class.getResource("/utilisateur/images/user.png")));
		frmConnexion.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmConnexion.setResizable(false);
		frmConnexion.setBounds(100, 100, 380, 370);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnexion.getContentPane().setLayout(null);
		frmConnexion.setUndecorated(true);
		frmConnexion.setLocationRelativeTo(null);
		
		FrameDragListener frameDragListener = new FrameDragListener(frmConnexion);
		frmConnexion.addMouseListener(frameDragListener);
		frmConnexion.addMouseMotionListener(frameDragListener);
		
		panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 380, 370);
		panel_principal.setBackground(new Color(194, 194, 194));
		frmConnexion.getContentPane().add(panel_principal);
	}
	private void titrePrincipal() {
		panel_principal.setLayout(null);
		JLabel lblNewLabel = new JLabel("NESTI");
		lblNewLabel.setBounds(95, 11, 182, 40);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNewLabel.setForeground(new Color(98, 129, 159));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		panel_principal.add(lblNewLabel);
	}	
	
	private void iconifie() {
		LesIconifies boutonIconifie = new LesIconifies(frmConnexion, 287);
		panel_principal.add(boutonIconifie);
	}
	
	private void sortir() {
		LesBoutonsSortir boutonsSortir = new LesBoutonsSortir(frmConnexion, 340);
		panel_principal.add(boutonsSortir);
	}
	
	private void titre() {
		panel_connexion = new JPanel();
		panel_connexion.setBackground(new Color(194, 194, 194));
		panel_connexion.setBorder(null);
		panel_connexion.setBounds(0, 53, 380, 317);
		panel_connexion.setLayout(null);
		panel_principal.add(panel_connexion);
		

		JLabel lblNewLabel_1 = new JLabel("CONNEXION");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(98, 129, 159));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 10, 380, 40);
		panel_connexion.add(lblNewLabel_1);
	}
	
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
	private void message() {
		message = new LesMessages(200, 380);
		panel_connexion.add(message);
	}
	
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
					System.out.println("coucou 1");
					try {
						user = user.chercherUser(listInput.get("pseudo").getText(), listInput.get("mdp").getText());	
						if (user != null) {
							System.out.println("coucou 2");
							new FrameVisuCompte(user).getfrmVisuCompte().setVisible(true);
							frmConnexion.dispose();					
						}else {
							System.out.println("coucou 3");
							ImageIcon icon = new ImageIcon(getClass().getResource("./images/ko.png"));
							JOptionPane.showMessageDialog(frmConnexion, "L'utilisateur n'est pas connue", "Erreur de connexion.", JOptionPane.WARNING_MESSAGE, icon);
							message.setText(" ");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel_connexion.add(connexion.getLabelTexte());
		panel_connexion.add(connexion.getLabelImage());
	}
	
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
	public JFrame getFrmConnexion() {
		return frmConnexion;
	}

	/**
	 * @param frmConnexion the frmConnexion to set
	 */
	public void setFrmConnexion(JFrame frmConnexion) {
		this.frmConnexion = frmConnexion;
	}
	
}
