import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        world.BugWorld world = new world.BugWorld();
        world.BugWorldGUI gui = new world.BugWorldGUI();
        gui.pack();
        gui.setVisible(true);
        gui.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));

        
        world.runSimulation(30); // run for # of days
    }
}