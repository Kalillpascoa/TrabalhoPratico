//Transição de estado em uma Máquina de Turing
public class Transicao {
    private String estadoAtual;
    private String simboloLido;
    private String novoSimbolo;
    private String movimento;
    private String novoEstado;

    //Construtor da classe, que inicializa uma nova transição
    public Transicao(String estadoAtual, String simboloLido, String novoSimbolo, String movimento, String novoEstado) {
        this.estadoAtual = estadoAtual;
        this.simboloLido = simboloLido;
        this.novoSimbolo = novoSimbolo;
        this.movimento = movimento;
        this.novoEstado = novoEstado;
    }

    // // Métodos Get que fornecem acesso aos atributos privados da Transição
    public String getEstadoAtual() {
        return estadoAtual;
    }

    public String getSimboloLido() {
        return simboloLido;
    }

    public String getNovoSimbolo() {
        return novoSimbolo;
    }

    public String getMovimento() {
        return movimento;
    }

    public String getNovoEstado() {
        return novoEstado;
    }
    
     // Método para atualizar o estado atual
     public void atualizarEstadoAtual(String novoEstado) {
        this.estadoAtual = novoEstado;
    }

}
