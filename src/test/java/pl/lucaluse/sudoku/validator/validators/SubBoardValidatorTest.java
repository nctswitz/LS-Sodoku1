package pl.lucaluse.sudoku.validator.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.lucaluse.sudoku.validator.model.Cell;
import pl.lucaluse.sudoku.validator.validators.observer.ValidationObserver;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static pl.lucaluse.sudoku.validator.TestUtils.newCell;

@RunWith(Parameterized.class)
public class SubBoardValidatorTest {
    private final SubBoardValidator underTest;
    private final Cell[] input;
    private final boolean expectedResult;

    public SubBoardValidatorTest(int x, int y, boolean result, Cell... input) {
        if (input.length < 0) {
            throw new IllegalArgumentException("Input shouldn't be empty.");
        }
        this.underTest = new SubBoardValidator(x,y, mock(ValidationObserver.class));
        this.input = input;
        this.expectedResult = result;
    }

    @Test
    public void testBoard() {
        for (Cell cell: input) {
            underTest.isValid(cell);
        }
        assertEquals(expectedResult, underTest.isValid());
    }


    @Parameterized.Parameters
    public static Iterable<Object[]> inputBoards() {
        return Arrays.asList(new Object[][] {
                {0, 0, true, new Cell[] {
                        newCell(0,0,1), newCell(1,0,2), newCell(2,0,3),
                        newCell(0,1,4), newCell(1,1,5), newCell(2,1,6),
                        newCell(0,2,7), newCell(1,2,8), newCell(2,2,9)
                }},
                {3, 3, true, new Cell[] {
                        newCell(3,3,7), newCell(4,3,2), newCell(5,3,3),
                        newCell(3,4,4), newCell(4,4,9), newCell(5,4,5),
                        newCell(3,5,1), newCell(4,5,8), newCell(5,5,6)
                }},
                {0, 0, false, new Cell[] {
                        newCell(0,0,1), newCell(0,1,0), newCell(0,2,3),
                        newCell(1,3,4), newCell(1,1,5), newCell(1,2,6),
                        newCell(2,0,7), newCell(3,1,8), newCell(2,2,9)
                }},
                {3, 3, false, new Cell[] {
                        newCell(3,4,7), newCell(3,5,2), newCell(3,6,3),
                        newCell(4,4,4), newCell(4,5,9), newCell(4,6,5),
                        newCell(5,4,1), newCell(6,5,8), newCell(5,6,6)
                }},
                {3, 3, false, new Cell[] { newCell(3,4,7), newCell(2,4,2)}},
                {3, 3, false, new Cell[] { newCell(3,4,7), newCell(3,6,2)}},
                {3, 3, false, new Cell[] { newCell(3,4,7), newCell(6,3,2)}}
        });
    }

}