package ru.sbrf.game2048.board;

import ru.sbrf.game2048.key.Key;
import java.util.*;

public class SquareBoard<V> extends Board<Key, V> {
    private final int gameSize;

    public SquareBoard(int gameSize) {
        super(gameSize, gameSize);
        this.gameSize = gameSize;
    }

    @Override
    public void fillBoard(List<V> list) {
        clear();
        if (list.size() > gameSize*gameSize) {
            throw new RuntimeException("Application initialization error, the length of " +
                    "the transmitted list goes beyond the board");
        }
        Iterator<V> iterator = list.iterator();
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (iterator.hasNext()) {
                    addItem(new Key(i, j), iterator.next());
                } else {
                    break;
                }
            }
        }
    }
    @Override
    public List<Key> availableSpace() {
        var availableKeyList = new ArrayList<Key>();
        for (var key : board.keySet()) {
            if (getValue(key) == null) {
                availableKeyList.add(key);
            }
        }
        return availableKeyList;
    }

    @Override
    public void addItem(Key key, V value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (var key : board.keySet()) {
            if (key.getI() == i && key.getJ() == j) {
                return key;
            }
        }
        return null;
    }
    @Override
    public V getValue(Key key) {

        return board.get(key);
    }
    @Override
    public List<Key> getColumn(int j){
        var keyColumnList = new ArrayList<Key>();
        for (var i = 0; i < gameSize; i++) {
                keyColumnList.add(getKey(i, j));
        }
        return keyColumnList;
    }

    @Override
    public List<Key> getRow(int i) {
        var keyRowList = new ArrayList<Key>();
        for (var j = 0; j < gameSize; j++) {
            keyRowList.add(getKey(i, j));
        }
        return keyRowList;
    }

    @Override
    public boolean hasValue(V value) {
        return board.containsValue(value);
    }

    @Override
    public List<V> getValues(List<Key> keys){
        var listValues = new ArrayList<V>();
        for (var key : keys) {
            listValues.add(getValue(key));
        }
        return listValues;
    }

    @Override
    public void clear() {
        board.clear();
    }
}
