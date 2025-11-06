public class AnalisadorDesempenho {

    private static final int NUM_EXECUCOES = 5;
    private static final int[] TAMANHOS = {100, 1000, 10000};
    private static final MeuRandom random = new MeuRandom();

    public static void main(String[] args) {
        System.out.println("Iniciando Analise de Desempenho...");
        System.out.println("Execucoes por cenario: " + NUM_EXECUCOES + "\n");

        executarTestesDeInsercao();
        executarTestesDeBusca();
        executarTestesDeOrdenacao();
    }

    private static void executarTestesDeInsercao() {
        System.out.println("--- TEMPOS DE INSERCAO (em milissegundos) ---");
        System.out.printf("%-20s | %-15s | %-15s | %-15s%n", 
            "Cenario (Tam/Ordem)", "Vetor", "ABB", "AVL");
        System.out.println(stringRepeat("-", 70));

        for (int tam : TAMANHOS) {
            String[] ordens = {"Ordenada", "Inversa", "Aleatoria"};
            for (String ordem : ordens) {
                long totalVetor = 0, totalABB = 0, totalAVL = 0;
                
                for (int i = 0; i < NUM_EXECUCOES; i++) {
                    int[] dados = gerarDados(ordem, tam);
                    long inicio, fim;

                    Vetor vetor = new Vetor(tam);
                    inicio = System.nanoTime();
                    for (int val : dados) vetor.inserir(val);
                    fim = System.nanoTime();
                    totalVetor += (fim - inicio);

                    ArvoreBinariaBusca abb = new ArvoreBinariaBusca();
                    inicio = System.nanoTime();
                    for (int val : dados) abb.inserir(val);
                    fim = System.nanoTime();
                    totalABB += (fim - inicio);

                    ArvoreAVL avl = new ArvoreAVL();
                    inicio = System.nanoTime();
                    for (int val : dados) avl.inserir(val);
                    fim = System.nanoTime();
                    totalAVL += (fim - inicio);
                }
                
                String cenario = tam + "/" + ordem;
                System.out.printf("%-20s | %-15.6f | %-15.6f | %-15.6f%n",
                        cenario,
                        nanoParaMs(totalVetor / NUM_EXECUCOES),
                        nanoParaMs(totalABB / NUM_EXECUCOES),
                        nanoParaMs(totalAVL / NUM_EXECUCOES));
            }
        }
    }
    
    private static void executarTestesDeBusca() {
        System.out.println("\n--- TEMPOS DE BUSCA (em milissegundos) ---");
        System.out.printf("%-20s | %-15s | %-15s | %-15s%n", 
            "Cenario (Tam/Ordem)", "Vetor (Seq)", "ABB", "AVL");
        System.out.println(stringRepeat("-", 70));
        
        for (int tam : TAMANHOS) {
            String[] ordens = {"Ordenada", "Inversa", "Aleatoria"};
            for (String ordem : ordens) {
                long totalVetor = 0, totalABB = 0, totalAVL = 0;

                for (int i = 0; i < NUM_EXECUCOES; i++) {
                    int[] dados = gerarDados(ordem, tam);
                    Vetor vetor = new Vetor(tam);
                    ArvoreBinariaBusca abb = new ArvoreBinariaBusca();
                    ArvoreAVL avl = new ArvoreAVL();
                    
                    for (int val : dados) {
                        vetor.inserir(val);
                        abb.inserir(val);
                        avl.inserir(val);
                    }
                    
                    int[] elementosBusca = getElementosParaBuscar(dados);
                    long inicio, fim;
                    
                    inicio = System.nanoTime();
                    for (int el : elementosBusca) vetor.buscaSequencial(el);
                    fim = System.nanoTime();
                    totalVetor += (fim - inicio);

                    inicio = System.nanoTime();
                    for (int el : elementosBusca) abb.buscar(el);
                    fim = System.nanoTime();
                    totalABB += (fim - inicio);
                    
                    inicio = System.nanoTime();
                    for (int el : elementosBusca) avl.buscar(el);
                    fim = System.nanoTime();
                    totalAVL += (fim - inicio);
                }
                
                String cenario = tam + "/" + ordem;
                System.out.printf("%-20s | %-15.6f | %-15.6f | %-15.6f%n",
                        cenario,
                        nanoParaMs(totalVetor / NUM_EXECUCOES),
                        nanoParaMs(totalABB / NUM_EXECUCOES),
                        nanoParaMs(totalAVL / NUM_EXECUCOES));
            }
        }
    }
    
    private static void executarTestesDeOrdenacao() {
        System.out.println("\n--- TEMPOS DE ORDENAÇÃO (em milissegundos) ---");
        System.out.printf("%-20s | %-15s | %-15s%n", 
            "Cenário (Tam/Ordem)", "Insertion Sort", "Merge Sort");
        System.out.println(stringRepeat("-", 55));
        
        for (int tam : TAMANHOS) {
            String[] ordens = {"Ordenada", "Inversa", "Aleatoria"};
            for (String ordem : ordens) {
                long totalInsertion = 0, totalMerge = 0;

                for (int i = 0; i < NUM_EXECUCOES; i++) {
                    int[] dados = gerarDados(ordem, tam);
                    long inicio, fim;

                    Vetor vInsertion = new Vetor(dados);
                    inicio = System.nanoTime();
                    vInsertion.insertionSort();
                    fim = System.nanoTime();
                    totalInsertion += (fim - inicio);

                    Vetor vMerge = new Vetor(dados);
                    inicio = System.nanoTime();
                    vMerge.mergeSort();
                    fim = System.nanoTime();
                    totalMerge += (fim - inicio);
                }
                
                String cenario = tam + "/" + ordem;
                System.out.printf("%-20s | %-15.6f | %-15.6f%n",
                        cenario,
                        nanoParaMs(totalInsertion / NUM_EXECUCOES),
                        nanoParaMs(totalMerge / NUM_EXECUCOES));
            }
        }
    }

    private static int[] gerarDados(String ordem, int tamanho) {
        if (ordem.equals("Ordenada")) return GeradorDados.gerarDadosOrdenados(tamanho);
        if (ordem.equals("Inversa")) return GeradorDados.gerarDadosInversamenteOrdenados(tamanho);
        return GeradorDados.gerarDadosAleatorios(tamanho);
    }

    private static int[] getElementosParaBuscar(int[] dados) {
        int tamanho = dados.length;
        int[] elementos = new int[7]; 
        elementos[0] = dados[0];
        elementos[1] = dados[tamanho - 1];
        elementos[2] = dados[tamanho / 2];
        elementos[3] = dados[random.nextInt(tamanho)];
        elementos[4] = dados[random.nextInt(tamanho)];
        elementos[5] = dados[random.nextInt(tamanho)];
        elementos[6] = tamanho + 10; 
        return elementos;
    }

    private static double nanoParaMs(long nanossegundos) {
        return (double) nanossegundos / 1000000.0;
    }
    
    private static String stringRepeat(String s, int n) {
        String r = "";
        for (int i=0; i < n; i++) r += s;
        return r;
    }
}