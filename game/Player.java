package game;

public interface Player {
	Move move(Position position);

	public default Move giveUp() {
		Move move = new Move(-1, -1, Cell.E);
        move.setGiveUp(true);
        return move;
	}

	public default Move offerDraw() {
		Move move = new Move(-1, -1, Cell.E);
        move.setDraw(true);
        return move;
	}

	Result answerToOffer(Position position);
}