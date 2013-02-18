package view.framework;
import java.awt.Color;
import java.util.ArrayList;

public class G2DGroup implements G2DObject {
	
	ArrayList<G2DObject> objects ;
	
	public G2DGroup(){ objects = new ArrayList<G2DObject>();}
	
	private G2DGroup(ArrayList<G2DObject> objects){
		this.objects = objects;
	}

	@Override
	public void draw(G2DAbstractCanvas absCanvas) {
		for (G2DObject obj : objects) obj.draw(absCanvas);
	}

	@Override
	public G2DGroup deepClone() {
		ArrayList<G2DObject> newObjects = new ArrayList<G2DObject>();
		for (G2DObject obj : objects)
			newObjects.add(obj.deepClone());
		return new G2DGroup(newObjects);
	}

	@Override
	public void setColor(Color color) {
	}

	public void add(G2DObject obj){
		objects.add(obj);
	}

	@Override
	public void transform(Matrix transformationMatrix) {
		for (G2DObject o : objects) o.transform(transformationMatrix);
	}

}
