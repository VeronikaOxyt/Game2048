package game;
import board.Board;
import direction.Direction;
import key.Key;

import java.util.*;

public class Game2048 implements Game {
    private GameHelper helper = new GameHelper();
    private Board board;
    private Random random = new Random();

     public Game2048(Board board) {
         this.board = board;
     }
    @Override
    public void init() {
        board.clear();
        List<Integer> list = Arrays.asList(2, null, null, 2);
        board.fillBoard(list);
    }
    @Override
    public boolean canMove() {
         return !board.availableSpace().isEmpty();
    }
    @Override
    public boolean move(Direction direction) {
        if (canMove()) {
            switch (direction) {
                case LEFT :
                    for (int i = 0; i < board.getWidth(); i++) {
                        helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                    }
                    break;
                case RIGHT :
                    for (int i = board.getWidth()-1; i >= 0; i--) {
                        helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                    }
                    break;
                case UP :
                    for (int j = 0; j < board.getHeight(); j++) {
                        helper.moveAndMergeEqual(board.getValues(board.getColumn(j)));
                    }
                    break;
                case DOWN :
                    for (int j = board.getHeight() -  1; j >=0 ; j--) {
                        helper.moveAndMergeEqual(board.getValues(board.getColumn(j)));
                    }
                    break;
            }
            addItem();
        }
        return canMove();
    }
    @Override
    public void addItem() {
         List<Key> freeCells = board.availableSpace();
        if (!freeCells.isEmpty()) {
            int randomCell = random.nextInt(freeCells.size() - 1);
            board.addItem(freeCells.get(randomCell), 2);
        }
    }

    @Override
    public Board getGameBoard() {
        return board;
    }
    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
