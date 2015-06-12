package kryptografia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class Kryptografia extends JFrame {

    private static JTextField kluczWejscie;
    private static JTextArea textWejscie, textStat, textKrypto, textWzorcowa;
    private static JTextPane textWyjscie;
    private JScrollPane scrollText, scrollText2, scrollTextStat, scrollTextKrypto, scrollTextWzorcowa;
    private JButton szyfrujPrzycisk, deszyfrujPrzycisk, kryptoanalizaPrzycisk, archiwumKluczyPrzycisk,
            wczytajPrzycisk, autorzyPrzycisk, zapiszSzyfrPrzycisk, wczytajSzyfrPrzycisk, zamianaMiejscPrzycisk,
            uzupelnijPrzycisk, wczytajWzorcowaPrzycisk, wygenerujWzorcowaPrzycisk, zapiszWzorcowaPrzycisk, zakonczPrzycisk;
    private PrzyciskSzyfruj obslugaSzyfruj;
    private PrzyciskDeszyfruj obslugaDeszyfruj;
    private PrzyciskKryptoanaliza obslugaKryptoanaliza;
    private PrzyciskArchiwumKluczy obslugaArchiwumKluczy;
    private PrzyciskWczytaj obslugaWczytaj;
    private PrzyciskAutorzy obslugaAutorzy;
    private PrzyciskZapiszSzyfr obslugaZapiszSzyfr;
    private PrzyciskWczytajSzyfr obslugaWczytajSzyfr;
    private PrzyciskZamianaMiejsc obslugaZamianaMiejsc;
    private PrzyciskUzupelnij obslugaUzupelnij;
    private PrzyciskWczytajWzorcowa obslugaWczytajWzorcowa;
    private PrzyciskWygenerujWzorcowa obslugaWygenerujWzorcowa;
    private PrzyciskZapiszWzorcowa obslugaZapiszWzorcowa;
    private PrzyciskZakoncz oblugaZakoncz;
    private String kluczKryptoanaliza;

    /**
     * Konstruktor głównej klasy, inicjalizowanie wyglądu programu
     */
    public Kryptografia() {
        Container okno = getContentPane();
        okno.setLayout(null);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Bład podczas ładowania wyglądu okna.");
        }

        this.setResizable(false);
        
        Font czcionka_textarea = new Font("Courier New", Font.BOLD, 13);
        Font czcionka_textarea2 = new Font("Courier New", Font.BOLD, 11);

        textWejscie = new JTextArea("", 50, 895);
        scrollText2 = new JScrollPane(textWejscie);
        scrollText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWejscie.setLineWrap(true);
        textWejscie.setWrapStyleWord(true);
        textWejscie.setFont(czcionka_textarea);
        scrollText2.setLocation(5, 52);
        scrollText2.setSize(400, 180);
        okno.add(scrollText2);

        textStat = new JTextArea("", 800, 50);
        scrollTextStat = new JScrollPane(textStat);
        scrollTextStat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textStat.setLineWrap(true);
        textStat.setWrapStyleWord(true);
        textStat.setEditable(false);
        textStat.setFont(czcionka_textarea2);
        scrollTextStat.setLocation(410, 52);
        scrollTextStat.setSize(180, 447);
        okno.add(scrollTextStat);

        textKrypto = new JTextArea("", 800, 50);
        scrollTextKrypto = new JScrollPane(textKrypto);
        scrollTextKrypto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textKrypto.setLineWrap(true);
        textKrypto.setWrapStyleWord(true);
        textKrypto.setFont(czcionka_textarea);
        scrollTextKrypto.setLocation(780, 52);
        scrollTextKrypto.setSize(180, 447);
        okno.add(scrollTextKrypto);

        textWzorcowa = new JTextArea("", 800, 50);
        scrollTextWzorcowa = new JScrollPane(textWzorcowa);
        scrollTextWzorcowa.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWzorcowa.setLineWrap(true);
        textWzorcowa.setWrapStyleWord(true);
        textWzorcowa.setEditable(false);
        textWzorcowa.setFont(czcionka_textarea2);
        scrollTextWzorcowa.setLocation(595, 52);
        scrollTextWzorcowa.setSize(180, 447);
        okno.add(scrollTextWzorcowa);

        //textWyjscie = new JTextPane(50, 895);
        textWyjscie = new JTextPane();
        textWyjscie.setLocation(50, 895);
        textWyjscie.setFont(czcionka_textarea);
        scrollText = new JScrollPane(textWyjscie);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //textWyjscie.setLineWrap(true);
        //textWyjscie.setWrapStyleWord(false);
        //textWyjscie.setEditable(false);
        scrollText.setLocation(5, 320);
        scrollText.setSize(400, 180);
        okno.add(scrollText);

        kluczWejscie = new JTextField(3);
        kluczWejscie.setLocation(115, 243);
        kluczWejscie.setSize(200, 40);
        kluczWejscie.setFont(czcionka_textarea);
        okno.add(kluczWejscie);

        szyfrujPrzycisk = new JButton("Szyfruj");
        obslugaSzyfruj = new PrzyciskSzyfruj();
        szyfrujPrzycisk.addActionListener(obslugaSzyfruj);
        szyfrujPrzycisk.setLocation(5, 502);
        szyfrujPrzycisk.setSize(150, 20);
        okno.add(szyfrujPrzycisk);

        deszyfrujPrzycisk = new JButton("Deszyfruj");
        obslugaDeszyfruj = new PrzyciskDeszyfruj();
        deszyfrujPrzycisk.addActionListener(obslugaDeszyfruj);
        deszyfrujPrzycisk.setLocation(165, 502);
        deszyfrujPrzycisk.setSize(150, 20);
        okno.add(deszyfrujPrzycisk);

        kryptoanalizaPrzycisk = new JButton("Start Kryptoanaliza");
        obslugaKryptoanaliza = new PrzyciskKryptoanaliza();
        kryptoanalizaPrzycisk.addActionListener(obslugaKryptoanaliza);
        kryptoanalizaPrzycisk.setLocation(485, 502);
        kryptoanalizaPrzycisk.setSize(150, 20);
        okno.add(kryptoanalizaPrzycisk);

        archiwumKluczyPrzycisk = new JButton("Archiwum kluczy");
        obslugaArchiwumKluczy = new PrzyciskArchiwumKluczy();
        archiwumKluczyPrzycisk.addActionListener(obslugaArchiwumKluczy);
        archiwumKluczyPrzycisk.setLocation(325, 502);
        archiwumKluczyPrzycisk.setSize(150, 20);
        okno.add(archiwumKluczyPrzycisk);

        zapiszSzyfrPrzycisk = new JButton("Zapisz szyfrogram");
        obslugaZapiszSzyfr = new PrzyciskZapiszSzyfr();
        zapiszSzyfrPrzycisk.addActionListener(obslugaZapiszSzyfr);
        zapiszSzyfrPrzycisk.setLocation(165, 522);
        zapiszSzyfrPrzycisk.setSize(150, 20);
        okno.add(zapiszSzyfrPrzycisk);

        wczytajSzyfrPrzycisk = new JButton("Wczytaj szyfrogram");
        obslugaWczytajSzyfr = new PrzyciskWczytajSzyfr();
        wczytajSzyfrPrzycisk.addActionListener(obslugaWczytajSzyfr);
        wczytajSzyfrPrzycisk.setLocation(5, 522);
        wczytajSzyfrPrzycisk.setSize(150, 20);
        okno.add(wczytajSzyfrPrzycisk);

        wczytajWzorcowaPrzycisk = new JButton("Wczytaj stat.wzorcową");
        wczytajWzorcowaPrzycisk.setToolTipText("Wczytaj statystykę wzorcową");
        obslugaWczytajWzorcowa = new PrzyciskWczytajWzorcowa();
        wczytajWzorcowaPrzycisk.addActionListener(obslugaWczytajWzorcowa);
        wczytajWzorcowaPrzycisk.setLocation(325, 522);
        wczytajWzorcowaPrzycisk.setSize(150, 20);
        okno.add(wczytajWzorcowaPrzycisk);

        wygenerujWzorcowaPrzycisk = new JButton("Wygeneruj stat.wzorcową");
        wygenerujWzorcowaPrzycisk.setToolTipText("Wygeneruj statystykę wzorcową");
        obslugaWygenerujWzorcowa = new PrzyciskWygenerujWzorcowa();
        wygenerujWzorcowaPrzycisk.addActionListener(obslugaWygenerujWzorcowa);
        wygenerujWzorcowaPrzycisk.setLocation(645, 522);
        wygenerujWzorcowaPrzycisk.setSize(200, 20);
        okno.add(wygenerujWzorcowaPrzycisk);

        zapiszWzorcowaPrzycisk = new JButton("Zapisz stat.wzorcową");
        zapiszWzorcowaPrzycisk.setToolTipText("Zapisz statystykę wzorcową");
        obslugaZapiszWzorcowa = new PrzyciskZapiszWzorcowa();
        zapiszWzorcowaPrzycisk.addActionListener(obslugaZapiszWzorcowa);
        zapiszWzorcowaPrzycisk.setLocation(485, 522);
        zapiszWzorcowaPrzycisk.setSize(150, 20);
        okno.add(zapiszWzorcowaPrzycisk);

        wczytajPrzycisk = new JButton("Wczytaj");
        obslugaWczytaj = new PrzyciskWczytaj();
        wczytajPrzycisk.addActionListener(obslugaWczytaj);
        wczytajPrzycisk.setLocation(320, 243);
        wczytajPrzycisk.setSize(82, 20);
        okno.add(wczytajPrzycisk);

        uzupelnijPrzycisk = new JButton("Uzupełnij");
        obslugaUzupelnij = new PrzyciskUzupelnij();
        uzupelnijPrzycisk.addActionListener(obslugaUzupelnij);
        uzupelnijPrzycisk.setLocation(320, 263);
        uzupelnijPrzycisk.setSize(82, 20);
        okno.add(uzupelnijPrzycisk);

        autorzyPrzycisk = new JButton("Autorzy");
        obslugaAutorzy = new PrzyciskAutorzy();
        autorzyPrzycisk.addActionListener(obslugaAutorzy);
        autorzyPrzycisk.setLocation(880, 5);
        autorzyPrzycisk.setSize(80, 20);
        okno.add(autorzyPrzycisk);
        
        zakonczPrzycisk = new JButton("Zakończ");
        zakonczPrzycisk.setToolTipText("Wyjdź z programu");
        oblugaZakoncz = new PrzyciskZakoncz();
        zakonczPrzycisk.addActionListener(oblugaZakoncz);
        zakonczPrzycisk.setLocation(880, 500);
        zakonczPrzycisk.setSize(80, 40);
        zakonczPrzycisk.setForeground(Color.RED);
        okno.add(zakonczPrzycisk);

        zamianaMiejscPrzycisk = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/resources/strzalki.png"));
            zamianaMiejscPrzycisk.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println("Problem z załadowaniem obrazka strzalki.png");
        }
        obslugaZamianaMiejsc = new PrzyciskZamianaMiejsc();
        zamianaMiejscPrzycisk.addActionListener(obslugaZamianaMiejsc);
        zamianaMiejscPrzycisk.setLocation(10, 250);
        zamianaMiejscPrzycisk.setSize(20, 27);
        okno.add(zamianaMiejscPrzycisk);

        setTitle("Kryptografia");

        Font czcionka = new Font("Courier New", Font.BOLD, 17);
        JLabel tekst1 = new JLabel("Tekst jawny:", SwingConstants.LEFT);
        tekst1.setSize(300, 20);
        tekst1.setLocation(5, 30);
        tekst1.setFont(czcionka);
        okno.add(tekst1);

        JLabel tekst2 = new JLabel("Klucz:", SwingConstants.LEFT);
        tekst2.setSize(300, 20);
        tekst2.setLocation(50, 255);
        tekst2.setFont(czcionka);
        okno.add(tekst2);

        JLabel tekst3 = new JLabel("Charakt.szyfrogr.", SwingConstants.LEFT);
        tekst3.setSize(300, 20);
        tekst3.setLocation(410, 30);
        tekst3.setFont(czcionka);
        okno.add(tekst3);

        JLabel tekst4 = new JLabel("Kryptoanaliza", SwingConstants.LEFT);
        tekst4.setSize(300, 20);
        tekst4.setLocation(780, 30);
        tekst4.setFont(czcionka);
        okno.add(tekst4);

        JLabel tekst5 = new JLabel("Szyfrogram:", SwingConstants.LEFT);
        tekst5.setSize(300, 20);
        tekst5.setLocation(5, 298);
        tekst5.setFont(czcionka);
        okno.add(tekst5);

        JLabel tekst6 = new JLabel("Stat.wzorcowa", SwingConstants.LEFT);
        tekst6.setSize(300, 20);
        tekst6.setLocation(595, 30);
        tekst6.setFont(czcionka);
        okno.add(tekst6);

        setSize(980, 585);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Konstruktor parametryczny wpisujący wybrany klucz z archiwum w pole
     * klucza
     *
     * @param wybor
     */
    public Kryptografia(String wybor) {
        kluczWejscie.setText(wybor);
    }
    
    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    /**
     * Obsługa przycisku "Szyfruj"
     */
    private class PrzyciskSzyfruj implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String tresc = textWejscie.getText();
            String klucz = kluczWejscie.getText();
            
            if(textWejscie.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Brak wpisanego tekstu jawnego !!!", "Brak tekstu jawnego" , JOptionPane.ERROR_MESSAGE);
                return ;
            }
            
            if(kluczWejscie.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Brak wpisanego klucza !!!", "Brak klucza" , JOptionPane.ERROR_MESSAGE);
                return ;
            }
            tresc = tresc.toUpperCase();
            textWejscie.setText(tresc);
            
            klucz = dodajAlfabet(klucz.toUpperCase());
            textWyjscie.setText("");
            kluczWejscie.setText(klucz);

            char[] tab_tresc = tresc.toCharArray();
            char[] tab_klucz = klucz.toCharArray();

            for (int i = 0; i < tresc.length(); i++) {
                int pozycja = sprawdzPozycjeAlfabet(tab_tresc[i]);
                char szyfruj;

                if (pozycja == -1) {
                    szyfruj = tab_tresc[i];
                } else {
                    szyfruj = tab_klucz[pozycja];
                }
                
//                textWyjscie.append(Character.toString(szyfruj));
                appendToPane(textWyjscie, (Character.toString(szyfruj)), Color.black);
            }

            String charakterystyka = wyznaczCharakterystyke(textWyjscie.getText());
            textStat.setText(charakterystyka);
            if (!kluczWejscie.getText().isEmpty()) {
                if (sprawdzCzyKluczSiePowtarza(kluczWejscie.getText()) == 1) {
                    zapiszDoPliku(kluczWejscie.getText().toUpperCase(), "klucze.txt", true);
                }
            }
        }
    }

    /**
     * Obsługa przycisku "Deszyfruj"
     */
    private class PrzyciskDeszyfruj implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String tresc = textWejscie.getText().trim();
            String klucz = kluczWejscie.getText().replace("\r", "");
            textWyjscie.setText("");

            if (klucz.length() != 26) {
                JOptionPane.showMessageDialog(null, "Podany klucz nie ma 26 znaków!\n"
                        + "Wczytaj klucz z archiwum bądź popraw już wpisany.", "Błąd klucza", JOptionPane.ERROR_MESSAGE);
            } else {
                String alfabet = pobierzAlfabet();
                char[] tab_alfabet = alfabet.toCharArray();
                char[] tab_tresc = tresc.toUpperCase().toCharArray();

                for (int i = 0; i < tresc.length(); i++) {
                    int pozycja = sprawdzPozycjeKlucz(tab_tresc[i], klucz);
                    char deszyfruj;

                    if (pozycja == -1) {
                        deszyfruj = tab_tresc[i];
                    } else {
                        deszyfruj = tab_alfabet[pozycja];
                    }

//                    textWyjscie.append(Character.toString(deszyfruj));
                    appendToPane(textWyjscie, (Character.toString(deszyfruj)), Color.black);
                }

                String charakterystyka = wyznaczCharakterystyke(textWyjscie.getText());
                textStat.setText(charakterystyka);
            }
        }
    }

    /**
     * Obsługa przycisku "Start Kryptoanaliza"
     */
    private class PrzyciskKryptoanaliza implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            textWyjscie.setText("");
            String text = textKrypto.getText();
            if (text.isEmpty()) {
                String szablon = wygenerujSzablonKryptoanalizy();
                textKrypto.setText(szablon);
            } else {
                pobierzZmianyKryptoanalizy(textKrypto.getText());
                String wejscie = textWejscie.getText().toUpperCase();
                if (!"".equals(wejscie)) {
                    String kryptoanaliza = kryptoanalizuj(wejscie);
                    char[] tab_wejscie = wejscie.toCharArray();
                    char[] tab_kryptoanaliza = kryptoanaliza.toCharArray();

                    kryptoanaliza = "";
                    for (int i = 0; i < tab_kryptoanaliza.length; i++) {
                        if (tab_wejscie[i] != tab_kryptoanaliza[i]) {
                            appendToPane(textWyjscie, "" + tab_kryptoanaliza[i], Color.red);
                        } else {
                            appendToPane(textWyjscie, "" + tab_kryptoanaliza[i], Color.black);
                        }
                    }
//                    textWyjscie.setText(kryptoanaliza);
                }
            }
        }
    }

    /**
     * Obsługa przycisku "Archiwum kluczy"
     */
    private class PrzyciskArchiwumKluczy implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String klucze = wczytajZpliku("klucze.txt");
            new Archiwum(klucze);
        }
    }

    /**
     * Obsługa przycisku "Autorzy"
     */
    private class PrzyciskAutorzy implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"- Goniprowski Mateusz"
                                              + "\n- Niesłuchowski Kamil"
                                              + "\n- Załuska Paweł"
                                              + "\n Wyrażamy zgodę na wykorzystywanie programu w celach dydaktycznych.", 
                                                "Autorzy",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private class PrzyciskZakoncz implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Obsługa przycisku "Wczytaj"
     */
    private class PrzyciskWczytaj implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String klucze = wczytajZpliku("klucze.txt");
            String[] tab_klucze = klucze.split("\n");
            String ostatniKlucz = tab_klucze[tab_klucze.length - 1];
            kluczWejscie.setText(ostatniKlucz);
        }
    }

    /**
     * Obsługa przycisku "Zapisz szyfrogram"
     */
    private class PrzyciskZapiszSzyfr implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String plik = wczytajSciezkePlikuZdysku("zapisz");
            if (plik != null) {
                zapiszDoPliku(textWyjscie.getText(), plik, false);
            }
        }
    }

    /**
     * Obsługa przycisku "Wczytaj szyfrogram"
     */
    private class PrzyciskWczytajSzyfr implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String plik = wczytajSciezkePlikuZdysku("wczytaj");
            String text = "";
            if (plik != null) {
                text = wczytajZpliku(plik);
                textWejscie.setText(text);
            }
        }
    }

    /**
     * Obsługa przycisku "Strzałki"
     */
    private class PrzyciskZamianaMiejsc implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String pomoc = textWejscie.getText();
            textWejscie.setText(textWyjscie.getText());
            textWyjscie.setText(pomoc);
        }
    }

    /**
     * Obsługa przycisku "Uzupełnij"
     */
    private class PrzyciskUzupelnij implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String klucz = kluczWejscie.getText();
            klucz = dodajAlfabet(klucz.toUpperCase());
            kluczWejscie.setText(klucz);
        }
    }

    /**
     * Obsługa przycisku "Wczytaj stat.wzorcowa"
     */
    private class PrzyciskWczytajWzorcowa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String plik = wczytajSciezkePlikuZdysku("wczytaj");
            if (plik != null) {
                String text = wczytajZpliku(plik);
                textWzorcowa.setText(text);
            }
        }
    }

    /**
     * Obsługa przycisku "Wygeneruj stat wzorcowa"
     */
    private class PrzyciskWygenerujWzorcowa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String plik = wczytajSciezkePlikuZdysku("wczytaj");
            if (plik != null) {
                String text = wczytajZpliku(plik);
                String charakterystyka = wyznaczCharakterystyke(text.toUpperCase());
                textWzorcowa.setText(charakterystyka);
            }
        }
    }

    /**
     * Obsługa przycisku "Zapisz stat wzorcowa"
     */
    private class PrzyciskZapiszWzorcowa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String plik = wczytajSciezkePlikuZdysku("zapisz");
            if (plik != null) {
                zapiszDoPliku(textWzorcowa.getText(), plik, false);
            }
        }
    }

    /**
     * Modyfikuje wpisany przez użytkownika klucz o znaki niepowtarzające się
     *
     * @param klucz
     * @return
     */
    public String dodajAlfabet(String klucz) {
        String wymieszanyAlfabet = wymieszajAlfabet(pobierzAlfabet());
        char[] tab_wymieszanyAlfabet = wymieszanyAlfabet.toCharArray();
        char[] znaki = klucz.toCharArray();

        for (int i = 0; i < tab_wymieszanyAlfabet.length; i++) {
            boolean poprawnosc = false;

            for (int j = 0; j < znaki.length; j++) {
                if (znaki[j] == tab_wymieszanyAlfabet[i]) {
                    poprawnosc = true;
                    break;
                }
            }
            if (!poprawnosc) {
                klucz += tab_wymieszanyAlfabet[i];
            }
        }

        return klucz;
    }

    /**
     * Miesza litery w alfabecie
     *
     * @param text
     * @return
     */
    static String wymieszajAlfabet(String text) {
        if (text.length() <= 1) {
            return text;
        }

        int split = text.length() / 2;

        String temp1 = wymieszajAlfabet(text.substring(0, split));
        String temp2 = wymieszajAlfabet(text.substring(split));

        if (Math.random() > 0.5) {
            return temp1 + temp2;
        } else {
            return temp2 + temp1;
        }
    }

    /**
     * Sprawdza pozycję danego znaku w alfabecie
     *
     * @param znak
     * @return
     */
    public int sprawdzPozycjeAlfabet(char znak) {
        int pozycja = 0;
        boolean czyZnaleziono = false;
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            if (ch == znak) {
                czyZnaleziono = true;
                break;
            }
            pozycja++;
        }

        if (czyZnaleziono) {
            return pozycja;
        } else {
            return -1;
        }
    }

    /**
     * Sprawdza pozycję danego znaku w kluczu
     *
     * @param znak
     * @param klucz
     * @return
     */
    public int sprawdzPozycjeKlucz(char znak, String klucz) {
        char[] tab_klucz = klucz.toCharArray();
        int pozycja = 0;
        boolean czyZnaleziono = false;
        for (int i = 0; i < klucz.length(); i++) {
            if (znak == tab_klucz[i]) {
                czyZnaleziono = true;
                break;
            }
            pozycja++;
        }

        if (czyZnaleziono) {
            return pozycja;
        } else {
            return -1;
        }
    }

    /**
     * Pobiera alfabet i zapisuje do stringa
     *
     * @return
     */
    public String pobierzAlfabet() {
        String alfabet = "";

        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            alfabet += ch;
        }

        return alfabet;
    }

    /**
     * Wyznacza charakterystykę podanego textu
     *
     * @param text
     * @return
     */
    public String wyznaczCharakterystyke(String text) {
        String charakterystyka = "";

        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            charakterystyka += ch;
            charakterystyka += " = ";

            int ilosc = policzWystapienia(text, ch);
            float srednia = (float) ilosc / (float) text.length();

            charakterystyka += Integer.toString(ilosc);
            charakterystyka += " (";
            charakterystyka += Float.toString(srednia);
            charakterystyka += ")";
            charakterystyka += '\n';
        }
        
        charakterystyka += "_";
            charakterystyka += " = ";

            int ilosc = policzWystapienia(text, ' ');
            float srednia = (float) ilosc / (float) text.length();

            charakterystyka += Integer.toString(ilosc);
            charakterystyka += " (";
            charakterystyka += Float.toString(srednia);
            charakterystyka += ")";
            charakterystyka += '\n';

        int[] tab_pozycji = sortujCharakterystyke(charakterystyka);
        String[] tab_charakterystyka = charakterystyka.split("\n");

        charakterystyka = "";
        for (int i = 0; i < tab_charakterystyka.length; i++) {
            charakterystyka += tab_charakterystyka[tab_pozycji[i]];
            charakterystyka += '\n';
        }

        return charakterystyka;
    }

    /**
     * Liczy wystąpienia danego znaku w tekście
     *
     * @param text
     * @param znak
     * @return
     */
    public int policzWystapienia(String text, char znak) {
        int wystapienia = 0;
        char[] tab_text = text.toCharArray();

        for (int i = 0; i < text.length(); i++) {
            if (tab_text[i] == znak) {
                wystapienia++;
            }
        }
        return wystapienia;
    }

    /**
     * Generuje szablon kryptoanalizy
     *
     * @return
     */
    public String wygenerujSzablonKryptoanalizy() {
        String szablon = "";
        String alfabet = pobierzAlfabet();
        char[] tab_alfabet = alfabet.toCharArray();

        kluczKryptoanaliza = pobierzAlfabet();

        char[] tab_kluczKryptoanaliza = kluczKryptoanaliza.toCharArray();

        for (int i = 0; i < kluczKryptoanaliza.length(); i++) {
            szablon += tab_alfabet[i];
            szablon += " => ";
            szablon += tab_kluczKryptoanaliza[i];
            szablon += "\n";
        }

        return szablon;
    }

    /**
     * Pobiera zmiany z szablonu kryptoanalizy do klucza
     *
     * @param szablon
     */
    public void pobierzZmianyKryptoanalizy(String szablon) {
        String[] tab_szablon = szablon.split(" => ");

        kluczKryptoanaliza = "";
        for (int i = 1; i < tab_szablon.length; i++) {
            kluczKryptoanaliza += tab_szablon[i].substring(0, 1);
        }
    }

    /**
     * Kryptoanaliza metodą prób i błędów
     *
     * @param text
     * @return
     */
    public String kryptoanalizuj(String text) {
        char[] tab_kluczKryptografia = kluczKryptoanaliza.toCharArray();
        char[] tab_text = text.toCharArray();
        String krypto = "";

        for (int i = 0; i < text.length(); i++) {
            int pozycja = sprawdzPozycjeAlfabet(tab_text[i]);

            if (pozycja == -1) {
                krypto += tab_text[i];
            } else {
                krypto += tab_kluczKryptografia[pozycja];
            }
        }

        return krypto;
    }

    /**
     * Wczytuje z pliku
     *
     * @param nazwaPliku
     * @return
     */
    public String wczytajZpliku(String nazwaPliku) {
        File plik = new File(nazwaPliku);
        StringBuilder zawartosc = new StringBuilder();
        BufferedReader czytnik = null;

        try {
            czytnik = new BufferedReader(new FileReader(plik));
            String text = null;

            while ((text = czytnik.readLine()) != null) {
                zawartosc.append(text).append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (czytnik != null) {
                    czytnik.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return zawartosc.toString();
    }

    /**
     * Zapisuje do pliku
     *
     * @param text
     * @param nazwaPliku
     * @param CzyDopisacDoPliku
     */
    public void zapiszDoPliku(String text, String nazwaPliku, Boolean CzyDopisacDoPliku) {
        try {
            PrintWriter zapis = new PrintWriter(new FileOutputStream(
                    new File(nazwaPliku),
                    CzyDopisacDoPliku));
            zapis.println(text);
            zapis.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sprawdza przed zapisem do pliku czy dany klucz istnieje w archiwum
     *
     * @param klucz
     * @return
     */
    public int sprawdzCzyKluczSiePowtarza(String klucz) {
        String archiwum = wczytajZpliku("klucze.txt");
        String[] tab_archiwum = archiwum.split("\\r?\\n");
        klucz = klucz.replace("\r", "");

        for (String tab_archiwum1 : tab_archiwum) {
            if (tab_archiwum1.equals(klucz.toUpperCase())) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * Wyświetla okno wyboru pliku do wczytania lub zapisu oraz pobiera jego
     * sciezke
     *
     * @return
     */
    private String wczytajSciezkePlikuZdysku(String opcja) {
        JFileChooser fc = new JFileChooser();
        int dialog = 0;

        if ("wczytaj".equals(opcja)) {
            dialog = fc.showOpenDialog(this);
        } else {
            dialog = fc.showSaveDialog(this);
        }

        if (dialog == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    /**
     * Sortowanie charakterystyki od największej ilości znaków do najmniejszej
     * Zwraca tablice pozycji posortowanych rekordów
     *
     * @param charakterystyka
     * @return
     */
    private int[] sortujCharakterystyke(String charakterystyka) {
        String[] tab = charakterystyka.split(" = ");
        String[] inty = new String[27];
        int[] tab_inty = new int[27];
        int[] tab_inty2 = new int[27];
        int[] tab_pozycje = new int[27];

        // sprawdzanie ile znaków ma liczba
        for (int i = 1; i < tab.length; i++) {
            for (int j = 1; true; j++) {
                if (tab[i].substring(j, j + 1).indexOf(" ") == 0) {
                    inty[i - 1] = tab[i].substring(0, j);
                    break;
                }
            }
            tab_inty[i - 1] = Integer.parseInt(inty[i - 1]);
            tab_inty2[i - 1] = Integer.parseInt(inty[i - 1]);
        }

        // sortowanie bąbelkowe
        for (int i = 0; i < tab_inty.length; i++) {
            for (int j = 0; j < tab_inty.length - 1; j++) {
                if (tab_inty[j] > tab_inty[j + 1]) {
                    int temp = tab_inty[j + 1];
                    tab_inty[j + 1] = tab_inty[j];
                    tab_inty[j] = temp;
                }
            }
        }

        // wyznaczanie pozycji
        // po wyznaczeniu kolejnej pozycji tab_inty2[j]=-1 zeby uniknąć powtórzeń
        int k = 0;
        for (int i = tab_inty.length - 1; i >= 0; i--) {
            for (int j = 0; j < tab_inty2.length; j++) {
                if (tab_inty[i] == tab_inty2[j]) {
                    tab_pozycje[k++] = j;
                    tab_inty2[j] = -1;
                    break;
                }
            }
        }

        return tab_pozycje;
    }

    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        Kryptografia obiekt = new Kryptografia();
        obiekt.setLocationRelativeTo(null);
    }
}
