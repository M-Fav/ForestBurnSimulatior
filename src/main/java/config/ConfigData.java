package config;


import lombok.Data;

@Data
public class ConfigData {
    private int gridHeight;     //hauteur de la grille
    private int gridWidth;      //largeur de la grille
    private int[][] initialFireCells;   //
    private double fireSpreadProbability;

}
