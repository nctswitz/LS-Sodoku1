package pl.lucaluse.sudoku.validator.validators;

import pl.lucaluse.sudoku.validator.model.Cell;
import pl.lucaluse.sudoku.validator.validators.observer.ValidationObserver;

public class SubBoardValidator implements CellValidator, Validator {
    private final CellValidator validator;
    private final CellCompletenessValidator completeness;
    private boolean valid = true;

    public SubBoardValidator(int x, int y, ValidationObserver observer) {
        this.completeness = new CellCompletenessValidator(x, x+2, y, y+2);
        this.validator = new OptionalValidator(new CellPositionValidator(x, x + 2, y, y + 2),
                new ComposingValidator(completeness,
                new AllDigitsValidator(new ValidationObserver() {
                    @Override
                    public void validationError(Cell cell, String errorMessage) {
                        observer.validationError(cell, String.format("SubBoard [%d, %d]: %s", x, y, errorMessage));
                    }
                })));
    }

    @Override
    public boolean isValid(Cell cell) {
        valid &= validator.isValid(cell);
        return valid;
    }

    @Override
    public boolean isValid() {
        return completeness.isValid();
    }
}
