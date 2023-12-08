package board;

import key.Key;
import java.util.*;


 public abstract class Board {
    private int width;
    private int height;
    Map<Key, Integer> board = new HashMap<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
       return width;
    }
    public int getHeight() {
       return height;
    }



    public abstract void fillBoard(List<Integer> list);

    public abstract List<Key> availableSpace();
    public abstract void addItem(Key key, Integer value);

    public abstract Key getKey(int i, int j);

    public abstract Integer getValue(Key key);

    public abstract List<Key> getColumn(int j);

    public abstract List<Key> getRow(int i);

    public abstract boolean hasValue(Integer value);

    public abstract List<Integer> getValues(List<Key> keys);

    public abstract void clear();
 }
