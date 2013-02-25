package model;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import view.PrototypeView;

import java.util.ArrayList;

public class FileParser {
    
    private PrototypeView pv;
    
    public FileParser(PrototypeView pv) {
        this.pv = pv;
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
                	this.pv.prototypeFlippers.add(
                							new Absorber(tok[1], new Point( Integer.parseInt(tok[2]), Integer.parseInt(tok[3]) ),
                									Integer.parseInt(tok[4]) - Integer.parseInt(tok[2]), Integer.parseInt(tok[5]) - Integer.parseInt(tok[3])));
                    //pv.addAbsorber(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]), Integer.parseInt(tok[4]), Integer.parseInt(tok[5]));
                    break;
                case "Ball":
                    pv.addBall(tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]), Double.parseDouble(tok[4]), Double.parseDouble(tok[5]));
                    break;
                case "Circle":
                	this.pv.prototypeFlippers.add(
                							new CircleBumper(tok[1], new Point(Integer.parseInt(tok[2]), Integer.parseInt(tok[3])),
                									1, 1));
                    //pv.addCircle(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "LeftFlipper":
                	this.pv.prototypeFlippers.add(
				                			new LeftFlipper(tok[1], new Point(Integer.parseInt(tok[2]), Integer.parseInt(tok[3])),
				                					1, 2));
                    //pv.addFlipper(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]), false);
                    break;
                case "RightFlipper":
                	this.pv.prototypeFlippers.add(
				                			new RightFlipper(tok[1], new Point(Integer.parseInt(tok[2]), Integer.parseInt(tok[3])),
				                					1, 2));
                    //pv.addFlipper(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]), true);
                    break;
                case "Square":
                	this.pv.prototypeFlippers.add( 
                							new SquareBumper(tok[1],
                									new Point( Integer.parseInt(tok[2]), Integer.parseInt(tok[3]) ), 
                									1, 1));
                    //pv.addSquare(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "Triangle":
                	this.pv.prototypeFlippers.add(
                							new TriangleBumper(tok[1], 
                									new Point( Integer.parseInt(tok[2]), Integer.parseInt(tok[3]) ), 
                									1, 1));
                    //pv.addTriangle(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "Gravity":
                    
                    break;
                case "Friction":
                    
                    break;
                case "Move":
                    pv.move(tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]));
                    break;
                case "Rotate":
					pv.rotate(tok[1]);
                    break;
                case "Connect":
                    
                    break;
                case "KeyConnect":
                    
                    break;
                case "Delete":
                    pv.delete(tok[1]);
                    break;
                default: //Error throw up error message dialog box
                    break;
            }
        }
    }
    
    public void saveFile() {
        
    }
    
}