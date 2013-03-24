import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.GraphicsController;
import controller.PhysicsController;
import controller.IController;

import model.Overlord;
import model.iOverlord;

import view.CompleteViewContainer;


public class Driver {

	public Driver() {
		
		CompleteViewContainer cvc = new CompleteViewContainer();
		iOverlord ov = new Overlord(cvc.getPlayView().getGridSize(), cvc.getPlayView().getCanvasSize());
		ov.loadGame("Input");
		ov.saveGame("Test");
		((Overlord) ov).addObserver(cvc.getPlayView());
		IController controller = new PhysicsController(ov);
		GraphicsController graphicsController = new GraphicsController(ov);
		cvc.addController(controller, graphicsController);
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
