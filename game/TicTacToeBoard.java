package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard extends Abstract2DBoard {

    public TicTacToeBoard() {
        super(3, 3, 3);
        position = new BoardPosition(this);
    }

    @Override
    public Result makeMove(Move move) {
        return makeMoveCheck(move, true, true, true, true);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" 123").append(System.lineSeparator());
        for (int r = 0; r < 3; r++) {
            sb.append(r + 1);
            for (Cell cell : cells[r]) {
                sb.append(SYMBOLS.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
