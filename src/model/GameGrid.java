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
	
	public boolean setGridPoint(Point p, int width, int height, boolean update)
	{	
		if( (p.getX() + width) > this.rows || (p.getY() + height) > this.columns ) return false;
		
		for(int i = p.x; i < p.x + width; i++){
			for(int j = p.y; j < p.y + height; j++){
				if(!this.gridPoints[i][j]){
					this.gridPoints[i][j] = update;
				}
				else return false;
			}
		}
		return true;
	}
	
	public void printGrid()
	{
		System.out.println("- - - - - - - - - - - - - - - - - - - - - -");
		for(int i = 0; i < this.rows; i++){
			System.out.print("| ");
			for(int j = 0; j < this.columns; j++){
				if(this.gridPoints[j][i]) System.out.print(1 + " ");
				else System.out.print(0 + " ");
			}
			System.out.println("|");
		}
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - ");
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
	public double getRowWidth() {
		return this.rows;
	}

	@Override
	public void setRowWidth(double w) {
		this.rows = w;
	}

	@Override
	public double getColumnHeight() {
		return this.columns;
	}

	@Override
	public void setColumnHeight(double h) {
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

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCellWidth(double w) {
		this.cellWidth = w;
	}

	@Override
	public void setCellHeight(double h) {
		this.cellHeight = h;
	}

	@Override
	public double timeUntilCollision(iBall ball) {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public void collide(iBall ball) {
		// TODO Auto-generated method stub
		
	}

}
