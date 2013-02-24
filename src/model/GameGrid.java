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
		this.location 	= null;
	}
	
	public boolean isEmpty(Point p)
	{
		return !this.gridPoints[p.x][p.y];
	}
	
	public void setGridPoint(Point p, int width, int height, boolean update)
	{
		System.out.println("Gizmo Strating at:" + p.toString());
		for(int i = p.x; i <= p.x + width; i++){
			for(int j = p.y; j < p.y + height; j++){
				System.out.println("Grid Pos: " + i + " : " + j );
				this.gridPoints[i][j] = update;
			}
		}
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
		return this.location;
	}

	@Override
	public void setLocation(Point p) {
		this.location = p;
	}

	@Override
	public double getWidth() {
		return this.rows;
	}

	@Override
	public void setWidth(double w) {
		this.rows = w;
	}

	@Override
	public double getHeight() {
		return this.columns;
	}

	@Override
	public void setHeight(double h) {
		this.columns = h;
	}

	@Override
	public double getRotation() {
		return this.rotation;
	}

	@Override
	public void setRotation(double r) {
		this.rotation = r;
	}

	@Override
	public void move() {
		//No functionality needed
	}

}
