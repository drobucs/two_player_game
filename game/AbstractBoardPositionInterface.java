package game;

public interface AbstractBoardPositionInterface {
	Cell getTurn();
	int getHeight();
	int getWidth();
	Cell getCell(int row, int column);
}