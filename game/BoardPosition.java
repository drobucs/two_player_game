package game;

public final class BoardPosition implements Position {
	private Board board;

	public BoardPosition(Board board) {
		this.board = board;
	}

	@Override
	public Cell getCell(int row, int column) {
		return board.getCell(row, column);
	}

	@Override
	public Cell getTurn() {
		return board.getTurn();
	}

	@Override
	public boolean isValid(Move move) {		
		return 0 <= move.getRow() && move.getRow() < board.getHeight()
				&& 0 <= move.getColumn() && move.getColumn() < board.getWidth()
				&& getCell(move.getRow(), move.getColumn()) == Cell.E
				&& move.getValue() == board.getTurn();
	}

	@Override
	public String toString() {
		return board.toString();
	}

	@Override
	public int getWidth() {
		return board.getWidth();
	}
	@Override
	public int getHeight() {
		return board.getHeight();
	}

}