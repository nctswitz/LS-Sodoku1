package pl.lucaluse.sudoku.validator.validators;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.lucaluse.sudoku.validator.model.Cell;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static pl.lucaluse.sudoku.validator.TestUtils.newCell;

@RunWith(MockitoJUnitRunner.class)
public class OptionalValidatorTest {
    @Mock
    private CellValidator onlyWhen;

    @Mock
    private CellValidator mainValidator;


    private CellValidator underTest;

    @Before
    public void setUp() {
        underTest = new OptionalValidator(onlyWhen, mainValidator);
    }

    @Test
    public void shouldCallSecondValidatorOnlyWhenFirstTellsThatCellIsValid() {
        //given deciding validator tells that cell is not valid
        when(onlyWhen.isValid(any())).thenReturn(false);

        //when
        underTest.isValid(newCell(1, 2, 123));
        underTest.isValid(newCell(0, 2, 1));

        //then
        verifyZeroInteractions(mainValidator);
    }

    @Test
    public void shouldCallSecondValidatorAndReturnItsResult() {
        //given
        when(onlyWhen.isValid(any())).thenReturn(true);
        Cell input = newCell(1, 2, 123);
        when(mainValidator.isValid(eq(input))).thenReturn(false);

        //when then
        assertFalse(underTest.isValid(input));

        //and
        verify(mainValidator).isValid(eq(input));
    }

    @Test
    public void shouldCallSecondValidatorAndReturnTrueWhenValid() {
        //given
        when(onlyWhen.isValid(any())).thenReturn(true);
        Cell input = newCell(1, 2, 123);
        when(mainValidator.isValid(eq(input))).thenReturn(true);

        //when then
        assertTrue(underTest.isValid(input));

        //and
        verify(mainValidator).isValid(eq(input));
    }
}