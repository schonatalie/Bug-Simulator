package bugs.air;
import bugs.Bug;    

public class TailWindWasp extends Bug {

    public TailWindWasp(String name) {
        super(name, "air", 1); //default level is 1
        imagePath = "images/tailwindwasp.jpg";
    }

    @Override
    public void attack(Bug target) {
        System.out.println(name + " uses Whirl Wind!");
        int damage = 2;
        target.takeDamage(damage);
        this.levelUp(1); 
    }

    @Override
    public void specialAttack(Bug target) {
        System.out.println(name + " uses Tornado!");
        int damage = 4;
        target.takeDamage(damage);
        this.levelUp(2);
    }
}