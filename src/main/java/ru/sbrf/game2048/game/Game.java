package ru.sbrf.game2048.game;
import ru.sbrf.game2048.board.Board;
import ru.sbrf.game2048.direction.Direction;

public interface Game {
    public void init();

    public boolean canMove();

    public boolean move(Direction direction);

    public void addItem();

    public Board getGameBoard();

    public boolean hasWin();

}
