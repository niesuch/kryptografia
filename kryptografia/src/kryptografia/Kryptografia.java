package kryptografia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.border.*;

public class Kryptografia extends JFrame {

    private static JTextField kluczWejscie;
    private static JTextArea textWyjscie, textWejscie;
    private JScrollPane scrollText, scrollText2;
    private JButton szyfrujPrzycisk, deszyfrujPrzycisk;
    private PrzyciskSzyfruj obslugaSzyfruj;
    private PrzyciskDeszyfruj obslugaDeszyfruj;


    public Kryptografia() {
        textWejscie = new JTextArea("", 50, 895);

        scrollText2 = new JScrollPane(textWejscie);
        scrollText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWejscie.setLineWrap(true);
        textWejscie.setWrapStyleWord(true);

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
        kluczWejscie.setLocation(465, 233);
        scrollText.setLocation(5, 273);

        deszyfrujPrzycisk.setSize(200, 40);
        szyfrujPrzycisk.setSize(200, 40);

        scrollText2.setSize(1010, 180);
        kluczWejscie.setSize(60, 40);
        scrollText.setSize(1010, 180);

        okno.add(deszyfrujPrzycisk);
        okno.add(szyfrujPrzycisk);
        okno.add(scrollText2);
        okno.add(kluczWejscie);
        okno.add(scrollText);

        setSize(1035, 530);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

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
        }
    }

    private class PrzyciskDeszyfruj implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String tresc = textWejscie.getText();
            int klucz = Integer.parseInt(kluczWejscie.getText());

            textWyjscie.setText("");

            if ((klucz >= -25) && (klucz <= 25)) {
                char[] znaki = tresc.toCharArray();

                for (int i=0; i<tresc.length(); i++) {
                    char znak = znaki[i];
                    char deszyfruj = deszyfruj(znak, klucz);
                    String wyjscie = Character.toString(deszyfruj);

                    textWyjscie.append(wyjscie);
                }
            }
        }
    }

    public static char deszyfruj(char znak, int klucz) {
        char[] alfabet = new char[26];
        int i=0;

        for (char ch='a'; ch<='z'; ++ch)
            alfabet[ch-'a']=ch;
    
        while (i<26) {
            if (znak == alfabet[i])
                return alfabet[(i-klucz+26)%26];
            i++;
        }
        return znak;
    }
    
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
    
    public int sprawdzPozycjeAlfabet(char znak) {
        int pozycja=0;
        for (char ch = 'A'; ch <= 'Z'; ++ch) {            
            if(ch == znak)
                break;        
            pozycja++;
        }

        return pozycja;
    }

    public static void main(String[] args) {
        Kryptografia obiekt = new Kryptografia();
        obiekt.setLocationRelativeTo(null);
    }
}
