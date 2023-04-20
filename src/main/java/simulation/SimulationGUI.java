package simulation;

import javax.swing.*;
import java.awt.*;

public class SimulationGUI extends JPanel {

    private final Simulation simulation;
    private final int cellSize;
    private final Color burnedCellColor;
    private final Color emptyCellColor;


    public SimulationGUI(Simulation simulation, int cellSize) {
        this.simulation = simulation;
        this.cellSize = cellSize;
        this.burnedCellColor = new Color(153, 76, 0); // couleur des cellules brûlées
        this.emptyCellColor = new Color(34, 139, 34); // couleur des cellules non touchées
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < simulation.getHeight(); i++) {
            for (int j = 0; j < simulation.getWidth(); j++) {
                if (simulation.getGrid()[i][j] == 2) {
                    g.setColor(burnedCellColor);
                } else {
                    g.setColor(emptyCellColor);
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    public void start() {
        JFrame frame = new JFrame("Simulation de feu de foret");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(simulation.getWidth() * cellSize, simulation.getHeight() * cellSize);
        frame.add(this);
        frame.setVisible(true);

        Thread thread = new Thread(() -> {
            simulation.run();
            repaint();

            System.out.println("La Simulation s'est terminee en " + simulation.getStepsCount() + " etapes");
            System.out.println("Nombre de cellules brulees: " + simulation.getBurnedCellsCount());

            JOptionPane.showMessageDialog(frame, "La simulation est terminee.\n\n" +
                    "Nombre d'etapes : " + simulation.getStepsCount() + "\n" +
                    "Nombre de cellules brulees : " + simulation.getBurnedCellsCount());
        });
        thread.start();
    }

}