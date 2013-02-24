import java.awt.Color;

import javax.swing.JFrame;

import view.PrototypeView;

public class Driver {

	public Driver() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)
	{	
		JFrame prototypeFrame = new JFrame();
		prototypeFrame.add(new PrototypeView());
		
		prototypeFrame.setBackground(Color.black);
		
		prototypeFrame.pack();
		prototypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prototypeFrame.setVisible(true);
	}

}
