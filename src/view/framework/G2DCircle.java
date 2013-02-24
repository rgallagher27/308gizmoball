package view.framework;

import java.awt.Color;


public class G2DCircle implements G2DObject {
	private G2DPoint centre;
	private double radius;
	private Color color;
	private G2DPolygon circle;
	
	
	public G2DCircle(G2DPoint centre, double radius, Color color) {
		this.centre = centre;
		this.radius = radius;
		this.color = color;
		
		circle = new G2DPolygon(this.color);
		
		for(int i = 0; i <= 1000; i++) {
			G2DPoint temp = new G2DPoint(this.centre.getX() ,this.centre.getY());
			
			double[][] rotate = { {1.0, 0.0, this.radius*Math.sin(Math.toRadians(0.36*i))} , 
								  {0.0, 1.0, -this.radius*Math.cos(Math.toRadians(0.36*i))}, 
								  {0.0, 0.0, 1.0} 
								};
			Matrix buildCircle = new Matrix(rotate);
			temp.transform(buildCircle);
			
			circle.addPoint(temp);
		}
	}
    
    @Override
    public int getX() {
        return 0;
    }
    
    @Override
    public int getY() {
        return 0;
    }
	
	@Override
	public void draw(G2DAbstractCanvas absCanvas) {
		circle.draw(absCanvas);
	}

	@Override
	public G2DObject deepClone() {
		return circle.deepClone();
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void transform(Matrix transformationMatrix) {
		circle.transform(transformationMatrix);
	}
}
