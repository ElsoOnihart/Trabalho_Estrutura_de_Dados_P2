public class Vetor {
    private int[] dados;
    private int tamanho;
    private int capacidade;

    public Vetor(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.dados = new int[capacidadeInicial];
        this.tamanho = 0;
    }

    public Vetor(int[] arrayInicial) {
        this.tamanho = arrayInicial.length;
        this.capacidade = arrayInicial.length;
        this.dados = new int[this.capacidade];
        for (int i = 0; i < this.tamanho; i++) {
            this.dados[i] = arrayInicial[i];
        }
    }

    public void inserir(int valor) {
        if (tamanho == capacidade) {
            capacidade = (capacidade == 0) ? 1 : capacidade * 2;
            int[] novoArray = new int[capacidade];
            for (int i = 0; i < tamanho; i++) {
                novoArray[i] = dados[i];
            }
            dados = novoArray;
        }
        dados[tamanho] = valor;
        tamanho++;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int[] getDados() {
        int[] copia = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            copia[i] = dados[i];
        }
        return copia;
    }

    public boolean buscaSequencial(int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (dados[i] == valor) {
                return true;
            }
        }
        return false;
    }

    public boolean buscaBinaria(int valor) {
        int esquerda = 0;
        int direita = tamanho - 1;
        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            if (dados[meio] == valor) {
                return true;
            }
            if (dados[meio] < valor) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return false;
    }

    public void insertionSort() {
        for (int i = 1; i < tamanho; i++) {
            int chave = dados[i];
            int j = i - 1;
            while (j >= 0 && dados[j] > chave) {
                dados[j + 1] = dados[j];
                j = j - 1;
            }
            dados[j + 1] = chave;
        }
    }

    public void mergeSort() {
        if (tamanho < 2) {
            return;
        }
        mergeSortRecursivo(dados, tamanho);
    }

    private void mergeSortRecursivo(int[] array, int n) {
        if (n < 2) {
            return;
        }
        int meio = n / 2;
        int[] esquerda = new int[meio];
        int[] direita = new int[n - meio];

        for (int i = 0; i < meio; i++) {
            esquerda[i] = array[i];
        }
        for (int i = meio; i < n; i++) {
            direita[i - meio] = array[i];
        }

        mergeSortRecursivo(esquerda, meio);
        mergeSortRecursivo(direita, n - meio);
        merge(array, esquerda, direita, meio, n - meio);
    }

    private void merge(int[] array, int[] esq, int[] dir, int tamEsq, int tamDir) {
        int i = 0, j = 0, k = 0;
        while (i < tamEsq && j < tamDir) {
            if (esq[i] <= dir[j]) {
                array[k++] = esq[i++];
            } else {
                array[k++] = dir[j++];
            }
        }
        while (i < tamEsq) {
            array[k++] = esq[i++];
        }
        while (j < tamDir) {
            array[k++] = dir[j++];
        }
    }
}