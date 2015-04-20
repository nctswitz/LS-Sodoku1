package pl.lucaluse.sudoku.validator.validators;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static pl.lucaluse.sudoku.validator.TestUtils.*;
import static pl.lucaluse.sudoku.validator.validators.CellPositionValidator.newColumnValidator;
import static pl.lucaluse.sudoku.validator.validators.CellPositionValidator.newRowValidator;

public class CellPositionValidatorTest {

    @Test
    public void shouldAcceptCellsWithValidPosition() {
        //given validator which accepts cells from 9x9 board
        CellValidator underTest = new CellPositionValidator(0, 9, 0, 9);

        //when validating cells within board
        //then validator should accept them
        assertTrue(underTest.isValid(cellWithPosition(0, 0)));
        assertTrue(underTest.isValid(cellWithPosition(9, 9)));

        assertTrue(underTest.isValid(cellWithPosition(1, 4)));
        assertTrue(underTest.isValid(cellWithPosition(7, 4)));
    }

    public void shouldRejectCellsWithInvalidPosition() {
        //given validator which accepts cells from 9x9 board
        CellValidator underTest = new CellPositionValidator(0, 9, 0, 9);

        //when validating cells within board
        //then validator should reject them
        assertFalse(underTest.isValid(cellWithPosition(0, -1)));
        assertFalse(underTest.isValid(cellWithPosition(-1, -1)));
        assertFalse(underTest.isValid(cellWithPosition(10, 2)));
        assertFalse(underTest.isValid(cellWithPosition(13, 25)));
    }

    @Test
    public void testNewColumnValidator() {
        //given
        CellValidator underTest = newColumnValidator(4);

        //when
        Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8).forEach(x -> {
            //then
            assertTrue(String.format("Invalid validation at [4, %d]", x), underTest.isValid(cellWithPosition(4, x)));
        });

        assertFalse(underTest.isValid(cellWithPosition(3, 1)));
        assertFalse(underTest.isValid(cellWithPosition(4, -1)));
        assertFalse(underTest.isValid(cellWithPosition(4, 9)));
    }

    @Test
    public void testNewRowValidator() {
        //given
        CellValidator underTest = newRowValidator(2);

        //when
        Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8).forEach(x -> {
            //then
            assertTrue(String.format("Invalid validation at [%d, 2]", x), underTest.isValid(cellWithPosition(x, 2)));
        });

        assertFalse(underTest.isValid(cellWithPosition(3, 1)));
        assertFalse(underTest.isValid(cellWithPosition(-1, 2)));
        assertFalse(underTest.isValid(cellWithPosition(9, 2)));

    }
}