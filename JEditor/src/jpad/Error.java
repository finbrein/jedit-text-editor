/*
 * 
 */
package jpad;

import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * <b>Program:</b> JEdit - Programmer's Editor<br>
 * <b>Copyright:</b> 2011 Michael Aro<br>
 * <b>License:</b> Free Software<br>
 * <b>Version:</b> 1.0<br>
 * <b>Date:</b> 29.05.2011<br>
 * <br>
 * <b>Info:</b> Class for error output
 */
public class Error {
	
	/**
	 * Constructor Returns the error to STDERR and as a dialogue.
	 *
	 * @param error the error
	 */
	public Error(String error) {
		System.err.println(error);
		JOptionPane.showMessageDialog(null, error, "An error occured", JOptionPane.ERROR_MESSAGE);
	}
}