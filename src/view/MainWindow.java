/**
 * @mainAuthor Miracle Okubor
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.*;
import model.*;

/**
 * This class is a container for all the panels that make up the programs main window.
 * It also contains the expand button and its functionality
 * @author mokubor
 *
 */
public class MainWindow extends Window{
	
	JPanel action;
	JPanel world;
	
	static PseudocodeList pL;
	static PseudocodeButtons pB;	
	
	public static JButton expand;
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			
			 if(source == expand){
				 Util.printcodeList();
				 WorldButtons.disable_buttons();
				 
				 int i = PseudocodeList.getTheJList().getSelectedIndex();
				 
				 if(i == -1){
					JOptionPane.showMessageDialog(null, "You must select an Action from the Pseudocode List to Expand", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
					return;
				 }
				 
				 String selected = Util.cntrl.getCodeList().get(i).getClass().toString();
				 
				 //System.out.println("expand for index "+ PseudocodeList.getTheJList().getSelectedIndex());
				 
				 /*expand an if/else statement*/
				 if( selected.equalsIgnoreCase("class model.IfElseCode")){
					 
					 //System.out.println(Util.cntrl.getCodeList().get(i).getClass().toString());
					 Util.EditIndex = i;
					 IfElseCode code = (IfElseCode)Util.cntrl.getCodeList().get(i);
					 IfElseDialog.getIfDialog(code);
					 
					 PseudocodeList.getTheJList().setSelectedIndex(-1);
					 return;
					 
				 }
				 else if(selected.equalsIgnoreCase("class model.LoopCode")){ /*expand a for-loop statement*/
					 
					// System.out.println(Util.cntrl.getCodeList().get(i).getClass().toString());
					 Util.EditIndex = i;
					 LoopCode code = (LoopCode)Util.cntrl.getCodeList().get(i);
					 LoopDialog.getForDialog(code);
					 
					 PseudocodeList.getTheJList().setSelectedIndex(-1);
					 return;
				 }
				 else if(selected.equalsIgnoreCase("class model.CustomCode")){ /*expand a custom statement*/

					 //System.out.println(Util.cntrl.getCodeList().get(i).getClass().toString());
					 String key = ((String) PseudocodeList.getTheModel().getElementAt(PseudocodeList.getTheJList().getSelectedIndex())).trim();
					 CustomCode c = Util.cntrl.getMacroMap().get(key);
					
					 MacroExpand.ExpandMacro(c.getName(), c.getCodeBody());
					 PseudocodeList.getTheJList().setSelectedIndex(-1);
					 
					 return;
				 }
				 else{ /* give warning message for basic statements*/
					 JOptionPane.showMessageDialog(null, "You cannot expand a Basic action", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
					 
					 PseudocodeList.getTheJList().setSelectedIndex(-1);
					 return;
				 }
			 }
		}

	}

	
	MainWindow(int x, int y){
		super("Main Window");
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    
				if (JOptionPane.showConfirmDialog(null, 
			            "Closing a window will quit this application.\nAre you sure you want to close this window?\nProgress will be saved.", "Close Window", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					if(Util.cntrl != null){
						
						if(Util.cntrl != null){
						  
					      WindowSaveSession.createWindowSaveSession();

					      //System.out.println("trying to exit");
						}else{
							System.exit(0);
						}
					}
				}
		    }
		});
		
		Main.currentWindow = this;
		
		expand = new JButton("Expand");
		expand.setEnabled(false);
		
		action = new ActionPanel();
		pL = new PseudocodeList(false);
		pB = new PseudocodeButtons();
		world = new WorldPanel(x, y);
		
		expand.addActionListener(new ButtonListener());
		
		pB.add(expand);
		
		
		
		add(action, BorderLayout.WEST);
		add(pL, BorderLayout.CENTER);
		add(pB, BorderLayout.SOUTH);
		add(world, BorderLayout.EAST);
		
	}
	
	/*public static void main(String[] args) {
		MainWindow frame = new MainWindow(10,10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Main Window");
		frame.setSize(1000,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}*/
	
	public static void createMainWindow(int x, int y) {
		
		JFrame frame = new MainWindow(x,y/*, _cntrl*/);
		frame.pack();
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	

	public void close(){
		this.dispose();
	}
	
	static void disableAll(){
		expand.setEnabled(false);
		pB.clearBut.setEnabled(false);
		pB.deleteBut.setEnabled(false);
	}
	static void enableAll(){
		expand.setEnabled(true);
		pB.clearBut.setEnabled(true);
		pB.deleteBut.setEnabled(true);
	}
	
}