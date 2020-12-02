package utilisateur;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Toolkit;

public class FrameConnexion {

	private JFrame frmConnexion;
	JTextField message;
	HashMap<String, JTextField> listInput = new HashMap<String, JTextField>();
	Image img = null;
	
	private String messageText;
	
	Utilisateur user;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameConnexion window = new FrameConnexion();
					window.frmConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */

	/**
	 * Create the application.
	 */
	public FrameConnexion() {
		initialize();
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
		

		JPanel panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 380, 370);
		panel_principal.setBackground(new Color(194, 194, 194));
		frmConnexion.getContentPane().add(panel_principal);

		JLabel lblNewLabel = new JLabel("NESTI");
		lblNewLabel.setBounds(73, 10, 219, 40);
		panel_principal.add(lblNewLabel);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNewLabel.setForeground(new Color(98, 129, 159));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		
		JLabel reduit = new JLabel("–");
		reduit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reduit.setHorizontalAlignment(SwingConstants.CENTER);
		reduit.setBorder(null);
		reduit.setBackground(new Color(240, 240, 240));
		reduit.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
		reduit.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				frmConnexion.setState(Frame.ICONIFIED);
			}
		});
		reduit.setBounds(287, 11, 43, 31);
		panel_principal.add(reduit);

		JButton sortir = new JButton("");
		sortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConnexion.dispose();
			}
		});
		sortir.setBorder(null);
		sortir.setRequestFocusEnabled(false);
		sortir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sortir.setMargin(new Insets(0, 0, 0, 0));
		sortir.setBounds(340, 11, 31, 31);
		sortir.setBackground(new Color(194, 194, 194));
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/sortir2.png"));
			sortir.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel_principal.setLayout(null);
		panel_principal.add(sortir);

		JPanel panel_connexion = new JPanel();
		panel_connexion.setBackground(new Color(194, 194, 194));
		panel_connexion.setBorder(null);
		panel_connexion.setBounds(0, 53, 380, 544);
		panel_principal.add(panel_connexion);
		panel_connexion.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("CONNEXION");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(98, 129, 159));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 10, 380, 40);
		panel_connexion.add(lblNewLabel_1);
		
		
		BlockInput pseudo = new BlockInput(false, "Pseudo ou Email", "user", 44, 50);
		panel_connexion.add(pseudo.getBlock_1());
		panel_connexion.add(pseudo.getBlock_2());
		panel_connexion.add(pseudo.getBlock_3());
		panel_connexion.add(pseudo.getBlock_4());
		listInput.put("pseudo", pseudo.getBlock_3());
		
		BlockInput mdp = new BlockInput(true, "Mot de passe", "mdp", 44, 120);
		panel_connexion.add(mdp.getBlock_1());
		panel_connexion.add(mdp.getBlock_2());
		panel_connexion.add(mdp.getBlock_3p());
		panel_connexion.add(mdp.getBlock_4());
		listInput.put("mdp", mdp.getBlock_3p());


		message = new JTextField();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		//message.setVisible(false);
		//message.setBorder(new LineBorder(Color.RED));
		//message.setInheritsPopupMenu(true);
		message.setEditable(false);
		message.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		message.setBounds(0, 200, 380, 32);
		message.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
            	System.out.println(getMessageText());
            	message.setText(getMessageText());
            	
            	
            }
            
            public void focusLost(FocusEvent e)
            {
            	setMessageText(" ");
            	message.setText("");
            }
        });
		panel_connexion.add(message);

		JLabel lblNewLabel_8 = new JLabel("CONNEXION");
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setBounds(0, 240, 380, 48);
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				
				
			
				
				user = new Utilisateur();
				System.out.println("coucou 1");
				
				
				
				
				try {
					
					user = user.chercherUser(listInput.get("pseudo").getText(), listInput.get("mdp").getText());
				
					if (user != null) {
						System.out.println("coucou 2");
						new FrameCreerCompte(user).getFrmCreerCompte().setVisible(true);
						frmConnexion.dispose();
						
					}else {
						System.out.println("coucou 3");
						listInput.get("pseudo").requestFocus();
						setMessageText("Utilisateur non trouvé !");
						message.requestFocus();
						JOptionPane.showMessageDialog(null, 8.9, "This is not an integer.", JOptionPane.WARNING_MESSAGE);
						
					
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_connexion.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel();
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBorder(null);
		lblNewLabel_9.setBounds(0, 240, 380, 48);
		img = null;
		try {
			img = ImageIO.read(getClass().getResource("./images/bouton.png"));
			lblNewLabel_9.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel_9.setBackground(new Color(194, 194, 194));
		panel_connexion.add(lblNewLabel_9);

		JLabel creerCompte = new JLabel("Créer votre compte");
		creerCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creerCompte.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		creerCompte.setHorizontalAlignment(SwingConstants.CENTER);
		creerCompte.setBounds(0, 285, 380, 32);
		creerCompte.setForeground(new Color(98, 139, 159));
		creerCompte.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				new FrameCreerCompte(null).getFrmCreerCompte().setVisible(true);
				frmConnexion.dispose();
			}
		});
		panel_connexion.add(creerCompte);
	}
	
	

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
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
