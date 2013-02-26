package model;

import java.awt.Point;

public class RightFlipper extends Flipper {

	public RightFlipper(String identifier, Point p,  double row, double column, double width, double height) {
		super(identifier, p, row, column, width, height);
		super.minRotation = 0;
		super.maxRotation = 90;
	}
	
	@Override
	public void move()
	{
		if(super.active){
			if(super.rotation < maxRotation)super.rotation += super.rotationIncrement;
		}else{
			if(super.rotation > minRotation)super.rotation -= super.rotationIncrement;
		}
		super.setChanged();
		super.notifyObservers();
	}
	
	@Override
	public void setRotation(double r) {
		super.rotation     = r;
		super.maxRotation += r;
		super.minRotation += r;
	}
}
