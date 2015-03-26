package kryptografia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.border.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Kryptografia extends JFrame {

    private static JTextField kluczWejscie;
    private static JTextArea textWyjscie, textWejscie, textStat, textKrypto;
    private JScrollPane scrollText, scrollText2, scrollTextStat, scrollTextKrypto;
    private JButton szyfrujPrzycisk, deszyfrujPrzycisk, kryptoanalizaPrzycisk, archiwumKluczyPrzycisk,
            wczytajPrzycisk, autorzyPrzycisk, zapiszSzyfrPrzycisk, wczytajSzyfrPrzycisk;
    private PrzyciskSzyfruj obslugaSzyfruj;
    private PrzyciskDeszyfruj obslugaDeszyfruj;
    private PrzyciskKryptoanaliza obslugaKryptoanaliza;
    private PrzyciskArchiwumKluczy obslugaArchiwumKluczy;
    private PrzyciskWczytaj obslugaWczytaj;
    private PrzyciskAutorzy obslugaAutorzy;
    private PrzyciskZapiszSzyfr obslugaZapiszSzyfr;
    private PrzyciskWczytajSzyfr obslugaWczytajSzyfr;
    private String kluczKryptoanaliza;

    /**
     * Konstruktor głównej klasy, inicjalizowanie wyglądu programu
     */
    public Kryptografia() {
        Container okno = getContentPane();
        okno.setLayout(null); 
        
        textWejscie = new JTextArea("", 50, 895);
        scrollText2 = new JScrollPane(textWejscie);
        scrollText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWejscie.setLineWrap(true);
        textWejscie.setWrapStyleWord(true);
        scrollText2.setLocation(5, 52);
        scrollText2.setSize(400, 180);
        okno.add(scrollText2);
        
        textStat = new JTextArea("", 800, 50);
        scrollTextStat = new JScrollPane(textStat);
        scrollTextStat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textStat.setLineWrap(true);
        textStat.setWrapStyleWord(true);
        textStat.setEditable(false);
        scrollTextStat.setLocation(410, 52);
        scrollTextStat.setSize(180, 400);
        okno.add(scrollTextStat);
        
        textKrypto = new JTextArea("", 800, 50);
        scrollTextKrypto = new JScrollPane(textKrypto);
        scrollTextKrypto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textKrypto.setLineWrap(true);
        textKrypto.setWrapStyleWord(true);
        scrollTextKrypto.setLocation(595, 52);
        scrollTextKrypto.setSize(180, 400);
        okno.add(scrollTextKrypto);

        textWyjscie = new JTextArea("", 50, 895);
        scrollText = new JScrollPane(textWyjscie);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);        
        textWyjscie.setLineWrap(true);
        textWyjscie.setWrapStyleWord(false);
        textWyjscie.setEditable(false);
        scrollText.setLocation(5, 273);
        scrollText.setSize(400, 180);
        okno.add(scrollText);
        
        kluczWejscie = new JTextField(3);
        kluczWejscie.setLocation(100, 233);
        kluczWejscie.setSize(200, 40);
        okno.add(kluczWejscie);

        szyfrujPrzycisk = new JButton("Szyfruj");
        obslugaSzyfruj = new PrzyciskSzyfruj();
        szyfrujPrzycisk.addActionListener(obslugaSzyfruj);
        szyfrujPrzycisk.setLocation(5, 452);
        szyfrujPrzycisk.setSize(150, 20);
        okno.add(szyfrujPrzycisk);
        
        deszyfrujPrzycisk = new JButton("Deszyfruj");
        obslugaDeszyfruj = new PrzyciskDeszyfruj();
        deszyfrujPrzycisk.addActionListener(obslugaDeszyfruj);
        deszyfrujPrzycisk.setLocation(165, 452);
        deszyfrujPrzycisk.setSize(150, 20);
        okno.add(deszyfrujPrzycisk);
        
        kryptoanalizaPrzycisk = new JButton("Start Kryptoanaliza");
        obslugaKryptoanaliza = new PrzyciskKryptoanaliza();
        kryptoanalizaPrzycisk.addActionListener(obslugaKryptoanaliza);
        kryptoanalizaPrzycisk.setLocation(485, 452);
        kryptoanalizaPrzycisk.setSize(150, 20);
        okno.add(kryptoanalizaPrzycisk);
        
        archiwumKluczyPrzycisk = new JButton("Archiwum kluczy");
        obslugaArchiwumKluczy = new PrzyciskArchiwumKluczy();
        archiwumKluczyPrzycisk.addActionListener(obslugaArchiwumKluczy);
        archiwumKluczyPrzycisk.setLocation(325, 452);
        archiwumKluczyPrzycisk.setSize(150,20);
        okno.add(archiwumKluczyPrzycisk);
        
        zapiszSzyfrPrzycisk = new JButton("Zapisz szyfr");
        obslugaZapiszSzyfr = new PrzyciskZapiszSzyfr();
        zapiszSzyfrPrzycisk.addActionListener(obslugaZapiszSzyfr);
        zapiszSzyfrPrzycisk.setLocation(165, 472);
        zapiszSzyfrPrzycisk.setSize(150,20);
        okno.add(zapiszSzyfrPrzycisk);
        
        wczytajSzyfrPrzycisk = new JButton("Wczytaj szyfr");
        obslugaWczytajSzyfr = new PrzyciskWczytajSzyfr();
        wczytajSzyfrPrzycisk.addActionListener(obslugaWczytajSzyfr);
        wczytajSzyfrPrzycisk.setLocation(5, 472);
        wczytajSzyfrPrzycisk.setSize(150,20);
        okno.add(wczytajSzyfrPrzycisk);
        
        wczytajPrzycisk = new JButton("Wczytaj");
        obslugaWczytaj = new PrzyciskWczytaj();
        wczytajPrzycisk.addActionListener(obslugaWczytaj);
        wczytajPrzycisk.setLocation(305, 243);
        wczytajPrzycisk.setSize(82,20);     
        okno.add(wczytajPrzycisk);
        
        autorzyPrzycisk = new JButton("Autorzy");
        obslugaAutorzy = new PrzyciskAutorzy();
        autorzyPrzycisk.addActionListener(obslugaAutorzy);
        autorzyPrzycisk.setLocation(700,472);
        autorzyPrzycisk.setSize(80, 20);
        okno.add(autorzyPrzycisk);

        setTitle("Kryptografia");
        
        Font czcionka =  new Font("Courier New", Font.BOLD, 17);
        JLabel tekst1 = new JLabel("Tekst do szyfrowania:", SwingConstants.LEFT);
        tekst1.setSize(300, 20);
        tekst1.setLocation(5, 30);
        tekst1.setFont(czcionka);
        okno.add(tekst1);
        
        JLabel tekst2 = new JLabel("Klucz:", SwingConstants.LEFT);
        tekst2.setSize(300, 20);
        tekst2.setLocation(25, 245);
        tekst2.setFont(czcionka);
        okno.add(tekst2);
        
        JLabel tekst3 = new JLabel("Charakterystyka", SwingConstants.LEFT);
        tekst3.setSize(300, 20);
        tekst3.setLocation(410, 30);
        tekst3.setFont(czcionka);
        okno.add(tekst3);
        
        JLabel tekst4 = new JLabel("Kryptoanaliza", SwingConstants.LEFT);
        tekst4.setSize(300, 20);
        tekst4.setLocation(600, 30);
        tekst4.setFont(czcionka);
        okno.add(tekst4);

        setSize(795, 530);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Konstruktor parametryczny wpisujący wybrany klucz z archiwum w pole klucza
     * @param wybor 
     */
    public Kryptografia(String wybor) {
        kluczWejscie.setText(wybor);
    }

    /**
     * Obsługa przycisku "Szyfruj"
     */
    private class PrzyciskSzyfruj implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String tresc = textWejscie.getText();
            String klucz = kluczWejscie.getText();
            klucz = dodajAlfabet(klucz.toUpperCase());
            textWyjscie.setText("");
            
            char[] tab_tresc = tresc.toUpperCase().toCharArray();
            char[] tab_klucz = klucz.toCharArray();

            for (int i = 0; i < tresc.length(); i++) {
                int pozycja = sprawdzPozycjeAlfabet(tab_tresc[i]);
                char szyfruj;
                
                if(pozycja == -1)
                    szyfruj = tab_tresc[i];
                else
                    szyfruj = tab_klucz[pozycja];
                
                textWyjscie.append(Character.toString(szyfruj));
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
        public void actionPerformed(ActionEvent e) {
            String tresc = textWejscie.getText().trim();
            String klucz = kluczWejscie.getText();
            klucz = dodajAlfabet(klucz.toUpperCase());
            textWyjscie.setText("");
            
            String alfabet = pobierzAlfabet();
            char[] tab_alfabet = alfabet.toCharArray();
            char[] tab_tresc = tresc.toUpperCase().toCharArray();

            for (int i = 0; i < tresc.length(); i++) {
                int pozycja = sprawdzPozycjeKlucz(tab_tresc[i], klucz);
                char deszyfruj;
                
                if(pozycja == -1)
                    deszyfruj = tab_tresc[i];
                else
                    deszyfruj = tab_alfabet[pozycja];
                
                textWyjscie.append(Character.toString(deszyfruj));
            }
            
            String charakterystyka = wyznaczCharakterystyke(textWyjscie.getText());
            textStat.setText(charakterystyka);
        }
    }
    
    /**
     * Obsługa przycisku "Start Kryptoanaliza"
     */
    private class PrzyciskKryptoanaliza implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String text = textKrypto.getText();
            if(text.isEmpty()) {
                String szablon = wygenerujSzablonKryptoanalizy();
                textKrypto.setText(szablon);
            }
            else {
                pobierzZmianyKryptoanalizy(textKrypto.getText());
                if (textWejscie.getText() != "") {
                    String kryptoanaliza = kryptoanalizuj(textWejscie.getText());
                    textWyjscie.setText(kryptoanaliza);
                }
            }
        }
    }
    
    /**
     * Obsługa przycisku "Archiwum kluczy"
     */
    private class PrzyciskArchiwumKluczy implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String klucze = wczytajZpliku("klucze.txt");
            new Archiwum(klucze);
        }
    }
    
    /**
     * Obsługa przycisku "Autorzy"
     */
    private class PrzyciskAutorzy implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Autorzy:"
                                              + "\n- Kamil Niesłuchowski"
                                              + "\n- Mateusz Goniprowski"
                                              + "\n- Paweł Załuska");
        }
    }
    
    /**
     * Obsługa przycisku "Wczytaj"
     */
    private class PrzyciskWczytaj implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String klucze = wczytajZpliku("klucze.txt");
            String[] tab_klucze = klucze.split("\n");
            String ostatniKlucz = tab_klucze[tab_klucze.length - 1];
            kluczWejscie.setText(ostatniKlucz);            
        }
    }
    
    /**
     * Obsługa przycisku "Zapisz szyfr"
     */
    private class PrzyciskZapiszSzyfr implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String plik = wczytajSciezkePlikuZdysku("zapisz");
            if (plik != null)
                zapiszDoPliku(textWyjscie.getText(), plik, false);
        }
    }
    
    /**
     * Obsługa przycisku "Wczytaj szyfr"
     */
    private class PrzyciskWczytajSzyfr implements ActionListener {

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
     * Modyfikuje wpisany przez użytkownika klucz o znaki niepowtarzające się
     * @param klucz
     * @return 
     */
    public static String dodajAlfabet(String klucz) {
        char[] znaki = klucz.toCharArray();

        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            boolean poprawnosc = false;
            for (int i = 0; i < znaki.length; i++)
                if (znaki[i] == ch)
                    poprawnosc = true;
            if (!poprawnosc)
                klucz += ch;
        }
        
        return klucz;
    }
    
    /**
     * Sprawdza pozycję danego znaku w alfabecie
     * @param znak
     * @return 
     */
    public int sprawdzPozycjeAlfabet(char znak) {
        int pozycja=0;
        boolean czyZnaleziono = false;
        for (char ch = 'A'; ch <= 'Z'; ++ch) {            
            if(ch == znak) {
                czyZnaleziono = true;
                break;
            }
            pozycja++;
        }

        if(czyZnaleziono)
            return pozycja;
        else
            return -1;
    }
    
    /**
     * Sprawdza pozycję danego znaku w kluczu
     * @param znak
     * @param klucz
     * @return 
     */
    public int sprawdzPozycjeKlucz(char znak, String klucz) {
        char[] tab_klucz = klucz.toCharArray();
        int pozycja=0;
        boolean czyZnaleziono = false;
        for (int i=0; i<klucz.length(); i++) {
            if(znak == tab_klucz[i]) {
                czyZnaleziono = true;
                break;
            }
            pozycja++;
        }
        
        if(czyZnaleziono)
            return pozycja;
        else
            return -1;
    }
    
    /**
     * Pobiera alfabet i zapisuje do stringa
     * @return 
     */
    public String pobierzAlfabet() {
        String alfabet = "";
        
        for (char ch='A'; ch <= 'Z'; ++ch)
            alfabet += ch;
        
        return alfabet;
    }
    
    /**
     * Wyznacza charakterystykę podanego textu
     * @param text
     * @return 
     */
    public String wyznaczCharakterystyke(String text) {
        String charakterystyka = "";
        for(char ch = 'A'; ch <= 'Z'; ++ch) {
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
        
        return charakterystyka;
    }
    
    /**
     * Liczy wystąpienia danego znaku w tekście
     * @param text
     * @param znak
     * @return 
     */
    public int policzWystapienia(String text, char znak) {
        int wystapienia = 0;
        char[] tab_text = text.toCharArray();
        
        for(int i=0; i<text.length(); i++) {
            if(tab_text[i]==znak)
                wystapienia++;
        }
        return wystapienia;
    }
    
    /**
     * Generuje szablon kryptoanalizy
     * @return 
     */
    public String wygenerujSzablonKryptoanalizy() {
        String szablon="";
        String alfabet = pobierzAlfabet();
        char[] tab_alfabet = alfabet.toCharArray();
               
        kluczKryptoanaliza = pobierzAlfabet();
        
        char[] tab_kluczKryptoanaliza = kluczKryptoanaliza.toCharArray();
        
        for(int i=0; i<kluczKryptoanaliza.length(); i++) {
            szablon += tab_alfabet[i];
            szablon += " => ";
            szablon += tab_kluczKryptoanaliza[i];
            szablon += "\n";
        }        
        
        return szablon;
    }
    
    /**
     * Pobiera zmiany z szablonu kryptoanalizy do klucza
     * @param szablon 
     */
    public void pobierzZmianyKryptoanalizy(String szablon) {
        String[] tab_szablon = szablon.split(" => ");
              
        kluczKryptoanaliza = "";
        for(int i=1; i<tab_szablon.length; i++)
            kluczKryptoanaliza += tab_szablon[i].substring(0,1);
    }
    
    /**
     * Kryptoanaliza metodą prób i błędów
     * @param text
     * @return 
     */
    public String kryptoanalizuj(String text) {
        char[] tab_kluczKryptografia = kluczKryptoanaliza.toCharArray();
        char[] tab_text = text.toCharArray();
        String krypto = "";
        
        for (int i = 0; i < text.length(); i++) {
            int pozycja = sprawdzPozycjeAlfabet(tab_text[i]);
            
            if (pozycja == -1)
                krypto += tab_text[i];
            else 
                krypto += tab_kluczKryptografia[pozycja];
        }
        
        return krypto;
    }
    
    /**
     * Wczytuje z pliku
     * @return 
     */
    public String wczytajZpliku(String nazwaPliku) {
        File plik = new File(nazwaPliku);
        StringBuilder zawartosc = new StringBuilder();
        BufferedReader czytnik = null;

        try {
            czytnik = new BufferedReader(new FileReader(plik));
            String text = null;

            while ((text = czytnik.readLine()) != null)
                zawartosc.append(text).append(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (czytnik != null)
                    czytnik.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return zawartosc.toString();
    }
    
    /**
     * Zapisuje do pliku
     */
    public void zapiszDoPliku(String text, String nazwaPliku, Boolean CzyDopisacDoPliku) {
        try {
            PrintWriter zapis = new PrintWriter(new FileOutputStream(
                    new File(nazwaPliku),
                    CzyDopisacDoPliku ));
            zapis.println(text);
            zapis.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    /**
     * Sprawdza przed zapisem do pliku czy dany klucz istnieje w archiwum
     * @param klucz
     * @return 
     */
    public int sprawdzCzyKluczSiePowtarza(String klucz) {
        String archiwum = wczytajZpliku("klucze.txt");
        String[] tab_archiwum = archiwum.split("\n");
        
        for(int i=0; i<tab_archiwum.length; i++) {
            if(tab_archiwum[i].equals(klucz.toUpperCase()))
                return 0;
        }
        return 1;
    }
    
    /**
     * Wyświetla okno wyboru pliku do wczytania i wczytuje jego sciezke
     * @return 
     */
    private String wczytajSciezkePlikuZdysku(String opcja) {
        JFileChooser fc = new JFileChooser();
        int dialog = 0;
        
        if(opcja == "wczytaj")
            dialog = fc.showOpenDialog(this);
        else
            dialog = fc.showSaveDialog(this);
        
        if (dialog == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile().getAbsolutePath();
        else
            return null;
    }

    /**
     * Main
     * @param args 
     */
    public static void main(String[] args) {
        Kryptografia obiekt = new Kryptografia();
        obiekt.setLocationRelativeTo(null);
    }
}
