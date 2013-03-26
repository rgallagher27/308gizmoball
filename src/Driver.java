import javax.swing.SwingUtilities;

import model.Overlord;
import model.iOverlord;
import view.CompleteViewContainer;
import controller.BuildController;
import controller.GraphicsController;
import controller.IController;
import controller.PhysicsController;


public class Driver {

	public Driver() {
		
		CompleteViewContainer cvc = new CompleteViewContainer();
		iOverlord ov = new Overlord(cvc.getPlayView().getGridSize(), cvc.getPlayView().getCanvasSize());
		ov.loadGame("Input");
		ov.saveGame("Test");
		((Overlord) ov).addObserver(cvc.getPlayView());
		IController controller = new PhysicsController(ov);
		BuildController buildCont = new BuildController(ov, cvc.getPlayView(), cvc);
		GraphicsController graphicsController = new GraphicsController(ov);
		cvc.addController(controller, graphicsController, buildCont);
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
