package pl.lucaluse.sudoku.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;
import static pl.lucaluse.sudoku.validator.TestUtils.newCell;

@RunWith(Parameterized.class)
public class SudokuValidatorTest {
    private final int[][] input;
    private final boolean expectedResult;

    public SudokuValidatorTest(boolean expectedResult, int[][] input) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Test
    public void shouldReturnFalseForIncompleteBoard() {
        SudokuValidator underTest = new SudokuValidator();
        for (int y = input.length - 1; y >= 0; y--) {
            int[] row = input[y];
            for (int x = row.length - 1; x >= 0; x--) {
                underTest.isValid(newCell(x,y,row[x]));
            }
        };
        assertEquals(expectedResult, underTest.isValid());
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> inputValues() {
        return Arrays.asList(new Object[][]{
                {true, new int[][]{
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {4, 5, 6, 7, 8, 9, 1, 2, 3},
                        {7, 8, 9, 1, 2, 3, 4, 5, 6},
                        {8, 9, 1, 2, 3, 4, 5, 6, 7},
                        {2, 3, 4, 5, 6, 7, 8, 9, 1},
                        {5, 6, 7, 8, 9, 1, 2, 3, 4},
                        {6, 7, 8, 9, 1, 2, 3, 4, 5},
                        {9, 1, 2, 3, 4, 5, 6, 7, 8},
                        {3, 4, 5, 6, 7, 8, 9, 1, 2}
                }},
                {false, new int[][]{
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {4, 5, 6, 7, 8, 9, 1, 2, 3},
                        {7, 8, -9, 1, 2, 3, 4, 5, 6},
                        {8, 9, 1, 2, 3, 4, 5, 6, 7},
                        {2, 3, 4, 5, 6, 7, 8, 9, 1},
                        {5, 6, 7, 8, 9, 1, 2, 3, 4},
                        {6, 7, 8, 9, 1, 2, 3, 4, 5},
                        {9, 1, 2, 3, 4, 5, 6, 7, 8},
                        {3, 4, 5, 6, 7, 8, 9, 1, 2}
                }},
                {false, new int[][]{
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                }},
                {false, new int[][]{
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {4, 5, 6, 7, 8, 9, 1, 2, 3},
                        {7, 8, 9, 1, 2, 3, 4, 5, 6},
                        {8, 9, 1, 2, 3, 4, 5, 6, 7},
                        {2, 3, 4, 5, 6, 7, 8, 9, 1},
                        {5, 6, 7, 8, 9, 1, 2, 3, 4},
                        {6, 7, 8, 9, 1, 2, 3, 4, 5},
                        {9, 1, 2, 3, 4, 5, 6, 7, 8},
                        {3, 4, 5, 6, 7, 8, 9, 1, 2},
                        {3, 4, 5, 6, 7, 8, 9, 1, 2}
                }}
        });
    }
}