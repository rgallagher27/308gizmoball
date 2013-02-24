package view.framework;

import java.awt.Color;

public class G2DFlipper implements G2DObject {
	
	private G2DGroup flipperGroup;
	private double xPos, yPos;
	private int width, height;

	public G2DFlipper(double x, double y, int width, int height) {
		this.flipperGroup = new G2DGroup();
		this.xPos  	= x;
		this.yPos  	= y;
		this.width 	= width;
		this.height = height;
		
		this.constructFlipperObject();
	}

	@Override
	public void draw(G2DAbstractCanvas absCanvas) {
		this.flipperGroup.draw(absCanvas);
	}
	
	private void constructFlipperObject()
	{
		int tlX, tlY, trX, trY, blX, blY, brX, brY;
		
		G2DCircle flipperHead = new G2DCircle(new G2DPoint(xPos, yPos), this.width, Color.green);

		tlX = (int) (this.xPos - (this.width));
		tlY = (int) this.yPos;
		
		trX = (int) (this.xPos + (this.width));
		trY = (int) this.yPos;
		
		blX = (int) (this.xPos - (this.width));
		blY = (int) (this.yPos + this.height - (this.width * 2));
		
		brX = (int) (this.xPos + (this.width));
		brY = (int) (this.yPos + this.height - (this.width * 2) );
		
		
		G2DPolygon 	flipperBody = new G2DPolygon(Color.green);
		flipperBody.addPoint(new G2DPoint(tlX, tlY));
		flipperBody.addPoint(new G2DPoint(trX, trY));
		flipperBody.addPoint(new G2DPoint(brX, brY));
		flipperBody.addPoint(new G2DPoint(blX, blY));
		
		G2DPoint bottomPoint = new G2DPoint(this.xPos, brY);
		
		G2DCircle flipperBottom = new G2DCircle(bottomPoint, this.width, Color.green);
		
		this.flipperGroup = new G2DGroup();
		this.flipperGroup.add(flipperHead);
		this.flipperGroup.add(flipperBody);
		this.flipperGroup.add(flipperBottom);
	}

	@Override
	public G2DObject deepClone() {
		// TODO Auto-generated method stub
		return this.flipperGroup.deepClone();
	}

	@Override
	public void setColor(Color color) {
		this.flipperGroup.setColor(color);
	}

	@Override
	public void transform(Matrix transformationMatrix) {
		this.flipperGroup.transform(transformationMatrix);
	}
    
    @Override
    public int getX() {
        return 0;
    }
    
    @Override
    public int getY() {
        return 0;
    }

}