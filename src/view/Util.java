/**
 * @Miracle Okubor
 * @helper Stephen Chung, Isaac Tyan, Joseph Ancona, Benjamin Salah
 */

package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import model.BasicCode;
import model.Code;
import model.Code.Action;
import model.Code.Proposition;
import model.Karel;
import model.World;
import model.World.Contents;
import control.Controller;

/**
 * A helper class with static variables and static methods.
 * @author mokubor
 *
 */
public class Util {
	
	static Controller cntrl;
	
	static JLabel[][] worldLabels;
	static ArrayList<World> worlds;	
	
	static int EditIndex;
	
	/**
	 * Method to initialize the pre-existing worlds.
	 * @param worldCount
	 */
	public static void initializeWorlds(int worldCount){
		worlds = new ArrayList<World>();
		
		World w = new World(4,4);		
		w.setContents(1, 1, model.World.Contents.BEEPER);
		w.setContents(1,  0, model.World.Contents.BEEPER);
		w.setContents(2, 2, model.World.Contents.BEEPER);
		w.setContents(0,  1, model.World.Contents.WALL);
		w.setContents(1, 2, model.World.Contents.WALL);
		w.setContents(2, 1, model.World.Contents.WALL);
		
		worlds.add(w);
		
		w = new World(6,6);
		w.setContents(1, 1, model.World.Contents.WALL);
		w.setContents(2, 1, model.World.Contents.WALL);
		w.setContents(3, 1, model.World.Contents.WALL);
		w.setContents(4, 1, model.World.Contents.WALL);
		w.setContents(2, 2, model.World.Contents.BEEPER);
		w.setContents(0, 3, model.World.Contents.WALL);
		w.setContents(2, 3, model.World.Contents.WALL);
		w.setContents(3, 3, model.World.Contents.BEEPER);
		w.setContents(4, 3, model.World.Contents.BEEPER);
		w.setContents(5, 3, model.World.Contents.WALL);
		w.setContents(0, 4, model.World.Contents.WALL);
		w.setContents(2, 4, model.World.Contents.WALL);
		w.setContents(4, 4, model.World.Contents.WALL);
		w.setContents(1, 5, model.World.Contents.BEEPER);
		w.setContents(2, 5, model.World.Contents.BEEPER);
		w.setContents(4, 5, model.World.Contents.BEEPER);
		
		worlds.add(w);
		
		w = new World(6,6);
		w.setContents(1, 1, model.World.Contents.BEEPER);
		w.setContents(2, 1, model.World.Contents.WALL);
		w.setContents(3, 1, model.World.Contents.BEEPER);
		w.setContents(4, 1, model.World.Contents.WALL);
		w.setContents(1, 2, model.World.Contents.WALL);
		w.setContents(3, 2, model.World.Contents.WALL);
		w.setContents(1, 3, model.World.Contents.BEEPER);
		w.setContents(3, 3, model.World.Contents.WALL);
		w.setContents(5, 3, model.World.Contents.WALL);
		w.setContents(1, 4, model.World.Contents.WALL);
		w.setContents(3, 4, model.World.Contents.BEEPER);
		w.setContents(5, 4, model.World.Contents.WALL);
		w.setContents(2, 5, model.World.Contents.BEEPER);
		w.setContents(3, 5, model.World.Contents.WALL);
		w.setContents(5, 5, model.World.Contents.BEEPER);
		
		worlds.add(w);

		w = new World(6,6);
		w.setContents(1, 0, model.World.Contents.BEEPER);
		w.setContents(2, 0, model.World.Contents.BEEPER);
		w.setContents(3, 0, model.World.Contents.BEEPER);
		w.setContents(5, 0, model.World.Contents.BEEPER);
		w.setContents(4, 0, model.World.Contents.WALL);
		w.setContents(1, 1, model.World.Contents.WALL);
		w.setContents(2, 1, model.World.Contents.WALL);
		w.setContents(3, 1, model.World.Contents.BEEPER);
		w.setContents(0, 2, model.World.Contents.BEEPER);
		w.setContents(1, 2, model.World.Contents.WALL);
		w.setContents(3, 2, model.World.Contents.WALL);
		w.setContents(5, 2, model.World.Contents.WALL);
		w.setContents(0, 3, model.World.Contents.WALL);
		w.setContents(2, 3, model.World.Contents.WALL);
		w.setContents(4, 3, model.World.Contents.BEEPER);
		w.setContents(5, 3, model.World.Contents.WALL);
		w.setContents(1, 4, model.World.Contents.BEEPER);
		w.setContents(2, 4, model.World.Contents.WALL);
		w.setContents(4, 4, model.World.Contents.WALL);
		w.setContents(5, 5, model.World.Contents.BEEPER);
		w.setContents(3, 5, model.World.Contents.BEEPER);
		
		worlds.add(w);
		
		w = new World(8,8);				   
		w.setContents(3, 4, model.World.Contents.WALL);
		w.setContents(3, 5, model.World.Contents.WALL);
		w.setContents(4, 3, model.World.Contents.WALL);
		w.setContents(4, 7, model.World.Contents.WALL);
		w.setContents(5, 2, model.World.Contents.WALL);
		w.setContents(6, 2, model.World.Contents.WALL);
		w.setContents(7, 2, model.World.Contents.WALL);
		w.setContents(6, 6, model.World.Contents.BEEPER);
		
		worlds.add(w);
	}
	
	/**
	 * A method to update the controllers code list
	 * @param index index of insertion
	 * @param c Code object being inserted
	 */
	public static void updateCodeList(int index, Code c){
		if(index == -1){
			cntrl.getCodeList().add(c);
		}
		else{
			cntrl.getCodeList().add(index, c);
		}
	}
	
	public static void setLabels(JLabel[][] labels) {
		worldLabels = labels;
	}
	
	public static JLabel getLabel(int x, int y) {
		return worldLabels[x][y];
	}
	
	/**
	 * A method to determine the direction karel is facing
	 * @param karel An instance of karel
	 * @return
	 */
	public static int facingWhere(Karel karel){
		switch(karel.getFacing()) {
		case EAST:
			return 0;
		case WEST:
			return 1;
		case NORTH:
			return 2;
		case SOUTH:
			return 3;
		}
		return 4;
		
	}
	/**
	 * A method to draw karels world
	 * @param karel An instance of Karel
	 * @param world a fully initialized world variable
	 */
	public static void drawWorld(Karel karel, World world) {
		if (karel == null) {
			karel = cntrl.getKarel();
		}
		if (world == null) {
			world = cntrl.getWorld();
		}
		for(int i = 0; i < world.getYSize(); i++) {
			for(int j = 0; j < world.getXSize(); j++) {
				Contents contents = world.getContents(j, world.getYSize() - 1 - i);
				JLabel label = worldLabels[i][j];
				  worldLabels[i][j].setOpaque(true);
				worldLabels[i][j].setBackground(Color.white);
				
				StringBuffer sb = new StringBuffer();
				
				boolean karelBeeper = false;
				
				switch(contents) {
				case BEEPER:
				     //System.out.println("beeper at xy: " + j + " and " + i);
				     if(karel.getX() == j && karel.getY() == world.getYSize() - 1 - i){
				    	 if(facingWhere(karel)==0)
				    	 worldLabels[i][j].setIcon(new ImageIcon("./images/beeperRight.png"));
				    	 else if(facingWhere(karel)==1)
					    	 worldLabels[i][j].setIcon(new ImageIcon("./images/beeperLeft.png"));
				    	 else if(facingWhere(karel)==2)
					    	 worldLabels[i][j].setIcon(new ImageIcon("./images/beeperNorth.png"));
				    	 else
						    	 worldLabels[i][j].setIcon(new ImageIcon("./images/beeperSouth.png"));
				    	 
				    	 karelBeeper = true;
				     }
				     else{
				    	 worldLabels[i][j].setIcon(new ImageIcon("./images/beeper.png"));
				     }
				     break;
				case NONE:
					  worldLabels[i][j].setOpaque(true);
					  worldLabels[i][j].setIcon(null);
						worldLabels[i][j].setBackground(Color.white);

					break;
				case OUT_OF_BOUNDS:
					//sb.append("O ");
					break;
				case WALL:
					//sb.append("Wall ");
			
					worldLabels[i][j].setIcon(new ImageIcon("./images/wall.png"));
					            
					break;
				default:
					break;
				}
				if(!karelBeeper && karel.getX() == j && karel.getY() == world.getYSize() - 1 - i) {
					ImageIcon image = new ImageIcon("./images/right.png");
					worldLabels[i][j].setIcon(image);
					//sb.append("Karel ");
					switch(karel.getFacing()) {
					case EAST:
						worldLabels[i][j].setIcon(new ImageIcon("./images/right.png"));

						break;
					case NORTH:
						worldLabels[i][j].setIcon(new ImageIcon("./images/north.png"));

						break;
					case SOUTH:
						worldLabels[i][j].setIcon(new ImageIcon("./images/south.png"));

						break;
					case WEST:
						worldLabels[i][j].setIcon(new ImageIcon("./images/left.png"));

						break;
					default:
						break;
					
					}
				}
				label.setText(sb.toString());
			}
		}
	}
	
	/**
	 * A method to convert a string to a code object
	 * @param s string to be converted
	 * @return the corresponding code object
	 */
	public static Code matchStringToCode(String s){

		switch(s){
		
			case "Move":			return new BasicCode(Action.MOVE);
			case "Turn Left":		return new BasicCode(Action.TURN_LEFT);
			case "Turn Right":		return new BasicCode(Action.TURN_RIGHT);
			case "Pick up beeper":	return new BasicCode(Action.PICK_UP);
			case "Put down beeper":	return new BasicCode(Action.PUT_DOWN);
			case "If":				
			case "End-If":
			case "Else":
			case "End-Else":
			case "For":
			case "End-For":			return null;
		
		}
		return null;
	}

	/**
	 * A method to convert a string to a proposition
	 * @param s string to be converted
	 * @return proposition
	 */
	public static Proposition stringtoProposition(String s){
		
		switch(s){
		
		case "Facing East":			return Proposition.IS_FACING_EAST;
		case "Facing West":		return Proposition.IS_FACING_WEST;
		case "Facing North":		return Proposition.IS_FACING_NORTH;
		case "Facing South":	return Proposition.IS_FACING_SOUTH;
		case "Next to a Beeper":		return Proposition.NEXT_TO_BEEPER;		
		case "Front is Clear":			return Proposition.IS_FRONT_CLEAR;
		case "Right is Clear":			return Proposition.IS_RIGHT_CLEAR;
		case "Left is Clear":			return Proposition.IS_LEFT_CLEAR;
	
		}
		
		return null;
	}
	
	/**
	 * A method to convert a basic code object to a string
	 * @param c code object to be converted
	 * @return string representation
	 */
	public static String codetoString(Code c){
		Action t = ((BasicCode)c).getInstruction();
		switch(t){
		
			case MOVE: return "Move";
			case TURN_LEFT: return "Turn Left";
			case TURN_RIGHT: return "Turn Right";
			case PICK_UP: return "Pick up Beeper";
			case PUT_DOWN: return "Put down Beeper";
		
		}
		
		return null;
	}
	
	/**
	 * A method that converts a proposition to a string
	 * @param p a proposition
	 * @return string representation
	 */
	public static String propositiontoString(Proposition p){
		
		switch(p){
		
			case IS_FRONT_CLEAR: return "Front is Clear";
			case IS_LEFT_CLEAR: return "Left is Clear";
			case IS_RIGHT_CLEAR: return "Right is Clear";
			case IS_FACING_NORTH: return "Facing North";
			case IS_FACING_SOUTH: return "Facing South";
			case IS_FACING_EAST: return "Facing East";
			case IS_FACING_WEST: return "Facing West";
			case NEXT_TO_BEEPER: return "Next to a Beeper";
		
		}
		return null;
	}
	
	/**
	 * Method that converts the Macro creation JList into an Array List of code objects
	 * @return ArrayList<Code> body
	 */
	public static ArrayList<Code> getBodyMacro(){
		ArrayList<Code> body = new ArrayList<Code>(1);
		
		//System.out.println("isMacro " + PseudocodeList.isMacro);
		if(PseudocodeList.getTheModel().getSize() == 1){
			if(((String)PseudocodeList.getTheModel().getElementAt(0)).equalsIgnoreCase("Begin by Draging an Action")){// add check for empty for if/for
				//System.out.println("NULL");
				return null;
			}
			//System.out.println("not null but still1");
		}
		
		for(int i = 0; i < PseudocodeList.getTheModel().getSize()-1; i++){
			String s = (String)PseudocodeList.getTheModel().getElementAt(i);
			s = s.trim();
			//System.out.println("s: "+ s);
			Code c = matchStringToCode(s);
			body.add(c);
		}
		
		return body;
	}
	
	/**
	 * Method that takes a JList and returns an array List of code objects
	 * @param list
	 * @return an Array List of code objects
	 * 
	 */
	public static ArrayList<Code> getBody(JList list){
		ArrayList<Code> body = new ArrayList<Code>(1);
		
		DefaultListModel model = (DefaultListModel)list.getModel();
		
		if(model.getSize() == 1){
			if(((String)model.getElementAt(0)).equalsIgnoreCase("empty")){
				return null;
			}
		}
		for(int i = 0; i < model.getSize(); i++){
			String s = (String)model.getElementAt(i);
			Code c = matchStringToCode(s);
			body.add(c);
		}
		
		return body;
	}
	
	/**
	 * helper function to print the class names of all objects in the code list
	 */
	public static void printcodeList(){
		for(int i = 0; i < cntrl.getCodeList().size(); i ++){
			 System.out.println(i + " " + cntrl.getCodeList().get(i).getClass().toString());
		 }
	}
}
