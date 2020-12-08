package elementsFrame;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class LesPasswordField extends JPasswordField {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pos;
	private int pos1;

	public  LesPasswordField(int pos, int pos1) {
		setPos(pos);
		setPos1(pos1);
		
		this.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		this.setForeground(new Color(98, 129, 159));
		this.setBounds(getPos(), getPos1(), 230, 28);
		this.setColumns(10);
		this.setBorder(null);
		
	}

	/**
	 * @return the pos1
	 */
	public int getPos1() {
		return pos1;
	}

	/**
	 * @param pos1 the pos1 to set
	 */
	public void setPos1(int pos1) {
		this.pos1 = pos1;
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
	

}
