/**
 * @mainAuthor Miracle Okubor
 */
package view;


import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

import control.*;

/**
 * The panel that makes up the right most section of the programs main window
 * It contains all elements of the panel. buttons, grid and console
 * @author mokubor
 *
 */
public class WorldPanel extends JPanel{
	JPanel buttons;
	JPanel grid;
	JPanel console;
	
	public WorldPanel(int x, int y){
		super();
		
		buttons = new WorldButtons();
		grid = new WorldGrid(x, y);
		console = new WorldConsole();
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border title = BorderFactory.createTitledBorder(loweredetched, "World");
		
		/*layout*/
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(buttons);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(grid);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(console);
		add(Box.createRigidArea(new Dimension(0,10)));
		
		setBorder(title);
		setVisible(true);
		
		
	}
	
	/*public static void main(String[] args) {
		JFrame temp = new JFrame();
		
		temp.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));
		
		WorldPanel ap = new WorldPanel(10, 10);
		
		temp.add(ap);
		temp.setSize(140, 600);
		temp.setLocationRelativeTo(null);
		//temp.setResizable(false);
		temp.setVisible(true);
		
		//temp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		temp.pack();
	}*/

}
