package game;

public class Game {
	private Player player1, player2;
	private boolean log;
	public Game(Player player1, Player player2, boolean log) {
		this.player1 = player1;
		this.player2 = player2;
		this.log = log;
	}

	public int play(Board board) {
		if (player1 == null || player2 == null) {
			throw new AssertionError("Player cannot be null");
		}
		if (board == null) {
			throw new AssertionError("Board cannot be null");
		}
		if (board.getWinLine() == 0) {
			log("Player 2 won");
			return 2;
		}
		while (true) {
			final int result1 = move(board, player1, 1);
			if (result1 != -1) {
				return result1;
			}
			final int result2 = move(board, player2, 2);
			if (result2 != -1) {
				return result2;
			}
		}
	}
	
	private int move(Board board, Player player, int nomer) {
		Move move = player.move(board.getPosition());
		if (move.getGiveUp()) {
			log("Player " + nomer + " give up");
			return 3 - nomer;
		}

		if (move.getDraw()) {
			log("------------------------------------------------------------------------------");
			log("Player " + nomer + " offer draw");
			//log("Enter \"yes\" if you accept a draw and enter \"no\" if you reject it:");
			if (nomer == 1) {
				Result res = player2.answerToOffer(board.getPosition());
				if (Result.DRAW == res) {
					log("Draw");
					return 0;
				} else if (Result.LOSE == res) {
					log("Player 2 lose(invalid input)");
					return 1;
				}
			} else {
				Result res = player1.answerToOffer(board.getPosition());
				if (Result.DRAW == res) {
					log("Draw");
					return 0;
				} else if (Result.LOSE == res) {
					log("Player 1 lose(invalid input)");
					return 2;
				}
			}
			log("Player " + (3 - nomer) + " rejected offer");
			move = player.move(board.getPosition());
		}

		if (move.getDraw()) {
			log("Player " + nomer + " lose: offer draw twice a turn");
			return 3 - nomer;
		}

		if (move.getInvalidInput()) {
			log("Invalid input ---> player " + nomer + " lose");
			return 3 - nomer;
		}

		if (!board.getPosition().isValid(move)) {
			log("Player " + nomer + " lose");
			return 3 - nomer;
		}
		

		Result result = board.makeMove(move);

		log("Player " + nomer + " move:" + move);
		log("Position:\n" + board);
		
		if (result == Result.WIN) {
			log("Player " + nomer + " won");
			return nomer;
		} else if (result == Result.LOSE) {
			log("Player " + nomer + " lose");
			return 3 - nomer;
		} else if (result == Result.DRAW) {
			log("Draw");
			return 0;
		} else {
			return -1;
		}
	}

	private void log(String message) {
		if (log) {
			System.out.println(message);
		}
	}
}