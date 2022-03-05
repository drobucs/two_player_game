package game;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Game game = new Game(new RandomPlayer (), new HumanPlayer (), true);
		game.play(new HexagonBoard(11, 5));
	}
}
