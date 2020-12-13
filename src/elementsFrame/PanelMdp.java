package elementsFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Cette class construit la zone de force du mot de passe 
 * @author CAVAUD
 *
 */
public class PanelMdp {
		private JPanel panelMdp;
		private JLabel labelTitreMdp;
		private JLabel[] labelCaractMdp = new JLabel[5];
		private JLabel[] cocheKO8CaractMdp = new JLabel[5];
		private JLabel[] cocheOK8CaractMdp = new JLabel[5];
		private String[] texteLabel = {"Au moins 8 caractères", "Au moins 1 chiffre", "Au moins une minuscule", "Au moins une MAJUSCULE", "Au moins un : & # - + ! $ @ % "};
	
	/**
	 * Le constructeur reçoit la position de la zone
	 * @param x
	 * @param y
	 */
	public PanelMdp(int x, int y) {	
		panelMdp = new JPanel();
		panelMdp.setBorder(new LineBorder(Color.RED));
		panelMdp.setBounds(x, y, 250, 113);
		panelMdp.setLayout(null);
		panelMdp.setBackground(new Color(194, 194, 194));
		
		labelTitreMdp = new JLabel("Force du mot de passe");
		labelTitreMdp.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		labelTitreMdp.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitreMdp.setBounds(0, 0, 245, 19);
		panelMdp.add(labelTitreMdp);
		
		for(int i = 0; i < labelCaractMdp.length; i++) {
			labelCaractMdp[i] = new JLabel(texteLabel[i]);
			labelCaractMdp[i].setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
			labelCaractMdp[i].setBounds(35, 21 + (i * 18), 277, 14);
			labelCaractMdp[i].setForeground(Color.RED);
			panelMdp.add(labelCaractMdp[i]);
			
			cocheKO8CaractMdp[i] = new JLabel("");
			cocheKO8CaractMdp[i] .setBounds(10, 17 + (i * 18), 20, 20);
			try {
			    Image img2 = ImageIO.read(getClass().getResource("../images/komdp.png"));
			    cocheKO8CaractMdp[i] .setIcon(new ImageIcon(img2));
			} catch (IOException e1) {
			    e1.printStackTrace();
			}
			panelMdp.add(cocheKO8CaractMdp[i] );
			
			cocheOK8CaractMdp[i] = new JLabel("");
			cocheOK8CaractMdp[i].setBounds(10, 17 + (i * 18), 20, 20);
			try {
			    Image img2 = ImageIO.read(getClass().getResource("../images/okmdp.png"));
			    cocheOK8CaractMdp[i].setIcon(new ImageIcon(img2));
			} catch (IOException e1) {
			    e1.printStackTrace();
			}
			cocheOK8CaractMdp[i].setVisible(false);
			panelMdp.add(cocheOK8CaractMdp[i]);
		}
	}

	/**
	 * @return the panelMdp
	 */
	public JPanel getPanelMdp() {
		return panelMdp;
	}

	/**
	 * @param panelMdp the panelMdp to set
	 */
	public void setPanelMdp(JPanel panelMdp) {
		this.panelMdp = panelMdp;
	}

	/**
	 * @return the labelCaractMdp
	 */
	public JLabel[] getLabelCaractMdp() {
		return labelCaractMdp;
	}

	/**
	 * @param labelCaractMdp the labelCaractMdp to set
	 */
	public void setLabelCaractMdp(JLabel[] labelCaractMdp) {
		this.labelCaractMdp = labelCaractMdp;
	}

	/**
	 * @return the cocheKO8CaractMdp
	 */
	public JLabel[] getCocheKO8CaractMdp() {
		return cocheKO8CaractMdp;
	}

	/**
	 * @param cocheKO8CaractMdp the cocheKO8CaractMdp to set
	 */
	public void setCocheKO8CaractMdp(JLabel[] cocheKO8CaractMdp) {
		this.cocheKO8CaractMdp = cocheKO8CaractMdp;
	}

	/**
	 * @return the cocheOK8CaractMdp
	 */
	public JLabel[] getCocheOK8CaractMdp() {
		return cocheOK8CaractMdp;
	}

	/**
	 * @param cocheOK8CaractMdp the cocheOK8CaractMdp to set
	 */
	public void setCocheOK8CaractMdp(JLabel[] cocheOK8CaractMdp) {
		this.cocheOK8CaractMdp = cocheOK8CaractMdp;
	}
	
	
}
