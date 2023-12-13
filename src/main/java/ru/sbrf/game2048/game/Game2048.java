package ru.sbrf.game2048.game;
import ru.sbrf.game2048.board.Board;
import ru.sbrf.game2048.board.SquareBoard;
import ru.sbrf.game2048.direction.Direction;
import ru.sbrf.game2048.key.Key;

import java.util.*;

public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    private GameHelper helper = new GameHelper();
    private Random random = new Random();

    @Override
    public void init() {
        board.clear();
        var listOfNull = new ArrayList<Integer>();
        for (int i = 0; i < GAME_SIZE*GAME_SIZE; i++) {
            listOfNull.add(null);
        }
        board.fillBoard(listOfNull);
        addItem();
        addItem();
    }
    @Override
    public boolean canMove() {
        if (board.availableSpace().isEmpty()) {
            for (int i = 0; i < GAME_SIZE; i++) {
            var rowValues = board.getValues(board.getRow(i));
            var columnValues = board.getValues(board.getColumn(i));
            for (int j = 1; j < GAME_SIZE; j++) {
                if (rowValues.get(j).equals(rowValues.get(j - 1)) ||
                        columnValues.get(j).equals(columnValues.get(j - 1))) {
                    return true;
                }
            }
        }
        }
        return !board.availableSpace().isEmpty();
    }
    @Override
    public boolean move(Direction direction) {
        boolean moveIsDone = false;
        if (canMove()) {
            switch (direction) {
                case LEFT :
                    for (int i = 0; i < GAME_SIZE; i++) {
                        var rowKeysLeft = board.getRow(i);
                        var rowValuesLeft = helper.moveAndMergeEqual(board
                                .getValues(rowKeysLeft)).iterator();
                        int counter = 0;
                        for (var key : rowKeysLeft) {
                            moveIsDone = moveIsDone || !Objects.equals(board
                                    .getValues(rowKeysLeft), rowValuesLeft);
                            counter++;
                            board.addItem(key, rowValuesLeft.next());
                        }
                    }
                    break;
                case RIGHT :
                    System.out.println("61 line");
                    for (int i = 0; i < GAME_SIZE; i++) {
                        var rowKeysRight = board.getRow(i);
                        Collections.reverse(rowKeysRight);
                        var rowValuesRight = helper.moveAndMergeEqual(board
                                .getValues(rowKeysRight)).iterator();
                        for (var key : rowKeysRight) {
                            moveIsDone = moveIsDone || !Objects.equals(board
                                    .getValues(rowKeysRight), rowValuesRight);
                            board.addItem(key, rowValuesRight.next());
                        }
                    }
                    break;
                case UP :
                    for (int j = 0; j < GAME_SIZE; j++) {
                        var columnKeysUp = board.getColumn(j);
                        var columnValuesUp = helper.moveAndMergeEqual(board
                                .getValues(columnKeysUp)).iterator();
                        for (var key : columnKeysUp) {
                            moveIsDone = moveIsDone || !Objects.equals(board
                                    .getValues(columnKeysUp), columnValuesUp);
                            board.addItem(key, columnValuesUp.next());
                        }
                    }
                    break;
                case DOWN :
                    for (int j = board.getHeight() -  1; j >=0 ; j--) {
                        var columnKeysDown = board.getColumn(j);
                        Collections.reverse(columnKeysDown);
                        var columnValuesDown = helper.moveAndMergeEqual(board
                                .getValues(columnKeysDown)).iterator();
                        for (var key : columnKeysDown) {
                            moveIsDone = moveIsDone || !Objects.equals(board
                                    .getValues(columnKeysDown), columnKeysDown);
                            board.addItem(key, columnValuesDown.next());
                        }
                    }
                    break;
            }
            if(moveIsDone) {
                addItem();
            }
        }
        return canMove();
    }
    @Override
    public void addItem() {
         List<Key> freeCells = board.availableSpace();
        if (!freeCells.isEmpty()) {
            int probability = random.nextInt(9);
            int randomCell = random.nextInt(freeCells.size() - 1);
            if (probability < 8) {
                board.addItem(freeCells.get(randomCell), 2);
            }
            else {
                board.addItem(freeCells.get(randomCell), 4);
            }
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
