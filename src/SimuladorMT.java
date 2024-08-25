import java.util.ArrayList;
import java.util.List;

//Simulador para uma Máquina de Turing
public class SimuladorMT {

    private List<Configuracao> transicoesFita = new ArrayList<>();//Lista que armazena as configurações da fita
    private MaquinaTuring maquinaTuring; //Máquina de Turing atual

    //Construtor que inicializa um simulador de uma máquina de Turing específica.
    public SimuladorMT(MaquinaTuring maquinaTuring) {
        this.maquinaTuring = maquinaTuring;
    }

    //Método que recebe uma entrada inicia uma simulação
    public void processarEntrada(String entradaFita) {
        Configuracao configuracaoFita = new Configuracao(maquinaTuring.getSimboloVazio(), entradaFita + maquinaTuring.getSimboloVazio(), maquinaTuring.getEstadoInicial());

        imprimeConfiguracaoFita(configuracaoFita);
        String estadoFinal = executaMaquinaTuring(configuracaoFita);
        while (!ehFinal(estadoFinal)) {
            if (transicoesFita.isEmpty()) {
                System.out.println("Rejeita a palavra - Sem mais transições possíveis");
                break;
            }
            estadoFinal = executaMaquinaTuring(transicoesFita.remove(0));
        }
    }

    //Método que verifica se o estado  em que a máquina parou está na lista de estados finais
    private boolean ehFinal(String estadoFinal) {
        if (maquinaTuring.getEstadoFinal() != null) {
            if (maquinaTuring.getEstadoFinal().contains(estadoFinal)) {
                System.out.println("Aceita a palavra");
                return true;
            } else {
                System.out.println("Rejeita a palavra");
                return false;
            }
        }
        return false;
    }

    //Método que executa a máquina de Turing usando a configuração da fita fornecida.
    private String executaMaquinaTuring(Configuracao configuracaoFita) {
        // Realiza a transição com base na configuração
        if (configuracaoFita.getTransicaoAtual() == null) {
            configuracaoFita.setTransicaoAtual(selecionaTransicao(configuracaoFita));
        }
        // Aplica as transições enquanto elas existirem, atualizando a configuração da fita e movendo a cabeça de leitura/escrita.
        while (configuracaoFita.getTransicaoAtual() != null) {
            configuracaoFita = aplicaMovimento(configuracaoFita);
            imprimeConfiguracaoFita(configuracaoFita);
            configuracaoFita.setTransicaoAtual(selecionaTransicao(configuracaoFita));
        }
        // Retorna o estado final em que a máquina parou
        return configuracaoFita.getEstadoAtual();
    }
  
    //Imprime a configuração atual da fita
    private void imprimeConfiguracaoFita(Configuracao configuracaoFita) {
        System.out.println(configuracaoFita.getFitaEsquerda() + " " +
                configuracaoFita.getEstadoAtual() + " " +
                configuracaoFita.getFitaDireita());
    }

    //Seleciona a transição com base no estado atual e no símbolo lido na fita.
    private Transicao selecionaTransicao(Configuracao configuracaoFita) {
        String simboloLido = getSimboloLido(configuracaoFita.getFitaDireita());
        String estadoAtual = configuracaoFita.getEstadoAtual();

        System.out.println("Simbolo lido:" + simboloLido);
        System.out.println("Estado atual:" + estadoAtual);

        List<Transicao> transicoesPossíveis = new ArrayList<>();
        
        //Procura na lista de transições e retorna a primeira transição correspondente.
        for (Transicao transition : maquinaTuring.getTransicoes()) {
            if (transition.getEstadoAtual().equals(configuracaoFita.getEstadoAtual()) &&
                transition.getSimboloLido().equals(simboloLido)) {
                transicoesPossíveis.add(transition);                               
            }
        }
        //Se houver mais de uma transição válida (não determinístico), adiciona as transições alternativas à lista.
        if (transicoesPossíveis.size() > 1) {
            transicaoNaoDeterministica(transicoesPossíveis.subList(1, transicoesPossíveis.size()), configuracaoFita);
        }
        return transicoesPossíveis.isEmpty() ? null : transicoesPossíveis.get(0);
    }

    //Adiciona as transições não determinísticas à lista 
    private void transicaoNaoDeterministica(List<Transicao> transicaoNaoDeterministica, Configuracao configuracaoFita) {
        for (Transicao transition : transicaoNaoDeterministica) {
            Configuracao novaConfig = new Configuracao(configuracaoFita);
            novaConfig.setTransicaoAtual(transition);
            transicoesFita.add(novaConfig);
        }
    }

    //Retorna o símbolo lido pela cabeça de leitura/escritaa.
    private String getSimboloLido(String fitaDiereita) {
        return fitaDiereita.isEmpty() ? "" : String.valueOf(fitaDiereita.charAt(0));
    }

    //Aplica o movimento da cabeça de leitura/escrita baseado na transição atual.
    private Configuracao aplicaMovimento(Configuracao configuracaoFita) {
        Transicao transicaoAtual = configuracaoFita.getTransicaoAtual();
        Configuracao novaConfiguracao = new Configuracao(configuracaoFita);
        switch (transicaoAtual.getMovimento()) {
            case "D":
                novaConfiguracao.setEstadoAtual(transicaoAtual.getNovoEstado());// Atualiza o estado atual da máquina de Turing para o novo estado definido pela transição    
                return movimentoDireita(novaConfiguracao);// Retorna a configuração da fita após o movimento
            case "E":
                novaConfiguracao.setEstadoAtual(transicaoAtual.getNovoEstado());// Atualiza o estado atual da máquina de Turing para o novo estado definido pela transição
                return movimentoEsquerda(novaConfiguracao);// Retorna a configuração da fita após o movimento
            case "P":
                novaConfiguracao.setEstadoAtual(transicaoAtual.getNovoEstado());// Atualiza o estado atual da máquina de Turing para o novo estado definido pela transição
                return movimentoEstatico(novaConfiguracao);// Retorna a configuração da fita após o movimento
            default:
                // Retorna a configuração da fita após o movimento
                return novaConfiguracao;              
            }
        
    }     

    //Move a cabeça de leitura/escrita para a direita
    private Configuracao movimentoDireita(Configuracao configuracaoFita) {
        configuracaoFita.setFitaEsquerda(configuracaoFita.getFitaEsquerda() + configuracaoFita.getTransicaoAtual().getNovoSimbolo());
        configuracaoFita.setFitaDireita(configuracaoFita.getFitaDireita().substring(1));
        if (configuracaoFita.getFitaDireita().length() == 0) {
            configuracaoFita.setFitaDireita(maquinaTuring.getSimboloVazio());           
        }
        return configuracaoFita;
    }

    //Move a cabeça de leitura/escrita para a esquerda
    private Configuracao movimentoEsquerda(Configuracao configuracaoFita) {
        if (configuracaoFita.getFitaEsquerda().length() == 0)  {
            return null;  // não deixa quebrar a fita se estiver vazia
        }
        configuracaoFita.setFitaDireita(
                configuracaoFita.getFitaEsquerda().substring(configuracaoFita.getFitaEsquerda().length() - 1) +
                configuracaoFita.getTransicaoAtual().getNovoSimbolo() +
                configuracaoFita.getFitaDireita().substring(1)
        );
        configuracaoFita.setFitaEsquerda(configuracaoFita.getFitaEsquerda().substring(0, configuracaoFita.getFitaEsquerda().length() - 1));
        return configuracaoFita;
    }

    //Não move a cabeça de leitura/escrita devido ao movimento estático, mas atualiza o símbolo atual
    private Configuracao movimentoEstatico(Configuracao configuracaoFita) {
        configuracaoFita.setFitaDireita(
                configuracaoFita.getTransicaoAtual().getNovoSimbolo() +
                configuracaoFita.getFitaDireita().substring(1)
        );
        return configuracaoFita;
    }
}
