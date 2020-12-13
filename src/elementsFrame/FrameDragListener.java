package elementsFrame;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 * Cette class permet d'attraper les fenêtres afin de les déplacer 
 * @author CAVAUD
 *
 */
public class FrameDragListener extends MouseAdapter {
	private final JFrame frame;
    private Point mouseDownCompCoords = null;

    /**
     * Le constructeur recoit la fenêtre en paramètre
     * @param frame
     */
    public FrameDragListener(JFrame frame) {
        this.frame = frame;
    }
	
    public void mouseReleased(MouseEvent e) {
        mouseDownCompCoords = null;
    }

    public void mousePressed(MouseEvent e) {
        mouseDownCompCoords = e.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        Point currCoords = e.getLocationOnScreen();
        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
    }
}
