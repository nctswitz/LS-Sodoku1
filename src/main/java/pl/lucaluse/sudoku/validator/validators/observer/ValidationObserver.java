package pl.lucaluse.sudoku.validator.validators.observer;

import pl.lucaluse.sudoku.validator.model.Cell;

public interface ValidationObserver {
    public void validationError(Cell cell, String errorMessage);
}
