# ⚔️ DesignPartner & Dragons (pdDragon)
## 🐉 Padrões de Projeto & Boas Práticas na Era da IA

Bem-vindo ao repositório de partida do seu projeto prático de **Padrões de Projeto**! 

Este projeto, chamado **`pdDragon`**, serve como a "bola de lama" inicial (um monolito acoplado e procedural) que você irá transformar, ao longo do semestre, 
em uma arquitetura de software profissional, desacoplada e elegante, digna de um Arquiteto de Software.

---

## 🎯 Por que Padrões de Projeto na era do Copilot e ChatBots IA?

Na era da IA generativa, codificar se tornou uma atividade extremamente rápida, mas a arquitetura e sustentabilidade do sistema continuam sendo atribuições essencialmente humanas. 

*   **Sem Padrões de Projeto:** Você pedirá à IA para criar funcionalidades, e ela gerará códigos soltos. O sistema se tornará um labirinto confuso de acoplamento e heranças abusivas ("bola de lama"), impossível de manter ou escalar.
*   **Com Padrões de Projeto:** Você usará os padrões como um **vocabulário arquitetônico** com a IA. Com prompts como *"Refatore essa lógica usando Strategy"* ou *"Implemente um Decorator para as armas"*, a IA gerará um código extremamente limpo, coeso e desacoplado.

Nessa aula, trabalharemos com o lema: **Pense como Arquiteto (Humano) 🧠 ➡️ Delegue com Precisão (IA) 🤖**.

---

## 🚀 Como Configurar o Projeto no IntelliJ IDEA

Siga o passo a passo abaixo para importar e preparar seu ambiente no **IntelliJ IDEA**:

### 1. Criar o Projeto do Zero
1. Abra a sua IDE (Irei usar o **IntelliJ IDEA**  ).
2. Clique em **New Project** (ou *File -> New -> Project*).
3. Defina os seguintes parâmetros:
   * **Name:** `pdDragon`
   * **Language:** `Java`
   * **Build System:** `IntelliJ` 
   * **JDK:** Selecione o **JDK 17** ou superior.
4. Clique em **Create**.

### 2. Estrutura de Diretórios Recomendada
Crie a estrutura de pacotes dentro da pasta `src` para organizar o seu jogo:
```text
pdDragon/
└── src/
    └── tech/
        └── angelofdiasg/
            └── pddragons/
                    └── Main.java  <-- Cole o Código de Partida aqui!
```

---

## 💾 O Código de Partida (A "Bola de Lama")

Copie o código abaixo e cole-o na sua classe `Main.java` (removendo qualquer código padrão gerado pela IDE). 

Este código está **totalmente funcional**, ou seja, você consegue rodar e jogar o RPG imediatamente pelo terminal. No entanto, sua estrutura arquitetural é péssima: ele está repleto de acoplamento rígido, condicionais aninhadas gigantescas, flags de estado confusas e herança inexistente. É o nosso ponto de partida perfeito para as refatorações!

```java

import java.util.Scanner;
import java.util.Random;

/**
 * ⚠️ ATENÇÃO: Este é o Código de Partida Monolítico ("Messy Code").
 * Toda a lógica, dados, estados, combate e exibição de console estão misturados
 * dentro deste único arquivo usando estruturas estáticas, condicionais aninhadas e flags rígidas.
 * 
 * Seu objetivo na disciplina é fragmentar e refatorar este arquivo aplicando GRASP e GoF.
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
```

---

## 🕹️ Como Executar o Jogo no IntelliJ

1. Com o arquivo `Main.java` colado e salvo, observe o canto superior direito do IntelliJ.
2. Você verá um botão de "Play" verde (ou um ícone de martelo de compilação).
3. Alternativamente, clique com o botão direito em qualquer parte do código da classe `Main` e escolha **`Run 'Main.main()'`** (ou use o atalho `Shift + F10`).
4. O console do IntelliJ abrirá na parte inferior da tela, pronto para interagir com você!

---

## 🛠️ Cronograma de Refatoração

Cada módulo de aula representará uma evolução direta no código do projeto `pdDragon`:

1.  **Módulo I (Princípios GRASP):** Remover o código de dentro da classe `Main`, isolando as entidades `Hero`, `Monster` e criando uma classe `GameEngine` para gerenciar o loop da masmorra.
2.  **Módulo II (Padrões Criacionais - GoF):** Substituir a criação de personagens por um robusto **Builder** (`HeroBuilder`) e o sistema de criação de monstros do andar por um **Factory Method** (`MonsterFactory`).
3.  **Módulo III (Padrões Estruturais - GoF):** Implementar o padrão **Decorator** para que as armas do jogo possam ser decoradas cumulativamente com bônus de ataque (ex: uma arma que recebe simultaneamente o modificador de Fogo e de Gelo).
4.  **Módulo IV (Padrões Comportamentais - GoF):** Substituir as flags e condicionais de envenenamento e atordoamento por um sistema modular usando o padrão **State**, e as escolhas de ataque por **Strategy**.

---

## 🎮 Game Design Document (GDD) Simplificado: DesignPartner & Dragons

Para tornar este projeto ainda mais imersivo e empolgante, transformamos nossa masmorra de programação em um jogo estruturado sob regras e interações clássicas de RPG. Abaixo, detalhamos o design do jogo para que você entenda como suas ações no terminal se traduzem em desafios e estratégias.

### 🕹️ Sinopse e Objetivo do Jogo
Em **DesignPartner & Dragons**, você encarna um programador lendário que adentra a **Masmorra Arquitetural**, um labirinto esquecido de servidores onde códigos antigos ganharam vida como monstros de pesadelo. Sua missão é purificar os 3 andares da masmorra, derrotando bugs encarnados como monstros, coletando artefatos e acumulando troféus, até derrotar o temido **Dragão Arquitetural (Boss)** no topo.

### ⚔️ Mecânicas de Jogo (O que o jogador PODE fazer)
As mecânicas são as regras fundamentais e ações diretas que o sistema permite:
1. **Escolha de Classe de Personagem (Builder):** Cada classe define atributos iniciais e equipamentos característicos:
    * **Guerreiro:** Alta resistência física (100 HP), pouca mana (20 MP). Inicia com *Espada de Ferro*.
    * **Mago:** Alta energia mágica (80 MP) mas frágil (60 HP). Inicia com *Cajado de Aprendiz*.
    * **Paladino:** Equilibrado (85 HP, 40 MP). Inicia com *Martelo de Guerra*.
2. **Escolhas de Turno em Combate (Strategy):** Em seu turno, o jogador escolhe entre três posturas estratégicas de batalha:
    * **Ataque Físico:** Causa dano físico bruto baseado na força física e bônus da arma atual.
    * **Magia Arcana (Custo: 10 MP):** Causa alto dano mágico contornando a armadura inimiga, ideal para quem tem alta inteligência.
    * **Postura Defensiva (+15 HP):** Permite recuperar o fôlego e pontos de vida no meio da batalha, sem consumir mana.
3. **Efeitos de Estado Negativos (State):** Condições físicas aplicadas por monstros que alteram as regras do turno:
    * **Envenenado (Poisoned):** O herói perde 5 HP no início de cada turno devido a saliva ácida de monstros.
    * **Atordoado (Stunned):** O herói fica atordoado e pula completamente o seu turno de decisão técnico (restaurando-se no turno seguinte).
4. **Encantamentos Dinâmicos (Decorator):** Ao vencer monstros de andares intermediários, o herói encontra bigornas ancestrais que permitem imbuir runas cumulativas em sua arma:
    * **Runas de Fogo (+6 de dano de chamas)**
    * **Runas Gélidas (+4 de dano de gelo)**
5. **Conquistas Reativas (Observer):** Um sistema automatizado que monitora em tempo real as ações de combate e desbloqueia troféus virtuais ao atingir marcos (ex: \"Primeiro Sangue!\", \"Matador de Dragões!\", \"Sobrevivente nato\").

### 🎲 Dinâmicas de Jogo (Como o jogador se COMPORTA e reage)
As dinâmicas surgem da interação das mecânicas com o comportamento e as escolhas do jogador:
* **Gerenciamento de Riscos (HP vs. MP):** Jogadores precisam decidir o momento exato de gastar mana para finalizar uma luta rápida com Magia contra o risco de ficar sem mana para se curar no momento crítico.
* **Sinergia de Builds (Customização):** Com o padrão Decorator, os jogadores podem acumular múltiplos encantamentos na mesma arma. Um guerreiro pode terminar o jogo empunhando uma *\"Espada de Ferro das Chamas Gélidas\"*, combinando bônus de dano de múltiplos andares.
* **Adaptabilidade Tática (Reagir aos Estados):** Se o herói for envenenado, a dinâmica muda de \"jogar com calma\" para \"finalizar o oponente rápido\" antes que o veneno drene o restante do HP. Se for atordoado, o jogador é forçado a assumir uma postura passiva e repensar a estratégia do próximo turno.

### 📜 Como Jogar
1. **Configuração e Execução:** Execute a classe `Main.java` no console do IntelliJ IDEA.
2. **Identificação:** Digite o nome do seu Herói e selecione uma classe de combate inicial (Guerreiro, Mago ou Paladino).
3. **Exploração da Masmorra:** O jogo avança automaticamente andar por andar (do 1º ao 3º). Em cada andar, um monstro específico (Goblin, Orc e Dragão) desafiará você.
4. **Combate Baseado em Turnos:** Escolha a ação digitando o número correspondente no console (1, 2 ou 3) e observe os resultados na tela.
5. **Aprimoramento de Armas:** No final dos andares 1 e 2, escolha se deseja e qual encantamento aplicar à sua arma na bigorna.
6. **Vitória ou Derrota:** O jogo termina quando você purificar o 3º andar derrotando o Dragão (Vitória) ou quando seu HP chegar a 0 (Fim de Jogo). Seus troféus serão exibidos no final!

---
*⚔️ Prepare suas interfaces, configure suas dependências e bom código! A masmorra de "Design Partner & Dragons" aguarda suas habilidades arquiteturais.*
