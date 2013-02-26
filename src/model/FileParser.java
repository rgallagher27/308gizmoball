package model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    
    private List<iGizmo> gizmos;
    private List<iBall> balls;
    private GameGrid gameGrid;
    
    public FileParser( List<iGizmo> g, List<iBall> b,  GameGrid gm ) {
        this.gizmos 	= g;
        this.balls 		= b;
        this.gameGrid 	= gm;
    }
    
    public void loadFile(String fileName) {
        ArrayList<String> input = new ArrayList<String>();
        String strLine = "";
        
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            while((strLine = br.readLine()) != null) {
                input.add(strLine);
            }
            br.close();
            in.close();
            fstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        generate(input);
    }
    
    private void generate(ArrayList<String> input) {
        for(String s : input) {
            String[] tok = s.split("\\s");
            
            switch(tok[0]) {
                case "":  //blank line in file
                    break;
                case "Absorber":
                	this.addAbsorber(   tok[1], 
                						Integer.parseInt(tok[2]), Integer.parseInt(tok[3]), 
                						Integer.parseInt(tok[4]) - Integer.parseInt(tok[2]), 
                						Integer.parseInt(tok[5]) - Integer.parseInt(tok[3])
                					);
                    break;
                case "Ball":
                	this.addBall(tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]));
                    break;
                case "Circle":
                	this.addCircle(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "LeftFlipper":
                	this.addFlipper(tok[1], "L", Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "RightFlipper":
                	this.addFlipper(tok[1], "R", Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "Square":
                	this.addSquare(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "Triangle":
                	this.addTriangel(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "Gravity":
                    
                    break;
                case "Friction":
                    
                    break;
                case "Move":
                    this.move(tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]));
                    break;
                case "Rotate":
					this.rotate(tok[1]);
                    break;
                case "Connect":
                    
                    break;
                case "KeyConnect":
                    this.keyConnect(Integer.parseInt(tok[2]), tok[3], tok[4]);
                    break;
                case "Delete":
                    this.delete(tok[1]);
                    break;
                default: //Error throw up error message dialog box
                    break;
            }
        }
    }
    
    public void saveFile() {
        
    }
    
    private void keyConnect(int keyCode, String type, String consumer )
    {
    	
    }
    
    private void delete(String name) {
    	for(iGizmo g : gizmos){
    		if( g.getIdentifier().equals(name) ) this.gizmos.remove(g);
    	}
    }
    
    private void move(String name, double x, double y) {
    	for(iGizmo g : gizmos){
    		if(g.getIdentifier().equals(name))g.setLocation( new Point((int)x, (int)y) );
    	}
    }
    
    private void rotate(String name) {
    	for(iGizmo g : gizmos){
    		if(g.getIdentifier().equals(name))g.setRotation(90);
    	}
    }
    
    private void addAbsorber(String id, int x, int y, int width, int height)
    {
    	if(this.gameGrid.setGridPoint(new Point(x, y), width, height, true)){
    		this.gizmos.add( new Absorber(id, new Point( x, y ), width, height, gameGrid.getCellWidth(), gameGrid.getCellHeight()) );
    	}
    }
    
    private void addBall(String id, double x, double y)
    {
    	if(this.gameGrid.setGridPoint(new Point((int)x, (int)y), 1, 1, true)){
    		System.out.println("Addin BALLS");
    		this.balls.add(new Ball(id, new Point2D.Double( x, y ), 1, 1, gameGrid.getCellWidth(), gameGrid.getCellHeight()) );
    	}
    }
    
    private void addCircle(String id, int x, int y)
    {
    	if(this.gameGrid.setGridPoint(new Point(x, y), 1, 1, true)){
    		this.gizmos.add(new CircleBumper(id, new Point(x, y), 1, 1, gameGrid.getCellWidth(), gameGrid.getCellHeight()));
    	}
    }
    
    private void addFlipper(String id, String type, int x, int y)
    {
    	if(this.gameGrid.setGridPoint(new Point(x, y), 2, 2, true)){
	    	if(type.equals("L")){
	    		this.gizmos.add(
            			new LeftFlipper(id, new Point(x, y),1, 2, gameGrid.getCellWidth(), gameGrid.getCellHeight()));
	    	}else if(type.equals("R")){
	    		this.gizmos.add(
            			new RightFlipper(id, new Point(x, y),1, 2, gameGrid.getCellWidth(), gameGrid.getCellHeight()));
	    	}
    	}
    }
    
    private void addSquare(String id, int x, int y)
    {
    	if(this.gameGrid.setGridPoint(new Point(x, y), 1, 1, true)){
    		this.gizmos.add( new SquareBumper(id, new Point( x, y ), 1, 1));
    	}
    }
    
    private void addTriangel(String id, int x, int y)
    {
    	if(this.gameGrid.setGridPoint(new Point(x, y), 1, 1, true)){
    		this.gizmos.add( new TriangleBumper(id, new Point( x, y ), 1, 1));
    	}
    }
    
}