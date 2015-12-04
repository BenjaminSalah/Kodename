/**
 * @mainAuthor Joseph Ancona
 * @helper Isaac Tyan
 */
package view;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.Controller;
import model.*;

// Class that has list of worlds to select from, welcome/title message, proceed/ok button, and world grid if include preview feature
public class StartPage extends Window {
	
	StartPageWorldList wl = new StartPageWorldList();
	StartPageTitle wt = new StartPageTitle();
	StartPageOK ok = new StartPageOK();
	StartPageGrid gd = new StartPageGrid();
	
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	JPanel northPanel = new JPanel();
	JPanel southPanel = new JPanel();

	JFrame mW;
	static JFrame frame;
	
	public StartPage() {
		super("Start Page World Selection");
		Main.currentWindow = this;
		
		leftPanel.add(wl);
		northPanel.add(wt);
		southPanel.add(ok);
		rightPanel.add(gd);
		
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		
		Util.cntrl = new Controller(Util.worlds.get(0));

		wl.jlist.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				//System.out.println("index selected: " + wl.jlist.getSelectedIndex());
				Util.cntrl = new Controller(Util.worlds.get(wl.jlist.getSelectedIndex()));
				gd.renderWorld(Util.worlds.get(wl.jlist.getSelectedIndex()));
		    }
		});
		
		ok.okBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.currentWindow.dispose();
				mW = new MainWindow(Util.cntrl.getWorld().getXSize(), Util.cntrl.getWorld().getYSize());
				mW.setVisible(true);
				mW.setSize(1000,600);
				mW.setLocationRelativeTo(null);
				Util.drawWorld(null, null);
				
			}
		});
	}
	
	public static void createStartPage() {
		
		JFrame frame = new StartPage();
		frame.setSize(1100,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	/*public static void main(String[] args) {
		
		
		frame = new StartPage();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Start Page World Selection");
		frame.setSize(1100,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}*/
	
	
	public void close(){
		this.dispose();
	}
}
