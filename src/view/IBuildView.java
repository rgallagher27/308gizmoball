package view;

import java.io.File;

public interface IBuildView extends IPlayView{
	
	void dispose();
	
	void addSquares();
	
	void addTriangles();
	
	void addLeftFlipper();
	
	void addRightFlipper();
	
	void addAbsorber();
	
	void remove();
	
	void rotate();
	
	void addTrigger();
	
	void removeTrigger();
	
	/**
	 * Asks the user to give the name and location to save the map.
	 * 
	 * @return The user's chosen file path or NULL if the user cancels.
	 */
	File askForSaveFile();
	
	/**
	 * Asks the user the given question.
	 * @param question
	 * @return The user's answer.
	 */
	String ask(String question);
}
