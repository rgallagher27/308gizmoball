package view;

import java.awt.Color;

import model.physics.Circle;
import model.physics.LineSegment;
import view.framework.G2DAbstractCanvas;
import view.framework.G2DCircle;
import view.framework.G2DFlipper;
import view.framework.G2DGroup;
import view.framework.G2DLine;
import view.framework.G2DObject;
import view.framework.G2DPoint;
import view.framework.G2DRectangle;
import view.framework.G2DTriangle;
import view.framework.Matrix;
import controller.GraphicsController;

public class GizmoFactory {

	private GraphicsController controller;
	
	public GizmoFactory(GraphicsController ic) {
		controller = ic;
	}
	
	public G2DObject drawBall(String ball)
    {
        double cellWidth 		= controller.getBallCellWidth(ball);
		double cellheight 		= controller.getBallCellHeight(ball);

		double x 				= controller.getBallX(ball);
		double y 				= controller.getBallY(ball);
		
    	return new G2DCircle(new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), cellWidth/4, Color.yellow);
    }
	
	public G2DObject draw(String giz){
		if(giz.contains("F")) return drawFlipper(giz);
		if(giz.contains("A")) return drawAbsorber(giz);
		if(giz.contains("S")) return drawSquareBumper(giz);
		if(giz.contains("T")) return drawTriangleBumper(giz);
		if(giz.contains("C")) return drawCircleBumper(giz);
		return null;
	}
	
	public G2DObject drawFlipper(String flipper)
	{	
		
		double flipperX = 0;
		double flipperY = 0;
		double cellWidth 			= controller.getGizWidth(flipper);
		double cellheight			= controller.getGizHeight(flipper);
		double flipperGridX			= controller.getGizX(flipper);
		double flipperGridY			= controller.getGizY(flipper);
		double flipperGridWidth		= controller.getGizRowWidth(flipper);
		double flipperGridHeight	= controller.getGizColumnHeight(flipper);

		double flipperWidth 		= (flipperGridWidth  * cellWidth) / 4;
		double flipperHeight 		= (flipperGridHeight * cellheight);
		
		if(flipper.contains("L")){
			flipperX = ((flipperGridX * cellWidth)  + (cellWidth  / 2)) - flipperWidth;
			flipperY = ((flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		}else if(flipper.contains("R")){
			flipperX = (((flipperGridX + (flipperGridWidth / 2)) * cellWidth) + (cellWidth)) + flipperWidth;
			flipperY = (( flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		}
		
		G2DGroup flipperGroup = new G2DGroup();
		
		G2DObject flipperMain = new G2DFlipper(flipperX, flipperY, (int)flipperWidth, (int)flipperHeight, controller.getGizColour(flipper));
        
		rotateObjectAroundSelf(controller.getGizRotation(flipper),
									flipperGroup,
									flipperX,
									flipperY);
		
		flipperGroup.add(flipperMain);
		
		for(LineSegment l : controller.getLines(flipper)){
			flipperGroup.add(new G2DLine(new G2DPoint(l.p1().x(), l.p1().y()), new G2DPoint(l.p2().x(), l.p2().y()), Color.green));
		}
		
		for(Circle c : controller.getCircles(flipper)){
			flipperGroup.add(new G2DCircle(new G2DPoint(c.getCenter().x(), c.getCenter().y()), c.getRadius(), Color.green));
		}
		
		return flipperGroup;
	}

    public G2DObject drawAbsorber(String absorber)
    {
    	
    	double cellWidth		= controller.getGizWidth(absorber);
        double cellheight		= controller.getGizHeight(absorber);

		
		double x				= controller.getGizX(absorber) * cellWidth;
		double y				= controller.getGizY(absorber) * cellheight;
	
    	
    	return new G2DRectangle(new G2DPoint( x , y ), 
    			                new G2DPoint( x + (controller.getGizRowWidth(absorber) * cellWidth), y + (controller.getGizColumnHeight(absorber) * cellheight)), 
    			                Color.red);
    }
	
	public G2DObject drawSquareBumper(String bumper)
    {
		Color colour = controller.getGizColour(bumper);
		
        double cellWidth 		= controller.getGizWidth(bumper);
		double cellheight 		= controller.getGizHeight(bumper);
		
		double x = controller.getGizX(bumper);
		double y = controller.getGizY(bumper);
    	
    	return new G2DRectangle(x*cellWidth,
				                y*cellheight,
				                (x*cellWidth)+(cellWidth),
				                (y*cellheight)+(cellheight),
				                colour);
    }
    
    public G2DObject drawTriangleBumper(String triangle)
    {
		Color colour = controller.getGizColour(triangle);
		
        double cellWidth 		= controller.getGizWidth(triangle);
		double cellheight 		= controller.getGizHeight(triangle);
		
		double x 				= controller.getGizX(triangle);
		double y 				= controller.getGizY(triangle);
		
		G2DObject newTriangle = new G2DTriangle((int)(x*cellWidth),
					                (int)(y*cellheight),
					                (int)cellWidth,
					                (int)cellheight,
					                colour);
		
		this.rotateObjectAroundSelf( controller.getGizRotation(triangle), newTriangle, 
									 (x * cellWidth) + (cellWidth / 2), 
									 (y * cellheight) + (cellheight / 2)
									);
    	
    	return newTriangle;
    }
	
	public G2DObject drawCircleBumper(String circle)
    {
		Color colour = controller.getGizColour(circle);
		
        double cellWidth 		= controller.getGizWidth(circle);
		double cellheight 		= controller.getGizHeight(circle);
		
		double x 				= controller.getGizX(circle);
		double y 				= controller.getGizY(circle);
		
		return new G2DCircle( new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), 
							controller.getGizRowWidth(circle) * (cellWidth / 2),
							colour);
    }
	
	public void drawGrid(G2DAbstractCanvas canvas, int rows, int columns, double rowWidth, double columnHeight)
	{
		for(int i = 0; i <= columns; i++){
			int startPointX  = (int) (i * rowWidth);
			int startPointY  = 0;
			int endPointX 	 = startPointX;
			int endPointY    = (int) (startPointY + (columns * columnHeight));
			
			G2DLine line = new G2DLine(new G2DPoint(startPointX, startPointY), new G2DPoint(endPointX, endPointY), Color.gray);
			line.draw(canvas);
		}
		
		for(int i = 0; i <= rows; i++){
			int startPointX  = 0;
			int startPointY  = (int) (i * columnHeight);
			int endPointX 	 = (int) (startPointX + (rows * rowWidth));
			int endPointY    = startPointY;
			
			G2DLine line = new G2DLine(new G2DPoint(startPointX, startPointY), new G2DPoint(endPointX, endPointY), Color.gray);
			line.draw(canvas);
		}
	}

	/*
	 * A private method to perform the rotation matrix calculations.
	 * 
	 * Steps needed to rotate:
	 * 		*Move the objects center to the origin (0,0).
	 * 		*Rotate the Object.
	 * 		*Move the objects center back to its original position.
	 */
	private void rotateObjectAroundSelf(double rotationDegree, G2DObject object, double X, double Y)
	{			
		double[][] movetoorigin = 	{ {1.0, 0.0, -X} , 
									  {0.0, 1.0, -Y}, 
									  {0.0, 0.0, 1.0} 
									};
		
		double[][] rotate = { {Math.cos(Math.toRadians(rotationDegree)), -Math.sin(Math.toRadians(rotationDegree)), 0.0}, 
							  {Math.sin(Math.toRadians(rotationDegree)), Math.cos(Math.toRadians(rotationDegree)), 0.0}, 
							  {0.0, 0.0, 1.0} 
							};
		
		double[][] movetostart = { {1.0, 0.0, X} , 
								   {0.0, 1.0, Y}, 
								   {0.0, 0.0, 1.0} 
								 };

		object.transform(new Matrix(movetoorigin));
		object.transform(new Matrix(rotate));
		object.transform(new Matrix(movetostart));
	}

}
