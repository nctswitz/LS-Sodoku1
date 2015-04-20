package pl.lucaluse.sudoku.validator.validators;

import pl.lucaluse.sudoku.validator.model.Cell;

import java.util.BitSet;

public class CellCompletenessValidator implements CellValidator, Validator {
    private final CellPositionValidator positionValidator;
    private final int xmin;
    private final int xrange;
    private final int ymin;
    private final int yrange;
    private final BitSet[] completenessSets;
    private boolean valid = true;

    public CellCompletenessValidator(int xmin, int xmax, int ymin, int ymax) {
        if (xmin > xmax) {
            throw new IllegalArgumentException("X max value must be greater then min");
        }
        if (ymin > ymax) {
            throw new IllegalArgumentException("Y max value must be greater then min");
        }
        this.positionValidator = new CellPositionValidator(xmin, xmax, ymin, ymax);
        this.xmin = xmin;
        this.ymin = ymin;
        this.xrange = (xmax - xmin)+1;
        this.yrange = (ymax - ymin)+1;
        this.completenessSets = new BitSet[xrange];
        for (int x = 0; x < xrange; x++) {
            for (int y = 0; y < yrange; y++) {
                completenessSets[x] = new BitSet(yrange);
            }
        }
    }

    @Override
    public boolean isValid(Cell cell) {
        boolean positionValid = positionValidator.isValid(cell);
        valid &= positionValidator.isValid(cell);
        if (valid) {
            valid &= !completenessSets[cell.getX() - xmin].get(cell.getY()-ymin);
            completenessSets[cell.getX() - xmin].set(cell.getY()-ymin, true);
        }
        return valid;
    }

    @Override
    public boolean isValid() {
        boolean complete = true;
        for (BitSet set: completenessSets) {
            complete &= (set.cardinality() == yrange);
            if (!complete) return complete;
        }
        return valid && complete;
    }
}
