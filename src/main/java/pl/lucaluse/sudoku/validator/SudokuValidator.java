package pl.lucaluse.sudoku.validator;

import pl.lucaluse.sudoku.validator.model.Cell;
import pl.lucaluse.sudoku.validator.validators.*;
import pl.lucaluse.sudoku.validator.validators.observer.ChainValidationObserver;
import pl.lucaluse.sudoku.validator.validators.observer.ValidationObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SudokuValidator implements CellValidator, Validator, ValidationObserver {
    private final ComposingValidator validator;
    private final CellCompletenessValidator complete;
    private boolean valid = true;


    public SudokuValidator() {
        complete = new CellCompletenessValidator(0, 8, 0, 8);
        ComposingValidator inputValidator = new ComposingValidator(
                new CellPositionValidator(0, 8, 0, 8),
                new ValueInRangeValidator(1, 9),
                complete
        );
        List<OptionalValidator> columnValidators = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8).map(x ->
                new OptionalValidator(
                        CellPositionValidator.newColumnValidator(x),
                        new AllDigitsValidator(new ChainValidationObserver("Column " + x + ": %s", this)))).collect(toList());
        List<OptionalValidator> rowValidators = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8).map(x ->
                new OptionalValidator(
                        CellPositionValidator.newRowValidator(x),
                        new AllDigitsValidator(new ChainValidationObserver("Row " + x + ": %s", this)))).collect(toList());
        List<CellValidator> subBoardValidators = new ArrayList();
        Stream.of(0, 1, 2).map(x ->
                        Stream.of(0, 1, 2).map(y ->
                                        new SubBoardValidator(x * 3, y * 3, this)
                        ).collect(toList())
        ).forEach(x -> subBoardValidators.addAll(x));

        ComposingValidator rowConstraint = new ComposingValidator(rowValidators.toArray(new CellValidator[rowValidators.size()]));
        ComposingValidator colConstraint = new ComposingValidator(columnValidators.toArray(new CellValidator[columnValidators.size()]));
        ComposingValidator subBoardConstraint = new ComposingValidator(subBoardValidators.toArray(new CellValidator[subBoardValidators.size()]));

        validator = new ComposingValidator(inputValidator, rowConstraint, colConstraint, subBoardConstraint);
    }

    @Override
    public boolean isValid() {
        return valid && complete.isValid();
    }

    @Override
    public boolean isValid(Cell cell) {
        this.valid &= validator.isValid(cell);
        return valid;
    }

    @Override
    public void validationError(Cell cell, String errorMessage) {
        System.err.println(errorMessage);
    }

}
