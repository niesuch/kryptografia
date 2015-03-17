package kryptografia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.border.*;

public class Kryptografia extends JFrame {

    private static JTextField kluczWejscie;
    private static JTextArea textWyjscie, textWejscie, textStat;
    private JScrollPane scrollText, scrollText2, scrollTextStat;
    private JButton szyfrujPrzycisk, deszyfrujPrzycisk;
    private PrzyciskSzyfruj obslugaSzyfruj;
    private PrzyciskDeszyfruj obslugaDeszyfruj;

    /**
     * Konstruktor głównej klasy, inicjalizowanie wyglądu programu
     */
    public Kryptografia() {
        textWejscie = new JTextArea("", 50, 895);

        scrollText2 = new JScrollPane(textWejscie);
        scrollText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWejscie.setLineWrap(true);
        textWejscie.setWrapStyleWord(true);
        
        textStat = new JTextArea("", 800, 50);
        scrollTextStat = new JScrollPane(textStat);
        scrollTextStat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textStat.setLineWrap(true);
        textStat.setWrapStyleWord(true);

        kluczWejscie = new JTextField(3);

        textWyjscie = new JTextArea("", 50, 895);
        textWyjscie.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textWyjscie = new JTextArea("", 50, 895);

        scrollText = new JScrollPane(textWyjscie);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        textWyjscie.setLineWrap(true);
        textWyjscie.setWrapStyleWord(false);
        textWyjscie.setEditable(false);

        szyfrujPrzycisk = new JButton("Szyfruj");
        obslugaSzyfruj = new PrzyciskSzyfruj();
        szyfrujPrzycisk.addActionListener(obslugaSzyfruj);
        
        deszyfrujPrzycisk = new JButton("Deszyfruj");
        obslugaDeszyfruj = new PrzyciskDeszyfruj();
        deszyfrujPrzycisk.addActionListener(obslugaDeszyfruj);

        setTitle("Kryptografia");

        Container okno = getContentPane();
        okno.setLayout(null);

        deszyfrujPrzycisk.setLocation(550, 452);
        szyfrujPrzycisk.setLocation(300, 452);

        scrollText2.setLocation(5, 52);
        kluczWejscie.setLocation(185, 233);
        scrollText.setLocation(5, 273);
        scrollTextStat.setLocation(410, 52);

        deszyfrujPrzycisk.setSize(200, 40);
        szyfrujPrzycisk.setSize(200, 40);

        scrollText2.setSize(400, 180);
        kluczWejscie.setSize(60, 40);
        scrollText.setSize(400, 180);
        scrollTextStat.setSize(180, 400);

        okno.add(deszyfrujPrzycisk);
        okno.add(szyfrujPrzycisk);
        okno.add(scrollText2);
        okno.add(kluczWejscie);
        okno.add(scrollText);
        okno.add(scrollTextStat);

        setSize(1035, 530);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                char szyfruj = tab_klucz[pozycja];
                textWyjscie.append(Character.toString(szyfruj));
            }
            
            String charakterystyka = wyznaczCharakterystyke(textWyjscie.getText());
            textStat.setText(charakterystyka);
        }
    }

    /**
     * Obsługa przycisku "Deszyfruj"
     */
    private class PrzyciskDeszyfruj implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String tresc = textWejscie.getText();
            String klucz = kluczWejscie.getText();
            klucz = dodajAlfabet(klucz.toUpperCase());
            textWyjscie.setText("");
            
            String alfabet = pobierzAlfabet();
            char[] tab_alfabet = alfabet.toCharArray();
            char[] tab_tresc = tresc.toUpperCase().toCharArray();

            for (int i = 0; i < tresc.length(); i++) {
                int pozycja = sprawdzPozycjeKlucz(tab_tresc[i], klucz);
                char deszyfruj = tab_alfabet[pozycja];
                textWyjscie.append(Character.toString(deszyfruj));
            }
            
            String charakterystyka = wyznaczCharakterystyke(textWyjscie.getText());
            textStat.setText(charakterystyka);
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
        for (char ch = 'A'; ch <= 'Z'; ++ch) {            
            if(ch == znak)
                break;        
            pozycja++;
        }

        return pozycja;
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
        for (int i=0; i<klucz.length(); i++) {
            if(znak == tab_klucz[i])
                break;
            pozycja++;
        }
        
        return pozycja;
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
     * Main
     * @param args 
     */
    public static void main(String[] args) {
        Kryptografia obiekt = new Kryptografia();
        obiekt.setLocationRelativeTo(null);
    }
}
