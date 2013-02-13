package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

/**
 * Adapted from MySecondCanvas used in G2D framework in CS309.
 * @author James Byrne
 *
 */

public class GizmoCanvas extends Canvas implements Observer {
	
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g){
		if(this.isDisplayable()){
			java.awt.Image bufferImage = createImage(getWidth(), getHeight() );
			
			java.awt.Graphics2D buffer = (Graphics2D) bufferImage.getGraphics();
			//Create the buffer and draw to the graphics
			buffer.clearRect(0, 0, getWidth(), getHeight());
			//Turn antialiasing on
			buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			buffer.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setColor(Color.RED);
			g.setFont(g.getFont().deriveFont(20f));
			g.setFont(g.getFont().deriveFont(Font.BOLD));
			g.drawString("This is the Canvas", 200, this.getHeight()/2);
//			g.drawImage(bufferImage, 0, 0, null); 
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}