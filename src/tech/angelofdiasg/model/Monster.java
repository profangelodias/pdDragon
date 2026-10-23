package tech.angelofdiasg.model;

public class Monster {
    private final String name;
    private int hp;
    private final int baseDamage;
    private final String ability;

    public Monster(String name, int hp, int baseDamage, String ability) {
        this.name = name;
        this.hp = hp;
        this.baseDamage = baseDamage;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public String getAbility() {
        return ability;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }
}
