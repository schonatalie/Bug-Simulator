package world;

import bugs.*;
import bugs.air.TailWindWasp;
import bugs.earth.MudMantis;
import bugs.metal.ArmourBeetle;
import bugs.water.RainOdonata;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class BugWorld {
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
        // spawn initial bugs
        bugs.add(new TailWindWasp("Chromium"));
        bugs.add(new MudMantis("Muddy"));
        bugs.add(new ArmourBeetle("Ironclad"));
        bugs.add(new RainOdonata("Rainy"));
        bugs.add(new TailWindWasp("Breezy"));
        bugs.add(new ArmourBeetle("Steely"));

        for (int day = 1; day <= days; day++) {
            System.out.println("\n== Day " + day + " ==");
            spawnBug();
            List<Bug> aliveBugs = getAliveBugs();
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
            }
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