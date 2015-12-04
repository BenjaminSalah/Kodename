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
 * This class is a container for all UI representation of Condition statements
 * 
 * @author Miracle Okubor
 *
 */
public class Conditions extends JPanel{
	static String[] cond = {"If-Else", "For-End For"};
	static JList list;
	static DefaultListModel model;
	
	public Conditions(){
		super();
		model = new DefaultListModel();
		
		for(int i = 0; i < cond.length; i++){
			model.addElement(cond[i]);
		}
		
		list = new JList (model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setFixedCellWidth(120);
		list.setEnabled(true);
		list.setVisible(true);
		list.setDragEnabled(true);
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border title = BorderFactory.createTitledBorder(loweredetched, "Conditions");
		
		setBorder(title);
		setVisible(true);
		add(list);
		
	}
}
