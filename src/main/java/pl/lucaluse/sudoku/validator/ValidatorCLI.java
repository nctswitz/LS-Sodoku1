package pl.lucaluse.sudoku.validator;

import pl.lucaluse.sudoku.validator.model.Cell;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class ValidatorCLI {
    public static void main(String... args) {
        if (args.length < 1) {
            exitWithMessage(1, "Please specify file to validate");
        }
        SudokuValidator validator = new SudokuValidator();
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(args[0]))) {
            String line = null;
            while ((line = lnr.readLine()) != null) {
                String[] entries = line.split(",");
                for (int y = 0; y < entries.length; y++) {
                    if (!validator.isValid(new Cell(lnr.getLineNumber() - 1, y, Integer.parseInt(entries[y].replaceAll("\\s", ""))))) {
                        exitWithError(4, null);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            exitWithError(2, e);
        } catch (IOException e) {
            exitWithError(3, e);
        }
        if (!validator.isValid()) {
            exitWithMessage(5, "Solution incomplete");
        }
        System.out.println("VALID");
    }

    public static void exitWithMessage(int code, String message) {
        System.err.println(message);
        exitWithError(code, null);

    }
    public static void exitWithError(int code, Throwable t) {
        if (t != null) {
            t.printStackTrace(System.err);
        }
        System.out.println("INVALID");
        System.exit(code);
    }
}
