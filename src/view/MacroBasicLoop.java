/**
 * @mainAuthor Joseph Ancona
 */
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

// Class for holding basic and conditional actions for macros.
public class MacroBasicLoop extends JPanel {

	JPanel basic_actions;
	JPanel conditioned_actions;
	
	public MacroBasicLoop(){
				
		basic_actions = new BasicActions();
		conditioned_actions = new Conditions();
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border title = BorderFactory.createTitledBorder(loweredetched, "Actions");
		
		/*layout*/
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(basic_actions);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(conditioned_actions);
		add(Box.createRigidArea(new Dimension(0,10)));
				
		setBorder(title);
		setVisible(true);
	}
}
