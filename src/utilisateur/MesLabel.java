package utilisateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MesLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String texte;
	private int pos;
	private int pos1;
	private String img;

	public MesLabel(String texte, String img, int pos, int pos1) {
		setTexte(texte);
		setImg(img);
		setPos(pos);
		setPos1(pos1);
		
		this.setText(getTexte());
		this.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		this.setForeground(new Color(98, 129, 159));
		this.setBounds(getPos(), getPos1(), 250, 45);
		
		if (getImg() != null) {
			try {
			    Image img2 = ImageIO.read(getClass().getResource("./images/" + getImg() + ".png"));
			    this.setIcon(new ImageIcon(img2));
			} catch (IOException e1) {
			    e1.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @return the pos1
	 */
	public int getPos1() {
		return pos1;
	}

	/**
	 * @param pos2 the pos2 to set
	 */
	public void setPos1(int pos1) {
		this.pos1 = pos1;
	}

	/**
	 * @return the texte
	 */
	public String getTexte() {
		return texte;
	}

	/**
	 * @param texte the texte to set
	 */
	public void setTexte(String texte) {
		this.texte = texte;
	}

	/**
	 * @return the pos
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img2 the img to set
	 */
	public void setImg(String img2) {
		this.img = img2;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
