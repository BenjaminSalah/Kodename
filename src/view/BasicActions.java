/**
 * @mainAuthor Miracle Okubor
 */
package view;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;

/**
 * This class is a container for all UI representations of basic actions
 * @author Miracle Okubor
 *
 */
public class BasicActions extends JPanel{
	static String[] bActions = {"Move", "Turn Left", "Turn Right", "Pick up beeper", "Put down beeper"};
	static JList list;
	static DefaultListModel model;
	
	public BasicActions(){
		super();
		model = new DefaultListModel();
		
		for(int i = 0; i < bActions.length; i++){
			model.addElement(bActions[i]);
		}
		
		list = new JList (model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setFixedCellWidth(120);
		list.setEnabled(true);
		list.setVisible(true);
		list.setDragEnabled(true);
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border title = BorderFactory.createTitledBorder(loweredetched, "Basic Actions");
		
		setBorder(title);
		setVisible(true);
		add(list);
		
	}
	
	/**
	 * A method to return the selected element
	 * @return string 
	 */
	public static String getElement(){
		if(list.getSelectedIndex() == -1){
			return null;
		}
		return (String)model.getElementAt(list.getSelectedIndex());
	}
}
