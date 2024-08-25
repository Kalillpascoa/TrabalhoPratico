import java.util.List;

//Atributos da Máquina de Turing
public class MaquinaTuring {
    private List<String> estado;
    private List<String> alfabetoEntrada;
    private List<String> alfabetoFita;
    private List<Transicao> transicoes;
    private String estadoInicial;
    private List<String> estadoFinal;
    private String simboloVazio;

    //Construtor da Máquina de Turing
    public MaquinaTuring(
        List<String> estado,
        List<String> alfabetoEntrada,
        List<String> alfabetoFita,
        List<Transicao> transicoes,
        String estadoInicial,
        List<String> estadoFinal,
        String simboloVazio
    ) {
        this.estado = estado;
        this.alfabetoEntrada = alfabetoEntrada;
        this.alfabetoFita = alfabetoFita;
        this.transicoes = transicoes;
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.simboloVazio = simboloVazio;
    }

    // Métodos Get que fornecem acesso aos atributos privados da Máquina de Turing
    public List<String> getEstado() {
        return estado;
    }

    public List<String> getAlfabetoEntrada() {
        return alfabetoEntrada;
    }

    public List<String> getAlfabetoFita() {
        return alfabetoFita;
    }

    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public List<String> getEstadoFinal() {
        return estadoFinal;
    }

    public String getSimboloVazio() {
        return simboloVazio;
    }
}
