package simulation;

import config.ConfigData;
import lombok.Data;

@Data
public class Simulation {
    //attributs de la simulation
    private int[][] grid;
    private int height;
    private int width;
    private int steps;
    private double propagationProbability;

    public Simulation(ConfigData config) {
        // récupération des paramètres depuis l'objet ConfigData
        this.height = config.getGridHeight();
        this.width = config.getGridWidth();
        this.steps = 0;
        this.propagationProbability = config.getFireSpreadProbability();

        // initialisation de la grille
        this.grid = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.grid[i][j] = 0; // initialisation à 0 (cellule non touchée)
            }
        }

        // mise en feu des cases initiales
        for (int[] pos : config.getInitialFireCells()) {
            this.grid[pos[0]][pos[1]] = 1; // initialisation à 1 (cellule en feu)
        }
    }

    public void run() {
        boolean fireSpread = true;
        while (fireSpread) {
            fireSpread = false;
            int[][] newGrid = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j] == 1) {
                        newGrid[i][j] = 2; // la cellule en feu devient une cellule brûlée
                        if (i > 0 && grid[i - 1][j] == 0 && Math.random() < propagationProbability) {
                            newGrid[i - 1][j] = 1; // la cellule au nord prend feu
                            fireSpread = true;
                        }
                        if (j > 0 && grid[i][j - 1] == 0 && Math.random() < propagationProbability) {
                            newGrid[i][j - 1] = 1; // la cellule à l'ouest prend feu
                            fireSpread = true;
                        }
                        if (i < height - 1 && grid[i + 1][j] == 0 && Math.random() < propagationProbability) {
                            newGrid[i + 1][j] = 1; // la cellule au sud prend feu
                            fireSpread = true;
                        }
                        if (j < width - 1 && grid[i][j + 1] == 0 && Math.random() < propagationProbability) {
                            newGrid[i][j + 1] = 1; // la cellule à l'est prend feu
                            fireSpread = true;
                        }
                    } else {
                        newGrid[i][j] = grid[i][j]; // la cellule reste dans son état précédent
                    }
                }
            }
            grid = newGrid; // mise à jour de la grille
            steps++; // incrémentation du nombre d'étapes

        }
    }

    public int getBurnedCellsCount() {
        int count = 0;
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 2) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getStepsCount() {
        return steps;
    }
}

