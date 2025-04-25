package bugs.metal;
import bugs.Bug; 

public class ArmourBeetle extends Bug {

    public ArmourBeetle(String name) {
        super(name, "metal", 1); //default level is 1
    }

    @Override
    public void attack(Bug target) {
        System.out.println(name + " uses Metal Shield!");
        int damage = 2;
        target.takeDamage(damage);
        this.levelUp(1); 
    }

    @Override
    public void specialAttack(Bug target) {
        System.out.println(name + " uses Metal Claw!");
        int damage = 4;
        target.takeDamage(damage);
        this.levelUp(3);
    }
}