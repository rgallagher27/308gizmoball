package model;

import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.Arrays;

public class FileParser {
    
    private ArrayList<String[]> absorbers = new ArrayList<String[]>();
    private ArrayList<String[]> balls = new ArrayList<String[]>();
    private ArrayList<String[]> circles = new ArrayList<String[]>();
    private ArrayList<String[]> lFlippers = new ArrayList<String[]>();
    private ArrayList<String[]> rFlippers = new ArrayList<String[]>();
    private ArrayList<String[]> squares = new ArrayList<String[]>();
    private ArrayList<String[]> triangles = new ArrayList<String[]>();
    private ArrayList<String[]> walls = new ArrayList<String[]>();
    private ArrayList<String[]> gravity = new ArrayList<String[]>();
    private ArrayList<String[]> friction = new ArrayList<String[]>();
    private ArrayList<String[]> moves = new ArrayList<String[]>();
    private ArrayList<String[]> rotates = new ArrayList<String[]>();
    private ArrayList<String[]> connects = new ArrayList<String[]>();
    private ArrayList<String[]> keyConnects = new ArrayList<String[]>();
    private ArrayList<String[]> deletes = new ArrayList<String[]>();

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
                case "Ball":
                    balls.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Circle":
                    circles.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "LeftFlipper":
                    lFlippers.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "RightFlipper":
                    rFlippers.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Square":
                    squares.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Triangle":
                    triangles.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Wall":
                    walls.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Gravity":
                    gravity.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Friction":
                    friction.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Move":
                    moves.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Rotate":
                    rotates.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Connect":
                    connects.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "KeyConnect":
                    keyConnects.add(Arrays.copyOfRange(tok, 1, tok.length));
                    break;
                case "Delete":
                    deletes.add(Arrays.copyOfRange(tok, 1, tok.length));
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
    
    public ArrayList<String[]> getBalls() {
        return balls;
    }
    
    public ArrayList<String[]> getCircles() {
        return circles;
    }
    
    public ArrayList<String[]> getLFlippers() {
        return lFlippers;
    }
    
    public ArrayList<String[]> getRFlippers() {
        return rFlippers;
    }
    
    public ArrayList<String[]> getSquares() {
        return squares;
    }
    
    public ArrayList<String[]> getTriangles() {
        return triangles;
    }
    
    public ArrayList<String[]> getWalls() {
        return walls;
    }
    
    public ArrayList<String[]> getGravity() {
        return gravity;
    }
    
    public ArrayList<String[]> getFriction() {
        return friction;
    }
    
    public ArrayList<String[]> getMoves() {
        return moves;
    }
    
    public ArrayList<String[]> getRotates() {
        return rotates;
    }
    
    public ArrayList<String[]> getConnects() {
        return connects;
    }
    
    public ArrayList<String[]> getKeyConnects() {
        return keyConnects;
    }
    
    public ArrayList<String[]> getDeletes() {
        return deletes;
    }

}