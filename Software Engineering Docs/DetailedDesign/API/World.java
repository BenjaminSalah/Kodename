package model;

import java.io.Serializable;

/**
 * World is a class which contains the definitions of the environment that the robot Karel will exist in.
 * It is a two-dimensional grid where each grid space can hold a beeper, wall, or nothing at all.
 * World must contain a positive set of dimensions. In other words, it is illegal to create a world
 * where it has a negative height and/or a negative width. It must also contain 0 or more contents.f
 * @author Kodename team
 *
 */
public class World implements Serializable {

	public enum Contents { WALL, BEEPER, NONE, OUT_OF_BOUNDS }
	
	Contents[][] grid;
	int xSize;
	int ySize;
	int beeperCount;
	
	/**
	 * Creates a new World instance of size x by y
	 * @param x The width of the World
	 * @param y The height of the World
	 */
	public World(int x, int y) {
		
		if (x < 0 || y < 0)
            throw new IllegalArgumentException("Negative coordinate entered");

		
		beeperCount = 0;
		grid = new Contents[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				grid[i][j] = Contents.NONE;
			}
		}
	};
	
	/**
	 * Returns whatever content type is present at grid coordinate x and y
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return whatever content type (wall, beeper, or none) is present at grid (x, y)
	 */
	public Contents getContents(int x, int y) {
		
		if (x < 0 || y < 0)
            throw new IllegalArgumentException("Negative coordinate entered");
		
		if (x >= xSize || y >= ySize) {
			return Contents.OUT_OF_BOUNDS;
		}
		return grid[x][y];
	}
	
	/**
	 * Adds a content, either wall or beeper, to the grid coordinates specified
	 * @param x The x coordinate of the new content to be added
	 * @param y The y coordinate of the new content to be added
	 * @param c The type of content to be added, either wall or beeper
	 */
	public void setContents(int x, int y, Contents c) {
		
		if (x < 0 || y < 0)
            throw new IllegalArgumentException("Negative coordinate entered");
		
		// don't forget to update beepercount
		// TODO everything here
		throw new UnsupportedOperationException("Unimplemented.");
	}
}
