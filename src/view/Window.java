/**
 * @mainAuthor Miracle Okubor
 */
package view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.Controller;

/**
 * An abstract class that extends java swing JFrame
 * @author mokubor
 *
 */

public abstract class Window extends JFrame{
	
	public static Controller cntrl;
	
	public Window(String title){
		super(title);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	
	/**
	 * This method allows the application to dispose of a window.
	 */
	public void close(){
		this.dispose();
	}
	
	

}
