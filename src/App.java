import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import bugs.Bug;
import bugs.air.TailWindWasp;
public class App {
    public static void main(String[] args) {
        world.BugWorld world = new world.BugWorld();
        world.runSimulation(30); // run for # of days
    }
}