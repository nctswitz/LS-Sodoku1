package pl.lucaluse.sudoku.validator.validators;

import org.junit.*;

import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pl.lucaluse.sudoku.validator.TestUtils.*;

public class ValueInRangeValidatorTest {

    @Test
    public void shouldAcceptInputFromGivenRangeOnly() {
        //given
        CellValidator underTest = new ValueInRangeValidator(1, 9);

        //when validating cells with values in range
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .forEach(i -> {
                            //then validator should accept them
                            assertTrue(underTest.isValid(cellWithValue(i)));
                        }
                );
    }

    @Test
    public void shouldRejectInputOutsideOfGivenRange() {
        //given
        CellValidator underTest = new ValueInRangeValidator(1, 9);

        //when validating cells with values outside of given range
        Stream.of(0, -2, 10, 11, 12345, -16, -1)
                .forEach(i -> {
                            //then validator should accept them
                            assertFalse(underTest.isValid(cellWithValue(i)));
                        }
                );


    }

}
