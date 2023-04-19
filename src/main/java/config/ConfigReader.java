package config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class ConfigReader {
    public static ConfigData readConfig(String filePath) throws IOException  {
        ObjectMapper objectMapper = new ObjectMapper();
        ConfigData config = new ConfigData();
        try {

            config = objectMapper.readValue(new File(filePath), ConfigData.class);
            validateConfig(config);

        } catch (FileNotFoundException e) {
            System.err.println("Le fichier spécifié est introuvable : " + filePath);
            throw e;
        }

        return config;
    }

    private static void validateConfig(ConfigData config) {
        int height = config.getGridHeight();
        int width = config.getGridWidth();
        double probability = config.getFireSpreadProbability();
        int[][] initialFireCells = config.getInitialFireCells();

        // Vérifier que la hauteur et la largeur de la grille sont positives
        if(height <= 0 || width <=0) {
            throw new IllegalArgumentException("Grid height and width must be positive");
        }

        // Vérifier que la probabilité de propagation du feu est entre 0 et 1
        if(probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        }

        // Vérifier que les cellules de feu initiales sont dans les limites de la grille
        for(int[] cell : initialFireCells) {
            int row = cell[0];
            int col = cell[1];
            if(row < 0 || row > height || col < 0 || col > width) {
                throw new IllegalArgumentException("Initial Fire cell (" + row + "," + col + "is out of bounds");
            }
        }
    }
}

