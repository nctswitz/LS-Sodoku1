package pl.lucaluse.sudoku.validator.validators;

import org.junit.Test;

import static org.junit.Assert.*;
import static pl.lucaluse.sudoku.validator.TestUtils.cellWithPosition;
import static pl.lucaluse.sudoku.validator.TestUtils.newCell;

public class CellCompletenessValidatorTest {
    @Test
    public void simpleCompletnessShouldCheckForAllCells() {
        //given
        CellCompletenessValidator underTest = new CellCompletenessValidator(3, 5, 3, 3);

        //when then
        assertTrue(underTest.isValid(cellWithPosition(3,3)));
        assertTrue(underTest.isValid(cellWithPosition(4,3)));
        assertTrue(underTest.isValid(cellWithPosition(5,3)));

        assertTrue(underTest.isValid());

        assertFalse(underTest.isValid(cellWithPosition(5, 3)));
        assertFalse(underTest.isValid());
    }

    @Test
    public void simpleCompletnessShouldCheckForOneCell() {
        //given
        CellCompletenessValidator underTest = new CellCompletenessValidator(0, 0, 0, 0);

        //when then
        assertFalse(underTest.isValid());
        assertTrue(underTest.isValid(cellWithPosition(0, 0)));
        assertTrue(underTest.isValid());

        assertFalse(underTest.isValid(cellWithPosition(0, 0)));
        assertFalse(underTest.isValid());
    }

    @Test
    public void simpleCompletnessShouldCheckLargeBoard() {
        //given
        CellCompletenessValidator underTest = new CellCompletenessValidator(0, 8, 0, 8);

        //then then
        assertTrue(underTest.isValid(newCell(8,8,2)));
    }

}