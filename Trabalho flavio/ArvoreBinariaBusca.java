public class ArvoreBinariaBusca {
    private class No {
        int valor;
        No esquerda;
        No direita;

           No(int valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private No raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No noAtual, int valor) {
        if (noAtual == null) {
            return new No(valor);
        }
        if (valor < noAtual.valor) {
            noAtual.esquerda = inserirRecursivo(noAtual.esquerda, valor);
        } else if (valor > noAtual.valor) {
            noAtual.direita = inserirRecursivo(noAtual.direita, valor);
        }
        return noAtual;
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(No noAtual, int valor) {
        if (noAtual == null) {
            return false;
        }
        if (valor == noAtual.valor) {
            return true;
        }
        return valor < noAtual.valor
            ? buscarRecursivo(noAtual.esquerda, valor)
            : buscarRecursivo(noAtual.direita, valor);
    }
}