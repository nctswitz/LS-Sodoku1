package pl.lucaluse.sudoku.validator.validators;

import pl.lucaluse.sudoku.validator.model.Cell;

public class CellPositionValidator implements CellValidator {
    private final int xmin;
    private final int xmax;
    private final int ymin;
    private final int ymax;

    public CellPositionValidator(int xmin, int xmax, int ymin, int ymax) {
        if (xmin > xmax) {
            throw new IllegalArgumentException("X max value must be greater then min");
        }
        if (ymin > ymax) {
            throw new IllegalArgumentException("Y max value must be greater then min");
        }
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public static CellValidator newColumnValidator(int columnIndex) {
        return new CellPositionValidator(columnIndex, columnIndex, 0, 8);
    }

    public static CellValidator newRowValidator(int rowIndex) {
        return new CellPositionValidator(0, 8, rowIndex, rowIndex);
    }

    @Override
    public boolean isValid(Cell cell) {
        return isXValid(cell.getX()) && isYValid(cell.getY());
    }

    private boolean isXValid(int x) {
        return xmin <= x && x <= xmax;
    }

    private boolean isYValid(int y) {
        return ymin <= y && y <= ymax;
    }
}
