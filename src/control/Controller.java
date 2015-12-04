/**
 * @mainAuthor Stephen Chung, Isaac Tyan
 */

package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import model.BasicCode;
import model.Code;
import model.Code.Action;
import model.Code.Proposition;
import model.CustomCode;
import model.IfElseCode;
import model.KRuntimeException;
import model.Karel;
import model.Karel.Facing;
import model.LoopCode;
import model.World;
import model.World.Contents;

/**
 * Controller acts as a communication medium between the code that the user has created for Karel and any
 * user interfaces (textual or graphical). The primary function of Controller is to maintain all state
 * information necessary to run a session (keep track of Karel and World objects and the user's code). It
 * will parse the user's code and issue commands to Karel.
 * 
 * @author Kodename team
 */
public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;

	final Karel karel;
	final World originalWorld;
	World world;
	Map<String, CustomCode> macros;
	List<Code> codeList;
	
	String executionMessage;
	int executionLine;
	
	// never make these fields available to other classes
	Deque<Executable> deque;
	boolean canExecute;
	
	/**
	 * Instantiate a controller, representing a new game / new session.
	 * 
	 * @param x number of columns in the world
	 * @param y number of rows in the world
	 * @return a new controller
	 */
	public Controller(World input) {
		originalWorld = input;
		world = originalWorld.cloneWorld();
		karel = new Karel(world, 0, 0);
		macros = new HashMap<String, CustomCode>();
		codeList = new ArrayList<Code>();
		deque = null;
		canExecute = false;
	}
	
	/**
	 * Save this controller to disk.
	 * 
	 * @param file the file to write to
	 * @return true if save succeeded
	 */
	public boolean save(File file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try { oos.close(); } catch (IOException e) { }
		}
		
		return true;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Karel getKarel() {
		return karel;
	}
	
	/**
	 * Load a controller from disk.
	 * 
	 * @param file the file to load from
	 * @return the controller, or null if an error occurred
	 */
	public static Controller load(File file) {
		
		
		FileInputStream fis;
		ObjectInputStream ois = null;
		Controller controller = null;
		
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		try {
			ois = new ObjectInputStream(fis);
			controller = (Controller) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try { ois.close(); } catch (IOException e) { }
		}
		
		return controller;
	}
	
	public static Controller deserialize(String userID)	throws FileNotFoundException, IOException, ClassNotFoundException{ 
		
		FileInputStream fileIn = new FileInputStream("data/" + userID +".dat");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Controller user = (Controller)in.readObject();
        in.close();
        fileIn.close();
        return user;
		
	}
	
	/**
	 * Change the macro associated with a macro name.
	 * 
	 * @param name the name of the macro
	 * @param cc the macro body
	 * @return null if no macro of the given name existed, else the previous macro body
	 * (that has just been replaced)
	 * @deprecated
	 */
	public CustomCode addMacro(String name, CustomCode cc) {
		if (name == null || cc == null) {
			throw new IllegalArgumentException("Neither macro name nor body can be null.");
		}
		return macros.put(name, cc);
	}
	
	/**
	 * Determine whether a macro of a given name exists.
	 * 
	 * @param name string name of the macro
	 * @return true iff the macro exists
	 * @deprecated
	 */
	public boolean hasMacro(String name) {
		return macros.containsKey(name);
	}
	
	/**
	 * Provide full access to macro map, for the views.
	 * 
	 * @return the map of macro names to macro bodies
	 */
	public Map<String, CustomCode> getMacroMap(){
		return this.macros;
	}
	
	/**
	 * Find a macro by name. Since no UI should ask for a nonexistent macro, this throws
	 * an exception if that case does occur.
	 * 
	 * @param name the name of the macro
	 * @return the CustomCode object representing the macro
	 * @throws IllegalArgumentException if no macro of the given name exists
	 * @deprecated
	 */
	public CustomCode getMacro(String name) {
		if (!macros.containsKey(name)) {
			throw new IllegalArgumentException("No macro named " + name + " exists.");
		}
		return macros.get(name);
	}

	/**
	 * Return the user's Karel program.
	 * 
	 * @return the list containing all blocks of code in the Karel program
	 */
	public List<Code> getCodeList() {
		return codeList;
	}
	
	/**
	 * Parses the codeList, populates the queue of BasicCode instructions.
	 * 
	 * @modifies the deque, such that it holds a sequence of BasicCode that
	 * is equivalent to the user's Karel program
	 */
	public void compile() {
		world = originalWorld.cloneWorld();
		karel.setWorld(world);
		deque = new LinkedList<Executable>();
		
		System.out.println("Begin compile.");
		for(int i = 0; i < codeList.size(); i++) {
			Code code = codeList.get(i);
			System.out.println(code);
			deque.addAll(eval(code, i));
		}
		System.out.println("End compile.");
		
		canExecute = true;
		//System.out.println("There are " + deque.size() + " Executables.");
		executionMessage = "";
		executionLine = -1;
	}
	
	/**
	 * Evaluate a Code object, transforming into one or more Executables.
	 * 
	 * @param code element to be evaluated
	 * @param line top-level line number
	 * @return a LinkedList (implementing deque) of Executables
	 */
	private LinkedList<Executable> eval(Code code, int line) {
		
		if (code == null) {
			throw new NullPointerException("Called eval with a null pointer.");
		}
		
		// this is the deque that will be returned
		LinkedList<Executable> list = new LinkedList<Executable>();
		
		if (code instanceof BasicCode) {
			list.add(new Instruction(line, (BasicCode) code));
		} else if (code instanceof IfElseCode) {
			IfElseCode iec = (IfElseCode) code;
			LinkedList<Executable> branch1 = new LinkedList<Executable>();
			LinkedList<Executable> branch2 = new LinkedList<Executable>();
			Iterator<Code> iter = iec.getBody1().iterator();
			
			while (iter.hasNext()) {
				branch1.addAll(eval(iter.next(), line));
			}
			
			iter = iec.getBody2().iterator();
			
			while (iter.hasNext()) {
				branch2.addAll(eval(iter.next(), line));
			}
			
			// branch instruction comes first
			list.add(new BranchOnFalse(line, iec.getCondition(), branch1.size()+1));
			list.addAll(branch1);
			// if branch1 taken, jump past all of branch2
			list.add(new Jump(line, branch2.size()));
			list.addAll(branch2);
		} else if (code instanceof LoopCode) {
			LinkedList<Executable> sublist = new LinkedList<Executable>();
			LoopCode lc = (LoopCode) code;
			Iterator<Code> iterator = lc.getBody().iterator();
			
			while(iterator.hasNext()) {
				sublist.addAll(eval(iterator.next(), line));
			}
			
			for(int i = 0; i < lc.getCounter(); i++) {
				list.addAll(sublist);
			}
		} else if (code instanceof CustomCode) {
			Iterator<Code> iterator = ((CustomCode)code).getCodeBody().iterator();
			
			while(iterator.hasNext()) {
				list.addAll(eval(iterator.next(), line));
			}
		} else {
			throw new IllegalArgumentException("Unrecognized code type.");
		}
		return list;
	}
	
	/**
	 * Execute one atomic instruction in the Karel program.
	 * 
	 * @assumes the Karel program has not been modified since the last call to
	 * parseCode
	 * @return a message indicating execution successful, or else a message
	 * indicating where execution failed
	 * @throws IllegalStateException if the code has not bee parsed
	 */
	public boolean execute(){
		if (deque == null) {
			throw new IllegalStateException("You must parse the code before executing.");
		}
		if (!canExecute) {
			throw new IllegalStateException("Cannot execute code now. Not compiled, or"
					+ " execution reached error, or execution reached end.");
		}

		if(deque.isEmpty()){
			this.executionMessage = "Code has finished running";
			canExecute = false;
			return false;
		}
		
		Executable exe = deque.removeFirst();
		
		if(exe instanceof Instruction){
			Instruction instr = (Instruction)exe;
			try{
				callKarel(instr.action);
			}catch(KRuntimeException kre){
				this.executionMessage = kre.getMessage();
				canExecute = false;
				return false;
			}catch(RuntimeException re){
				this.executionMessage = re.getMessage();
				canExecute = false;
				return false;
			}
			this.executionMessage = _getExecutionMessage(instr.action);
			this.executionLine = instr.lineNumber;
			return true;
		}else if(exe instanceof BranchOnFalse){
			
			BranchOnFalse bof = (BranchOnFalse)exe;
			
			if(evaluateProposition(bof.prop)){
				return execute();
			}else{
				goToOffset(bof.offset);
				return execute();	
				// if bof is false, pop offset and evaluate (offset must pop off Jump)
			}
		}else if(exe instanceof Jump){
			Jump jump = (Jump)exe;
			goToOffset(jump.offset);
			return execute();
		}else{
			throw new RuntimeException("Unknown object in code");
		}		
	}
	
	private void goToOffset(int offset){
		for(int i = 0; i<offset; i++){
			this.deque.removeFirst();
		}
	}
	
	private boolean isFacing(Proposition prop){
		
		switch(prop){
			case IS_FACING_NORTH:	return karel.getFacing() == Facing.NORTH;
			case IS_FACING_SOUTH:	return karel.getFacing() == Facing.SOUTH;
			case IS_FACING_EAST:	return karel.getFacing() == Facing.EAST;
			case IS_FACING_WEST:	return karel.getFacing() == Facing.WEST;
			default: throw new RuntimeException("For this proposition, should not have called isFacing().");
		}
	}
	
	private boolean evaluateProposition(Proposition prop){
		
		switch(prop){
			
			case IS_FRONT_CLEAR:	return karel.isFrontClear();
			case IS_LEFT_CLEAR:		return karel.isLeftClear();
			case IS_RIGHT_CLEAR:	return karel.isRightClear();
			case IS_FACING_NORTH:
			case IS_FACING_SOUTH:
			case IS_FACING_EAST:
			case IS_FACING_WEST:	return isFacing(prop);			
			case NEXT_TO_BEEPER:	return world.getContents(karel.getX(), karel.getY()) == Contents.BEEPER;
		
		}
		throw new IllegalArgumentException("Unknown proposition used");
	}
	
	private String _getExecutionMessage(Action a){
		
		String msg;
		
		switch(a){
		
			case MOVE:			msg = "Karel moved";
								break;
			case TURN_LEFT:		msg = "Karel turned left";
								break;
			case TURN_RIGHT:	msg = "Karel turned right";
								break;
			case PICK_UP:		msg = "Karel picked up a beeper";
								break;
			case PUT_DOWN:		msg = "Karel put down a beeper";
								break;
			default:			msg = "ERROR: could not execute";
								break;		
		}
		
		return msg;
	}
	
	/**
	 * Calls Karel to act on the given instruction
	 * @param s the executable instruction
	 */
	private void callKarel(Code.Action action){
		switch(action) {
		case MOVE:
			karel.move();
			break;
		case PICK_UP:
			karel.pickUp();
			break;
		case PUT_DOWN:
			karel.putDown();
			break;
		case TURN_LEFT:
			karel.turnLeft();
			break;
		case TURN_RIGHT:
			karel.turnRight();
			break;
		default:
			throw new RuntimeException("Controller cannot execute this unknown action: " + action);
		}
	}
	
	public String getExecutionMessage(){
		return executionMessage;
	}
	
	public int getExecutionLine() {
		return executionLine;
	}
}

