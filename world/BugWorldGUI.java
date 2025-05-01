package world;

import bugs.Bug;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class BugWorldGUI extends JFrame {
    private static final int GRID_SIZE = 10;
    private static final int CELL_SIZE = 50;
    private JPanel gridPanel;

    public BugWorldGUI() {
        setTitle("Bug World");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gridPanel.add(cell);
           //JLabel test = new JLabel("Hello world");
            //cell.add(test);

            //HERE we could have the bugs in? cell.add()?

            
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    public void updateGrid(List<Bug> bugs) {
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            JPanel cell = (JPanel) gridPanel.getComponent(i);
            cell.setBackground(Color.WHITE);
            cell.removeAll();
        }

        for (Bug bug : bugs) {
            int x = bug.getX();
            int y = bug.getY();
            JPanel cell = (JPanel) gridPanel.getComponent(y * GRID_SIZE + x);
            JLabel label = new JLabel(bug.getName());
            cell.add(label);
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

}

