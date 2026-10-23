package tech.angelofdiasg;

import tech.angelofdiasg.model.Hero;
import tech.angelofdiasg.model.Monster;
import tech.angelofdiasg.ui.GameUI;

import java.util.Random;

public class Main {
    private final GameUI ui;
    private final Random random = new Random();

    private Main(GameUI ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {
        GameUI ui = new GameUI();
        new Main(ui).run();
        ui.close();
    }

    private void run() {
        ui.showWelcome();
        Hero hero = createHero();
        ui.showHero(hero);

        int currentFloor = 1;
        boolean heroAlive = true;

        while (currentFloor <= 3 && heroAlive) {
            ui.showFloor(currentFloor);
            Monster monster = createMonster(currentFloor);
            ui.showMonster(monster);

            while (hero.isAlive() && monster.isAlive()) {
                ui.showCombatStatus(hero, monster);

                if (hero.isPoisoned() && hero.getPoisonTurns() > 0) {
                    hero.takeDamage(5);
                    boolean poisonEnded = hero.consumePoisonTurn();
                    ui.showPoisonDamage(hero);
                    if (!hero.isAlive()) {
                        break;
                    }
                    if (poisonEnded) {
                        ui.showPoisonEnded();
                    }
                }

                if (hero.isStunned()) {
                    ui.showStunnedTurn();
                    hero.clearStun();
                } else {
                    takeHeroTurn(hero, monster);
                }

                if (monster.isAlive()) {
                    takeMonsterTurn(hero, currentFloor, monster);
                }
            }

            if (!hero.isAlive()) {
                ui.showGameOver(hero, currentFloor);
                heroAlive = false;
            } else {
                ui.showMonsterDefeated(monster, currentFloor);
                if (currentFloor < 3) {
                    rewardHero(hero, currentFloor);
                }
                hero.rest();
                ui.showRest();
                currentFloor++;
            }
        }

        if (heroAlive) {
            ui.showVictory(hero);
        }
    }

    private Hero createHero() {
        String name = ui.askHeroName();
        int classOption = ui.askHeroClass();

        if (classOption == 2) {
            return new Hero(name, "Mago", 60, 80, 5, "Cajado de Aprendiz", 3);
        }
        if (classOption == 3) {
            return new Hero(name, "Arqueiro", 80, 40, 8, "Arco Curto", 4);
        }
        return new Hero(name, "Guerreiro", 100, 20, 10, "Espada Enferrujada", 5);
    }

    private Monster createMonster(int floor) {
        if (floor == 1) {
            return new Monster("Goblin Traquinas", 35, 8, "Mordida Rápida");
        }
        if (floor == 2) {
            return new Monster("Orc Saqueador", 65, 12, "Clava de Ferro");
        }
        return new Monster("Architectural Dragon (BOSS)", 150, 18, "Sopro de Código Legado (Fogo)");
    }

    private void takeHeroTurn(Hero hero, Monster monster) {
        int action = ui.askHeroAction();
        switch (action) {
            case 1:
                int physicalDamage = hero.getBaseDamage() + hero.getWeaponDamageBonus() + random.nextInt(6);
                monster.takeDamage(physicalDamage);
                ui.showPhysicalAttack(hero, monster, physicalDamage);
                break;
            case 2:
                if (hero.spendMana(10)) {
                    int magicDamage = hero.getBaseDamage() * 2 + random.nextInt(12);
                    monster.takeDamage(magicDamage);
                    ui.showMagicAttack(monster, magicDamage);
                } else {
                    ui.showInsufficientMana();
                }
                break;
            case 3:
                if (hero.spendMana(5)) {
                    int healing = 20 + random.nextInt(11);
                    hero.heal(healing);
                    ui.showHealing(healing);
                } else {
                    ui.showInsufficientMana();
                }
                break;
            default:
                ui.showInvalidAction();
                break;
        }
    }

    private void takeMonsterTurn(Hero hero, int floor, Monster monster) {
        int monsterAction = random.nextInt(10);
        if (monsterAction < 6) {
            int damage = monster.getBaseDamage() + random.nextInt(5);
            hero.takeDamage(damage);
            ui.showMonsterAttack(monster, damage);
        } else if (monsterAction < 8) {
            int damage = monster.getBaseDamage() + 8;
            hero.takeDamage(damage);
            ui.showMonsterSpecialAttack(monster, damage);
        } else if (floor == 1) {
            hero.applyPoison(3);
            ui.showPoisonApplied(monster);
        } else if (floor == 2) {
            hero.applyStun();
            ui.showStunApplied(monster);
        } else {
            hero.applyPoison(2);
            hero.applyStun();
            ui.showPoisonAndStunApplied(monster);
        }
    }

    private void rewardHero(Hero hero, int floor) {
        ui.showTreasure();
        if (floor == 1) {
            ui.showFirstReward();
            if (ui.askToEquip("Deseja equipá-la? (1-Sim, 2-Não)")) {
                hero.equip("Espada de Aço de Fogo", 12);
                ui.showEquippedWeapon(hero);
            }
        } else if (floor == 2) {
            ui.showSecondReward();
            if (ui.askToEquip("Deseja equipá-lo? (1-Sim, 2-Não)")) {
                hero.equip("Cajado do Arquimago Sagrado", 18);
                hero.increaseMaxManaAndRestore();
                ui.showEquippedWeaponAndMana(hero);
            }
        }
    }
}