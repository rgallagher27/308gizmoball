package model;

import java.awt.Dimension;
import java.awt.Point;

public class GameGrid {
	
	protected double rows, columns, cellWidth, cellHeight;
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

	public double getRowWidth() {
		return this.rows;
	}

	public void setRowWidth(double w) {
		this.rows = w;
	}

	public double getColumnHeight() {
		return this.columns;
	}

	public void setColumnHeight(double h) {
		this.columns = h;
	}

	public void setCellWidth(double w) {
		this.cellWidth = w;
	}

	public void setCellHeight(double h) {
		this.cellHeight = h;
	}

}
