package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import exception.CannotRotateException;

public class FileParser {
    
	private iOverlord overlord;
    private File saveFile;
    private FileWriter fw;
    private BufferedWriter bw;

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
        ArrayList<String> ids = new ArrayList<String>();

        for(String s : input) {
            String[] tok = s.split("\\s");

            switch(tok[0]) {
            case "":  //blank line in file
                break;
            case "Absorber":
                if(!checkIDs(ids, tok[1])) {
                    overlord.addAbsorber(  tok[1], 
						Integer.parseInt(tok[2]), 
                        Integer.parseInt(tok[3]), 
						Integer.parseInt(tok[4]), 
						Integer.parseInt(tok[5]));
                    ids.add(tok[1]);
                } else {
                    overlord.fileError();
                }
                break;
            case "Ball":
                if(!checkIDs(ids, tok[1])) {
                    overlord.addBall(tok[1], "", Float.parseFloat(tok[2]), Float.parseFloat(tok[3]), Double.parseDouble(tok[4])/25, Double.parseDouble(tok[5])/25);
                    ids.add(tok[1]);
                } else {
                    overlord.fileError();
                }
                break;
            case "Circle":
                if(!checkIDs(ids, tok[1])) {
                    overlord.addCircle(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    ids.add(tok[1]);
                } else {
                    overlord.fileError();
                }
                break;
            case "LeftFlipper":
                if(!checkIDs(ids, tok[1])) {
                    overlord.addFlipper(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]), false);
                    ids.add(tok[1]);
                } else {
                    overlord.fileError();
                }
                break;
            case "RightFlipper":
                if(!checkIDs(ids, tok[1])) {
                    overlord.addFlipper(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]), true);
                    ids.add(tok[1]);
                } else {
                    overlord.fileError();
                }
                break;
            case "Square":
                if(!checkIDs(ids, tok[1])) {
                    overlord.addSquare(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    ids.add(tok[1]);
                } else {
                    overlord.fileError();
                }
                break;
            case "Triangle":
                if(!checkIDs(ids, tok[1])) {
                    overlord.addTriangle(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                    ids.add(tok[1]);
                } else {
                    overlord.fileError();
                }
                break;
            case "Portal":
                if(!checkIDs(ids, tok[1])) {
                   overlord.addPortal(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]), Integer.parseInt(tok[4]), Integer.parseInt(tok[5]));
                    ids.add(tok[1]);
                } else {
                    overlord.fileError();
                }
                break;
            case "Gravity":
                overlord.setGravity(Float.parseFloat(tok[1]));
                break;
            case "Friction":
                overlord.setFriction(Float.parseFloat(tok[1]), Float.parseFloat(tok[2]));
                break;
            case "Move":
                overlord.moveGizmo(tok[1], Integer.parseInt(tok[2]), Integer.parseInt(tok[3]));
                break;
            case "Rotate":
			try {
				overlord.rotateGizmo(tok[1]);
			} catch (CannotRotateException e) {
				System.err.println(tok[1] + " cannot be rotated, this is invalid input.");
			}
                break;
            case "Connect":
                overlord.connect(tok[1], tok[2]);
                break;
            case "KeyConnect":
                boolean temp = false;
                if(tok[3].equals("up")) {
                    temp = true;
                } else if(tok[3].equals("down")) {
                    temp = false;
                } else {
                    overlord.fileError();
                }
                overlord.keyConnect(Integer.parseInt(tok[2]), temp, tok[4]);
                break;
            case "Delete":
                overlord.removeGizmo(tok[1]);
                break;
            default: 
                overlord.fileError();
                break;
            }
        }
    }
    
    public void saveFile(String fileName) {
        try {
            saveFile = new File(fileName);
 
            // If file doesnt exists, then create it
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            } else {
                //Override Message
                //Override to blank file
                saveFile.delete();
                saveFile = new File(fileName);
                saveFile.createNewFile();
            }

            fw = new FileWriter(saveFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGizmo(String gizmoString) {
        try {
            bw.write(gizmoString);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeSaveFile() {
        try {
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIDs(ArrayList<String> ids, String ident) {
        for(String id : ids) {
            if(id.equals(ident)) {
                return true;
            }
        }
        return false;
    }
}