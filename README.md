# Trabalho_Estrutura_de_Dados_P2

Este projeto implementa e compara o desempenho (inserção, busca e ordenação) de três estruturas de dados: Vetor, Árvore Binária de Busca (ABB) e Árvore AVL.

## Estrutura de Arquivos

* `Trabalho Flavio` (Pasta dos Codigos)
    * `AnalisadorDesempenho.java`: Classe principal que executa a metodologia de testes.
    * `Vetor.java`: Implementação do vetor dinâmico e algoritmos (Insertion/Merge Sort, Busca Seq/Binária).
    * `ArvoreBinariaBusca.java`: Implementação da ABB.
    * `ArvoreAVL.java`: Implementação da Árvore AVL (com rotações).
    * `GeradorDados.java`: Classe utilitária para gerar os conjuntos de dados (ordenados, inversos, aleatórios).
    * `MeuRandom.java`: Implementação de um gerador pseudo-aleatório simples (para cumprir o requisito de "não usar bibliotecas").

## Como Compilar e Executar (Usando um IDE)

Este projeto consiste em arquivos `.java` independentes e não requer bibliotecas externas.

### 1. Preparação

* **Abra os arquivos:** Adicione todos os 6 arquivos `.java` ao seu projeto no seu IDE (como jGRASP, Eclipse, IntelliJ, etc.).
* **Certifique-se** de que todos os arquivos estejam na mesma pasta ou pacote (package).

### 2. Compilação

* **Compilação Automática:** A maioria dos IDEs compila os arquivos automaticamente quando você os salva ou tenta executá-los.
* **Compilação Manual (se necessário):**
    * No **jGRASP**, por exemplo, você pode abrir o `AnalisadorDesempenho.java` e clicar no botão "Compile" (ou `Build > Compile`). O IDE encontrará e compilará os outros arquivos dos quais ele depende.

### 3. Execução

1.  Abra o arquivo que contém o método `main`: `AnalisadorDesempenho.java`.
2.  Encontre e clique no botão **"Run"** (Executar) do seu IDE (geralmente um ícone de "play" verde).
    * No **jGRASP**, você pode simplesmente clicar no botão "Run".

O programa iniciará os testes e imprimirá os resultados das tabelas de desempenho (Inserção, Busca e Ordenação) no console ou na janela de saída do seu IDE.
