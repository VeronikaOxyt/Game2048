package board;

import key.Key;
import java.util.*;

public class SquareBoard extends Board {
    private int size;

    public SquareBoard(int size) {
        super(size, size);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void fillBoard(List<Integer> list) {
        clear();
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        for (Key key : board.keySet()) {
            if (getValue(key) == null) {
                availableKeyList.add(key);
            }
        }
        return availableKeyList;
    }
    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }
    @Override
    public Key getKey(int i, int j) {
        for (Key key : board.keySet()) {
            if (key.getI() == i && key.getJ() == j) {
                return key;
            }
        }
        return null;
    }
    @Override
    public Integer getValue(Key key) {
        return board.get(key);
    }
    @Override
    public List<Key> getColumn(int j){
        var keyColumnList = new ArrayList<Key>();
        for (Key key : board.keySet()) {
            if (key.getJ() == j) {
                keyColumnList.add(key);
            }
        }
        return keyColumnList;
    }
    @Override
    public List<Key> getRow(int i) {
        var keyRowList = new ArrayList<Key>();
        for (Key key : board.keySet()) {
            if (key.getI() == i) {
                keyRowList.add(key);
            }
        }
        return keyRowList;
    }
    @Override
    public boolean hasValue(Integer value) {
        return board.containsValue(value);
    }
    @Override
    public List<Integer> getValues(List<Key> keys){
        var listValues = new ArrayList<Integer>();
        for (Key key : keys) {
            listValues.add(getValue(key));
        }
        return listValues;
    }

    @Override
    public void clear() {
        board.clear();
    }
}
