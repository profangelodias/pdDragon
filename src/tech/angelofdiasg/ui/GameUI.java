package tech.angelofdiasg.ui;

import tech.angelofdiasg.model.Hero;
import tech.angelofdiasg.model.Monster;

import java.util.Scanner;

public class GameUI {
    private final Scanner scanner = new Scanner(System.in);

    public void close() {
        scanner.close();
    }

    public void showWelcome() {
        System.out.println("=========================================");
        System.out.println("    BEM-VINDO A DESIGN PARTNER & DRAGONS!    ");
        System.out.println("=========================================");
    }

    public String askHeroName() {
        System.out.print("Digite o nome do seu Herói: ");
        return scanner.nextLine();
    }

    public int askHeroClass() {
        System.out.println("Escolha sua classe: 1-Guerreiro, 2-Mago, 3-Arqueiro");
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public void showHero(Hero hero) {
        System.out.println("\nHerói " + hero.getName() + " o " + hero.getCharacterClass() + " criado com sucesso!");
        System.out.println("Armado com " + hero.getWeaponName() + " (+ " + hero.getWeaponDamageBonus() + " Dano)");
        System.out.println("Atributos: HP=" + hero.getHp() + "/" + hero.getMaxHp() + " | MP=" + hero.getMp() + "/" + hero.getMaxMp() + "\n");
    }

    public void showFloor(int floor) {
        System.out.println("-----------------------------------------");
        System.out.println("👉 ENTRANDO NO ANDAR " + floor + " DA MASMORRA!");
        System.out.println("-----------------------------------------");
    }

    public void showMonster(Monster monster) {
        System.out.println("Um terrível " + monster.getName() + " bloqueia seu caminho!");
    }

    public void showCombatStatus(Hero hero, Monster monster) {
        System.out.println("\n--- STATUS COMBATE ---");
        System.out.println(hero.getName() + " [HP: " + hero.getHp() + "/" + hero.getMaxHp()
                + " | MP: " + hero.getMp() + "/" + hero.getMaxMp() + "]");
        System.out.println(monster.getName() + " [HP: " + monster.getHp() + "]");
        if (hero.isPoisoned()) {
            System.out.println("⚠️ ATENÇÃO: Você está envenenado! (" + hero.getPoisonTurns() + " turnos restantes)");
        }
        if (hero.isStunned()) {
            System.out.println("🌀 ATENÇÃO: Você está atordoado!");
        }
        System.out.println("----------------------");
    }

    public void showPoisonDamage(Hero hero) {
        System.out.println("💀 O veneno queima nas suas veias! Causou 5 de dano. Seu HP atual: " + hero.getHp());
    }

    public void showPoisonEnded() {
        System.out.println("✨ O veneno se dissipou do seu corpo.");
    }

    public void showStunnedTurn() {
        System.out.println("🌀 Você está atordoado e não consegue se mover neste turno!");
    }

    public int askHeroAction() {
        System.out.println("Sua vez! Escolha uma ação:");
        System.out.println("1 - Ataque Físico");
        System.out.println("2 - Ataque Mágico (Custo: 10 MP)");
        System.out.println("3 - Curar-se (Custo: 5 MP)");
        System.out.print("Opção: ");
        return scanner.nextInt();
    }

    public void showPhysicalAttack(Hero hero, Monster monster, int damage) {
        System.out.println("⚔️ Você atacou com " + hero.getWeaponName() + " e causou " + damage
                + " de dano no " + monster.getName() + "!");
    }

    public void showMagicAttack(Monster monster, int damage) {
        System.out.println("🔥 Você conjurou uma Bola de Fogo! Causou " + damage
                + " de dano mágico no " + monster.getName() + "!");
    }

    public void showInsufficientMana() {
        System.out.println("❌ Sem mana suficiente! Você perdeu a vez de forma atrapalhada.");
    }

    public void showHealing(int amount) {
        System.out.println("💚 Você usou Magia Divina e recuperou " + amount + " pontos de HP!");
    }

    public void showInvalidAction() {
        System.out.println("🌀 Comando confuso! Você hesitou e perdeu seu turno.");
    }

    public void showMonsterAttack(Monster monster, int damage) {
        System.out.println("💥 O " + monster.getName() + " ataca você causando " + damage + " de dano!");
    }

    public void showMonsterSpecialAttack(Monster monster, int damage) {
        System.out.println("⚡ O " + monster.getName() + " executa seu golpe especial: ["
                + monster.getAbility() + "]!");
        System.out.println("💥 O golpe especial causa estrondosos " + damage + " de dano em você!");
    }

    public void showPoisonApplied(Monster monster) {
        System.out.println("🤢 O " + monster.getName() + " cospe uma saliva ácida em você!");
        System.out.println("Você foi ENVENENADO por 3 turnos!");
    }

    public void showStunApplied(Monster monster) {
        System.out.println("🔨 O " + monster.getName() + " golpeia sua cabeça com um escudo pesado!");
        System.out.println("Você foi ATORDOADO para o próximo turno!");
    }

    public void showPoisonAndStunApplied(Monster monster) {
        System.out.println("🔥 O " + monster.getName() + " ruge e chove labaredas tóxicas!");
        System.out.println("Você está ENVENENADO e ATORDOADO!");
    }

    public void showGameOver(Hero hero, int floor) {
        System.out.println("\n💀💀💀 O Herói " + hero.getName() + " sucumbiu na masmorra no andar "
                + floor + "... GAME OVER 💀💀💀");
    }

    public void showMonsterDefeated(Monster monster, int floor) {
        System.out.println("\n🎉 Você derrotou o " + monster.getName() + "! O andar " + floor + " foi purificado.");
    }

    public void showTreasure() {
        System.out.println("Você encontrou um baú misterioso!");
    }

    public void showFirstReward() {
        System.out.println("Dentro do baú há uma: [Espada de Aço de Fogo]");
    }

    public void showSecondReward() {
        System.out.println("Dentro do baú há um: [Cajado do Arquimago Sagrado]");
    }

    public boolean askToEquip(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt() == 1;
    }

    public void showEquippedWeapon(Hero hero) {
        System.out.println("Você equipou a " + hero.getWeaponName()
                + "! Seu bônus de dano agora é +" + hero.getWeaponDamageBonus());
    }

    public void showEquippedWeaponAndMana(Hero hero) {
        System.out.println("Você equipou a " + hero.getWeaponName() + "! Seu bônus de dano é +"
                + hero.getWeaponDamageBonus() + " e sua mana máxima aumentou!");
    }

    public void showRest() {
        System.out.println("Após descansar, você recuperou um pouco de HP e MP!");
    }

    public void showVictory(Hero hero) {
        System.out.println("\n=======================================================");
        System.out.println("🏆 PARABÉNS! " + hero.getName() + " conquistou a Masmorra Arquitetural!");
        System.out.println("Você provou ser um verdadeiro mestre dos padrões!");
        System.out.println("=======================================================");
    }
}
