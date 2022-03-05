package game;

public interface Board extends AbstractBoardPositionInterface {
	Position getPosition();																				
	Result makeMove(Move move);
	int getWinLine();
 }