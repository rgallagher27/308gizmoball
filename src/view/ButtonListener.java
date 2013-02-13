package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ButtonListener implements ActionListener {
	
	public static final int LOAD = 1;
	public static final int SAVE = 2;
	public static final int BUILD = 3;
	public static final int PLAY = 4;
	
	private final int type; 
	public ButtonListener(final int type){
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(type){
		case LOAD: JOptionPane.showMessageDialog(null, "Load button pressed."); break;
		case SAVE: JOptionPane.showMessageDialog(null, "Save button pressed."); break;
		case BUILD: JOptionPane.showMessageDialog(null, "Build button pressed."); break;
		case PLAY: JOptionPane.showMessageDialog(null, "Play button pressed."); break;
		default: 
		}
		 
	}

}