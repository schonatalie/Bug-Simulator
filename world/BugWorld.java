package world;

import bugs.*;
import bugs.air.TailWindWasp;
import bugs.earth.MudMantis;
import bugs.metal.ArmourBeetle;
import bugs.water.RainOdonata;
import java.util.*;
import java.util.stream.Collectors;

public class BugWorld {
    private List<Bug> bugs = new ArrayList<>();

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