package pl.lucaluse.sudoku.validator;

import pl.lucaluse.sudoku.validator.model.Cell;

public class TestUtils {
    public static Cell newCell(int x, int y, int value) {
        return new Cell(x, y, value);
    }

    public static Cell cellWithValue(int value) {
        return newCell(0, 0, value);
    }

    public static Cell cellWithPosition(int x, int y) {
        return newCell(x, y, 0);
    }
}
