package pl.lucaluse.sudoku.validator.validators;

import pl.lucaluse.sudoku.validator.model.Cell;

public class OptionalValidator implements CellValidator {
    private final CellValidator onlyWhen;
    private final CellValidator validator;

    public OptionalValidator(CellValidator onlyWhen, CellValidator validator) {
        this.onlyWhen = onlyWhen;
        this.validator = validator;
    }

    @Override
    public boolean isValid(Cell cell) {
        if (onlyWhen.isValid(cell)) {
            return validator.isValid(cell);
        }
        return true;
    }
}
