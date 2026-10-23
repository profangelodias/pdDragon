package tech.angelofdiasg.pddragons;
import java.util.Scanner;
import java.util.Random;

/**
 * ⚠️ ATENÇÃO: Este é o Código de Partida Monolítico ("Messy Code").
 * Toda a lógica, dados, estados, combate e exibição de console estão misturados
 * dentro deste único arquivo usando estruturas estáticas, condicionais aninhadas e flags rígidas.
 *
 * Seu objetivo no Bootcamp é fragmentar e refatorar este arquivo aplicando GRASP e GoF.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=========================================");
        System.out.println("    BEM-VINDO A DESIGN PARTNER & DRAGONS!    ");
        System.out.println("=========================================");

        // --- CRIAÇÃO MACARRÔNICA DO PERSONAGEM (DADOS DESACOPLADOS) ---
        System.out.print("Digite o nome do seu Herói: ");
        String heroiNome = scanner.nextLine();

        System.out.println("Escolha sua classe: 1-Guerreiro, 2-Mago, 3-Arqueiro");
        System.out.print("Escolha: ");
        int classeOpcao = scanner.nextInt();

        String heroiClasse = "Guerreiro";
        int heroiHP = 100;
        int heroiMaxHP = 100;
        int heroiMP = 20;
        int heroiMaxMP = 20;
        int heroiDanoBase = 10;
        String armaNome = "Punhos Nus";
        int armaDanoBonus = 0;

        // Atribuição de atributos de forma chumbada (Telescoping Constructor fail)
        if (classeOpcao == 2) {
            heroiClasse = "Mago";
            heroiHP = 60;
            heroiMaxHP = 60;
            heroiMP = 80;
            heroiMaxMP = 80;
            heroiDanoBase = 5;
            armaNome = "Cajado de Aprendiz";
            armaDanoBonus = 3;
        } else if (classeOpcao == 3) {
            heroiClasse = "Arqueiro";
            heroiHP = 80;
            heroiMaxHP = 80;
            heroiMP = 40;
            heroiMaxMP = 40;
            heroiDanoBase = 8;
            armaNome = "Arco Curto";
            armaDanoBonus = 4;
        } else {
            // Guerreiro padrão
            armaNome = "Espada Enferrujada";
            armaDanoBonus = 5;
        }

        System.out.println("\nHerói " + heroiNome + " o " + heroiClasse + " criado com sucesso!");
        System.out.println("Armado com " + armaNome + " (+ " + armaDanoBonus + " Dano)");
        System.out.println("Atributos: HP=" + heroiHP + "/" + heroiMaxHP + " | MP=" + heroiMP + "/" + heroiMaxMP + "\n");

        // --- SISTEMA DE FLORES (ANDAR DA MASMORRA) ---
        int andarAtual = 1;
        int totalAndares = 3;
        boolean heroiVivo = true;

        // Loop de andares da masmorra (Controle Procedural)
        while (andarAtual <= totalAndares && heroiVivo) {
            System.out.println("-----------------------------------------");
            System.out.println("👉 ENTRANDO NO ANDAR " + andarAtual + " DA MASMORRA!");
            System.out.println("-----------------------------------------");

            // Criação rígida de monstros (Spawner acoplado usando condicionais de Strings)
            String monstroNome = "";
            int monstroHP = 0;
            int monstroDanoBase = 0;
            String monstroHabilidade = "";

            if (andarAtual == 1) {
                monstroNome = "Goblin Traquinas";
                monstroHP = 35;
                monstroDanoBase = 8;
                monstroHabilidade = "Mordida Rápida";
            } else if (andarAtual == 2) {
                monstroNome = "Orc Saqueador";
                monstroHP = 65;
                monstroDanoBase = 12;
                monstroHabilidade = "Clava de Ferro";
            } else if (andarAtual == 3) {
                monstroNome = "Architectural Dragon (BOSS)";
                monstroHP = 150;
                monstroDanoBase = 18;
                monstroHabilidade = "Sopro de Código Legado (Fogo)";
            }

            System.out.println("Um terrível " + monstroNome + " bloqueia seu caminho!");

            // Flags de estado voláteis para efeitos temporários (Falta do padrão State)
            boolean heroiEnvenenado = false;
            boolean heroiAtordoado = false;
            int turnosEnvenenamento = 0;

            // --- LOOP DE BATALHA ACOPLADO ---
            while (heroiHP > 0 && monstroHP > 0) {
                System.out.println("\n--- STATUS COMBATE ---");
                System.out.println(heroiNome + " [HP: " + heroiHP + "/" + heroiMaxHP + " | MP: " + heroiMP + "/" + heroiMaxMP + "]");
                System.out.println(monstroNome + " [HP: " + monstroHP + "]");
                if (heroiEnvenenado) {
                    System.out.println("⚠️ ATENÇÃO: Você está envenenado! (" + turnosEnvenenamento + " turnos restantes)");
                }
                if (heroiAtordoado) {
                    System.out.println("🌀 ATENÇÃO: Você está atordoado!");
                }
                System.out.println("----------------------");

                // Efeitos de estado aplicados no início do turno do Herói
                if (heroiEnvenenado && turnosEnvenenamento > 0) {
                    int danoVeneno = 5;
                    heroiHP -= danoVeneno;
                    turnosEnvenenamento--;
                    System.out.println("💀 O veneno queima nas suas veias! Causou " + danoVeneno + " de dano. Seu HP atual: " + heroiHP);
                    if (heroiHP <= 0) {
                        break; // Herói morreu por veneno
                    }
                    if (turnosEnvenenamento == 0) {
                        heroiEnvenenado = false;
                        System.out.println("✨ O veneno se dissipou do seu corpo.");
                    }
                }

                // Turno do Herói
                if (!heroiAtordoado) {
                    System.out.println("Sua vez! Escolha uma ação:");
                    System.out.println("1 - Ataque Físico");
                    System.out.println("2 - Ataque Mágico (Custo: 10 MP)");
                    System.out.println("3 - Curar-se (Custo: 5 MP)");
                    System.out.print("Opção: ");
                    int acao = scanner.nextInt();

                    // Switch estrutural gigante de ações de combate (Falta do padrão Strategy)
                    switch (acao) {
                        case 1:
                            int danoCalculado = heroiDanoBase + armaDanoBonus + random.nextInt(6);
                            monstroHP -= danoCalculado;
                            System.out.println("⚔️ Você atacou com " + armaNome + " e causou " + danoCalculado + " de dano no " + monstroNome + "!");
                            break;
                        case 2:
                            if (heroiMP >= 10) {
                                heroiMP -= 10;
                                int danoMagico = heroiDanoBase * 2 + random.nextInt(12);
                                monstroHP -= danoMagico;
                                System.out.println("🔥 Você conjurou uma Bola de Fogo! Causou " + danoMagico + " de dano mágico no " + monstroNome + "!");
                            } else {
                                System.out.println("❌ Sem mana suficiente! Você perdeu a vez de forma atrapalhada.");
                            }
                            break;
                        case 3:
                            if (heroiMP >= 5) {
                                heroiMP -= 5;
                                int cura = 20 + random.nextInt(11);
                                heroiHP = Math.min(heroiMaxHP, heroiHP + cura);
                                System.out.println("💚 Você usou Magia Divina e recuperou " + cura + " pontos de HP!");
                            } else {
                                System.out.println("❌ Sem mana suficiente! Você perdeu a vez de forma atrapalhada.");
                            }
                            break;
                        default:
                            System.out.println("🌀 Comando confuso! Você hesitou e perdeu seu turno.");
                            break;
                    }
                } else {
                    System.out.println("🌀 Você está atordoado e não consegue se mover neste turno!");
                    heroiAtordoado = false; // Recupera para o próximo turno
                }

                // Turno do Monstro (Se ainda estiver vivo)
                if (monstroHP > 0) {
                    // Decide ação do monstro de forma procedural
                    int acaoMonstro = random.nextInt(10);

                    if (acaoMonstro < 6) { // 60% chance de ataque comum
                        int danoInimigo = monstroDanoBase + random.nextInt(5);
                        heroiHP -= danoInimigo;
                        System.out.println("💥 O " + monstroNome + " ataca você causando " + danoInimigo + " de dano!");
                    } else if (acaoMonstro < 8) { // 20% chance de usar habilidade especial
                        System.out.println("⚡ O " + monstroNome + " executa seu golpe especial: [" + monstroHabilidade + "]!");
                        int danoEspecial = monstroDanoBase + 8;
                        heroiHP -= danoEspecial;
                        System.out.println("💥 O golpe especial causa estrondosos " + danoEspecial + " de dano em você!");
                    } else { // 20% chance de aplicar efeito negativo (Veneno / Atordoamento)
                        if (andarAtual == 1) { // Goblin envenena
                            System.out.println("🤢 O " + monstroNome + " cospe uma saliva ácida em você!");
                            heroiEnvenenado = true;
                            turnosEnvenenamento = 3;
                            System.out.println("Você foi ENVENENADO por 3 turnos!");
                        } else if (andarAtual == 2) { // Orc atordoa
                            System.out.println("🔨 O " + monstroNome + " golpeia sua cabeça com um escudo pesado!");
                            heroiAtordoado = true;
                            System.out.println("Você foi ATORDOADO para o próximo turno!");
                        } else { // Dragon faz os dois!
                            System.out.println("🔥 O " + monstroNome + " ruge e chove labaredas tóxicas!");
                            heroiEnvenenado = true;
                            turnosEnvenenamento = 2;
                            heroiAtordoado = true;
                            System.out.println("Você está ENVENENADO e ATORDOADO!");
                        }
                    }
                }
            } // Fim do Loop de Batalha

            if (heroiHP <= 0) {
                System.out.println("\n💀💀💀 O Herói " + heroiNome + " sucumbiu na masmorra no andar " + andarAtual + "... GAME OVER 💀💀💀");
                heroiVivo = false;
            } else {
                System.out.println("\n🎉 Você derrotou o " + monstroNome + "! O andar " + andarAtual + " foi purificado.");

                // --- DROPS RÍGIDOS NO PÓS-COMBATE (Acoplamento de Recompensas) ---
                if (andarAtual < totalAndares) {
                    System.out.println("Você encontrou um baú misterioso!");
                    if (andarAtual == 1) {
                        System.out.println("Dentro do baú há uma: [Espada de Aço de Fogo]");
                        System.out.println("Deseja equipá-la? (1-Sim, 2-Não)");
                        int equipChoice = scanner.nextInt();
                        if (equipChoice == 1) {
                            armaNome = "Espada de Aço de Fogo";
                            armaDanoBonus = 12; // Dano fixado chumbado (decorator fail)
                            System.out.println("Você equipou a " + armaNome + "! Seu bônus de dano agora é +" + armaDanoBonus);
                        }
                    } else if (andarAtual == 2) {
                        System.out.println("Dentro do baú há um: [Cajado do Arquimago Sagrado]");
                        System.out.println("Deseja equipá-lo? (1-Sim, 2-Não)");
                        int equipChoice = scanner.nextInt();
                        if (equipChoice == 1) {
                            armaNome = "Cajado do Arquimago Sagrado";
                            armaDanoBonus = 18;
                            heroiMaxMP += 20; // Altera atributos diretamente de forma perigosa
                            heroiMP = heroiMaxMP;
                            System.out.println("Você equipou a " + armaNome + "! Seu bônus de dano é +" + armaDanoBonus + " e sua mana máxima aumentou!");
                        }
                    }
                }

                // Descanso e avanço
                heroiHP = Math.min(heroiMaxHP, heroiHP + 30); // Recuperação parcial
                heroiMP = Math.min(heroiMaxMP, heroiMP + 10);
                System.out.println("Após descansar, você recuperou um pouco de HP e MP!");
                andarAtual++;
            }
        } // Fim do Loop de Masmorra

        if (heroiVivo) {
            System.out.println("\n=======================================================");
            System.out.println("🏆 PARABÉNS! " + heroiNome + " conquistou a Masmorra Arquitetural!");
            System.out.println("Você provou ser um verdadeiro mestre dos padrões!");
            System.out.println("=======================================================");
        }

        scanner.close();
    }
}