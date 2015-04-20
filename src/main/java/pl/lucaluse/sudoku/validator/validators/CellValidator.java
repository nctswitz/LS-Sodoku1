package pl.lucaluse.sudoku.validator.validators;

import pl.lucaluse.sudoku.validator.model.Cell;

public interface CellValidator {
    public boolean isValid(Cell cell);
}
