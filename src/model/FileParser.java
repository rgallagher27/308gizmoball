package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileParser {
    
	private iOverlord overlord;
    
    public FileParser(iOverlord overlord ) {
    	this.overlord 	= overlord;
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
                	this.overlord.addAbsorber(  tok[1], 
		                						Integer.parseInt(tok[2]), Integer.parseInt(tok[3]), 
		                						Integer.parseInt(tok[4]) - Integer.parseInt(tok[2]), 
		                						Integer.parseInt(tok[5]) - Integer.parseInt(tok[3])
		                					);
                    break;
                case "Ball":
                	this.overlord.addBall(tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]), Double.parseDouble(tok[4]), Double.parseDouble(tok[5]));
                    break;
                case "Circle":
                	this.overlord.addCircle(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "LeftFlipper":
                	this.overlord.addFlipper(tok[1], "L", Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "RightFlipper":
                	this.overlord.addFlipper(tok[1], "R", Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "Square":
                	this.overlord.addSquare(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "Triangle":
                	this.overlord.addTriangel(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    break;
                case "Gravity":
                    
                    break;
                case "Friction":
                    
                    break;
                case "Move":
                    this.overlord.move(tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]));
                    break;
                case "Rotate":
					this.overlord.rotate(tok[1]);
                    break;
                case "Connect":
                    
                    break;
                case "KeyConnect":
                    this.overlord.keyConnect(Integer.parseInt(tok[2]), tok[3], tok[4]);
                    break;
                case "Delete":
                    this.overlord.delete(tok[1]);
                    break;
                default: //Error throw up error message dialog box
                    break;
            }
        }
    }
    
    public void saveFile() {
        
    }
}