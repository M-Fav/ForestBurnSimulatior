package config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class ConfigReaderTest {

    @Test
    void testLoadValidConfig() throws IOException {
        ConfigData config = ConfigReader.readConfig("C:\\WORK-SRC\\ForestBurnSimulator\\src\\test\\ressources\\configTest.json");
        assertEquals(100, config.getGridHeight());
        assertEquals(20, config.getGridWidth());
        assertEquals(0, config.getFireSpreadProbability());
        int[][] expected = {{0, 0}, {1, 1}, {2, 2}};
        assertArrayEquals(expected, config.getInitialFireCells());
    }

    @Test
    void testLoadMissingFile() {
        assertThrows(IOException.class, () -> ConfigReader.readConfig("nonexistent-file.json"));
    }

    // Méthode pour comparer deux tableaux d'entiers à deux dimensions
    public static void assertArrayEquals(int[][] expected, int[][] actual) {
        if (expected.length != actual.length) {
            throw new IllegalArgumentException("Arrays have different lengths");
        }
        for (int i = 0; i < expected.length; i++) {
            if (!Arrays.equals(expected[i], actual[i])) {
                throw new IllegalArgumentException("Arrays differ at index " + i);
            }
        }
    }
}

