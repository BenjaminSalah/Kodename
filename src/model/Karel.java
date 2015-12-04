/**
 * @mainAuthor Stephen Chung, Isaac Tyan, Benjamin Salah
 */

package model;

import java.io.Serializable;
import model.World.Contents;
import static model.World.Contents.*;


/**
 * This class represents a Karel robot. 
 * It knows its own position, and has the
 * ability to perform actions such as move, turn, and pick up beeper. It
 * inhabits a World, which introduces constraints to its actions.
 */
public class Karel implements Serializable {

	public enum Facing { NORTH, SOUTH, EAST, WEST };
	
	protected World world;
	protected Facing facing;
	protected int xPosition;
	protected int yPosition;
    protected int beepersPlaced;
    protected int beepersPicked;
	
	/**
	 * Instantiate a Karel robot that will exist in a specified world.
	 * @param w the world this robot will occupy
	 * @param x the robot's position on the x-axis
	 * @param y the robot's position on the y-axis
	 * @throws IllegalArgumentException if the world is null, if the specified
     * coordinates refer to a wall or if Karel is out of bounds
	 */
	public Karel(World w, int x, int y) throws IllegalArgumentException {
        if (w == null) {
            throw new IllegalArgumentException("World cannot be null.");
        }
		Contents c = w.getContents(x,y);
		if (c == WALL) {
			throw new IllegalArgumentException("Cannot place Karel in a wall.");
		}
        if (c == OUT_OF_BOUNDS) {
            throw new IllegalArgumentException("Cannot place Karel out of bounds.");
        }
		this.world = w;
		facing = Facing.NORTH;
		xPosition = x;
		yPosition = y;
        beepersPlaced = beepersPicked = 0;
        assert(invariantsMaintained());
	}

	/**
	 * Place Karel in a different world at position (0, 0) facing north.
	 * Reset beeper counts.
	 */
	public void setWorld(World world) {
		this.world = world;
		facing = Facing.NORTH;
		xPosition = 0;
		yPosition = 0;
		beepersPlaced = 0;
		beepersPicked = 0;
	}
	
	/**
	 * Returns the current x position of Karel.
	 * 
	 * @return Karel's x position
	 */
	public int getX() {
        assert(invariantsMaintained());
		return xPosition;
	}
	
    /**
	 * Returns the current y position of Karel.
	 * 
	 * @return Karel's y position
	 */
	public int getY() {
        assert(invariantsMaintained());
		return yPosition;
	}
	

    /**
     * Places Karel at a specified position.
     *
     * @param x new x position
     * @param y new y position
     * @throws IllegalArgumentException if new position is out of bounds
     * or occupied by a wall
     */
	public void setPosition(int x, int y) throws IllegalArgumentException {
		Contents c = world.getContents(x,y);
		if (c == WALL) {
			throw new IllegalArgumentException("Cannot place Karel on a wall.");
		}
        if (c == OUT_OF_BOUNDS) {
            throw new IllegalArgumentException("Cannot place Karel out of bounds.");
        }
		xPosition = x;
		yPosition = y;
        assert(invariantsMaintained());
	}
	
	/**
	 * Rotate Karel's facing by 90 degrees clockwise.
	 * 
	 * Pre-condition: Karel is facing one of the cardinal directions.
	 * This is enforced by our choice of an enum in Java's type system.
	 */
	public void turnRight() {
		switch (facing) {
		case EAST:
			facing = Facing.SOUTH;
			break;
		case NORTH:
			facing = Facing.EAST;
			break;
		case SOUTH:
			facing = Facing.WEST;
			break;
		case WEST:
			facing = Facing.NORTH;
			break;
		default:
			break;
		}
        assert(invariantsMaintained());
	}

	/**
	 * Rotate Karel's facing by 90 degrees counterclockwise.
	 * 
	 * Pre-condition: Karel is facing one of the cardinal directions.
	 * This is enforced by our choice of an enum in Java's type system.
	 */
	public void turnLeft() {
		for (int i = 0; i < 3; i++)
			turnRight();
        assert(invariantsMaintained());
	}

	/**
	 * Cause Karel to take one step forward in the direction he is facing.
	 * This will cause either the x or the y position (but not both) to
	 * change by one unit.
	 * 
	 * @throws KRuntimeException if Karel is facing a wall, or is at
	 * the end of the world
	 */
	public void move() throws KRuntimeException {
		
		if (!isFrontClear()) {
			throw new KRuntimeException("Karel cannot run into a wall or out of bounds.");
		}
		if(this.facing==Facing.SOUTH){
			yPosition--;
		}else if(this.facing==Facing.EAST){
			xPosition++;
		}else if(this.facing==Facing.WEST){
			xPosition--;
		}else if(this.facing==Facing.NORTH){
			yPosition++;
		}
		assert(invariantsMaintained());
	}

	/**
	 * Instruct Karel to pick up a beeper at his current location.
	 * @throws KRuntimeException if there is no beeper present
	 */
	public void pickUp() throws KRuntimeException {
		if (world.getContents(xPosition, yPosition) != BEEPER) {
			throw new KRuntimeException("No beeper here.");
		}
		world.setContents(xPosition, yPosition, NONE);
        beepersPicked++;
        assert(invariantsMaintained());
	}

	/**
	 * Instructs Karel to place a beeper at his current location
	 * @throws KRuntimeException if there is already a beeper here
	 */
	public void putDown() throws KRuntimeException {
		if (world.getContents(xPosition, yPosition) != NONE) {
			throw new KRuntimeException("This grid is occupied.");
		}
		world.setContents(xPosition, yPosition, BEEPER);
        beepersPlaced++;
        assert(invariantsMaintained());
	}

	public boolean isFrontClear(){

		Contents contents;
		switch(facing){
			case NORTH: contents = world.getContents(xPosition, yPosition + 1);
						break;
			case SOUTH: contents = world.getContents(xPosition, yPosition - 1);
						break;
			case EAST: 	contents = world.getContents(xPosition + 1, yPosition);
						break;
			case WEST:	contents = world.getContents(xPosition - 1, yPosition);
						break;
			default: throw new RuntimeException("Could not determine Karel's facing.");
		}
		return !(contents == WALL || contents == OUT_OF_BOUNDS);
		
	}
	
	public boolean isLeftClear(){
		
		Contents contents;
		switch(facing){
			case NORTH: contents = world.getContents(xPosition - 1, yPosition);
						break;
			case SOUTH: contents = world.getContents(xPosition + 1, yPosition);
						break;
			case EAST: 	contents = world.getContents(xPosition, yPosition + 1);
						break;
			case WEST:	contents = world.getContents(xPosition, yPosition - 1);
						break;
			default: throw new RuntimeException("Could not determine Karel's facing.");
		}
		return !(contents == WALL || contents == OUT_OF_BOUNDS);
		
	}
	
	public boolean isRightClear(){
		
		Contents contents;
		switch(facing){
			case NORTH: contents = world.getContents(xPosition + 1, yPosition);
						break;
			case SOUTH: contents = world.getContents(xPosition - 1, yPosition);
						break;
			case EAST: 	contents = world.getContents(xPosition + 1, yPosition - 1);
						break;
			case WEST:	contents = world.getContents(xPosition - 1, yPosition + 1);
						break;
			default: throw new RuntimeException("Could not determine Karel's facing.");
		}
		return !(contents == WALL || contents == OUT_OF_BOUNDS);
		
	}
	
	/**
	 * Check all representation invariants for an instance of this class.
	 * 1) world must be non-null
     * 2) Karel must occupy a location that is in-bounds and not a wall
     * 
	 * @return true if invariants hold, otherwise false
	 */
	private boolean invariantsMaintained() {
		if (world == null) {
			return false;
		}
        Contents contents = world.getContents(xPosition, yPosition);
        if (contents == WALL || contents == OUT_OF_BOUNDS) {
            return false;
        }
        return true;
	}
	
	public Facing getFacing(){
		return this.facing;
	}
 

}
