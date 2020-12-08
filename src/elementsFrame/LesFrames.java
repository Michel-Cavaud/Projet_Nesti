package elementsFrame;

import java.awt.Toolkit;

import javax.swing.JFrame;


public class LesFrames extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LesFrames(String texte, int pos1, int pos2) {
		
		
		this.setTitle(texte);
	
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/user.png")));
		this.setSize(pos1, pos2);
		this.getContentPane().setLayout(null);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		
		FrameDragListener frameDragListener = new FrameDragListener(this);
		this.addMouseListener(frameDragListener);
		this.addMouseMotionListener(frameDragListener);
	}

}
