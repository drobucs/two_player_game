package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
	private final PrintStream out;
	private final Scanner in;
	private boolean isDrawOffer;
	public HumanPlayer(PrintStream out, Scanner in) {
		this.out = out;
		this.in = in;
		isDrawOffer = false;
	}

	public HumanPlayer() {
		this(System.out, new Scanner(System.in));
	}

	@Override
	public Move move(Position position) {
		printOptions();
		out.println("Position");
		out.println(position);
        out.println(position.getTurn() + "'s move");
        out.println("Enter:");
        while (true) {
			String command;
			if (in.hasNextLine()) {
				command = in.nextLine();
			} else {
				isDrawOffer = false;
				return giveUp();
			}
			if (command.isEmpty()) {
				continue;
			}
			if (command.toLowerCase().equals("draw")) {
				if (isDrawOffer) {
					printOnceDraw();
					continue;
				}
				isDrawOffer = true;
				return offerDraw();
			}

			if (command.toLowerCase().equals("giveup")) {
				isDrawOffer = false;
				return giveUp();
			}

			if (!isValidCoordinates(command)) {
				printInvalidCoordiante();
				continue;
			}

			Move move = parseCoordinate(position, command);

			if (!position.isValid(move)) {
				printInvalidCoordiante();
				continue;
			}
			isDrawOffer = false;
			return move;
		}
	}

	@Override
	public Result answerToOffer(Position position) {
		out.println(position.getTurn() + "'s player offer draw. Your answer:");
		while (true) {
			String command;
			if (in.hasNextLine()) {
				command = in.nextLine();
			} else {
				return Result.DRAW;
			}
			if (command.isEmpty()) {
				continue;
			}
			if (command.toLowerCase().equals("yes")) {
				return Result.DRAW;
			} else if (command.toLowerCase().equals("no")) {
				return Result.UNKNOWN;
			} else {
				printUnknownMesssageError();
			}
		}
	}

	private void printUnknownMesssageError() {
		out.println("Unknown command. Try again:");
	}

	private void printOnceDraw() {
		out.println("You cannot offer draw twice. Try again:");
	}

	private void printInvalidCoordiante() {
		out.println("Invalid coordinates. Try again:");
	}

	private void printOptions() {
		out.println("-----------------------------rules------------------------------------------------");
		out.println("If you give up enter \"giveup\"" + System.lineSeparator());
		out.println("If you want to offer draw enter \"draw\" (you can offer draw only one time)" + System.lineSeparator());
		out.println("If you never give up enter row and column!(for example \"1 2\")" + System.lineSeparator());
		out.println("----------------------------------------------------------------------------------");
	}
	private Move parseCoordinate(Position position, final String str) {
		Scanner scan = new Scanner(str);
		int row = scan.nextInt() - 1;
		int column = scan.nextInt() - 1;
		scan.close();
		return new Move(row, column, position.getTurn());
	}
	/// whs + num + whs + num + whs
	private boolean isValidCoordinates(final String str) {
		if (str == null) {
			return false;
		}

		int index = skipWhitespaces(0, str);
		if (!isDigit(index, str)) {
			return false;
		}

		int lenOfDigit = -index;
		index = skipDigits(index, str);
		lenOfDigit += index;
		if (index == str.length() || !Character.isWhitespace(str.charAt(index)) || lenOfDigit > 9) {
			return false;
		}

		index = skipWhitespaces(index, str);
		if (!isDigit(index, str)) {
			return false;
		}

		lenOfDigit = -index;
		index = skipDigits(index, str);
		lenOfDigit += index;
		if ((index < str.length() && !Character.isWhitespace(str.charAt(index))) || lenOfDigit > 9) {
			return false;
		}

		index = skipWhitespaces(index, str);
		if (index != str.length()) {
			return false;
		}
		return true;
	}

	private boolean isDigit(int index, final String str) {
		if (index == str.length()) {
			return false;
		}
		return Character.isDigit(str.charAt(index));
	}

	private int skipWhitespaces(int index, final String str) {
		while (index < str.length() && Character.isWhitespace(str.charAt(index))) {
			index++;
		}
		return index;
	}

	private int skipDigits(int index, final String str) {
		while (index < str.length() && Character.isDigit(str.charAt(index))) {
			index++;
		}
		return index;
	}
	
	private Move invalidInput() {
		Move move = new Move(-1, -1, Cell.E);
		move.setInvalidInput(true);
		return move;
	}

}