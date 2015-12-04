/**
 * @mainAuthor Joseph Ancona
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Class to allow users to make name for macro and save it.
public class MacroName extends JPanel {


	JLabel label = new JLabel("Macro Name:");

	public JTextField tx = new JTextField(25);
	public JButton ok;

	JPanel topPanel = new JPanel();

	public MacroName() {

		ok = new JButton("Save");
		topPanel.add(label);
		topPanel.add(tx);
		topPanel.add(ok);

		add(topPanel);
	}
}
