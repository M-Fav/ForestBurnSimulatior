

import config.ConfigReader;
import config.ConfigData;
import simulation.Simulation;



import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // création d'une instance de ConfigReader pour lire les paramètres depuis le fichier de configuration
        ConfigReader configReader = new ConfigReader();
        ConfigData config = configReader.readConfig("C:\\WORK-SRC\\ForestBurnSimulator\\src\\main\\resources\\config.json");

        // création d'une instance de Simulation en utilisant l'objet ConfigData
        Simulation simulation = new Simulation(config);

        // exécution de la simulation
        simulation.run();

        // affichage des résultats de la simulation
        int burnedCellsCount = simulation.getBurnedCellsCount();
        int stepsCount = simulation.getStepsCount();
        System.out.println("Simulation finished in " + stepsCount + " steps");
        System.out.println("Number of burned cells: " + burnedCellsCount);
    }
}

