package bugs;

public abstract class Bug {
    protected String name;
    protected String type;
    protected String imagePath; //path to image of said bug
    protected int level;
    protected int health;
    protected int maxHealth;
    protected Bug enemy; // current target
    protected int x; // x coordinate on the grid
    protected int y; // y coordinate on the grid
    protected boolean evolved = false;

    public Bug(String name, String type, int level) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.maxHealth = 10 + (level * 2);
        this.health = this.maxHealth;
        this.x = (int) (Math.random() * 9);
        this.y = (int) (Math.random() * 9);
    }

    public abstract void attack(Bug target);
    public abstract void specialAttack(Bug target);

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.println(name + " takes " + damage + " damage!");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void levelUp(int levels) {
        this.level += levels;
        int oldMaxHealth = maxHealth;
        this.maxHealth = 10 + (level * 2);
        this.health += (maxHealth - oldMaxHealth); // heal when leveling up
        System.out.println(name + " leveled up to level " + level + "!");
        evolved = false;
    }

    // getters/setters
    public String getName() {
        return this.name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean hasEvolved() {
        return evolved;
    }
    public void setEvolved(boolean evolved) {
        this.evolved = evolved;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public String getImagepath(){
        return this.imagePath;
    }
}
