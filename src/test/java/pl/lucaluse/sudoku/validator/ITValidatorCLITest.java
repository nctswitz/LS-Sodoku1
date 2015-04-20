package pl.lucaluse.sudoku.validator;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static org.junit.Assert.*;

public class ITValidatorCLITest {
    public static String getBatchScriptName() {
        return SystemUtils.IS_OS_WINDOWS ? "validate.bat" : "./validate.sh";
    }

    @Test(timeout = 1000)
    public void testValidInput() throws IOException {
        URL validResource = this.getClass().getResource("/valid.csv");
        assertNotNull(validResource);

        Process runtime = Runtime.getRuntime().exec(new String[]{getBatchScriptName(), validResource.getPath()});
        try (BufferedReader br = new BufferedReader(new InputStreamReader(runtime.getInputStream()))) {
            assertEquals("VALID", br.readLine());
        }
    }

    @Test(timeout = 1000)
    public void testInvalidInput() throws IOException {
        URL validResource = this.getClass().getResource("/invalid.csv");
        assertNotNull(validResource);

        Process runtime = Runtime.getRuntime().exec(new String[]{getBatchScriptName(), validResource.getPath()});
        try (BufferedReader br = new BufferedReader(new InputStreamReader(runtime.getInputStream()))) {
            assertEquals("INVALID", br.readLine());
        }
    }

}