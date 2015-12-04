/**
 * @mainAuthor Miracle Okubor
 * @helper Stephen Chung
 */

package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;

/**
 * A container for the grid representation of Karels' world in the world panel
 * @author mokubor
 *
 */
public class WorldGrid extends JPanel {
	int rows;
	int cols;
	
	public WorldGrid(int rows, int cols){
		super();
		final int borderWidth = 1;
		final int numRows = rows;
		
		GridLayout layout = new GridLayout(rows + 1, cols + 1);
		
		JPanel panel = new JPanel(layout);
		
	
		JLabel[][] labels = new JLabel[rows][cols];
		Util.setLabels(labels);
		
		panel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		int i = 0;
		for (int row = 0; row < rows + 1; row++) {
			for (int col = 0; col < cols + 1; col++) {
				JLabel label = new JLabel("", SwingConstants.CENTER);
				
				if (row != 0 && col != 0) {
				labels[row-1][col-1] = label;
				label.setVisible(true);
				label.setSize(10, 10);
				if (row == 1) {
					if (col == 1) {
						// Top left corner, draw all sides
						label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					}
					else {
						// Top edge, draw all sides except left edge
						label.setBorder(BorderFactory.createMatteBorder(borderWidth, 
	                                                                	0, 
	                                                                	borderWidth, 
	                                                                	borderWidth, 
	                                                                	Color.BLACK));
					}
				}
				else {
					if (col == 1) {
						// Left-hand edge, draw all sides except top
						label.setBorder(BorderFactory.createMatteBorder(0, 
	                                                             		borderWidth, 
	                                                             		borderWidth, 
	                                                             		borderWidth, 
	                                                             		Color.BLACK));
					}
					else {
						// Neither top edge nor left edge, skip both top and left lines
						label.setBorder(BorderFactory.createMatteBorder(0, 
	                                                                	0, 
	                                                                	borderWidth, 
	                                                                	borderWidth, 
	                                                                	Color.BLACK));
					}
				}
				} else if (row == 0) {
					if (col != 0)
						label.setText(String.valueOf(col - 1));
				} else {
					if (row != 0)
						label.setText(String.valueOf(numRows - row));
				}
				
					panel.add(label);//take out of else
				
					i++;
				
			}
		}
	
		add(panel);
	}
	
	
	/*public static void main(String[] args) {
		JFrame temp = new JFrame();
		
		temp.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));
		
		WorldGrid ap = new WorldGrid(6, 6);
		
		temp.add(ap);
		temp.setSize(140, 600);
		temp.setLocationRelativeTo(null);
		//temp.setResizable(false);
		temp.setVisible(true);
		
		//temp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		temp.pack();
	}*/
	
}
