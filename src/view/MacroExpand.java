/**
 * @mainAuthor Miracle Okubor
 */
package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import model.Code;
import model.CustomCode;
import model.LoopCode;

/**
 * This class is used to expand macros added to the user's pseudocode list
 * so the user can see the contents pf the macro
 * @author mokubor
 *
 */
public class MacroExpand extends JDialog{
	static JList list;
	static DefaultListModel model;
	JLabel name;
	static JButton done;
	JScrollPane scroll;
	ArrayList<String> x;
	
	/*
	 * private button listener
	 */
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton source = (JButton)e.getSource();
			
			if(source == done){
				dispose();
			}
			else{
				
				dispose();
			}
		}
	}
	
	public MacroExpand(String key, ArrayList<Code> Body){
		if (key == null) {
			throw new IllegalArgumentException("MacroExpand constructor: name is null");
		}
		
		JLabel label = new JLabel ("Macro Name: ");
		
		name = new JLabel("");
		
		if(Body == null){
			throw new IllegalArgumentException("MacroExpand constructor: body is null");
		}
		
		model = new DefaultListModel();
		name = new JLabel();
		
		name.setText(key);
		name.setVisible(true);
		
		x = new ArrayList<String>(1);
		System.out.println(Body.size());
		
		for(int i = 0; i < Body.size(); i++){
			String temp = Body.get(i).toString();
			//System.out.println(temp);
			x.add(temp);
		}
			
		//populate the model	
		for(int i = 0; i < x.size(); i ++){
			model.addElement(x.get(i));
		}
			
		list = new JList(model);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setFixedCellWidth(200);
		list.setEnabled(true);
		list.setVisible(true);
		list.setSelectedIndex(-1);
		
		scroll = new JScrollPane(list);
			
		done = new JButton("Done");
		done.addActionListener(new ButtonListener());
		
		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT,5, 5));
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(name);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		

		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border title = BorderFactory.createTitledBorder(loweredetched, "Actions in Macro");
				
		JPanel panel2 = new JPanel();
		panel2.setVisible(true);
		panel2.add(scroll);
		panel2.setBorder(title);
		
		/* Layout*/
		setLayout(new GridBagLayout());
		GridBagConstraints x = new GridBagConstraints();
		
		x.anchor = GridBagConstraints.CENTER;
		x.gridx = 0;
		x.gridy = 0;
		add(panel, x);
		
		x.gridy = 1;
		x.gridheight = 3;
		add(panel2, x);
		
		x.gridy = 4;
		x.gridheight = 1;
		add(done, x);
		
	}
	
	public static void ExpandMacro(String key, ArrayList<Code> code){
		JDialog c = new MacroExpand(key, code);
		c.pack();
		c.setLocationRelativeTo(null);
		c.setVisible(true);
		c.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		c.setModal(true);
	}
}
