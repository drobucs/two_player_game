package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move move(Position position) {
        while (true) {
            final Move move = new Move(
                    random.nextInt(position.getHeight()),
                    random.nextInt(position.getWidth()),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }

    @Override
    public Result answerToOffer(Position position) {
        int decide = random.nextInt(2);
        if (decide == 0) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }
}
