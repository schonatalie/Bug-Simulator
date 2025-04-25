package bugs.earth;
import bugs.Bug; 

public class MudMantis extends Bug {

    public MudMantis(String name) {
        super(name, "earth", 1); //default level is 1
    }

    @Override
    public void attack(Bug target) {
        System.out.println(name + " uses Rock Slide!");
        int damage = 2;
        target.takeDamage(damage);
        this.levelUp(1); 
    }

    @Override
    public void specialAttack(Bug target) {
        System.out.println(name + " uses Quick Sand!");
        int damage = 3;
        target.takeDamage(damage);
        this.levelUp(3);
    }
}