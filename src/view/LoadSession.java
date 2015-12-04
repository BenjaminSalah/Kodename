/**
 * @mainAuthur Isaac Tyan
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.Controller;

public class LoadSession extends Window{

	JFrame mW;
	
	JButton newBut = new JButton("New Session");
	JButton loadBut = new JButton("Load Session");
	
	DefaultListModel dlmModel = new DefaultListModel();
	JList jlist;
	JScrollPane scroller;
	ArrayList<String> sessionNames = new ArrayList<String>();
	
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	
	
	public LoadSession() {
		super("Load Session");
		
		jlist = new JList();
		jlist.setModel(dlmModel);
		
		initiate();
		
		for (int i = 0; i < sessionNames.size(); i++) {
			dlmModel.addElement("     " + sessionNames.get(i) + "     ");
		}
		jlist.setSelectedIndex(0);			
		jlist.setVisibleRowCount(10);
		
		
		setLayout(new GridLayout(2,1));
		
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		leftPanel.add(jlist);
		rightPanel.add(newBut);
		rightPanel.add(loadBut);
		
		
		
		add(leftPanel);
		add(rightPanel);

		
		
		newBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartPage.createStartPage();
				dispose();
			}
		});
		
		loadBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(jlist.getSelectedIndex() == -1){
					System.out.println("index is negative");
					return;
				}
				
				String filepath = (String)dlmModel.get(jlist.getSelectedIndex());
				String filename = getName(filepath).trim();
				System.out.println("filename: " + filename);
				File f = new File("../data" + File.separator + filename);
				
				Util.cntrl = Controller.load(f);
				
				
				if(Util.cntrl != null){
					mW = new MainWindow(Util.cntrl.getWorld().getXSize(), Util.cntrl.getWorld().getYSize()/*, cntrl*/);
					mW.setVisible(true);
					mW.setSize(1000,600);
					mW.setLocationRelativeTo(null);
					Util.drawWorld(null, null);
					dispose();
				}
			}
		});		
	}
	
	public String getName(String path) {
	    File file = new File(path);
	    return file.getName();
	}
	
	public static void createLoadSessionPage() {
		
		JFrame frame = new LoadSession();
		frame.setSize(600,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	private void initiate()
	{
		String dataFolder = null;
		File[] files;
		File currentDirectory = new File(new File("").getAbsolutePath());
		
		System.out.println("Current Directory: " + currentDirectory);
		
		try {
			dataFolder = currentDirectory.getCanonicalPath() + File.separator + ".."+ File.separator +"data";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("datafolder: " + dataFolder);
		File folder = new File(dataFolder);

		files = folder.listFiles();
		
		if(files == null){
			System.out.println("files are null");
			return;
		}
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].isFile())
			{
				sessionNames.add(files[i].toString());
			}
		}
	}
	
	private String stripExtension(File file)
	{
		if(file == null) return null;
		
		String name = file.getName();
		
		int pos = name.indexOf(".");
		
		return name.substring(0, pos);
	}

}
