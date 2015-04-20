package pl.lucaluse.sudoku.validator.validators.observer;

import pl.lucaluse.sudoku.validator.SudokuValidator;
import pl.lucaluse.sudoku.validator.model.Cell;

public class ChainValidationObserver implements ValidationObserver {
    private final String message;
    private final ValidationObserver observer;

    public ChainValidationObserver(String message, ValidationObserver observer) {
        this.message = message;
        this.observer = observer;
    }

    @Override
    public void validationError(Cell cell, String errorMessage) {
        observer.validationError(cell, String.format(message, errorMessage));
    }
}
