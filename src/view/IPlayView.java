package view;

import java.io.File;

public interface IPlayView {

	void dispose();

	/**
	 * Asks the user to select the map file to use.
	 * 
	 * @return The user's chosen file or NULL if the user cancels.
	 */
	File askForMapFile();

	/**
	 * Displays the given information message.
	 * 
	 * @param message
	 */
	void information(String message);

	/**
	 * Displays the given error message.
	 * 
	 * @param message
	 */
	void error(String message);

}
