/**
 * @mainAuthor Isaac Tyan
 */
package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.Controller;

public class WindowSaveSession extends JDialog{
	
	JLabel nameL = new JLabel("Filename: ");
	JTextField nameTF = new JTextField(10);
	
	JButton saveBut = new JButton("Save");
	JButton cancelBut = new JButton("Cancel");
	JButton doNotSaveBut = new JButton("Dont Save");
	
	public WindowSaveSession(){
		super();
	
		setLayout(new FlowLayout());
		
		add(nameL);
		add(nameTF);
		add(saveBut);
		add(cancelBut);
		add(doNotSaveBut);
		
		saveBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s = nameTF.getText();
				if(s.contains(":") ||
						s.contains("~") ||
						s.contains("#") ||
						s.contains("%") ||
						s.contains("&") ||
						s.contains("*") ||
						s.contains("{") ||
						s.contains("}") ||
						s.contains("\\") ||
						s.contains("<") ||
						s.contains(">") ||
						s.contains("?") ||
						s.contains("//") ||
						s.contains("+") ||
						s.contains("|") ||
						s.contains("\"")){
					JOptionPane.showMessageDialog(null, "Invalid characters used for filename");
					return;
				}
				
				Date d = new Date();
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			    File f = new File("../data" + File.separator + nameTF.getText());
			    System.out.println("FILE: " + f);
			    Util.cntrl.save(f);
			    System.exit(0);
			}
		});
		
		cancelBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		doNotSaveBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main.currentWindow.dispose();
			}
		});
	
	}
	
	public static void createWindowSaveSession() {
		
		JDialog frame = new WindowSaveSession();
		frame.setSize(600,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
