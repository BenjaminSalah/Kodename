/**
 * @mainAuthor Isaac Tyan
 * @helper Stephen Chung
 */

package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import control.Controller;
import model.World;
import model.World.Contents;

/**
 * IMPORTANT: All worlds created through CustomWorld are SQUARE.
 */
public class CustomWorld extends JDialog{

	JFrame mW;

	JTextField xCoorTF = new JTextField(3);
	JTextField yCoorTF = new JTextField(3);

	String[] elementArr = {"Beeper", "Wall", "None"};
	JComboBox elementBox = new JComboBox(elementArr);

	JButton addBut = new JButton("Add element");
	JButton doneBut = new JButton("Take Me To My World");
	JButton cancelBut = new JButton("Cancel");		
	JLabel xCoorL = new JLabel("X Coordinate: ");
	JLabel yCoorL = new JLabel("Y Coordinate: ");
	
	World w;
	
	CustomWorldGridPanel gp = new CustomWorldGridPanel();
	
	public CustomWorld(final World w) {
		
		super();
		
		this.w = w;
		gp.renderWorld(w);
		
		setLayout(new FlowLayout());

		add(xCoorL);
		add(yCoorTF);
		add(yCoorL);
		add(xCoorTF);
		add(doneBut);
		add(cancelBut);
		add(elementBox);
		add(addBut);
		add(gp);
		
		Util.cntrl = new Controller(w);
		
		addBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(xCoorTF.getText().equals("") || xCoorTF.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Missing one or both coordinates.");
				}
				
				if (inRange(xCoorTF.getText().toString()) &&
						inRange(yCoorTF.getText().toString())) {
					int x = Integer.parseInt(xCoorTF.getText());
					int y = Integer.parseInt(yCoorTF.getText());

					String el = (String)elementBox.getItemAt(elementBox.getSelectedIndex());

					if(el.equals("Beeper")){
						w.setContents(x, y, Contents.BEEPER);
					}else if(el.equals("Wall")){
						w.setContents(x, y, Contents.WALL);
					}else{
						w.setContents(x, y, Contents.NONE);
					}

					Util.cntrl = new Controller(w);
					gp.renderWorld(w);
				} else {
					JOptionPane.showMessageDialog(null, "Coordinates provided are out of range.");
				}
				
				Util.cntrl = new Controller(w);
				gp.renderWorld(w);
				
			}
		});

		doneBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.currentWindow.dispose();
				mW = new MainWindow(w.getXSize(), w.getYSize()/*, cntrl*/);
				mW.setVisible(true);
				mW.setSize(1000,600);
				mW.setLocationRelativeTo(null);
				Util.drawWorld(null, null);
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

	public boolean inRange(String in) {
		if (in == null || in.length() == 0) {
			return false;
		}
		for(int i = 0; i < in.length(); i++) {
			if (!Character.isDigit(in.charAt(i))) {
				return false;
			}
		}
		int val = Integer.parseInt(in, 10);

		// since world is square
		if (val < 0 || val > w.getXSize() - 1) {
			return false;
		} else {
			return true;
		}
	}
	
}
