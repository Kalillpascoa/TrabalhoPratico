## GCC108 – TEORIA DA COMPUTAÇÃO - 14A - 2021/1
## Kalill José Viana da Páscoa - 202120153
## Trabalho Prático - Desenvolviemnto de um Simulador de Máquina de Turing

Objetivo desse trabalho foi implementar um simulador de MT que recebe uma descrição e uma fita de entrada e processa o comportamento da máquina, aceitando ou não a palavra de entrada.

## Relatório técnico: Estrutura do código

O código foi desenvolvido em Java e está difvidido em 5 classes, descritas a seguir:

1- Classe App:
    Controla a execução de diferentes Máquinas de Turing baseadas em uma escolha feita pelo usuário. Ela serve como ponto de entrada do programa, gerencia a interação com o usuário, exibe um menu de opções e executa a MT correspondente à opção selecionada. 

    * Método main instancia a classe App e invoca o método executar, que inicia a execução do programa.
    * Método executar exibe um menu de opções e solicita ao usuário que escolha qual MT deseja executar através de um objeto Scanner. A opção escolhida é processada pelo método tratarOpcao e o processo continua em um loop até que o usuário escolha a opção de sair (opção 5).
    * Método exibirMenu exibe as opções disponíveis para o usuário no terminal. Cada opção corresponde a um caso de teste diferente envolvendo uma MT.
        Opções Disponíveis:
        A-MT que aceita palavras formadas por infinitos as seguidos por um único b.
        B-MT que troca a por b e vice-versa.
        C-MT não determinística que aceita palavras formadas por a e b terminadas com a.
        D-MT que mantém balanceado o número de as, bs e cs na fita.
        E-Sair.
    * Método tratar opção recebe a opção escolhida pelo usuário e através de um bloco switch-case chama o método correspondente que executa a MT relacionada àquela opção.
    * Métodos de Execução de Casos de Teste (executarCaso1, executarCaso2, executarCaso3, executarCaso4) instancia e executa uma MT específica, processando uma palavra de entrada e simulando o comportamento da máquina conforme a descrição da MT fornecida.
    *  Descrição das Máquinas de Turing é formada pelos:
        a-Estados pelos quais a máquina transita durante o processamento da palavra.
        b-Alfabeto de Entrada: Conjunto de símbolos que a MT pode ler.
        c-Alfabeto da Fita: Conjunto de símbolos que a MT pode escrever na fita.
        d-Transições: Comportamento da MT, especificando como a máquina deve transitar entre os estados ao ler determinados símbolos.
        e-Estado Inicial: O estado em que a MT começa a execução.
        f-Estados Finais: Estados em que a MT pode parar e aceitar a palavra.
        g-Símbolo Branco: Representa uma célula vazia na fita.

2- Classe MaquinaTuring
    Define a estrutura de uma Máquina de Turing (MT), com seus atributos e métodos de acesso para cada um deles.

3- Configuração
    Representa uma configuração específica de uma Máquina de Turing em um determinado momento durante sua execução. Esta configuração inclui o estado atual da MT e o conteúdo das fitas à esquerda e à direita da cabeça de leitura/escrita. Durante a execução da MT, a Configuracao é continuamente atualizada para refletir as mudanças nas fitas e no estado. Isso permite que o simulador da MT acompanhe o progresso da máquina e tome decisões baseadas no estado atual e no símbolo lido, conforme definido nas transições.

4- Transição
    Representa uma transição de estado em uma Máquina de Turing com todos os detalhes necessários para definir como a MT deve mudar de estado com base no símbolo que ela lê na fita e as ações subsequentes. 

5 - SimuladorMT
    Implementa a lógica de um simulador de Máquina de Turing 
    * Atributos:
        a-transicoesFita: Uma lista de configurações (Configuracao) que armazena os diferentes estados da fita durante a execução.
        b-maquinaTuring: Uma MaquinaTuring, que contém a definição da MT (estados, alfabeto, transições, etc.).
    * Método processarEntrada recebe uma fita de entrada e inicia a simulação da MT. Cria uma configuração inicial com a fita e o estado inicial da máquina e executa a MT até que não haja mais transições possíveis ou até que um estado final seja alcançado.
    * Método ehFinal verifica se o estado atual é um dos estados finais definidos na MaquinaTuring. Se sim, a palavra (entrada) é aceita; caso contrário, é rejeitada.
    * Método executaMaquinaTuring onde a execução real da MT ocorre, aplica as transições à configuração da fita até que nenhuma transição válida esteja disponível, retorna o estado final da MT após a execução.
    * Método imprimeConfiguracaoFita imprime a configuração atual da fita, mostrando o estado atual, a parte esquerda e direita da fita. Isso ajuda a visualizar o progresso da MT durante a simulação e compreender e analisar seu funcionamento
    * Método selecionaTransicao seleciona uma transição válida com base no estado atual e no símbolo lido pela cabeça de leitura/escrita. Se mais de uma transição for possível (não-determinismo), as transições alternativas são armazenadas para exploração futura.
    * Método transicaoNaoDeterministica lida com o não-determinismo, onde mais de uma transição válida pode ser aplicada. Ele cria novas configurações de fita para cada transição alternativa e as adiciona à lista de transicoesFita processando ambas.
    * Método getSimboloLido retorna o símbolo sob a cabeça de leitura/escrita, que é o primeiro caractere da fitaDireita.
    * Método aplicaMovimento aplica o movimento da cabeça de leitura/escrita (esquerda, direita ou estático) de acordo com a transição atual e retorna a nova configuração da fita.
    * Método movimentoDireita, movimentoEsquerda e movimentoEstatico move a cabeça de leitura/escrita para a direita, esquerda ou a mantém parada, atualizando a fitaEsquerda e a 

Assim, o simulador imprime ao final do progresso a fita e determina se a palavra foi aceita ou rejeitada com base nos estados finais.

Um dos principais desafios enfrentados na implementação foi o controle do estado atual da Máquina de Turing durante a simulação. Como a máquina pode transitar entre diferentes estados com base nas entradas lidas na fita, era crucial garantir que cada transição fosse corretamente aplicada e que o estado atual fosse atualizado de forma precisa.

## Manual do usuário

Iniciando o Programa
    Compilação e Execução: compile o código Java usuando ou o terminal ou o editor de código de sua preferência.

Menu Principal
    Após iniciar o programa, você verá um menu com as seguintes opções no terminal de execução com as seguintes opções para serem escolhidas:
    1- MT que aceita palavras formadas por infinitos a's seguido por um único b
    2- MT que troca a por b e vice-versa
    3- MT não determinística que aceita palavras formadas por a e b terminadas com a
    4- MT que mantém balanceado o número de a's, b's e c's na fita
    5- Sair   

Digite a opção a ser executada e pressione ENTER

Processamento da Entrada:
    O sistema exige que você fornecer uma palavra de entrada específica para o caso de teste selecionado. Caso queira alterar a entrada a ser testada é necessário fazer essa alteração diretamente no código dentro da função executarCasoX(), da mesma forma alterações na configuração da máquina (função de transição, estados finais e iniciais, alfabeto) precisam ser realizadas nessa parte do código

Visualização dos Resultados:
    O simulador exibirá no terminal o estado atual da fita (fita a esquerda e direta da cabeça de leitura, estado atual e simbolo lido) e o estado da máquina após cada transição. A simulação continuará até que a máquina atinja um estado final ou não haja mais transições possíveis.

Interpretação dos Resultados:
    A saída mostrará se a palavra foi aceita ou rejeitada com base na configuração da Máquina de Turing e na entrada fornecida. 

    (Atenção: Como o menu é exibido automaticamente ao final do processamento da MT, explore o terminal pois a saida com o resultado final estará logo acima do menu)

## Casos de testes
1. Máquina de Turing que Aceita Palavras Formadas por Infinitos 'a's Seguidos por um Único 'b'
Descrição do Caso de Teste: Este teste verifica se a Máquina de Turing aceita palavras que consistem em qualquer número de 'a's seguidos por exatamente um 'b'. A máquina deve rejeitar palavras que não se encaixam neste padrão, como palavras com múltiplos 'b's ou sem 'b' no final.

Máquina Avaliada: A Máquina de Turing (MT) neste caso possui as seguintes características:

Estados: Definidos para identificar e aceitar a condição correta.
Alfabeto de Entrada: {'a', 'b'}
Alfabeto da Fita: {'a', 'b', ''} (onde '' representa o símbolo vazio)
Transições: Configuradas para mover a cabeça de leitura/escrita para a direita enquanto lê 'a' e verificar se há exatamente um 'b' no final.

Resultado:
Entrada Válida (ex. aaaab): Aceita, pois a palavra segue o padrão de infinitos 'a's seguidos por um único 'b'.
Entrada Inválida (ex. aaaabb ou aab): Rejeitada, pois não segue o padrão especificado.


2. Máquina de Turing que Troca 'a' por 'b' e Vice-Versa
Descrição do Caso de Teste: Este teste verifica a capacidade da Máquina de Turing de substituir cada ocorrência de 'a' por 'b' e cada 'b' por 'a'. A máquina deve processar a fita e produzir a saída com os caracteres trocados.

Máquina Avaliada: A Máquina de Turing neste caso possui:

Estados: Definidos para realizar a troca de símbolos.
Alfabeto de Entrada: {'a', 'b'}
Alfabeto da Fita: {'a', 'b', '_'}
Transições: Configuradas para substituir 'a' por 'b' e 'b' por 'a', movendo a cabeça conforme necessário.

Resultado :
Entrada (ex. aabbb): Saída é bbaaa.

3. Máquina de Turing Não Determinística que Aceita Palavras Formadas por 'a' e 'b' Terminadas com 'a'
Descrição do Caso de Teste: Este teste avalia a capacidade da Máquina de Turing não determinística de aceitar palavras compostas por 'a's e 'b's, desde que a palavra termine com 'a'. O comportamento não determinístico deve ser tratado adequadamente.

Máquina Avaliada: A Máquina de Turing neste caso é:

Estados: Incluem estados para aceitar palavras que terminam com 'a'.
Alfabeto de Entrada: {'a', 'b'}
Alfabeto da Fita: {'a', 'b', '_'}
Transições: Configuradas para aceitar qualquer palavra que termine com 'a', permitindo diferentes caminhos para chegar a esse estado final.

Resultado:
Entrada Válida (ex. aaabbba): Aceita, pois termina com 'a'.
Entrada Inválida (ex. aaabb ou bbb): Rejeitada, pois não termina com 'a'.


4. Máquina de Turing que Mantém Balanceado o Número de 'a's, 'b's e 'c's na Fita
Descrição do Caso de Teste: Este teste verifica a capacidade da Máquina de Turing de garantir que o número de 'a's, 'b's e 'c's na fita esteja balanceado. A máquina deve aceitar palavras com o mesmo número de cada símbolo e rejeitar as demais.

Máquina Avaliada: A Máquina de Turing neste caso possui:

Estados: Projetados para contar e comparar o número de 'a's, 'b's e 'c's.
Alfabeto de Entrada: {'a', 'b', 'c'}
Alfabeto da Fita: {'a', 'b', 'c', '_'}
Transições: Configuradas para contar e verificar se o número de 'a's, 'b's e 'c's é igual.

Resultado:
Entrada Válida (ex. aaabbbccc): Aceita, pois o número de 'a's, 'b's e 'c's está balanceado.
Entrada Inválida (ex. aaabbbccc ou aaaabbb): Rejeitada, pois os números de 'a's, 'b's e 'c's não estão balanceados.

De forma geral, para os testes executados a máquina foi capaz de respodner de forma esperada a cada uma das entradas