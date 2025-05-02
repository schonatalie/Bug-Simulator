import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import bugs.Bug;
import bugs.air.TailWindWasp;
public class App {
    public static void main(String[] args) {
        world.BugWorld world = new world.BugWorld();
        //world.BugWorldGUI gui = new world.BugWorldGUI();
        //List<Bug> temp = new ArrayList<>();
        //TailWindWasp test = new TailWindWasp("Test");
        //temp.add(test);
        //gui.pack();
        //gui.setVisible(true);
        //gui.updateGrid(temp);
        //gui.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        
        

        world.runSimulation(30); // run for # of days
    }
}