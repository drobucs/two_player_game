package game;

public class Move {
	private final int row;
	private final int column;
	private final Cell value;
	private boolean draw;
	private boolean giveUp;
	private boolean invalidInput;

	public Move(int row, int column, Cell value) {
		this.row = row;
		this.column = column;
		this.value = value;
		this.draw = false;
		this.giveUp = false;
		this.invalidInput = false;
	}

	public boolean setInvalidInput(boolean invalidInput) {
		return this.invalidInput = invalidInput;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public void setGiveUp(boolean giveUp) {
		this.giveUp = giveUp;
	}

	public boolean getInvalidInput() {
		return invalidInput;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Cell getValue() {
		return value;
	}

	public boolean getDraw() {
		return draw;
	}

	public boolean getGiveUp() {
		return giveUp;
	}

	@Override
    public String toString() {
        return "row=" + (row + 1) + ", column=" + (column + 1) + ", value=" + value;
    }

}