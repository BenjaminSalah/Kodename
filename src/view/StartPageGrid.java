/**
 * @mainAuthor Isaac Tyan
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import control.Controller;

import model.Karel;
import model.World;

// Class to call world grid if we include preview feature
public class StartPageGrid extends JPanel {

	JPanel wg;
	
	public StartPageGrid() {
		Util.initializeWorlds(5);
		wg = new WorldGrid(Util.worlds.get(0).getXSize(), Util.worlds.get(0).getYSize());
		Karel k = new Karel(Util.worlds.get(0), 0, 0);
		Util.drawWorld(k, Util.worlds.get(0));
		add(wg, BorderLayout.EAST);
	}
	
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
