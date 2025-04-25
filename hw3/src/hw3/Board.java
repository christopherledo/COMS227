package hw3;

import static api.Direction.*;
import static api.Orientation.*;

import java.util.ArrayList;

import api.Cell;
import api.Direction;
import api.Move;

/**
 * Represents a board in the game. A board contains a 2D grid of cells and a
 * list of boulders that slide over the cells.
 */
public class Board {

	/**
	 * Stores the state of being grabbed
	 */
	private boolean grabbed;

	/**
	 * Stores the number of which boulder is currently being grabbed
	 */
	private int grabbedBoulderIndex;

	/**
	 * Tracks the currently grabbed boulder object
	 */
	private Boulder grabbedBoulder;

	/**
	 * stores game status if false, game is still running if true, game is over
	 */
	private boolean gameOver = false;

	// end user definitions

	/**
	 * 2D array of cells, the indexes signify (row, column) with (0, 0) representing
	 * the upper-left corner of the board.
	 */
	private Cell[][] grid;

	/**
	 * A list of boulders that are positioned on the board.
	 */
	private ArrayList<Boulder> boulders;

	/**
	 * A list of moves that have been made in order to get to the current position
	 * of boulders on the board.
	 */
	private ArrayList<Move> moveHistory;

	/**
	 * Places all boulders in the boulder list onto the board
	 * 
	 * @param boulders the list of boulders to be placed, passed from the
	 *                 constructor
	 */
	private void boulderSetup(ArrayList<Boulder> boulders) {
		for (Boulder b : boulders) {
//			grid[b.getFirstRow()][b.getFirstCol()].placeBoulder(b);
			if (b.getOrientation() == HORIZONTAL) { // if boulder is horizontal, place left to right
				for (int i = b.getFirstCol(); i < b.getLength() + b.getFirstCol(); ++i) {
					if (isAvailable(b.getFirstRow(), i)) {
						grid[b.getFirstRow()][i].placeBoulder(b);
					}
				}
			} else { // if boulder is vertical, place top to bottom
				for (int i = b.getFirstRow(); i < b.getLength() + b.getFirstRow(); ++i) {
					if (isAvailable(i, b.getFirstCol())) {
						grid[i][b.getFirstCol()].placeBoulder(b);
					}
				}
			}
		}
	}

	/**
	 * Constructs a new board from a given 2D array of cells and list of boulders.
	 * The cells of the grid should be updated to indicate which cells have boulders
	 * placed over them (i.e., placeBoulder() method of Cell). The move history
	 * should be initialized as empty.
	 * 
	 * @param grid        a 2D array of cells which is expected to be a rectangular
	 *                    shape
	 * @param boulders    list of boulders already containing row-column position
	 *                    which should be placed on the board
	 * @param moveHistory a log of all moves played on this specific board
	 * @param grabbed     a status bool to indicate if a boulder is currently being
	 *                    grabbed
	 */

	public Board(Cell[][] grid, ArrayList<Boulder> boulders) {
		this.grid = grid;
		this.boulders = new ArrayList<Boulder>();
		this.boulders.addAll(boulders);
		this.moveHistory = new ArrayList<Move>();
		boulderSetup(boulders);
		grabbed = false; // no boulders should be picked up at the start
	}

	/**
	 * DO NOT MODIFY THIS CONSTRUCTOR
	 * <p>
	 * Constructs a new board from a given 2D array of String descriptions.
	 * 
	 * @param desc 2D array of descriptions
	 */
	public Board(String[][] desc) {
		this(GridUtil.createGrid(desc), GridUtil.findBoulders(desc));
	}

	/**
	 * Returns the number of rows of the board.
	 * 
	 * @return number of rows
	 */
	public int getRowSize() {
		return grid.length;
	}

	/**
	 * Returns the number of columns of the board.
	 * 
	 * @return number of columns
	 */
	public int getColSize() {
		return grid[0].length;
	}

	/**
	 * Returns the cell located at a given row and column.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @return the cell at the specified location
	 */
	public Cell getCellAt(int row, int col) {
		Cell cell = grid[row][col];
		return cell;
	}

	/**
	 * Returns the total number of moves (calls to moveGrabbedBoulder which resulted
	 * in a boulder being moved) made so far in the game.
	 * 
	 * @return the number of moves
	 */
	public int getMoveCount() {
		return moveHistory.size();
	}

	/**
	 * Returns a list of all boulders on the board.
	 * 
	 * @return a list of all boulders
	 */
	public ArrayList<Boulder> getBoulders() {
		ArrayList<Boulder> list = new ArrayList<Boulder>();
		for (Boulder b : boulders) {
			list.add(b);
		}
		return list;
	}

	/**
	 * Returns true if the player has completed the puzzle by positioning a boulder
	 * over an exit, false otherwise.
	 * 
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Models the user grabbing (mouse button down) a boulder over the given row and
	 * column. The purpose of grabbing a boulder is for the user to be able to drag
	 * the boulder to a new position, which is performed by calling
	 * moveGrabbedBoulder().
	 * <p>
	 * This method should find which boulder has been grabbed (if any) and record
	 * that boulder as grabbed in some way.
	 * 
	 * @param row row to grab the boulder from
	 * @param col column to grab the boulder from
	 */
	public void grabBoulderAt(int row, int col) {
		if (!grid[row][col].hasBoulder()) {
			return; // cannot grab boulder if it isn't there :)
		}
		for (Boulder b : boulders) {
			if (b.getOrientation() == HORIZONTAL && b.getFirstRow() == row) { // checks if boulder is in the same row
				if (col >= b.getFirstCol() && col <= b.getFirstCol() + b.getLength() - 1) { // checks if grab is within
																							// the boulder's occupation
																							// of the column
					grabbed = true;
					grabbedBoulder = b;
					grabbedBoulderIndex = boulders.indexOf(b);
					return; // don't need to keep iterating if the conditions are met
				}

			}
			if (b.getOrientation() == VERTICAL && b.getFirstCol() == col) { // checks if boulder is in the same column
				if (row >= b.getFirstRow() && row <= b.getFirstRow() + b.getLength() - 1) { // checks if grab is within
																							// the boulder's occupation
																							// of the row
					grabbed = true;
					grabbedBoulder = b;
					grabbedBoulderIndex = boulders.indexOf(b);
					return; // don't need to keep iterating if the conditions are met
				}
			}

		}
	}

	/**
	 * Models the user releasing (mouse button up) the currently grabbed boulder (if
	 * any). Update the object accordingly to indicate no boulder is currently being
	 * grabbed.
	 */
	public void releaseBoulder() {
		if (!grabbed) {
			return;
		} // does nothing if there is no boulder grabbed
		if (grabbedBoulder.getOrientation() == HORIZONTAL) {
			int start = grabbedBoulder.getFirstCol();
			int end = grabbedBoulder.getFirstCol() + grabbedBoulder.getLength();// FIXME-1?
			int row = grabbedBoulder.getFirstRow();
			for (int i = start; i < end; ++i) {
				if (grid[row][i].isExit()) {
					gameOver = true;
				}
			}
		} else if (grabbedBoulder.getOrientation() == VERTICAL) {
			int start = grabbedBoulder.getFirstRow();
			int end = grabbedBoulder.getFirstRow() + grabbedBoulder.getLength();// FIXME-1?
			int col = grabbedBoulder.getFirstCol();
			for (int i = start; i < end; ++i) {
				if (grid[i][col].isExit()) {
					gameOver = true;
				}
			}
		}
		grabbedBoulder = null;
		grabbed = false;
	}

	/**
	 * Returns the currently grabbed boulder. If there is no currently grabbed
	 * boulder the method return null.
	 * 
	 * @return the currently grabbed boulder or null if none
	 */
	public Boulder getGrabbedBoulder() {
		return grabbedBoulder;
	}

	/**
	 * Returns true if the cell at the given row and column is available for a
	 * boulder to be placed over it. Boulders can only be placed over ground and
	 * exits. Additionally, a boulder cannot be placed over a cell that is already
	 * occupied by another boulder.
	 * 
	 * @param row row location of the cell
	 * @param col column location of the cell
	 * @return true if the cell is available for a boulder, otherwise false
	 */
	public boolean isAvailable(int row, int col) {
		// check if cell is a valid call
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
			return false;
		}

		// check if boulder
		if (grid[row][col].hasBoulder() && grabbedBoulder == null) {
			return false;
		} else if (grid[row][col].hasBoulder() && grabbedBoulder != null) {
			int startrow = grabbedBoulder.getFirstRow();
			int startcol = grabbedBoulder.getFirstCol();
			int length = grabbedBoulder.getLength();
			int lastrow = row + length - 1;
			int lastcol = col + length - 1;
			if (grabbedBoulder.getOrientation() == HORIZONTAL) {// if horizontal,
				if (row == startrow && col >= startcol && col <= lastcol)// check if col is within the boulder's and on
																			// the same row
					return true;
			}
			if (grabbedBoulder.getOrientation() == VERTICAL) {
				if (col == startcol && row >= startrow && row <= lastrow) {// check if col is within the boulder's and
																			// on the same row
					return true;
				}
			}
			return false; // boulder is not the current grabbedBoulder
		}
		// check is exit
		if (grid[row][col].isExit()) {
			return true;
		}
		// check is ground
		else if (grid[row][col].isGround()) {
			return true;
		}
		// check is wall
		else if (grid[row][col].isWall()) {
			return false;
		}
		// just adding this so I don't get an annoying error
		else {
			return false;
		}
	}

	/**
	 * Moves the currently grabbed boulder by one cell in the given direction. A
	 * horizontal boulder is only allowed to move right and left and a vertical
	 * boulder is only allowed to move up and down. A boulder can only move over a
	 * cell that is a floor or exit and is not already occupied by another boulder.
	 * The method does nothing under any of the following conditions:
	 * <ul>
	 * <li>The game is over.</li>
	 * <li>No boulder is currently grabbed by the user.</li>
	 * <li>A boulder is currently grabbed by the user, but the boulder is not
	 * allowed to move in the given direction.</li>
	 * </ul>
	 * If none of the above conditions are meet, the method does at least the
	 * following:
	 * <ul>
	 * <li>Moves the boulder object by calling its move() method.</li>
	 * <li>Calls placeBoulder() for the grid cell that the boulder is being moved
	 * into.</li>
	 * <li>Calls removeBoulder() for the grid cell that the boulder is being moved
	 * out of.</li>
	 * <li>Adds the move (as a Move object) to the end of the move history
	 * list.</li>
	 * <li>Increments the count of total moves made in the game.</li>
	 * </ul>
	 * 
	 * @param dir the direction to move
	 */
	public void moveGrabbedBoulder(Direction dir) {
		if (isGameOver() || !grabbed) {
			return; // quit execution as conditions have not been met
		}

		int row = grabbedBoulder.getFirstRow();
		int col = grabbedBoulder.getFirstCol();
		int length = grabbedBoulder.getLength();
		int lastrow = row + length - 1;
		int lastcol = col + length - 1;

		if ((dir == RIGHT || dir == LEFT) && grabbedBoulder.getOrientation() == HORIZONTAL) {// horizontal movement
			if (dir == LEFT && isAvailable(row, col - 1)) {
				grabbedBoulder.move(LEFT);
				grid[row][col - 1].placeBoulder(grabbedBoulder);
				grid[row][lastcol].removeBoulder();
			} else if (dir == RIGHT && isAvailable(row, col + 1)) {
				grabbedBoulder.move(RIGHT);
				grid[row][lastcol + 1].placeBoulder(grabbedBoulder);
				grid[row][col].removeBoulder();
			} else {
				return; // quit execution as conditions have not been met
			}
		}

		else if ((dir == UP || dir == DOWN) && grabbedBoulder.getOrientation() == VERTICAL) {// vertical movement
			if (dir == UP && isAvailable(row - 1, col)) {
				grabbedBoulder.move(UP);
				grid[row - 1][col].placeBoulder(grabbedBoulder);
				grid[lastrow][col].removeBoulder();
			}

			else if (dir == DOWN && isAvailable(row + 1, col)) {
				grabbedBoulder.move(DOWN);
				grid[lastrow + 1][col].placeBoulder(grabbedBoulder);
				grid[row][col].removeBoulder();
			} else {
				return; // quit execution as conditions have not been met
			}
		}

		// Check if on exit square
		if (grabbedBoulder.getOrientation() == HORIZONTAL) {
			int start = grabbedBoulder.getFirstCol();
			int end = grabbedBoulder.getFirstCol() + grabbedBoulder.getLength();
			for (int i = start; i < end; ++i) {
				if (grid[row][i].isExit()) {
					gameOver = true;
				}
			}
		}

		if (grabbedBoulder.getOrientation() == VERTICAL) {
			int start = grabbedBoulder.getFirstRow();
			int end = grabbedBoulder.getFirstRow() + grabbedBoulder.getLength();
			for (int i = start; i < end; ++i) {
				if (grid[i][col].isExit()) {
					gameOver = true;
				}
			}
		}

		moveHistory.add(new Move(boulders.get(grabbedBoulderIndex), dir));
	}

	/**
	 * Resets the state of the game back to the start, which includes the move
	 * count, the move history, and whether the game is over. The method calls the
	 * reset method of each boulder object. It also updates each grid cells by
	 * calling their placeBoulder method to either set a boulder if one is located
	 * over the cell or set null if no boulder is located over the cell.
	 */
	public void reset() {
		moveHistory.clear();
		grabbed = false;
		gameOver = false;
		for (int row = 0; row < getRowSize(); ++row) {
			for (int col = 0; col < getColSize(); ++col) {
				grid[row][col].removeBoulder();
			}
		}

		for (Boulder b : boulders) {
			b.reset();
		}
		boulderSetup(boulders);
	}

	/**
	 * Returns a list of all legal moves that can be made by any boulder on the
	 * current board.
	 * 
	 * @return a list of legal moves
	 */
	public ArrayList<Move> getAllPossibleMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Boulder b : boulders) {
			int row = b.getFirstRow();
			int col = b.getFirstCol();
			int length = b.getLength();
			int lastrow = row + length - 1;
			int lastcol = col + length - 1;

			if (b.getOrientation() == HORIZONTAL) {// horizontal movement
				if (col > 0 && isAvailable(row, col - 1) && isAvailable(row, lastcol)) {// check left movement
					moves.add(new Move(b, LEFT));
				}
				if (col + length < getColSize() && isAvailable(row, lastcol + 1)) {// check right movement
					moves.add(new Move(b, RIGHT));
				}
			} else if (b.getOrientation() == VERTICAL) {// vertical movement
				if (row > 0 && isAvailable(row - 1, col) && isAvailable(lastrow, col)) {// check up movement
					moves.add(new Move(b, UP));
				}
				if (row + length < getRowSize() && isAvailable(lastrow + 1, col)) {// check down movement
					moves.add(new Move(b, DOWN));
				}
			}
		}
		return moves;
	}

	/**
	 * Gets the list of all moves performed to get to the current position on the
	 * board.
	 * 
	 * @return a list of moves performed to get to the current position
	 */
	public ArrayList<Move> getMoveHistory() {
		return moveHistory;
	}

	/**
	 * EXTRA CREDIT 5 POINTS
	 * <p>
	 * This method is only used by the Solver.
	 * <p>
	 * Undo the previous move. The method gets the last move on the moveHistory list
	 * and performs the opposite actions of that move, which are the following:
	 * <ul>
	 * <li>if required, sets is game over to false</li>
	 * <li>grabs the moved boulder and calls moveGrabbedBoulder passing the opposite
	 * direction</li>
	 * <li>decreases the total move count by two to undo the effect of calling
	 * moveGrabbedBoulder twice</li>
	 * <li>removes the move from the moveHistory list</li>
	 * </ul>
	 * If the moveHistory list is empty this method does nothing.
	 */
	public void undoMove() {
		moveHistory.remove(moveHistory.size() - 1);
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		boolean first = true;
		for (Cell row[] : grid) {
			if (!first) {
				buff.append("\n");
			} else {
				first = false;
			}
			for (Cell cell : row) {
				buff.append(cell.toString());
				buff.append(" ");
			}
		}
		return buff.toString();
	}
}
