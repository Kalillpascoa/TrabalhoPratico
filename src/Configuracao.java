//Representar uma configuração específica da fita e do estado atual de uma Máquina de Turing em um determinado momento durante sua execução.
public class Configuracao {
    private String fitaDireita;
    private String fitaEsquerda;
    private Transicao transicaoAtual;
    private String estadoAtual;
    

    //Construtor que inicializa uma nova Configuracao recebendo como parâmetros as fitas e o estado atual.
    public Configuracao(String fitaEsquerda, String fitaDireita, String estadoAtual) {
        this.fitaEsquerda = fitaEsquerda;
        this.fitaDireita = fitaDireita;
        this.estadoAtual = estadoAtual;
    }
    //Construtor de cópia que cria uma nova configuração com base em outra configuração já existente
    public Configuracao(Configuracao proxima) {
        this.fitaEsquerda = proxima.fitaEsquerda;
        this.fitaDireita = proxima.fitaDireita;
        this.estadoAtual = proxima.estadoAtual;
        this.transicaoAtual = proxima.transicaoAtual;
    }

    // Métodos Get e Set que fornecem acesso aos atributos privados da Configuração
    public String getFitaEsquerda() {
        return fitaEsquerda;
    }

    public void setFitaEsquerda(String fitaEsquerda) {
        this.fitaEsquerda = fitaEsquerda;
    }

    public String getFitaDireita() {
        return fitaDireita;
    }

    public void setFitaDireita(String fitaDireita) {
        this.fitaDireita = fitaDireita;
    }

    public String getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(String estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public Transicao getTransicaoAtual() {
        return transicaoAtual;
    }

    public void setTransicaoAtual(Transicao transicaoAtual) {
        this.transicaoAtual = transicaoAtual;
    }
}
