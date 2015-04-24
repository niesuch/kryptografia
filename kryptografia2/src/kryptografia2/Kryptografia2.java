package kryptografia2;

import AES.AES;
import rc4.RC4;
import DES.DES;
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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.xml.bind.DatatypeConverter;

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
    private JRadioButton radioRC4, radioAES, radioDES;
    String wyborRadio="RC4";
    long start = 0;
    long end = 0;

    /**
     * Konstruktor głównej klasy, inicjalizowanie wyglądu programu
     */
    public Kryptografia2() {
        Container okno = getContentPane();
        okno.setLayout(null); 
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Bład podczas ładowania wyglądu okna.");
        }
        
        this.setResizable(false);
        
        Font czcionkatext = new Font("Courier New", Font.BOLD, 12);
        textWejscie = new JTextArea("", 50, 895);
        scrollText2 = new JScrollPane(textWejscie);
        scrollText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWejscie.setLineWrap(true);
        textWejscie.setWrapStyleWord(true);
        textWejscie.setFont(czcionkatext);
        scrollText2.setLocation(5, 52);
        scrollText2.setSize(470, 180);
        okno.add(scrollText2);
        
        textWyjscie = new JTextArea("", 50, 895);
        scrollText = new JScrollPane(textWyjscie);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);        
        textWyjscie.setLineWrap(true);
        textWyjscie.setWrapStyleWord(false);
        textWyjscie.setFont(czcionkatext);
        //textWyjscie.setEditable(false);
        scrollText.setLocation(5, 320);
        scrollText.setSize(470, 180);
        okno.add(scrollText);
        
        kluczWejscie = new JTextField(3);
        kluczWejscie.setLocation(115, 243);
        kluczWejscie.setSize(270, 40);
        kluczWejscie.setFont(czcionkatext);
        kluczWejscie.setText("01FE01FE01FE01FE");
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
        
        ButtonGroup grupa = new ButtonGroup();
        radioRC4 = new JRadioButton("RC4");
        radioAES = new JRadioButton("AES");
        radioDES = new JRadioButton("DES");
        grupa.add(radioRC4);
        grupa.add(radioAES);
        grupa.add(radioDES);
        okno.add(radioRC4);
        okno.add(radioAES);
        okno.add(radioDES);
        radioRC4.addActionListener(obslugaRadio);
        radioAES.addActionListener(obslugaRadio);
        radioDES.addActionListener(obslugaRadio);
        radioRC4.setLocation(105,5);
        radioAES.setLocation(205,5);
        radioDES.setLocation(305,5);
        radioRC4.setSize(75,25);        
        radioAES.setSize(75,25);
        radioDES.setSize(75,25);
        radioRC4.setSelected(true);
        
                
        radioAES.addActionListener(obslugaRadio);
              
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
        
        JLabel tekst5 = new JLabel("Szyfrogram:", SwingConstants.LEFT);
        tekst5.setSize(300, 20);
        tekst5.setLocation(5, 298);
        tekst5.setFont(czcionka);
        okno.add(tekst5);
        
        JLabel tekst6 = new JLabel("Algorytm:", SwingConstants.LEFT);
        tekst6.setSize(300,20);
        tekst6.setLocation(5,7);
        tekst6.setFont(czcionka);
        okno.add(tekst6);
        
        setSize(490, 585);
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

            if ("RC4".equals(wyborRadio)) {
                start = System.nanoTime();
                RC4 rc4 = new RC4(klucz);
                char[] wynik = rc4.szyfrowanie(tekst.toCharArray());
                textWyjscie.setText(new String(wynik));
                end = System.nanoTime();
            } else if ("AES".equals(wyborRadio)) {
                start = System.nanoTime();
                new AES();
                byte[] enc = AES.szyfruj(tekst.getBytes(), klucz.getBytes());
                String CMsg = new String(enc);
                CMsg = toHexString(enc);
                textWyjscie.append(CMsg);
                end = System.nanoTime();
            } else if ("DES".equals(wyborRadio)) {
                start = System.nanoTime();
                new DES();
                byte[] enc = DES.encrypt(tekst.getBytes(), klucz.getBytes());
                String CMsg = new String(enc);
                CMsg = toHexString(enc);
                textWyjscie.append(CMsg);
                end = System.nanoTime();
            }
            JOptionPane.showMessageDialog(null, "Czas wykonania szyfrowania w sekundach: " + (end - start) / 1000000000.0);

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
            String klucz = kluczWejscie.getText();
            String tekst = textWejscie.getText();

            if ("RC4".equals(wyborRadio)) {
                start = System.nanoTime();
                RC4 rc4 = new RC4(klucz);
                char[] wynik = rc4.deszyfrowanie(tekst.toCharArray());
                textWyjscie.setText(new String(wynik));
                end = System.nanoTime();
            } else if ("AES".equals(wyborRadio)) {
                start = System.nanoTime();
                new AES();
                byte[] CMsg = toByteArray(tekst);
                byte[] dec = AES.odszyfruj(CMsg, klucz.getBytes());
                textWyjscie.append(new String(dec));
                end = System.nanoTime();
            } else if ("DES".equals(wyborRadio)) {
                start = System.nanoTime();
                new DES();
                byte[] CMsg = toByteArray(tekst.replace("\r\n", ""));
                byte[] dec = DES.decrypt(CMsg, klucz.replace("\r\n", "").getBytes());
                textWyjscie.append(new String(dec));
                end = System.nanoTime();
            }
            
            JOptionPane.showMessageDialog(null, "Czas wykonania deszyfrowania w sekundach: " + (end - start) / 1000000000.0);
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
//            String pomoc = textWejscie.getText();
            textWejscie.setText(textWyjscie.getText());
            textWyjscie.setText("");
        }
    }
    
    /**
     * Obsługa radio buttonów
     */
    private ActionListener obslugaRadio = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            wyborRadio = ((JRadioButton) e.getSource()).getText();
        }
    };

        public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    /**
     * Konwersja tablicy bitowej do obiektu typu String
     *
     * @param array
     * @return
     */
    public static String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }

    /**
     * Konwersja obiektu String do tablicy bitów
     *
     * @param s
     * @return
     */
    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
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
