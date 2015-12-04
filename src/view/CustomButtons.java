/**
 * @mainAuthor Miracle Okubor
 * @helper Joseph Ancona
 */
package view;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * This class is a container for the buttons used to manipulate custom
 * actions.
 * @author mokubor
 *
 */
public class CustomButtons extends JPanel {
	static JButton create;
	static JButton delete;
	
	
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			
			if(source == create){
				create.setEnabled(false);
				JDialog dialog = new MacroCreation();
				
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("Macro Creation");
				dialog.setSize(1000,600);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				
				// Enable create macro button when window is explicitly X'd out/closed
				dialog.addWindowListener(new WindowAdapter() {
				    @Override
				    public void windowClosed(WindowEvent e) {
				        CustomButtons.create.setEnabled(true);
				        
				        // Switching pseudocodes lists
				        PseudocodeList.isMacro = false;
				    }
				});
			
				
			}
			else if (source == delete) {
				
				if (Customs.list.getSelectedIndex() != -1) {
					int choice = JOptionPane.showOptionDialog(null, "Are you sure that you want to delete the selected macro?",
							"Delete Macro?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				
					if(choice == 0) {
						
						String key = Customs.getKey();
						Util.cntrl.getMacroMap().remove(key);
						Customs.delete();
						
						if (Customs.model.isEmpty()) {
							Customs.resetCustomsList();
						}
						
						//delete from pseudocodelist
						PseudocodeList.onCustomDelete(key);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "There was no macro selected.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public CustomButtons(){
		super();
		create = new JButton("Create");
		delete = new JButton ("Delete");
		
		create.setPreferredSize(new Dimension(20, 20));
		delete.setPreferredSize(new Dimension(20, 20));
		
		create.addActionListener(new ButtonListener());
		delete.addActionListener(new ButtonListener());
		
		/**if there are no macros, disable delete button*/
		if(Util.cntrl.getMacroMap().size() == 0){
			delete.setEnabled(false);
		}
		
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		add(create);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(delete);
		add(Box.createRigidArea(new Dimension(0,10)));
		
	}
}