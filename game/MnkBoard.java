package game;

import java.util.*;

public class MnkBoard extends Abstract2DBoard {

	private int lengthOfNumber;

	public MnkBoard(int height, int width, int winLine) {
		super(height, width, winLine);
		position = new BoardPosition(this);
		lengthOfNumber = Math.max(Integer.toString(width).length(),
									Integer.toString(height).length());
	}

	@Override
	public Result makeMove(Move move) {
		return makeMoveCheck(move, true, true, true, true);
	}

	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lengthOfNumber + 1;  ++i) {
			sb.append(" ");
		}
		for(int i = 1; i <= width; ++i) {
			printString(sb, Integer.toString(i));
		}
		sb.append("\n");
		for (int i = 1; i <= height; ++i) {
			printString(sb, Integer.toString(i));
			for (int j = 0; j < width; ++j) {
				printString(sb, SYMBOLS.get(cells[i - 1][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private void printString(StringBuilder sb, String str) {
		for (int j = 0; j <= lengthOfNumber - str.length(); ++j) {
			sb.append(" ");
		}
		sb.append(str);
	}
}