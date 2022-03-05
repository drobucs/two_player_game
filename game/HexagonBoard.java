package game;

public class HexagonBoard extends Abstract2DBoard {

	public HexagonBoard(int side, int winLine) {
		super(side, side, winLine);
		position = new BoardPosition(this); 
	}

	@Override
	public Result makeMove(Move move) {
		return makeMoveCheck(move, true, true, false, true);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("rows is left side, columns is right side" + System.lineSeparator());
		for (int i = -1; i < width - 1; ++i) {
			for (int j = width - i - Integer.toString(i + 2).length(); j >= 0; --j) {
				sb.append(" ");
			}
			sb.append(Integer.toString(i + 2) + " ");
			int k = i;
			int j = 0;
			while (k >= 0 && j < width) {
				sb.append(SYMBOLS.get(cells[k][j]) + " ");
				--k;
				++j;
			}
			sb.append(Integer.toString(i + 2));
			sb.append("\n");
		}

		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < i + 3; ++j) {
				sb.append(" ");
			}
			int k = width - 1;
			int j = i;
			while (k >= 0 && j < width) {
				sb.append(SYMBOLS.get(cells[k][j]) + " ");
				--k;
				++j;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}