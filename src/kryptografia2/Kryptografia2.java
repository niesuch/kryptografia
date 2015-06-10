package kryptografia2;

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
import javax.imageio.ImageIO;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Kryptografia2 extends JFrame {

    private static JTextField kluczWejscie;
    private static JTextArea textWyjscie, textWejscie;
    private JScrollPane scrollText, scrollText2;
    private JButton szyfrujPrzycisk, deszyfrujPrzycisk, archiwumKluczyPrzycisk,
            wczytajPrzycisk, autorzyPrzycisk, zapiszSzyfrPrzycisk, wczytajSzyfrPrzycisk, zamianaMiejscPrzycisk;
    private PrzyciskSzyfruj obslugaSzyfruj;
    private PrzyciskDeszyfruj obslugaDeszyfruj;
    private PrzyciskArchiwumKluczy obslugaArchiwumKluczy;
    private PrzyciskWczytaj obslugaWczytaj;
    private PrzyciskAutorzy obslugaAutorzy;
    private PrzyciskZapiszSzyfr obslugaZapiszSzyfr;
    private PrzyciskWczytajSzyfr obslugaWczytajSzyfr;
    private PrzyciskZamianaMiejsc obslugaZamianaMiejsc;

    /**
     * Konstruktor głównej klasy, inicjalizowanie wyglądu programu
     */
    public Kryptografia2() {
        Container okno = getContentPane();
        okno.setLayout(null); 
        
        textWejscie = new JTextArea("", 50, 895);
        scrollText2 = new JScrollPane(textWejscie);
        scrollText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWejscie.setLineWrap(true);
        textWejscie.setWrapStyleWord(true);
        scrollText2.setLocation(5, 52);
        scrollText2.setSize(470, 180);
        okno.add(scrollText2);
        
        textWyjscie = new JTextArea("", 50, 895);
        scrollText = new JScrollPane(textWyjscie);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);        
        textWyjscie.setLineWrap(true);
        textWyjscie.setWrapStyleWord(false);
        //textWyjscie.setEditable(false);
        scrollText.setLocation(5, 320);
        scrollText.setSize(470, 180);
        okno.add(scrollText);
        
        kluczWejscie = new JTextField(3);
        kluczWejscie.setLocation(115, 243);
        kluczWejscie.setSize(270, 40);
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
        
        archiwumKluczyPrzycisk = new JButton("Archiwum kluczy");
        obslugaArchiwumKluczy = new PrzyciskArchiwumKluczy();
        archiwumKluczyPrzycisk.addActionListener(obslugaArchiwumKluczy);
        archiwumKluczyPrzycisk.setLocation(325, 502);
        archiwumKluczyPrzycisk.setSize(150,20);
        okno.add(archiwumKluczyPrzycisk);
        
        zapiszSzyfrPrzycisk = new JButton("Zapisz szyfrogram");
        obslugaZapiszSzyfr = new PrzyciskZapiszSzyfr();
        zapiszSzyfrPrzycisk.addActionListener(obslugaZapiszSzyfr);
        zapiszSzyfrPrzycisk.setLocation(165, 522);
        zapiszSzyfrPrzycisk.setSize(150,20);
        okno.add(zapiszSzyfrPrzycisk);
        
        wczytajSzyfrPrzycisk = new JButton("Wczytaj szyfrogram");
        obslugaWczytajSzyfr = new PrzyciskWczytajSzyfr();
        wczytajSzyfrPrzycisk.addActionListener(obslugaWczytajSzyfr);
        wczytajSzyfrPrzycisk.setLocation(5, 522);
        wczytajSzyfrPrzycisk.setSize(150,20);
        okno.add(wczytajSzyfrPrzycisk);
        
        wczytajPrzycisk = new JButton("Wczytaj");
        obslugaWczytaj = new PrzyciskWczytaj();
        wczytajPrzycisk.addActionListener(obslugaWczytaj);
        wczytajPrzycisk.setLocation(390, 253);
        wczytajPrzycisk.setSize(82,20);     
        okno.add(wczytajPrzycisk);
        
        autorzyPrzycisk = new JButton("Autorzy");
        obslugaAutorzy = new PrzyciskAutorzy();
        autorzyPrzycisk.addActionListener(obslugaAutorzy);
        autorzyPrzycisk.setLocation(390,5);
        autorzyPrzycisk.setSize(80, 20);
        okno.add(autorzyPrzycisk);
        
        zamianaMiejscPrzycisk = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/resources/strzalki.png"));
            zamianaMiejscPrzycisk.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println("Problem z załadowaniem obrazka strzalki.png");
        }
        obslugaZamianaMiejsc = new PrzyciskZamianaMiejsc();
        zamianaMiejscPrzycisk.addActionListener(obslugaZamianaMiejsc);
        zamianaMiejscPrzycisk.setLocation(10,250);
        zamianaMiejscPrzycisk.setSize(20, 27);
        okno.add(zamianaMiejscPrzycisk);

        setTitle("Kryptografia");
        
        Font czcionka =  new Font("Courier New", Font.BOLD, 17);
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
        
        JLabel tekst5 = new JLabel("Szyfrogram:", SwingConstants.LEFT);
        tekst5.setSize(300, 20);
        tekst5.setLocation(5, 298);
        tekst5.setFont(czcionka);
        okno.add(tekst5);
        
        setSize(495, 585);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Konstruktor parametryczny wpisujący wybrany klucz z archiwum w pole klucza
     * @param wybor 
     */
    public Kryptografia2(String wybor) {
        kluczWejscie.setText(wybor);
    }

    /**
     * Obsługa przycisku "Szyfruj"
     */
    private class PrzyciskSzyfruj implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String klucz = kluczWejscie.getText();
            String tekst = textWejscie.getText();
            RC4 rc4 = new RC4(klucz);
            char[] wynik = rc4.szyfrowanie(tekst.toCharArray());
            textWyjscie.setText(new String(wynik));
            
            if (!kluczWejscie.getText().isEmpty())
                if (sprawdzCzyKluczSiePowtarza(kluczWejscie.getText()) == 1)
                    zapiszDoPliku(kluczWejscie.getText().toUpperCase(), "klucze.txt", true);
        }
    }

    /**
     * Obsługa przycisku "Deszyfruj"
     */
    private class PrzyciskDeszyfruj implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String klucz = kluczWejscie.getText();
            String tekst = textWejscie.getText();
            RC4 rc4 = new RC4(klucz);
            char[] wynik = rc4.deszyfrowanie(tekst.toCharArray());
            textWyjscie.setText(new String(wynik));
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
                                              + "\n- Goniprowski Mateusz"
                                              + "\n- Niesłuchowski Kamil"
                                              + "\n- Załuska Paweł");
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
     * Obsługa przycisku "Zapisz szyfrogram"
     */
    private class PrzyciskZapiszSzyfr implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String plik = wczytajSciezkePlikuZdysku("zapisz");
            if (plik != null)
                zapiszDoPliku(textWyjscie.getText(), plik, false);
        }
    }
    
    /**
     * Obsługa przycisku "Wczytaj szyfrogram"
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
     * Obsługa przycisku "Strzałki"
     */
    private class PrzyciskZamianaMiejsc implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String pomoc = textWejscie.getText();
            textWejscie.setText(textWyjscie.getText());
            textWyjscie.setText(pomoc);
        }
    }
    
    /**
     * Wczytuje z pliku
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
     * @param text
     * @param nazwaPliku
     * @param CzyDopisacDoPliku 
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
        String[] tab_archiwum = archiwum.split("\\r?\\n");
        klucz = klucz.replace("\r","");
        
        for(int i=0; i<tab_archiwum.length; i++) {
            if(tab_archiwum[i].equals(klucz.toUpperCase()))
                return 0;
        }
        return 1;
    }
    
    /**
     * Wyświetla okno wyboru pliku do wczytania lub zapisu oraz 
     * pobiera jego sciezke
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
        Kryptografia2 obiekt = new Kryptografia2();
        obiekt.setLocationRelativeTo(null);
    }
}
