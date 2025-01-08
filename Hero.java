public class Hero {
    private String heroName;
    private String element;
    private double hp;
    private double attack;

    public Hero(String name, String element, double hp, double attack) {
        this.heroName = name;
        this.element = element;
        this.hp = hp;
        this.attack = attack;
    }

    public double calculateDamage(Villain enemy, int rsMultiplier) {
        double dominanceMultiplier = getDominanceMultiplier(this.element, enemy.getElement());
        double damage = this.attack * dominanceMultiplier * rsMultiplier - enemy.getDefense();
        return Math.max(damage, 1); // Ensure at least 1 damage is dealt
    }

    private double getDominanceMultiplier(String heroElement, String enemyElement) {
        if (heroElement.equals("Water") && enemyElement.equals("Fire") ||
            heroElement.equals("Fire") && enemyElement.equals("Earth") ||
            heroElement.equals("Earth") && enemyElement.equals("Water") ||
            heroElement.equals("Light") && enemyElement.equals("Dark") ||
            heroElement.equals("Dark") && enemyElement.equals("Light")) {
            return 1.5; // dominant element
        } else if (heroElement.equals("Fire") && enemyElement.equals("Water") ||
                   heroElement.equals("Earth") && enemyElement.equals("Fire") ||
                   heroElement.equals("Water") && enemyElement.equals("Earth")) {
            return 0.5; // weak element
        } else {
            return 1.0; // neutral element
        }
    }

    public double getHp() {
        return hp;
    }

    public String getElement() {
        return element;
    }
    
    public String getName() {
        return heroName;
    }

    @Override
    public String toString() {
        return "Name: " + heroName + "\nElement: " + element + "\nHP: " + hp + "\nAttack: " + attack;
    }
}