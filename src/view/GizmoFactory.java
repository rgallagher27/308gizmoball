package view;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.Absorber;
import model.CircleBumper;
import model.LeftFlipper;
import model.Portal;
import model.RightFlipper;
import model.SquareBumper;
import model.TriangleBumper;
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
	private Random rnd = new Random();
	
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
	
	public G2DObject drawLine(String connect, String to){
		double conX;
		double conY;
		double toX;
		double toY;
		if(controller.getGizType(connect).equals(LeftFlipper._TYPE)){
			double cellWidth 			= controller.getGizWidth(connect);
			double cellheight			= controller.getGizHeight(connect);
			double flipperGridX			= controller.getGizX(connect);
			double flipperGridY			= controller.getGizY(connect);
			double flipperGridWidth		= controller.getGizRowWidth(connect);
			double flipperWidth 		= (flipperGridWidth  * cellWidth) / 4;
			
			conX = ((flipperGridX * cellWidth)  + (cellWidth  / 2)) - flipperWidth;
			conY = ((flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		}else if(controller.getGizType(connect).equals(RightFlipper._TYPE)){
			double cellWidth 			= controller.getGizWidth(connect);
			double cellheight			= controller.getGizHeight(connect);
			double flipperGridX			= controller.getGizX(connect);
			double flipperGridY			= controller.getGizY(connect);
			double flipperGridWidth		= controller.getGizRowWidth(connect);

			double flipperWidth 		= (flipperGridWidth  * cellWidth) / 4;
			
			conX = (((flipperGridX + (flipperGridWidth / 2)) * cellWidth) + (cellWidth)) + flipperWidth;
			conY = (( flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		}else{
			conX = (controller.getGizX(connect) * controller.getGizWidth(connect)) + (controller.getGizWidth(connect) / 2); 
			conY = (controller.getGizY(connect) * controller.getGizHeight(connect)) + (controller.getGizHeight(connect) / 2);
		}
		
		if(controller.getGizType(to).equals(LeftFlipper._TYPE)){
			double cellWidth 			= controller.getGizWidth(to);
			double cellheight			= controller.getGizHeight(to);
			double flipperGridX			= controller.getGizX(to);
			double flipperGridY			= controller.getGizY(to);
			double flipperGridWidth		= controller.getGizRowWidth(to);
			double flipperWidth 		= (flipperGridWidth  * cellWidth) / 4;
			
			toX = ((flipperGridX * cellWidth)  + (cellWidth  / 2)) - flipperWidth;
			toY = ((flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		}else if(controller.getGizType(to).equals(RightFlipper._TYPE)){
			double cellWidth 			= controller.getGizWidth(to);
			double cellheight			= controller.getGizHeight(to);
			double flipperGridX			= controller.getGizX(to);
			double flipperGridY			= controller.getGizY(to);
			double flipperGridWidth		= controller.getGizRowWidth(to);

			double flipperWidth 		= (flipperGridWidth  * cellWidth) / 4;
			
			toX = (((flipperGridX + (flipperGridWidth / 2)) * cellWidth) + (cellWidth)) + flipperWidth;
			toY = (( flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		}else{
			toX = (controller.getGizX(to) * controller.getGizWidth(to)) + (controller.getGizWidth(to) / 2); 
			toY = (controller.getGizY(to) * controller.getGizHeight(to)) + (controller.getGizHeight(to) / 2);
		}
		
		return new G2DLine(conX, conY, toX, toY, new Color(rnd.nextInt(155) + 100, rnd.nextInt(155) + 100, rnd.nextInt(155) + 100));
		
	}
	
	public List<G2DObject> drawBounds(String giz){
		LinkedList<G2DObject> tmp = new LinkedList<G2DObject>();
		tmp.clear();
		
		double cellWidth 			= controller.getGizWidth(giz);
		double cellHeight			= controller.getGizHeight(giz);
		double GridX				= controller.getGizX(giz);
		double GridY				= controller.getGizY(giz);
		double width				= controller.getGizRowWidth(giz);
		double height				= controller.getGizColumnHeight(giz);
		double topLX;
		double topLY;
		double topRX;
		double topRY;
		double bottomLX;
		double bottomLY;
		double bottomRX;
		double bottomRY;
		
		if(!giz.contains("F")){
			topLX = GridX * cellWidth;
			topLY = GridY * cellHeight;
		
			topRX = topLX + (width * cellWidth);
			topRY = topLY;
		
			bottomLX = topLX;
			bottomLY = topLY + (height * cellHeight);
		
			bottomRX = topRX;
			bottomRY = bottomLY;
		
		}else{
			topLX = GridX * cellWidth;
			topLY = GridY * cellHeight;
			
			topRX = topLX + ((2 *width) * cellWidth);
			topRY = topLY;
			
			bottomLX = topLX;
			bottomLY = topLY + (height * cellHeight);
			
			bottomRX = topRX;
			bottomRY = bottomLY;
		}
		
		tmp.add(new G2DLine(topLX, topLY, topRX, topRY, Color.CYAN));
	
		tmp.add(new G2DLine(bottomLX, bottomLY, bottomRX, bottomRY, Color.CYAN));
	
		tmp.add(new G2DLine(topLX, topLY, bottomLX, bottomLY, Color.CYAN));
	
		tmp.add(new G2DLine(topRX, topRY, bottomRX, bottomRY, Color.CYAN));
		
		return tmp;
		
		
	}
	
	public G2DObject draw(String giz){
		switch (controller.getGizType(giz)) {
			case LeftFlipper._TYPE:
				return drawLeftFlipper(giz);
			case RightFlipper._TYPE:
				return drawRightFlipper(giz);
			case Absorber._TYPE:
				return drawAbsorber(giz);
			case SquareBumper._TYPE:
				return drawSquareBumper(giz);
			case TriangleBumper._TYPE:
				return drawTriangleBumper(giz);
			case CircleBumper._TYPE:
				return drawCircleBumper(giz);
			case Portal._TYPE:
				return drawPortal(giz);
			default:
				return null;
		}
	}
	
	public G2DObject drawPortal(String portal)
	{
		Color colour = controller.getGizColour(portal);
		
        double cellWidth 		= controller.getGizWidth(portal);
		double cellheight 		= controller.getGizHeight(portal);
		
		double x 				= controller.getGizX(portal);
		double y 				= controller.getGizY(portal);
		
		double x2 				= controller.getPortalX2(portal);
		double y2				= controller.getPortalY2(portal);
		
		G2DGroup circleGroup 	= new G2DGroup();
		
		G2DObject centerCircle 	= new G2DCircle(new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), 
												controller.getGizRowWidth(portal) * (cellWidth / 8),
												Color.red);
		
		G2DObject innerCircle 	= new G2DCircle(new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), 
												controller.getGizRowWidth(portal) * (cellWidth / 4),
												Color.orange);
		
		G2DObject outerCircle 	= new G2DCircle( new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), 
												controller.getGizRowWidth(portal) * (cellWidth / 2),
												colour);
		

		G2DObject centerCircle2 	= new G2DCircle(new G2DPoint((int)(x2*cellWidth)+(cellWidth/2), (int)(y2*cellheight)+(cellheight/2)), 
												controller.getGizRowWidth(portal) * (cellWidth / 8),
												Color.red);
		
		G2DObject innerCircle2 	= new G2DCircle(new G2DPoint((int)(x2*cellWidth)+(cellWidth/2), (int)(y2*cellheight)+(cellheight/2)), 
												controller.getGizRowWidth(portal) * (cellWidth / 4),
												Color.orange);
		
		G2DObject outerCircle2 	= new G2DCircle( new G2DPoint((int)(x2*cellWidth)+(cellWidth/2), (int)(y2*cellheight)+(cellheight/2)), 
												controller.getGizRowWidth(portal) * (cellWidth / 2),
												colour);

		circleGroup.add(outerCircle);
		circleGroup.add(innerCircle);
		circleGroup.add(centerCircle);
		
		circleGroup.add(outerCircle2);
		circleGroup.add(innerCircle2);
		circleGroup.add(centerCircle2);
		
		return circleGroup;
	}
	
	public G2DObject drawLeftFlipper(String flipper)
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
		
		flipperX = ((flipperGridX * cellWidth)  + (cellWidth  / 2)) - flipperWidth;
		flipperY = ((flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		
		
		G2DGroup flipperGroup = new G2DGroup();
		
		G2DObject flipperMain = new G2DFlipper(flipperX, flipperY, (int)flipperWidth, (int)flipperHeight, controller.getGizColour(flipper));
		
		flipperGroup.add(flipperMain);
	
		rotateObjectAroundSelf(controller.getGizRotation(flipper),
				flipperGroup,
				flipperX,
				flipperY);
		
		return flipperGroup;
	}
	
	public G2DObject drawRightFlipper(String flipper)
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
		
		flipperX = (((flipperGridX + (flipperGridWidth / 2)) * cellWidth) + (cellWidth)) + flipperWidth;
		flipperY = (( flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		
		G2DGroup flipperGroup = new G2DGroup();
		
		G2DObject flipperMain = new G2DFlipper(flipperX, flipperY, (int)flipperWidth, (int)flipperHeight, controller.getGizColour(flipper));
		
		flipperGroup.add(flipperMain);
		
		rotateObjectAroundSelf(controller.getGizRotation(flipper),
				flipperGroup,
				flipperX,
				flipperY);
		
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
		
		G2DGroup squareGroup = new G2DGroup();
		
		G2DRectangle innerSquare = new G2DRectangle( 	(x*cellWidth) 				  + (cellWidth/4), 
														(y*cellheight) 				  + (cellheight/4),
														((x*cellWidth)+(cellWidth))   - (cellWidth/4),   
														((y*cellheight)+(cellheight)) - (cellheight/4),
														Color.green);
		
		G2DRectangle outerSquare = new G2DRectangle(	x*cellWidth,
									                	y*cellheight,
									                	(x*cellWidth)+(cellWidth),
									                	(y*cellheight)+(cellheight),
									                	colour);
		
		squareGroup.add(outerSquare);
		squareGroup.add(innerSquare);
    	
    	return squareGroup;
    }
    
    public G2DObject drawTriangleBumper(String triangle)
    {
		Color colour = controller.getGizColour(triangle);
		
        double cellWidth 		= controller.getGizWidth(triangle);
		double cellheight 		= controller.getGizHeight(triangle);
		
		double x 				= controller.getGizX(triangle);
		double y 				= controller.getGizY(triangle);
		
		G2DGroup triangleGroup 	= new G2DGroup();
		
		G2DObject innerTriangle = new G2DTriangle(	(int)((x*cellWidth)  + (cellWidth/8)),
									                (int)((y*cellheight) + (cellheight/8)),
									                (int)(cellWidth/2),
									                (int)(cellheight/2), 
													Color.red);
		
		G2DObject outerTriangle = new G2DTriangle(	(int)(x*cellWidth),
									                (int)(y*cellheight),
									                (int)cellWidth,
									                (int)cellheight,
									                colour);
		
		triangleGroup.add(outerTriangle);
		triangleGroup.add(innerTriangle);
		
		this.rotateObjectAroundSelf( controller.getGizRotation(triangle), triangleGroup, 
									 (x * cellWidth) + (cellWidth / 2), 
									 (y * cellheight) + (cellheight / 2)
									);
    	return triangleGroup;
    }
	
	public G2DObject drawCircleBumper(String circle)
    {
		Color colour = controller.getGizColour(circle);
		
        double cellWidth 		= controller.getGizWidth(circle);
		double cellheight 		= controller.getGizHeight(circle);
		
		double x 				= controller.getGizX(circle);
		double y 				= controller.getGizY(circle);
		
		G2DGroup circleGroup 	= new G2DGroup();
		
		G2DObject innerCircle 	= new G2DCircle(new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), 
												controller.getGizRowWidth(circle) * (cellWidth / 4),
												Color.orange);
		
		G2DObject outerCircle 	= new G2DCircle( new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), 
												controller.getGizRowWidth(circle) * (cellWidth / 2),
												colour);

		circleGroup.add(outerCircle);
		circleGroup.add(innerCircle);
		
		return circleGroup;
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
