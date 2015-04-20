package pl.lucaluse.sudoku.validator.validators;

import org.junit.Test;
import pl.lucaluse.sudoku.validator.validators.AllDigitsValidator;
import pl.lucaluse.sudoku.validator.validators.CellValidator;

import java.util.stream.Stream;

import static pl.lucaluse.sudoku.validator.TestUtils.*;

import static org.junit.Assert.*;

public class AllDigitsValidatorTest {

    @Test
    public void shouldAcceptAllDigits() {
        //given
        CellValidator underTest = new AllDigitsValidator();

        //when validating all digits from 1 to 9
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).forEach(x -> {
            //then validator should accept them
            assertTrue("Unexpected value: " + x, underTest.isValid(cellWithValue(x)));
        });
    }

    @Test
    public void shouldRejectPreviouslyValidatedValues() {
        //given
        CellValidator underTest = new AllDigitsValidator();

        //when validating some digits
        Stream.of(1, 3, 5, 6).forEach(x -> {
            //then validator should accept them
            assertTrue("Unexpected value: " + x, underTest.isValid(cellWithValue(x)));
        });

        //and then should reject values already validated
        Stream.of(1, 3, 5, 6).forEach(x -> {
            assertFalse(underTest.isValid(cellWithValue(x)));
        });
    }

}