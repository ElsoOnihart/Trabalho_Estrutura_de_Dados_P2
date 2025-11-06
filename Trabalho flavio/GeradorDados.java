public class GeradorDados {
    
    private static MeuRandom rand = new MeuRandom();

    public static int[] gerarDadosOrdenados(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = i + 1;
        }
        return dados;
    }

    public static int[] gerarDadosInversamenteOrdenados(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = tamanho - i;
        }
        return dados;
    }

    public static int[] gerarDadosAleatorios(int tamanho) {
        int[] dados = gerarDadosOrdenados(tamanho);
        
        for (int i = tamanho - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            
            int temp = dados[i];
            dados[i] = dados[j];
            dados[j] = temp;
        }
        return dados;
    }
}