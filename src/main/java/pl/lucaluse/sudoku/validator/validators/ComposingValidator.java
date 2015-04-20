package pl.lucaluse.sudoku.validator.validators;

import pl.lucaluse.sudoku.validator.model.Cell;

public class ComposingValidator implements CellValidator {
    private final CellValidator[] validators;

    public ComposingValidator(CellValidator... validators) {
        this.validators = validators;
    }


    @Override
    public boolean isValid(Cell cell) {
        boolean retValue = true;
        for (CellValidator validator : validators) {
            retValue &= validator.isValid(cell);
        }
        return retValue;
    }
}
