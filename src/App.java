import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.executar();
    }
    //Coleta opção do usuário via terminal de qual MT executar
    public void executar() {
        Scanner scanner = new Scanner(System.in);
        int opcaoEscolhida;
        //Exibe o Menu
        do {
            exibirMenu();
            System.out.println("\nDigite a opção de qual caso de teste quer executar: ");
            opcaoEscolhida = Integer.parseInt(scanner.nextLine());
            tratarOpcao(opcaoEscolhida);            
        } while (opcaoEscolhida != 5); // Se adicionar mais opções de MT, modificar aqui       
        scanner.close();
    }

    // Exibe as opções de menu com os exemplos disponíveis  
    public void exibirMenu() {
        System.out.println("1 - MT que aceita palavras formadas por infinitos a's seguido por um único b");
        System.out.println("2 - MT que troca a por b e vice-versa");
        System.out.println("3 - MT não deterministica que aceita palavras formadas por a e b terminadas com a");        
        System.out.println("4 - MT que mantém balanceado o número de a's, b's e c's na fita");
        System.out.println("5 - Sair");
    }

    // Trata as opções do menu    
    private void tratarOpcao(int opcaoEscolhida) {
        switch (opcaoEscolhida) {
            case 1:
                executarCaso1();
                break;
            case 2:
                executarCaso2();
                break;
            case 3:
                executarCaso3();
                break;
            case 4:
                executarCaso4();
                break;
            case 5:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }  
    }

    private void executarCaso1() {
        // Caso de teste para uma MT que aceita palavras formadas por infinitos a's seguido por um único b
        MaquinaTuring maquinaTuring = new MaquinaTuring(
            Arrays.asList("q0", "q1"), // Estados
            Arrays.asList("a", "b"), // Alfabeto de entrada
            Arrays.asList("a", "b", "_"), // Alfabeto da fita
            // Transições
            Arrays.asList(
                new Transicao("q0", "a", "a", "D", "q0"),
                new Transicao("q0", "b", "b", "D", "q1"),
                new Transicao("q1", "a", "a", "D", "qErro"),
                new Transicao("q1", "b", "b", "D", "qErro")
            ),
            "q0", // Estado inicial
            Arrays.asList("q1"), // Estados finais
            "_" // Símbolo branco
        );
        //Inicializa o simulador da MT
        SimuladorMT simulador = new SimuladorMT(maquinaTuring);
        //Palavra de entrada a ser testada
        simulador.processarEntrada("aab");//Modifique a palavra de entrada    
    }

    private void executarCaso2() {
        // Caso de teste para uma MT que troca a por b e vice-versa
        MaquinaTuring maquinaTuring = new MaquinaTuring(
            Arrays.asList("q0"), // Estados
            Arrays.asList("a", "b"), // Alfabeto de entrada
            Arrays.asList("a", "b", "_"), // Alfabeto da fita
            // Transições
            Arrays.asList(
                new Transicao("q0", "a", "b", "D", "q0"),
                new Transicao("q0", "b", "a", "D", "q0")
            ),
            "q0", // Estado inicial
            Arrays.asList("q0"), // Estados finais
            "_" // Símbolo branco
        );
        //Inicializa o simulador da MT
        SimuladorMT simulador = new SimuladorMT(maquinaTuring);
        //Palavra de entrada a ser testada
        simulador.processarEntrada("aaabbb");//Modifique a palavra de entrada    
    }

    private void executarCaso3(){
        //MT não deterministica que aceita palavras formadas por a e b terminadas com a
        MaquinaTuring maquinaTuring = new MaquinaTuring(
            Arrays.asList("q0", "q1"),
            Arrays.asList("a", "b"),
            Arrays.asList("0", "1", "_"),
            Arrays.asList(
                new Transicao("q0", "a", "a", "D", "q0"),
                new Transicao("q0", "a", "a", "D", "q1"),
                new Transicao("q0", "b", "b", "D", "q0"),
                new Transicao("q1", "a", "a", "D", "q1"),
                new Transicao("q1", "b", "b", "D", "q0")
            ),
                "q0",
                Arrays.asList("q1"),
                "_"
        );            
        //Inicializa o simulador da MT
        SimuladorMT simulador = new SimuladorMT(maquinaTuring);
        //Palavra de entrada a ser testada
        simulador.processarEntrada("aaabbba");//Modifique a palavra de entrada  
    }

    private void executarCaso4() {
        // MT que mantém balanceado o número de 'a's, 'b's e c's na fita
        MaquinaTuring maquinaTuring = new MaquinaTuring(
            Arrays.asList("q0", "q1", "q2", "q3", "qAceita"),
            Arrays.asList("a", "b", "c"),
            Arrays.asList("a", "b", "X", "_"),
            Arrays.asList(
                new Transicao("q0", "a", "X", "D", "q1"),// Encontrar 'a' e substituir por 'X'
                new Transicao("q1", "a", "a", "D", "q1"),// Anda sobre a's
                new Transicao("q1", "b", "X", "D", "q2"),// Encontrar 'b' e substituir por 'X'
                new Transicao("q2", "b", "b", "D", "q2"),// Anda sobre b's
                new Transicao("q2", "c", "X", "E", "q3"),// Encontrar 'c' e substituir por 'X' e volta E
                new Transicao("q3", "a", "a", "E", "q3"),// Rebobina a's
                new Transicao("q3", "b", "b", "E", "q3"),// Rebobina b's
                new Transicao("q3", "X", "X", "E", "q3"),// Rebobina X's
                new Transicao("q3", "_", "_", "D", "q0"),// Achou o começo da fita
                new Transicao("q0", "X", "X", "D", "q0"),// Anda sobre X's
                new Transicao("q1", "X", "X", "D", "q1"),// Anda sobre X's
                new Transicao("q2", "X", "X", "D", "q2"),// Anda sobre X's
                new Transicao("q3", "X", "X", "E", "q3"),// Anda sobre X's
                new Transicao("q0", "_", "_", "E", "qAceita")// Verifica se fita toda é formada por X                
            ),
            "q0",  // Estado inicial
            Arrays.asList("qAceita"),  // Estado final de aceitação
            "_"  // Símbolo branco
        );
    
        // Inicializa o simulador da MT
        SimuladorMT simulador = new SimuladorMT(maquinaTuring);
        // Palavra de entrada a ser testada
        simulador.processarEntrada("aaabbbccc"); // Modifique a palavra de entrada conforme necessário
    }
    
}
       
