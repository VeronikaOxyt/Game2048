package ru.sbrf.game2048;
import org.junit.jupiter.api.Test;
import ru.sbrf.game2048.board.SquareBoard;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareBoardTest {
    private SquareBoard<Integer> squareBoard;


    @Test
    void testFillBoardWrongLIstSize() {
        squareBoard = new SquareBoard<>(4);
        List<Integer> list = new ArrayList<>();
        for (var i = 0; i < 17; i++) {
            list.add(i);
        }
        assertThrows(RuntimeException.class, () -> squareBoard.fillBoard(list));
    }

}
