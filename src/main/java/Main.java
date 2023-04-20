

import config.ConfigReader;
import config.ConfigData;
import simulation.Simulation;
import simulation.SimulationGUI;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // création d'une instance de ConfigReader pour lire les paramètres depuis le fichier de configuration
        ConfigReader configReader = new ConfigReader();
        ConfigData config = configReader.readConfig("C:\\WORK-SRC\\ForestBurnSimulator\\src\\main\\resources\\config.json");

        // création d'une instance de Simulation en utilisant l'objet ConfigData
        Simulation simulation = new Simulation(config);

        // création d'une instance de SimulationGUI en utilisant l'instance de Simulation et une taille de cellule de 10 pixels
        SimulationGUI simulationGUI = new SimulationGUI(simulation, 10);

        // exécution de la simulation dans un thread séparé et affichage des résultats dans une boîte de dialogue
        simulationGUI.start();

    }
}

