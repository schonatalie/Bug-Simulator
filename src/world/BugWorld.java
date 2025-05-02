package world;
import java.util.Random;
import bugs.*;
import bugs.air.TailWindWasp;
import bugs.earth.MudMantis;
import bugs.metal.ArmourBeetle;
import bugs.water.RainOdonata;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import java.util.concurrent.TimeUnit;

public class BugWorld {
    world.BugWorldGUI gui = new world.BugWorldGUI();

    private List<Bug> bugs = new ArrayList<>();
    private static final int MAX_BUGS = 15;
    private int genisis = 20;
    private List<String> names = new ArrayList<>();
    private boolean readNames = false;

    public void spawnBug() {
        if (bugs.size() >= MAX_BUGS) { // if the max number of bugs is reached, do not spawn a new bug
            return;
        }

        int randomNum = new Random().nextInt(100) + 1;
        if (randomNum > genisis) { // if the random number is greater than the genisis rate, do not spawn a new bug
            return;
        }

        int randomType = new Random().nextInt(4);
        String name = getRandomName();

        switch (randomType) { // 0 = MudMantis, 1 = ArmourBeetle, 2 = RainOdonata, 3 = TailWindWasp
            case 0 -> bugs.add(new MudMantis(name));
            case 1 -> bugs.add(new ArmourBeetle(name));
            case 2 -> bugs.add(new RainOdonata(name));
            case 3 -> bugs.add(new TailWindWasp(name));
        }
    }

     private void readNamesFile() {
        try {
            names = Files.readAllLines((Paths.get("names.txt")));
        } catch (Exception e) {
            System.out.println("Error reading creature types: " + e.getMessage());
        }
        readNames = true;
    }

    private String getRandomName() {
        if (!readNames) {
            readNamesFile();
        }

        if (!names.isEmpty()) {
            return names.get(new Random().nextInt(names.size()));
        } else {
            return "Bug-" + (int) (Math.random() * 50);
        }
    }
    
    public void runSimulation(int days) {
        gui.pack();
        gui.setVisible(true);
        //gui.updateGrid(temp);
        gui.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        // spawn initial bugs
        bugs.add(new TailWindWasp("Chromium"));
        bugs.add(new MudMantis("Muddy"));
        bugs.add(new ArmourBeetle("Ironclad"));
        bugs.add(new RainOdonata("Rainy"));
        bugs.add(new TailWindWasp("Breezy"));
        bugs.add(new ArmourBeetle("Steely"));
        Random rand = new Random();
        for (int day = 1; day <= days; day++) {
            System.out.println("\n== Day " + day + " ==");
            spawnBug();
            List<Bug> aliveBugs = getAliveBugs();
 
            for (Bug bug : aliveBugs) {
                if (bug.getLevel() > 0 && bug.getLevel() % 15 == 0 && !bug.hasEvolved()) {
                    System.out.println(bug.getName() + " is evolving at level " + bug.getLevel() + "!");
                    bug.setMaxHealth((int) (bug.getMaxHealth() * 1.2));
                    bug.setHealth(bug.getMaxHealth());
                    bug.setEvolved(true);
                }
                if (bug.getLevel() % 15 !=0) {
                    bug.setEvolved(false);
                }
            }
            gui.updateGrid(aliveBugs);
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch(Exception e){
                System.out.println("Cant wait for some reason");
            }
            
            if (aliveBugs.size() >= 2) {
                Collections.shuffle(aliveBugs);
                Bug attacker = aliveBugs.get(0);
                Bug defender = aliveBugs.get(1);
                
                if (Math.random() > 0.5) {
                    attacker.attack(defender);
                } else {
                    attacker.specialAttack(defender);
                }
                
                bugs.removeIf(bug -> !bug.isAlive());
                for(int i = 0; i < aliveBugs.size(); i++){
                    if((aliveBugs.get(i).getX()+1 < 9) && (aliveBugs.get(i).getX()-1 > 0)){
                        aliveBugs.get(i).setX(aliveBugs.get(i).getX()+rand.nextInt(-1,2));
                    }
                    if((aliveBugs.get(i).getY()+1 < 9) && (aliveBugs.get(i).getY()-1 > 0)){
                        aliveBugs.get(i).setY(aliveBugs.get(i).getY()+rand.nextInt(-1,2));
                    }
                    //aliveBugs.get(i).setY(aliveBugs.get(i).getY()+Math.round(.5f)); 
                    //aliveBugs.get(i).setX(aliveBugs.get(i).getX()+rand.nextInt(-3,3));
                    //aliveBugs.get(i).setY(aliveBugs.get(i).getY()+Math.round(.5f));
                }
            }
            //Update bug postions
            
            printStatus();
        }
    }

    private List<Bug> getAliveBugs() {
        return bugs.stream().filter(Bug::isAlive).collect(Collectors.toList());
    }

    private void printStatus() {
        System.out.println("Alive bugs: " + getAliveBugs().size());
        getAliveBugs().forEach(bug -> 
            System.out.println("- " + bug.getName() + " (" + bug.getType() + ") Lv." + bug.getLevel() + 
                             " HP: " + bug.getHealth() + "/" + bug.getMaxHealth())
        );
    }
}