public class ArvoreAVL {
    private class NoAVL {
        int valor;
        int altura;
        NoAVL esquerda;
        NoAVL direita;

        NoAVL(int valor) {
            this.valor = valor;
            this.altura = 1;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private NoAVL raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int altura(NoAVL no) {
        return (no == null) ? 0 : no.altura;
    }

    private void atualizarAltura(NoAVL no) {
        if (no != null) {
            no.altura = 1 + max(altura(no.esquerda), altura(no.direita));
        }
    }

    private int getFatorBalanceamento(NoAVL no) {
        return (no == null) ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        atualizarAltura(x);
        atualizarAltura(y);
        return y;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private NoAVL inserirRecursivo(NoAVL no, int valor) {
        if (no == null) {
            return new NoAVL(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
        } else {
            return no;
        }

        atualizarAltura(no);
        int fb = getFatorBalanceamento(no);

        if (fb > 1 && valor < no.esquerda.valor) {
            return rotacaoDireita(no);
        }
        if (fb < -1 && valor > no.direita.valor) {
            return rotacaoEsquerda(no);
        }
        if (fb > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (fb < -1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(NoAVL noAtual, int valor) {
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