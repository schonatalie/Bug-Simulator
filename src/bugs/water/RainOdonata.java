package bugs.water;
import bugs.Bug;

public class RainOdonata extends Bug {
    public RainOdonata(String name) {
        super(name, "water", 1);
        imagePath = "images/rainordonata.jpg";
    }

    @Override
    public void attack(Bug target) {
        System.out.println(name + " uses Whirl Pool!");
        int damage = 2;
        target.takeDamage(damage);
        this.levelUp(1); 
    }

    @Override
    public void specialAttack(Bug target) {
        System.out.println(name + " uses Tsunami!");
        int damage = 4;
        target.takeDamage(damage);
        this.levelUp(3);
    }
}