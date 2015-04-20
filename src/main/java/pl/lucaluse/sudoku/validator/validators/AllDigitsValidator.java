package pl.lucaluse.sudoku.validator.validators;

import pl.lucaluse.sudoku.validator.model.Cell;
import pl.lucaluse.sudoku.validator.validators.observer.ValidationObserver;

import java.util.BitSet;

public class AllDigitsValidator implements CellValidator {
    private final BitSet previouslyValidated = new BitSet(10);
    private final CellValidator isDigit = new ValueInRangeValidator(1,9);
    private final ValidationObserver observer;

    public AllDigitsValidator() {
        this(new ValidationObserver() {
            @Override
            public void validationError(Cell cell, String errorMessage) {
                //ignore
            }
        });
    }

    public AllDigitsValidator(ValidationObserver observer) {
        this.observer = observer;
    }

    @Override
    public boolean isValid(Cell cell) {
        if (isDigit.isValid(cell)) {
            final int value = cell.getValue();
            boolean seen = previouslyValidated.get(value);
            previouslyValidated.set(value, true);
            if (!seen) {
                return true;
            }
            observer.validationError(cell, String.format("Value %d already seen [%d, %d]", cell.getValue(), cell.getX(), cell.getY()));
            return false;
        } else {
            observer.validationError(cell, "Invalid input value: " + cell.getValue());
            return false;
        }
    }
}
