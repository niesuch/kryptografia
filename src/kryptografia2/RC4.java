package kryptografia2;

public class RC4 {
    private char[] klucz;
    private int[] tab;

    /**
     * Kontruktor parametryczny klasy RC4
     * @param klucz 
     */
    public RC4(String klucz) {
        this.klucz = klucz.toCharArray();
    }

    /**
     * Deszyfrowanie tekstu
     * @param tekst
     * @return 
     */
    public char[] deszyfrowanie(final char[] tekst) {
        return szyfrowanie(tekst);
    }

    /**
     * Szyfrowanie tekstu
     * @param tekst
     * @return 
     */
    public char[] szyfrowanie(final char[] tekst) {
        tab = init(klucz);
        char[] szyfrowanie = new char[tekst.length];
        int i = 0;
        int j = 0;
        for (int n = 0; n < tekst.length; n++) {
            i = (i + 1) % 256;
            j = (j + tab[i]) % 256;
            zamienElTab(i, j, tab);
            int rand = tab[(tab[i] + tab[j]) % 256];
            szyfrowanie[n] = (char) (rand ^ (int) tekst[n]);
        }
        return szyfrowanie;
    }

    /**
     * Inicjalizacja klucza
     * @param klucz
     * @return 
     */
    private int[] init(char[] klucz) {
        int[] tab = new int[256];
        int j = 0;

        for (int i = 0; i < 256; i++)
            tab[i] = i;

        for (int i = 0; i < 256; i++) {
            j = (j + tab[i] + klucz[i % klucz.length]) % 256;
            zamienElTab(i, j, tab);
        }
        
        return tab;
    }

    /**
     * Swapowanie elementów tablicy
     * @param i
     * @param j
     * @param tab 
     */
    private void zamienElTab(int i, int j, int[] tab) {
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }
}
