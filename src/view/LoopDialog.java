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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Code;
import model.LoopCode;

/**
 * This class is a Dialog that helps the user create Loop statements
 * @author mokubor
 *
 */
public class LoopDialog extends JDialog{
	
	static int value;//return value that says if object was created or user canceled the action
	JPanel basic;
	String[] no_of_iterations = {"0", "1", "2", "3", "4"};
	JComboBox iterations;
	JButton done;
	JButton cancel;
	JButton add;
	JButton remove;
	static JList for_list;
	JLabel for_label;
	JLabel end_label;
	static DefaultListModel for_model;
	JScrollPane scrollbar;
	
	int count;
	LoopCode loop_code_piece;
	
	boolean isempty = true; //flag to tell if this is from an expand call or on initial creation
	
	/*
	 * private class button listener for all button elements in dialog
	 */
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton source = (JButton)e.getSource();
			
			if(source == done){
				if(iterations.getSelectedIndex() == 0){
					JOptionPane.showMessageDialog(null, "repeat count cannot be 0", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
					return;
				}
				else{
					
					int count = iterations.getSelectedIndex();
					System.out.println("count: " + count);
					
					if(isListEmpty()){
						JOptionPane.showMessageDialog(null, "Add an action to the list or select the Cancel button", "Empty List", JOptionPane.WARNING_MESSAGE);
						return;
					}
					ArrayList<Code> body = Util.getBody(for_list);
					
					((LoopCode) loop_code_piece).setCounter(count);
					((LoopCode) loop_code_piece).setBody(body);
					
					if(isempty == true){
						Util.updateCodeList(Util.EditIndex, loop_code_piece);
						
					}else{
						Util.cntrl.getCodeList().remove(Util.EditIndex);
						Util.updateCodeList(Util.EditIndex, loop_code_piece);
					}
					
					value = 1;
					dispose();
				}
			
			}
			else if(source == add){
				add(BasicActions.getElement());
			}
			else if(source == remove){
				remove(for_list.getSelectedIndex());
			}
			else{
				value = -1;
				dispose();
			}
		}
	}
	
	public LoopDialog(JFrame owner, Code c){
		super(owner, "Create Loop statement", true);
		
		//Check is code object is empty
		if(c == null){
			loop_code_piece = new LoopCode(0, null);
		}
		else{
			loop_code_piece = (LoopCode)c;
			isempty = false;
		}
		
		basic = new BasicActions();
		
		iterations = new JComboBox(no_of_iterations);
		iterations.setSelectedIndex(0);
		
		/*Initialize and add listeners to buttons*/
		done = new JButton("Done");
		done.addActionListener(new ButtonListener());
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ButtonListener());
		add = new JButton("Add");
		add.addActionListener(new ButtonListener());
		remove = new JButton("Remove");
		remove.addActionListener(new ButtonListener());
		
		/*Initialize labels*/
		JLabel message = new JLabel("How many times do you want to repeat the loop:");
		for_label = new JLabel("For:");
		end_label = new JLabel("End For");
		
		for_model = new DefaultListModel();
		
		/*populate model*/
		if(isempty){
			String[] empty = new String[1];
			empty[0] = "Empty";
		
			for(int i = 0; i < empty.length; i++){
				for_model.addElement(empty[i]);
			}
		}
		else{ //populate models and set combo box selection to object's count variable
			count = loop_code_piece.getCounter();
			iterations.setSelectedIndex(count);
			
			ArrayList<Code> Body = loop_code_piece.getBody();
			for(int i = 0; i < Body.size(); i++){
				String t = Util.codetoString(Body.get(i));
				System.out.println(t + " if");
				for_model.addElement(t);
			}
		}
		
		for_list = new JList(for_model);
		for_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for_list.setLayoutOrientation(JList.VERTICAL_WRAP);
		for_list.setFixedCellWidth(200);
		for_list.setEnabled(true);
		for_list.setVisible(true);
		for_list.setSelectedIndex(-1);
		
		scrollbar = new JScrollPane(for_list);
		
		JPanel bpanel = new JPanel();
		bpanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		bpanel.add(cancel);
		bpanel.add(done);
		
		/*Layout*/
		setLayout(new GridBagLayout());
		GridBagConstraints x = new GridBagConstraints();
		
		//rightmost column
		x.anchor = GridBagConstraints.WEST;
		x.gridx = 0;
		x.gridy = 1;
		x.gridheight = 5;
		add(basic, x);
		
		//center column
		x.gridx = 1;
		x.gridy = 3;
		x.anchor = GridBagConstraints.CENTER;
		x.gridheight = 1;
		add(add, x);
		
		x.gridy = 4;
		add(remove, x);
		
		//leftmost column
		x.anchor = GridBagConstraints.WEST;
		x.gridx = 2;
		x.gridy = 0;
		add(message, x);
		
		x.gridy = 1;
		add(iterations, x);
		
		x.gridy = 2;
		add(for_label, x);
		
		x.gridy = 3;
		x.gridheight = 2;
		x.anchor = GridBagConstraints.NORTHWEST;
		add(for_list, x);
		
		x.gridy = 5;
		x.gridheight = 1;
		add(end_label, x);
		
		x.gridx = 2;
		x.gridy = 6;
		x.anchor = GridBagConstraints.EAST;
		add(bpanel, x);
		
		
	}
	
	public static void getForDialog(Code code){
		JDialog c = new LoopDialog(null, code);
		c.pack();
		c.setLocationRelativeTo(null);
		c.setVisible(true);
		c.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		c.setModal(true);
		
	}
	
	/**
	 * Method to add a new element to the JList container
	 * @param data string being added
	 */
	public static void add(String data){
		if(data == null){
			JOptionPane.showMessageDialog(null, "You must select a basic action", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//System.out.println(data + " to some list");
		
		if(for_model.getSize() == 0 || for_model.getSize() == 1){
			if(((String)for_model.getElementAt(0)).equalsIgnoreCase("empty")){
				for_model.remove(0);
				for_model.addElement(data);
			}
			else{
				for_model.addElement(data);
			}
		}
		else{
			for_model.addElement(data);
		}
		
		for_list.setSelectedIndex(-1);
	}
	
	/**
	 * method to remove an element from the JList
	 */
	public void remove(int index){
		
		if(index == -1){
			JOptionPane.showMessageDialog(null, "You must select from For to remove", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(for_model.size() == 1){
			for_model.remove(0);
			for_model.addElement("Empty");
			
		}
		else{
			for_model.remove(index);
		}
		for_list.setSelectedIndex(-1);
		
		return;
	}
	
	/*
	 * Method to return value
	 */
	public static int getValue(){
		return value;
	}
	
	/*
	 * Method which checks to see if the list is empty and throw an exception
	 */
	public static boolean isListEmpty(){
		if(for_model.getSize() == 1){
			if(((String)for_model.getElementAt(0)).equalsIgnoreCase("empty")){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
}
