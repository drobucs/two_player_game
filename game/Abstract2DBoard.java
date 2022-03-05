package game;

import java.util.*;

public abstract class Abstract2DBoard implements Board {
	protected final Map<Cell, String> SYMBOLS = Map.of(
			Cell.X, "X",
			Cell.O, "O",
			Cell.E, "."
	);

	protected final int height;
	protected final int width;
	private final int winLine;
	private int cntOfTurns;
	private Cell turn;
	protected Cell[][] cells;
	protected BoardPosition position;

	public Abstract2DBoard(int height, int width, int winLine) {
		if (height <= 0 || width <= 0 || winLine < 0) {
			throw new AssertionError("Invalid height or width or k");
		}
		this.height = height;
		this.width = width;
		this.winLine = winLine;
		cntOfTurns = 0;
		turn = Cell.X;
		cells = new Cell[height][width];
		for (Cell[] row : cells) {
			Arrays.fill(row, Cell.E);
		}
		position = null;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public Cell getTurn() {
		return turn;
	}

	@Override
	public abstract Result makeMove(Move move);

	@Override
	public abstract String toString();

	@Override
	public Cell getCell(int row, int column) {
		return cells[row][column];
	}

	@Override
	public int getWinLine() {
		return winLine;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	protected Result makeMoveCheck(Move move, boolean row, boolean column,
									boolean mainDiag, boolean sideDiag) {
		if (!position.isValid(move)) {
			return Result.LOSE;
		}

		cntOfTurns++;

		cells[move.getRow()][move.getColumn()] = move.getValue();

		Result res = checkResult(move, row, column, mainDiag, sideDiag);

		turn = turn == Cell.X ? Cell.O : Cell.X;

		return res;
	}

	private boolean checkForDraw() {
		return width * height == cntOfTurns;
	}

	private Result checkResult(Move move, boolean row, boolean column, 
								boolean mainDiag, boolean sideDiag) {
		if ((row && check(move, 0, 1) == Result.WIN) 
			|| (column && check(move, 1, 0) == Result.WIN)
			|| (mainDiag && check(move, 1, 1) == Result.WIN)
			|| (sideDiag && check(move, -1, 1) == Result.WIN)) {
				return Result.WIN;
		}

		if (checkForDraw()) {
			return Result.DRAW;
		}

		return Result.UNKNOWN;
	}

	private boolean corectHeight(int index) {
		return 0 <= index && index < height;
	}

	private boolean corectWidth(int index) {
		return 0 <= index && index < width;
	}

	private Result check(Move move, final int deltaX, final int deltaY) {
		if (checkVector(move, deltaX, deltaY)
			 + checkVector(move, -deltaX, -deltaY) + 1 >= winLine) {
			return Result.WIN;
		}
		return Result.UNKNOWN;
	}

	private int checkVector(Move move, final int dx, final int dy) {
		int cntTurn = 0;
		int i = move.getRow() + dx;
		int j = move.getColumn() + dy;
		while (corectHeight(i) && corectWidth(j)) {
			if (cells[i][j] == turn) {
				cntTurn++;
			} else {
				break;
			}
			i += dx;
			j += dy;
		}
		return cntTurn;
	}
}