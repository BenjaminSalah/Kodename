/**
 * @mainAuthur Joseph Ancona
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Class to hold title message and prompt when program starts
public class StartPageTitle extends JPanel {
	
	JPanel panel = new JPanel();
	JPanel titlePanel = new JPanel();
	
	JLabel label = new JLabel("Welcome to Kodename!");
	JLabel label2 = new JLabel("Please select one of the pre-made worlds and then press the OK button to proceed.");
	
	GridBagConstraints c = new GridBagConstraints();

	public StartPageTitle() {
		
		c.insets = new Insets(25,25,25,25);
		
		titlePanel.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		titlePanel.add(label, c);
		c.gridy = 1;
		titlePanel.add(label2, c);
		
		add(titlePanel, BorderLayout.NORTH);
		
		label.setFont(new Font("Dialog", Font.BOLD, 50));
		label.setForeground(Color.BLUE);
	}
}
