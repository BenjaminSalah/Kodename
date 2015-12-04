/**
 * @mainAuthor Isaac Tyan
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Karel;
import model.World;

public class CustomWorldGridPanel extends JPanel{
	
	JPanel wg;

	public void renderWorld(World w){
		int x = w.getXSize();
		int y = w.getYSize();
		
		Karel k = new Karel(w, 0, 0);
		
		wg = new WorldGrid(x, y);
		Util.drawWorld(k, w);
		
		removeAll();
		revalidate();
		add(wg, BorderLayout.EAST);
	}

}
