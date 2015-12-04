/**
 * @mainAuthor Joseph Ancona
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

// Its a class to hold one button.
public class StartPageOK extends JPanel {

	JPanel panel = new JPanel();	
	public JButton okBut = new JButton("      OK      ");
	
	public StartPageOK() {
		
		panel.add(okBut);
		add(panel, BorderLayout.SOUTH);
	}
}
