package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Overlord implements iOverlord {
	
	private Dimension gridDimentions, canvasDimentions;
	
	private List<iGizmo> gizmoObjects;
	private List<iBall> ballObjects;
	private FileParser fileParser;
	
	private double cellWidth, cellHeight;

	public Overlord(Dimension gridDimentions, Dimension canvasDimentions) {
		
		this.gridDimentions	 	= gridDimentions;
		this.canvasDimentions	= canvasDimentions;
		this.cellWidth			= this.canvasDimentions.getWidth()  / this.gridDimentions.getWidth();
		this.cellHeight			= this.canvasDimentions.getHeight() / this.gridDimentions.getHeight();
		
		this.gizmoObjects 		= new ArrayList<iGizmo>();
		this.ballObjects		= new ArrayList<iBall>();
		this.fileParser			= new FileParser(gizmoObjects, ballObjects, this.cellWidth, this.cellHeight);
		
		this.fileParser.loadFile("Input");
		
		this.gizmoObjects.add(new Wall(cellWidth, cellHeight));

	}

	@Override
	public List<iGizmo> getAllGizmos() {
		return this.gizmoObjects;
	}

	@Override
	public List<iBall> getAllballs() {
		return this.ballObjects;
	}

	@Override
	public iGizmo getGizmo(String identifier) {
		for(iGizmo g : this.gizmoObjects)
			if(g.getIdentifier().equals(identifier))return g;
		
		return null;
	}

	@Override
	public iBall getBall(String identifier) {
		for(iBall b : this.ballObjects)
			if(b.getIdentifier().equals(identifier))return b;
		
		return null;
	}

	@Override
	public void addGizmoObserver(Observer o) {
		for(iGizmo g : this.gizmoObjects) ((Observable)g).addObserver(o);
	}

	@Override
	public void addBallObserver(Observer o) {
		for(iBall b : this.ballObjects) ((Observable)b).addObserver(o);
	}

}
