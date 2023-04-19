

import config.ConfigReader;
import config.ConfigData;




import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // création d'une instance de ConfigReader pour lire les paramètres depuis le fichier de configuration
        ConfigReader configReader = new ConfigReader();
        ConfigData config = configReader.readConfig("C:\\WORK-SRC\\ForestBurnSimulator\\src\\main\\resources\\config.json");


    }
}

