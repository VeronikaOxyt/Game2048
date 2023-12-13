package ru.sbrf.game2048.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        var listWithoutNull = new ArrayList<Integer>();
        var modifiedList = new ArrayList<Integer>();

        if (list.isEmpty()) {
            return list;
        }
        for (Integer value : list) {
            if (value != null) {
                listWithoutNull.add(value);
            }
        }
        for (int i = 0; i < listWithoutNull.size(); i++) {
            if (i < listWithoutNull.size()-1 &&
                    Objects.equals(listWithoutNull.get(i), listWithoutNull.get(i + 1))) {
                modifiedList.add(listWithoutNull.get(i)*2);
                if (i < listWithoutNull.size()-1) {
                    i++;
                }
            } else {
                modifiedList.add(listWithoutNull.get(i));
            }
        }

        while (modifiedList.size() < list.size()) {
            modifiedList.add(null);
        }
        return modifiedList;
    }
}
