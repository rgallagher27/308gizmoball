package model;

import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.Arrays;

public class FileParser {
    
    private ArrayList<String[]> absorbers = new ArrayList<String[]>();

    public FileParser() {
        
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
                    absorbers.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Ball":  //create Ball
                    break;
                case "Bumper":  //create Bumper
                    break;
                case "Circle":  //create Circle
                    break;
                case "LeftFlipper":  //create LeftFlipper
                    break;
                case "RightFlipper":  //create RightFlipper
                    break;
                case "Square":  //create Square
                    break;
                case "Triangle":  //create Triangle
                    break;
                case "Wall":  //create Wall
                    break;
                case "Gravity": //set Gravity
                    break;
                case "Friction": //set Friction
                    break;
                case "Move": //move Gizmo
                    break;
                case "Rotate": //rotate Gizmo
                    break;
                case "Connect": //connect Gizmos
                    break;
                case "KeyConnect": //key connect Gizmo
                    break;
                default: //Error throw up error message dialog box
                    break;
            }
        }
    }
    
    public void saveFile() {
        
    }
    
    public ArrayList<String[]> getAbsorbers() {
        return absorbers;
    }

}