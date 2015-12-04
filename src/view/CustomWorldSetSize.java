/**
 * @mainAuthor Isaac Tyan
 */
package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.World;

public class CustomWorldSetSize extends JDialog{
	
	JLabel sizeL = new JLabel("Dimensions (n x n): ");
	
	String[] sizeArr = {"2", "3", "4", "5", "6", "7", "8"};
	JComboBox sizeBox = new JComboBox(sizeArr);
	
	JButton createBut = new JButton("Create World");
	JButton cancelBut = new JButton("Cancel");
	
	public CustomWorldSetSize(){
		
		setLayout(new FlowLayout());
		add(sizeL);
		add(sizeBox);
		add(createBut);
		add(cancelBut);
		
		createBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int size = Integer.parseInt((String)sizeBox.getItemAt(sizeBox.getSelectedIndex()));
					CustomWorld cW = new CustomWorld(new World(size, size));
					cW.setVisible(true);
					cW.setSize(1000,600);
					cW.setLocationRelativeTo(null);
					dispose();
				}

		});
		
		cancelBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, 
			            "Are you sure you want to close out this window?", 
			            "Cancel Confirmation", JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});		
	}	
}
