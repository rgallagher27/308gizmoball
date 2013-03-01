import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.PrototypeView;

public class Driver {

	public Driver() {
		JFrame prototypeFrame = new JFrame();
		prototypeFrame.add(new PrototypeView());
		
		prototypeFrame.setBackground(Color.black);
		
		prototypeFrame.pack();
		prototypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prototypeFrame.setVisible(true);
	}
	
	public static void main(String[] args)
	{	
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Driver();
			}
		});
	}

}
