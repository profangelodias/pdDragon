package tech.angelofdiasg.model;

public class Hero {
    private final String name;
    private final String characterClass;
    private int hp;
    private final int maxHp;
    private int mp;
    private int maxMp;
    private final int baseDamage;
    private String weaponName;
    private int weaponDamageBonus;
    private boolean poisoned;
    private boolean stunned;
    private int poisonTurns;

    public Hero(String name, String characterClass, int hp, int mp, int baseDamage,
                String weaponName, int weaponDamageBonus) {
        this.name = name;
        this.characterClass = characterClass;
        this.hp = hp;
        this.maxHp = hp;
        this.mp = mp;
        this.maxMp = mp;
        this.baseDamage = baseDamage;
        this.weaponName = weaponName;
        this.weaponDamageBonus = weaponDamageBonus;
    }

    public String getName() {
        return name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMp() {
        return mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getWeaponDamageBonus() {
        return weaponDamageBonus;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public boolean spendMana(int amount) {
        if (mp < amount) {
            return false;
        }
        mp -= amount;
        return true;
    }

    public void equip(String name, int damageBonus) {
        weaponName = name;
        weaponDamageBonus = damageBonus;
    }

    public void increaseMaxManaAndRestore() {
        maxMp += 20;
        mp = maxMp;
    }

    public void rest() {
        hp = Math.min(maxHp, hp + 30);
        mp = Math.min(maxMp, mp + 10);
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public int getPoisonTurns() {
        return poisonTurns;
    }

    public void applyPoison(int turns) {
        poisoned = true;
        poisonTurns = turns;
    }

    public boolean consumePoisonTurn() {
        if (!poisoned || poisonTurns <= 0) {
            return false;
        }
        poisonTurns--;
        if (poisonTurns == 0) {
            poisoned = false;
            return true;
        }
        return false;
    }

    public boolean isStunned() {
        return stunned;
    }

    public void applyStun() {
        stunned = true;
    }

    public void clearStun() {
        stunned = false;
    }
}
