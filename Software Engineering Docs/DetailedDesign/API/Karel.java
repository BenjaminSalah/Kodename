package model;

import java.io.Serializable;
import static model.World.Contents.*;

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
        beepersPlaces = beepersPicked = 0;
        assert(invariantsMaintained());
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
		Contents c = w.getContents(x,y);
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
        throw new UnsupportOperationException("Not yet implemented.");
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
	public void putDown() throw KRuntimeException {
		if (world.getContents(xPosition, yPosition) != NONE) {
			throw new KRuntimeException("This grid is occupied.");
		}
		world.setContents(xPosition, yPosition, BEEPER);
        beepersPlaced++;
        assert(invariantsMaintained());
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
 

}
