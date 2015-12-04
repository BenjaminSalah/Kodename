/**
 * @author Miracle Okubor
 */

package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

import control.Controller;

/**
 * This class is a container for all the elements that appear in the leftmost panel of 
 * the UI main Window
 * @author Miracle Okubor
 *
 */
public class ActionPanel extends JPanel{
	JPanel custom_actions;
	JPanel basic_actions;
	JPanel conditioned_actions;
	
	public ActionPanel(){
		super();
		
		custom_actions = new Customs();
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
		add(custom_actions);
		add(Box.createRigidArea(new Dimension(0,10)));
		
		setBorder(title);
		setVisible(true);
	}
}
