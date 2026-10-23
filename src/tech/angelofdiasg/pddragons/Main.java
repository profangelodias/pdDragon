package tech.angelofdiasg.pddragons;

import java.util.Random;
import java.util.Scanner;

/**
 * Versão monolítica do jogo, mantida para comparação didática com Main.
 * As regras, atributos e opções são equivalentes; aqui permanecem concentrados
 * no método main para evidenciar o que os padrões extraem e organizam.
 */
public class Main {
    public static void main(String[] args) {
        // [REFATORAÇÃO INCREMENTAL - Controller]
        // Scanner e Random continuam locais ao fluxo principal do monólito.
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // [ITEM 2/8 - GoF Facade: apresentação extraída para ui/GameUI.java.
        // Menus e leitura do Scanner continuam no fluxo de Main.]
        System.out.println("====================================================================");
        System.out.println("  BEM-VINDO A DESIGN PARTNER & DRAGONS");
        System.out.println("  A masmorra da arquitetura espera por você na era da IA.");
        System.out.println("  Aprenda Padrões de Projeto refatorando e combatendo bugs!");
        System.out.println("  Tente limpar as 3 masmorras de pé.");
        System.out.println("====================================================================");

        // [ITEM 3/8 - GoF Builder: este bloco de escolha e atribuição de dados
        // foi extraído para builder/HeroBuilder.java. A versão refatorada chama
        // setName(...), setClass(...) e build() em Main.java.]
        System.out.print("Digite o nome do seu Herói: ");
        String heroName = scanner.nextLine();

        System.out.println("\nEscolha sua Classe:");
        System.out.println("1. Guerreiro (Alto HP, Espada Balanceada)");
        System.out.println("2. Mago (Alto MP, Feitiço Poderoso)");
        System.out.println("3. Paladino (Híbrido de cura e resistência)");
        System.out.println("4. Arqueiro (Ataque equilibrado à distância)");
        System.out.print("Opção: ");
        int classOption = scanner.nextInt();

        String heroClass = "Aventureiro";
        int strength = 10;
        int intelligence = 10;
        int maxHp = 80;
        int maxMana = 40;
        String weaponName = "Adaga Enferrujada";
        int weaponDamage = 6;

        if (classOption == 1) {
            heroClass = "Guerreiro";
            strength = 14;
            intelligence = 6;
            maxHp = 100;
            maxMana = 20;
            weaponName = "Espada de Ferro";
            weaponDamage = 10;
        } else if (classOption == 2) {
            heroClass = "Mago";
            strength = 5;
            intelligence = 15;
            maxHp = 60;
            maxMana = 80;
            weaponName = "Cajado de Aprendiz";
            weaponDamage = 5;
        } else if (classOption == 3) {
            heroClass = "Paladino";
            strength = 11;
            intelligence = 9;
            maxHp = 85;
            maxMana = 40;
            weaponName = "Martelo de Guerra";
            weaponDamage = 8;
        } else if (classOption == 4) {
            heroClass = "Arqueiro";
            strength = 12;
            intelligence = 10;
            maxHp = 80;
            maxMana = 40;
            weaponName = "Arco Longo";
            weaponDamage = 8;
        }

        int hp = maxHp;
        int mana = maxMana;
        int level = 1;
        int experience = 0;
        String currentState = "Saudável";
        String currentStrategy = "Ataque Físico";

        // [ITEM 1/8 - GRASP Information Expert & Controller]
        // No monólito, os dados e operações do herói estão nestas variáveis e
        // condicionais. Foram movidos para model/Hero.java; Main continua como
        // Controller, orquestrando os turnos sem manter os atributos do herói.
        System.out.println("\nHerói " + heroName + " (Nível " + level + " " + heroClass + ") criado com sucesso!");
        System.out.println("Vida: " + hp + " / " + maxHp + " | Mana: " + mana + " / " + maxMana);
        System.out.println("Estado Físico: " + currentState);
        System.out.println("Arma Atual: " + weaponName + " (Dano: +" + weaponDamage + ")");
        System.out.println("Estratégia Escolhida: " + currentStrategy);

        // [ITEM 1/8 - GRASP Controller: o laço de andares e de combate é fluxo
        // do jogo e permanece em Main.java também após a refatoração.]
        int currentFloor = 1;
        while (hp > 0 && currentFloor <= 3) {
            // [ITEM 4/8 - GoF Factory Method: esta seleção condicional foi
            // extraída para factory/DungeonSpawner.java. Os dados e ações dos
            // produtos foram extraídos para model/Monster.java, Goblin.java,
            // Orc.java e ArchitecturalDragon.java.]
            String monsterName;
            int monsterHp;
            int monsterAttackPower;
            if (currentFloor == 1) {
                monsterName = "Goblin Saqueador";
                monsterHp = 35;
                monsterAttackPower = 8;
            } else if (currentFloor == 2) {
                monsterName = "Orc Guerreiro";
                monsterHp = 60;
                monsterAttackPower = 14;
            } else {
                monsterName = "Dragão Arquitetural";
                monsterHp = 120;
                monsterAttackPower = 22;
            }

            System.out.println("\n====================================================================");
            System.out.println("  NOVO ANDAR DA MASMORRA - " + currentFloor + "F");
            System.out.println("  Um terrível " + monsterName + " bloqueia o seu caminho!");
            System.out.println("  Derrote-o para avançar de nível e coletar relíquias!");
            System.out.println("====================================================================");

            // [ITEM 6/8 - GoF State: esta variável e seus efeitos foram extraídos
            // para state/CharacterState.java, HealthyState.java, PoisonedState.java
            // e StunnedState.java. Os monstros aplicam estados nas classes model/.

            while (hp > 0 && monsterHp > 0) {
                // [ITEM 6/8 - GoF State: o dano contínuo e a verificação do
                // atordoamento foram extraídos para os objetos de estado.
                if (currentState.equals("Envenenado")) {
                    hp = Math.max(0, hp - 5);
                    System.out.println(heroName + " sofreu 5 de dano! [HP Restante: " + hp + "/" + maxHp + "]");
                    System.out.println(" ✨ [EFEITO] " + heroName + " está envenenado e sofreu 5 de dano de Veneno!");
                    if (hp == 0) {
                        break;
                    }
                }

                if (currentState.equals("Atordoado")) {
                    System.out.println(" ✨ [EFEITO] " + heroName + " está atordoado e perdeu a rodada!");
                    System.out.println(" ⚙️  Você está atordoado e não pode tomar decisões técnicas este turno!");
                    currentState = "Saudável";
                } else {
                    // [ITEM 1/8 - Information Expert: estes dados exibidos são
                    // apresentados por Hero.printStatus() na versão refatorada.]
                    System.out.println("\n====================================================================");
                    System.out.println("  STATUS DO HERÓI");
                    System.out.println("  Nome: " + heroName + " (Nível " + level + " " + heroClass + ")");
                    System.out.println("  Vida: " + hp + " / " + maxHp + " | Mana: " + mana + " / " + maxMana);
                    System.out.println("  Estado Físico: " + currentState);
                    System.out.println("  Arma Atual: " + weaponName + " (Dano: +" + weaponDamage + ")");
                    System.out.println("  Estratégia Escolhida: " + currentStrategy);
                    System.out.println("====================================================================");

                    // [ITEM 7/8 - GoF Strategy: o switch foi extraído para
                    // strategy/CombatStrategy.java, MeleeAttack.java,
                    // SpellcastingAttack.java e DefensiveStance.java. Main
                    // continua lendo a opção e escolhe qual estratégia executar.
                    System.out.println("\n--- SEU TURNO ---");
                    System.out.println("Escolha sua estratégia de programação/combate:");
                    System.out.println("1. Ataque Físico com sua arma (" + weaponName + ")");
                    System.out.println("2. Conjurar Magia Arcana (Custo: 10 MP)");
                    System.out.println("3. Adotar Postura Defensiva (+15 HP)");
                    System.out.print("Estratégia: ");
                    int strategyOption = scanner.nextInt();

                    switch (strategyOption) {
                        case 1:
                            currentStrategy = "Ataque Físico";
                            int meleeDamage = weaponDamage + strength / 2;
                            System.out.println(" ⚔️  " + heroName + " ataca fisicamente com " + weaponName
                                    + " causando " + meleeDamage + " de dano.");
                            monsterHp = Math.max(0, monsterHp - meleeDamage);
                            break;
                        case 2:
                            currentStrategy = "Magia Arcana (Custo: 10 MP)";
                            if (mana >= 10) {
                                mana -= 10;
                                System.out.println(" ⚔️  " + heroName + " lança um feitiço arcano em "
                                        + monsterName + " causando 25 de dano!");
                                monsterHp = Math.max(0, monsterHp - 25);
                            } else {
                                System.out.println(" ⚔️  " + heroName + " tentou conjurar magia, mas não possui Mana suficiente ("
                                        + mana + "/10)! Ataque falhou.");
                            }
                            break;
                        case 3:
                            currentStrategy = "Postura Defensiva (+15 HP)";
                            hp = Math.min(maxHp, hp + 15);
                            System.out.println(" ⚔️  " + heroName + " adota postura defensiva e recupera 15 HP!");
                            break;
                        default:
                            currentStrategy = "Ataque Físico";
                            System.out.println(" ⚙️  Opção inválida! Adotando postura padrão de ataque físico.");
                            int defaultDamage = weaponDamage + strength / 2;
                            System.out.println(" ⚔️  " + heroName + " ataca fisicamente com " + weaponName
                                    + " causando " + defaultDamage + " de dano.");
                            monsterHp = Math.max(0, monsterHp - defaultDamage);
                    }
                }

                // [ITEM 1/8 - Information Expert: vida, dano e ação dos inimigos
                // foram movidos para model/Monster.java e suas subclasses. Main
                // permanece responsável pela ordem dos turnos.]
                if (monsterHp > 0) {
                    System.out.println("\n--- TURNO DO MONSTRO (" + monsterName + ") ---");
                    if (currentFloor == 1) {
                        System.out.println(" ⚔️  " + monsterName + " avança sorrateiramente e morde seu calcanhar!");
                        hp = Math.max(0, hp - monsterAttackPower);
                        System.out.println(" ⚔️  " + heroName + " sofreu " + monsterAttackPower
                                + " de dano! [HP Restante: " + hp + "/" + maxHp + "]");
                        if (random.nextInt(100) < 30 && !currentState.equals("Atordoado")) {
                            currentState = "Envenenado";
                            System.out.println(" ✨ [EFEITO] " + monsterName + " aplicou toxina venenosa a você!");
                        }
                    } else if (currentFloor == 2) {
                        System.out.println(" ⚔️  " + monsterName + " ergue seu machado pesado e desfere um golpe brutal!");
                        hp = Math.max(0, hp - monsterAttackPower);
                        System.out.println(" ⚔️  " + heroName + " sofreu " + monsterAttackPower
                                + " de dano! [HP Restante: " + hp + "/" + maxHp + "]");
                        if (random.nextInt(100) < 25 && !currentState.equals("Atordoado")) {
                            currentState = "Atordoado";
                            System.out.println(" ✨ [EFEITO] " + monsterName
                                    + " bateu o escudo na sua cabeça, deixando você atordoado!");
                        }
                    } else {
                        int dragonAction = random.nextInt(3);
                        if (dragonAction == 0) {
                            System.out.println(" ⚔️  " + monsterName + " ruge e lança um Sopro de Fogo Primordial!");
                            hp = Math.max(0, hp - (monsterAttackPower + 8));
                            System.out.println(" ⚔️  " + heroName + " sofreu " + (monsterAttackPower + 8)
                                    + " de dano! [HP Restante: " + hp + "/" + maxHp + "]");
                            if (!currentState.equals("Atordoado")) {
                                currentState = "Envenenado";
                            }
                        } else if (dragonAction == 1) {
                            System.out.println(" ⚔️  " + monsterName + " bate sua cauda espinhosa contra você!");
                            hp = Math.max(0, hp - monsterAttackPower);
                            System.out.println(" ⚔️  " + heroName + " sofreu " + monsterAttackPower
                                    + " de dano! [HP Restante: " + hp + "/" + maxHp + "]");
                            currentState = "Atordoado";
                        } else {
                            int clawDamage = monsterAttackPower - 4;
                            System.out.println(" ⚔️  " + monsterName + " ataca com suas garras gigantes!");
                            hp = Math.max(0, hp - clawDamage);
                            System.out.println(" ⚔️  " + heroName + " sofreu " + clawDamage
                                    + " de dano! [HP Restante: " + hp + "/" + maxHp + "]");
                        }
                    }
                }
            }

            if (hp <= 0) {
                System.out.println("\n====================================================================");
                System.out.println("  FALHA DE SISTEMA - GAME OVER");
                System.out.println("  Seu herói sucumbiu à bola de lama e ao forte acoplamento do monstro.");
                System.out.println("  Não desanime! Refatore sua mente, revise o código e tente de novo.");
                System.out.println("====================================================================");
            } else {
                System.out.println("\n====================================================================");
                System.out.println("  VITÓRIA!");
                System.out.println("  Você derrotou o " + monsterName + "!");
                System.out.println("====================================================================");

                // [ITEM 8/8 - GoF Observer: o monólito não possuía observadores.
                // Hero notifica observer/GameObserver.java e
                // observer/AchievementSystem.java na versão refatorada.]
                System.out.println(" ⚙️  Você ganhou 40 EXP de derrotar " + monsterName + "!");
                experience += 40;
                if (experience >= level * 40) {
                    level++;
                    experience = 0;
                    hp = maxHp;
                    mana = maxMana;
                    System.out.println(" ✨ [EFEITO] LIVEL UP! Você subiu para o nível " + level
                            + "! Seu HP e MP foram restaurados.");
                }
                currentState = "Saudável";

                // [ITEM 5/8 - GoF Decorator: os bônus incrementais foram
                // extraídos para decorator/Weapon.java, BasicWeapon.java,
                // WeaponDecorator.java, FireWeaponDecorator.java e
                // IceWeaponDecorator.java. A decisão continua no fluxo principal.
                if (currentFloor < 3) {
                    System.out.println("\n🎁 Você encontrou uma bigorna ancestral de refatoração!");
                    System.out.println("Deseja aplicar um encantamento dinâmico à sua arma utilizando o padrão DECORATOR?");
                    System.out.println("1. Encantamento de Fogo (Adiciona +6 de dano de chamas)");
                    System.out.println("2. Encantamento Gélido (Adiciona +4 de dano de gelo)");
                    System.out.println("3. Ignorar encantamento e manter design limpo");
                    System.out.print("Escolha: ");
                    int upgradeOption = scanner.nextInt();

                    if (upgradeOption == 1) {
                        weaponName += " das Chamas";
                        weaponDamage += 6;
                        System.out.println(" ⚙️  Você equipou: " + weaponName + " (Dano Bônus: " + weaponDamage + ")");
                    } else if (upgradeOption == 2) {
                        weaponName += " Gélida";
                        weaponDamage += 4;
                        System.out.println(" ⚙️  Você equipou: " + weaponName + " (Dano Bônus: " + weaponDamage + ")");
                    } else {
                        System.out.println(" ⚙️  Você decidiu manter sua arma padrão para evitar complexidade desnecessária.");
                    }
                } else {
                    System.out.println("\n====================================================================");
                    System.out.println("  FIM DA AVENTURA - VITÓRIA REAL");
                    System.out.println("  Parabéns, arquiteto de software renomado!");
                    System.out.println("  Você superou os desafios arquitetônicos e salvou o reino da bola de lama.");
                    System.out.println("====================================================================");
                }
                currentFloor++;
            }
        }

        // [REFATORAÇÃO INCREMENTAL - permanece em Main.java]
        // O encerramento do fluxo e o fechamento do Scanner continuam no Controller.
        System.out.println(" ⚙️  Obrigado por jogar Design Partner & Dragons! Obrigado Prof. Angelo.");
        scanner.close();
    }
}
