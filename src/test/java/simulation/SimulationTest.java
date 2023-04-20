package simulation;

import config.ConfigData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {

    @Test
    void simulationProbabilityZero() {
        ConfigData config = new ConfigData();
        config.setGridWidth(10);
        config.setGridHeight(10);
        config.setFireSpreadProbability(0);
        int[][] expected = {{0, 0}, {1, 1}, {2, 2}};
        config.setInitialFireCells(expected);
        Simulation simulation = new Simulation(config);
        simulation.run();

        assertEquals(3, simulation.getBurnedCellsCount());
    }

    @Test
    void simulationProbabilityOne() {
        ConfigData config = new ConfigData();
        config.setGridWidth(10);
        config.setGridHeight(10);
        config.setFireSpreadProbability(1);
        int[][] expected = {{0, 0}, {1, 1}, {2, 2}};
        config.setInitialFireCells(expected);
        Simulation simulation = new Simulation(config);
        simulation.run();

        assertEquals(100, simulation.getBurnedCellsCount());
    }
}
