package model;

import java.awt.Dimension;
import java.awt.Point;

public class GameGrid implements iGizmo {
	
	protected Point location;
	protected double rows, columns, cellWidth, cellHeight, rotation;
	protected boolean gridPoints[][];

	public GameGrid(int rows, int columns, Dimension canvasSize) {
		this.rows 		= rows;
		this.columns 	= columns;
		this.cellWidth 	= canvasSize.getWidth()  / rows;
		this.cellHeight = canvasSize.getHeight() / columns;
		this.gridPoints = new boolean[(int) this.rows][(int) this.columns];
	}
	
	public boolean isEmpty(Point p)
	{
		return this.gridPoints[p.x][p.y];
	}
	
	public void setGridPoint(Point p, boolean update)
	{
		this.gridPoints[p.x][p.y] = update;
	}
	
	public double getCellWidth()
	{
		return this.cellWidth;
	}
	
	public double getCellHeight()
	{
		return this.cellHeight;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return this.location;
	}

	@Override
	public void setLocation(Point p) {
		this.location = p;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.rows;
	}

	@Override
	public void setWidth(double w) {
		this.rows = w;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return this.columns;
	}

	@Override
	public void setHeight(double h) {
		this.columns = h;
	}

	@Override
	public double getRotation() {
		// TODO Auto-generated method stub
		return this.rotation;
	}

	@Override
	public void setRotation(double r) {
		this.rotation = r;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

}
