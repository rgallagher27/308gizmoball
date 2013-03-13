import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.AnimationEventListener;
import controller.IController;

import model.Overlord;
import model.iOverlord;

import view.CompleteViewContainer;
import view.PrototypeView;

public class Driver {

	public Driver() {
		
		CompleteViewContainer cvc = new CompleteViewContainer();
		iOverlord ov = new Overlord(cvc.getPlayView().getGridSize(), cvc.getPlayView().getCanvasSize());
		ov.loadGame("Input");
		ov.saveGame("Test");
		((Overlord) ov).addObserver(cvc.getPlayView());
		IController controller = new AnimationEventListener(ov);
		cvc.addController(controller);
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
