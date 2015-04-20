package pl.lucaluse.sudoku.validator.validators;

import org.junit.Test;
import pl.lucaluse.sudoku.validator.model.Cell;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.lucaluse.sudoku.validator.TestUtils.newCell;

public class ComposingValidatorTest {

    @Test
    public void shouldCallAllSubValidators() {
        //given
        CellValidator v1 = mock(CellValidator.class);
        CellValidator v2 = mock(CellValidator.class);
        CellValidator v3 = mock(CellValidator.class);
        ComposingValidator underTest = new ComposingValidator(v1, v2, v3);
        Cell input = newCell(2, 3, 4);

        //when
        underTest.isValid(input);

        //then
        verify(v1).isValid(eq(input));
        verify(v2).isValid(eq(input));
        verify(v3).isValid(eq(input));
    }

    public void shouldReturnFalseIfOneOfValidatorsReturnsFalse() {
        //given
        CellValidator v1 = mock(CellValidator.class);
        CellValidator v2 = mock(CellValidator.class);
        ComposingValidator underTest = new ComposingValidator(v1, v2);
        Cell input = newCell(2, 3, 4);

        when(v1.isValid(eq(input))).thenReturn(true);
        when(v2.isValid(eq(input))).thenReturn(false);

        //when
        boolean result = underTest.isValid(input);

        //then
        verify(v1).isValid(eq(input));
        verify(v2).isValid(eq(input));
        assertEquals(false, result);
    }
}