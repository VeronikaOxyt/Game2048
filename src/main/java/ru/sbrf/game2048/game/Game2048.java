package ru.sbrf.game2048.game;
import ru.sbrf.game2048.board.Board;
import ru.sbrf.game2048.board.SquareBoard;
import ru.sbrf.game2048.direction.Direction;
import ru.sbrf.game2048.exception.NotEnoughSpace;
import ru.sbrf.game2048.key.Key;

import java.util.*;

public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    private final GameHelper helper = new GameHelper();
    private final Random random = new Random();

    @Override
    public void init() {
        board.clear();
        var listOfNull = new ArrayList<Integer>();
        for (int i = 0; i < GAME_SIZE*GAME_SIZE; i++) {
            listOfNull.add(null);
        }
        board.fillBoard(listOfNull);
        try {
            addItem();
            addItem();
        } catch (NotEnoughSpace e) {
            System.out.println(e.getMessage());
        }
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
                        moveIsDone = makeAMove(rowKeysLeft, moveIsDone);
                    }
                    break;
                case RIGHT :
                    for (int i = 0; i < GAME_SIZE; i++) {
                        var rowKeysRight = board.getRow(i);
                        Collections.reverse(rowKeysRight);
                        moveIsDone = makeAMove(rowKeysRight, moveIsDone);
                    }
                    break;
                case UP :
                    for (var j = 0; j < GAME_SIZE; j++) {
                        var columnKeysUp = board.getColumn(j);
                        moveIsDone = makeAMove(columnKeysUp, moveIsDone);
                    }
                    break;
                case DOWN :
                    for (var j = board.getHeight() -  1; j >=0 ; j--) {
                        var columnKeysDown = board.getColumn(j);
                        Collections.reverse(columnKeysDown);
                        moveIsDone = makeAMove(columnKeysDown, moveIsDone);
                    }
                    break;
            }
            try {
                if(moveIsDone) {
                    addItem();
                }
            } catch (NotEnoughSpace e) {
                System.out.println(e.getMessage());
            }
        }
        return canMove();
    }
    @Override
    public void addItem() throws NotEnoughSpace {
        List<Key> freeCells = board.availableSpace();
        if (freeCells.isEmpty()) {
            throw new NotEnoughSpace("No available space");
        }
        int probability = random.nextInt(9);
        int randomCell = random.nextInt(freeCells.size());
        if (probability < 8) {
            board.addItem(freeCells.get(randomCell), 2);
        }
        else {
            board.addItem(freeCells.get(randomCell), 4);
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

    private boolean makeAMove(List<Key> rowOrColumnKeys, boolean moveIsDone) {
        var rowOrColumnValues = helper.moveAndMergeEqual(board
                .getValues(rowOrColumnKeys)).iterator();
        for (var key : rowOrColumnKeys) {
            moveIsDone = moveIsDone || !Objects.equals(board
                    .getValues(rowOrColumnKeys), rowOrColumnValues);
            board.addItem(key, rowOrColumnValues.next());
        }
        return moveIsDone;
    }
}
