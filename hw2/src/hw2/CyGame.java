package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (they have negative
 * money).
 * 
 * @author Christopher Ledo
 */
public class CyGame {
	/**
	 * The endzone square type.
	 */
	public static final int ENDZONE = 0;
	/**
	 * The CyTown square type.
	 */
	public static final int CYTOWN = 1;
	/**
	 * The pay rent square type.
	 */
	public static final int PAY_RENT = 2;
	/**
	 * The fall behind square type.
	 */
	public static final int FALL_BEHIND = 3;
	/**
	 * The blizzard square type.
	 */
	public static final int BLIZZARD = 4;
	/**
	 * The pass class square type.
	 */
	public static final int PASS_CLASS = 5;
	/**
	 * Points awarded when landing on or passing over the endzone square.
	 */
	public static final int ENDZONE_PRIZE = 200;
	/**
	 * The standard rent payed to the other player when landing on a pay rent
	 * square.
	 */
	public static final int STANDARD_RENT_PAYMENT = 80;
	/**
	 * The cost to by CyTown.
	 */
	public static final int CYTOWN_COST = 200;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;

	private int numSquares;

	private int player1Money = 0;
	private int player2Money = 0;

	/**
	 * Player 1's current square number
	 */
	private int player1Square = 0;
	/**
	 * Player 2's current square number
	 */
	private int player2Square = 0;

	/**
	 * The player number that owns cyTown. If 0, no one owns CyTown.
	 */
	private int cyTownOwner = 0;

	/**
	 * The player number whose turn it currently is.
	 */
	private int currentPlayer = 1;

	/**
	 * The constructor for the cygame
	 * 
	 * @param numSquares    - number of squares on board
	 * @param startingMoney - amount of money that each player starts with
	 */
	public CyGame(int numSquares, int startingMoney) {
		this.numSquares = numSquares;
		player1Square = 0;
		player2Square = 0;
		this.player1Money = startingMoney;
		this.player2Money = startingMoney;

	}

	/**
	 * If all conditions are met, buys cytown for current player and ends turn
	 */
	public void buyCyTown() {
		if (/* currentPlayer == 1 && */ player1Money >= CYTOWN_COST && isPlayer2CyTownOwner() == false
				&& player1Square == numSquares - 1) {
			player1Money -= CYTOWN_COST;
			cyTownOwner = 1;
			endTurn();
		}

		else if (/* currentPlayer == 2 && */ player2Money >= CYTOWN_COST && isPlayer1CyTownOwner() == false
				&& player1Square == numSquares - 1) {
			player2Money -= CYTOWN_COST;
			cyTownOwner = 2;
			endTurn();
		}
	}

	/**
	 * Ends the turn of the current player
	 */
	public void endTurn() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
		}

		else if (currentPlayer == 2) {
			currentPlayer = 1;
		}
	}

	/**
	 * gets the current player number
	 * 
	 * @return the current active player
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * gets the opposing player number
	 * 
	 * @return the other player than the current one
	 */
	public int getOtherPlayer() {
		if (currentPlayer == 1) {
			return 2;
		}

		else if (currentPlayer == 2) {
			return 1;
		}

		else {
			return 0;
		}
	}

	/**
	 * gets the current player's square number
	 * 
	 * @param player inputs the player number
	 * @return the square number the current player is on
	 */
	public int getPlayerSquare(int player) {
		if (player == 1) {
			return player1Square;
		}

		else if (player == 2) {
			return player2Square;
		} else {
			return 0;
		}
	}

	/**
	 * gets the current player's money
	 * 
	 * @param player inputs the player number
	 * @return the amount of money that player has
	 */
	public int getPlayerMoney(int player) {
		if (player == 1) {
			return player1Money;
		}

		else if (player == 2) {
			return player2Money;
		}

		else {
			return 0;
		}
	}

	/**
	 * gets the given square's type
	 * 
	 * @param square pulls the square number that the current player is on
	 * @return the square type that the current player is on
	 */
	public int getSquareType(int square) {
		if (square == 0) {
			return ENDZONE;
		}

		else if (square == (numSquares - 1)) {
			return CYTOWN;
		}

		else if (square % 5 == 0) {
			return PAY_RENT;
		}

		else if (square % 11 == 0 || square % 7 == 0) {
			return FALL_BEHIND;
		}

		else if (square % 3 == 0) {
			return BLIZZARD;
		}

		else {
			return PASS_CLASS;
		}
	}

	/**
	 * Indicates if the game is over
	 * 
	 * @return true if game is ended, otherwise false
	 */
	public boolean isGameEnded() {
		if (player1Money >= MONEY_TO_WIN || player2Money >= MONEY_TO_WIN || player1Money < 0 || player2Money < 0) {
			return true;
		}
		return false;
	}

	/**
	 * Indicates if player 1 owns cytown
	 * 
	 * @return true is player 1 is cytown owner, otherwise false
	 */
	public boolean isPlayer1CyTownOwner() {
		if (cyTownOwner == 1) {
			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * Indicates if player 2 owns cytown
	 * 
	 * @return true if player 2 is cytown owner, otherwise false
	 */
	public boolean isPlayer2CyTownOwner() {
		if (cyTownOwner == 2) {
			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * Commits the roll action. First checks if game is ended Then checks player
	 * number Then checks if current space is a blizzard Moves forward if odd,
	 * otherwise ends roll action Then checks all other space types
	 * 
	 * @param value the value of the dice roll
	 */
	public void roll(int value) {
		boolean hitEndzone = false;

//		if (value <= 6 && value >= 1) {// Checks that dice roll is within bounds
		if (isGameEnded() == false) {// Checks that dice roll is within bounds
			rollcheck: if (currentPlayer == 1) {// Executes for player 1

				// BLIZZARD CHECKS
				if (getSquareType(player1Square) == BLIZZARD) {// Checks if current square is a BLIZZARD square
					if (value % 2 == 1) {// Moves forward only if roll is odd
						player1Square += value;
					} else {
						// do nothing
					}
				}

				// NORMAL ROLL
				else {
					player1Square += value;
					if (player1Square >= numSquares && !hitEndzone) { // Checks if square passed endzone, adjusts square
																		// within bounds of numSquares
						player1Square %= numSquares;
						player1Money += ENDZONE_PRIZE; // Awards player endzone prize
						hitEndzone = true;

					}
				} // END BLIZZARD CHECKS

				if (getSquareType(player1Square) == CYTOWN) {
					break rollcheck;
				}

				if (getSquareType(player1Square) == PAY_RENT) {
					if (isPlayer2CyTownOwner()) {
						player1Money -= STANDARD_RENT_PAYMENT;
						player2Money += STANDARD_RENT_PAYMENT;
					}
					player1Money -= STANDARD_RENT_PAYMENT;
					player2Money += STANDARD_RENT_PAYMENT;
				}

				if (getSquareType(player1Square) == FALL_BEHIND) {
					player1Square--;
					roll(0);
					break rollcheck;
				}

				if (getSquareType(player1Square) == PASS_CLASS) {
					roll(4);
					break rollcheck;
				}
				endTurn();
			} // end of player 1 action list

			else if (currentPlayer == 2) {
				// BLIZZARD CHECKS
				if (getSquareType(player2Square) == BLIZZARD) {// Checks if current square is a BLIZZARD square
					if (value % 2 == 1) {// Moves forward only if roll is odd
						player2Square += value;
					} else {
						// do nothing
					}
				} else {
					player2Square += value;
					if (player2Square >= numSquares) {
						player2Square %= numSquares;
						player2Money += ENDZONE_PRIZE;
					}
				} // END BLIZZARD CHECKS

				if (getSquareType(player2Square) == CYTOWN) {
					break rollcheck;
				}

				if (getSquareType(player2Square) == PAY_RENT) {
					if (isPlayer1CyTownOwner()) {
						player2Money -= STANDARD_RENT_PAYMENT;
						player1Money += STANDARD_RENT_PAYMENT;
					}
					player2Money -= STANDARD_RENT_PAYMENT;
					player1Money += STANDARD_RENT_PAYMENT;
				}

				if (getSquareType(player2Square) == FALL_BEHIND) {
					player2Square--;
					roll(0);
					break rollcheck;
				}

				if (getSquareType(player2Square) == PASS_CLASS) {
					roll(4);
					break rollcheck;
				}
				endTurn();
			} // end of player 2 action list
		}
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, false, $0) Player 2: (0, false, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it is.
	 * The values (0, false, $0) indicate which square the player is on, if the
	 * player is the owner of CyTown, and how much money the player has
	 * respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player 1%s: (%d, %b, $%d) Player 2%s: (%d, %b, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
			player1Turn = "*";
		} else {
			player2Turn = "*";
		}
		return String.format(fmt, player1Turn, getPlayerSquare(1), isPlayer1CyTownOwner(), getPlayerMoney(1),
				player2Turn, getPlayerSquare(2), isPlayer2CyTownOwner(), getPlayerMoney(2));
	}
}
