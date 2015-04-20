package pl.lucaluse.sudoku.validator.validators;

import pl.lucaluse.sudoku.validator.model.Cell;

public class ValueInRangeValidator implements CellValidator {
    private final int minValue;
    private final int maxValue;

    public ValueInRangeValidator(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean isValid(Cell cell) {
        return minValue <= cell.getValue()
                && cell.getValue() <= maxValue;
    }
}
