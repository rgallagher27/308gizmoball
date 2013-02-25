package view.framework;

import java.awt.Color;


public class G2DTriangle implements G2DObject {
	private Color color;
	private int x;
	private int y;
    private G2DPolygon tri;
	
	public G2DTriangle(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		
		tri = new G2DPolygon(this.color);
		
        tri.addPoint(new G2DPoint(x ,y));
        tri.addPoint(new G2DPoint(x+width ,y));
        tri.addPoint(new G2DPoint(x ,y+height));
        
        this.x = this.x + width/2;
        this.y = this.y + height/2;
	}
    
    @Override
    public int getX() {
        return this.x;
    }
    
    @Override
    public int getY() {
        return this.y;
    }
	
	@Override
	public void draw(G2DAbstractCanvas absCanvas) {
		tri.draw(absCanvas);
	}
    
	@Override
	public G2DObject deepClone() {
		return tri.deepClone();
	}
    
	@Override
	public void setColor(Color color) {
		this.color = color;
	}
    
	@Override
	public void transform(Matrix transformationMatrix) {
		tri.transform(transformationMatrix);
	}
}