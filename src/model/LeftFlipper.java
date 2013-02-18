package model;

import java.awt.Point;

public class LeftFlipper extends Flipper {

	public LeftFlipper(Point p, double width, double height) {
		super(p, width, height);
		super.minRotation = 0;
		super.maxRotation = -90;
	}

	@Override
	public void move()
	{
		if(super.active){
			if(super.rotation > maxRotation)super.rotation -= super.rotationIncrement;
		}else{
			if(super.rotation < minRotation)super.rotation += super.rotationIncrement;
		}
		super.setChanged();
		super.notifyObservers();
	}
	
	@Override
	public void setRotation(double r) {
		super.rotation = -r;
		super.maxRotation -= r;
		super.minRotation -= r;
	}
}
