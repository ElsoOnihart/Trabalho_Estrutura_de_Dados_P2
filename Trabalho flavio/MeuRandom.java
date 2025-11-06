public class MeuRandom {
    private long seed;

    public MeuRandom() {
        this.seed = System.nanoTime(); 
    }
    
    private int next() {
        seed = (seed * 25214903917L + 11L) & (281474976710655L);
        return (int)(seed >> 16);
    }

    public int nextInt(int bound) {
        int r = next();
        r = (r < 0) ? -r : r;
        return r % bound;
    }
}