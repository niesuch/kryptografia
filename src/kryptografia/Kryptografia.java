package kryptografia;

import AES.AES;
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

import javax.xml.bind.DatatypeConverter;

public class Kryptografia extends JFrame {

    private static JTextField kluczWejscie;
    private static JTextArea textWyjscie, textWejscie, textStat, textKrypto, textWzorcowa;
    private JScrollPane scrollText, scrollText2, scrollTextStat, scrollTextKrypto, scrollTextWzorcowa;
    private JButton szyfrujPrzycisk, deszyfrujPrzycisk, archiwumKluczyPrzycisk,
            autorzyPrzycisk, zapiszSzyfrPrzycisk, wczytajSzyfrPrzycisk, zamianaMiejscPrzycisk,
            uzupelnijPrzycisk, wczytajWzorcowaPrzycisk, wygenerujWzorcowaPrzycisk, zapiszWzorcowaPrzycisk;
    private PrzyciskSzyfruj obslugaSzyfruj;
    private PrzyciskDeszyfruj obslugaDeszyfruj;

    private PrzyciskAutorzy obslugaAutorzy;
    private PrzyciskZapiszSzyfr obslugaZapiszSzyfr;
    private PrzyciskWczytajSzyfr obslugaWczytajSzyfr;
    private PrzyciskZamianaMiejsc obslugaZamianaMiejsc;

    long start = 0;
    long end = 0;

    /**
     * Konstruktor głównej klasy, inicjalizowanie wyglądu programu
     */
    public Kryptografia() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Bład podczas ładowania wyglądu okna.");
        }
        
        this.setResizable(false);
        
        Container okno = getContentPane();
        okno.setLayout(null);

        textWejscie = new JTextArea("", 50, 895);
        scrollText2 = new JScrollPane(textWejscie);
        scrollText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWejscie.setLineWrap(true);
        textWejscie.setWrapStyleWord(true);
        scrollText2.setLocation(5, 52);
        scrollText2.setSize(500, 180);
        okno.add(scrollText2);

        textWyjscie = new JTextArea("", 50, 895);
        scrollText = new JScrollPane(textWyjscie);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textWyjscie.setLineWrap(true);
        textWyjscie.setWrapStyleWord(false);
        //textWyjscie.setEditable(false);
        scrollText.setLocation(5, 320);
        scrollText.setSize(500, 180);
        okno.add(scrollText);

        kluczWejscie = new JTextField(3);
        kluczWejscie.setText("5qw8sd4h");
        kluczWejscie.setLocation(115, 243);
        kluczWejscie.setSize(200, 40);
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

        autorzyPrzycisk = new JButton("Autorzy");
        obslugaAutorzy = new PrzyciskAutorzy();
        autorzyPrzycisk.addActionListener(obslugaAutorzy);
        autorzyPrzycisk.setLocation(325, 522);
        autorzyPrzycisk.setSize(150, 20);
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
        zamianaMiejscPrzycisk.setLocation(10, 250);
        zamianaMiejscPrzycisk.setSize(20, 27);
        okno.add(zamianaMiejscPrzycisk);

        setTitle("AES");

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

        setSize(520, 585);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Obsługa przycisku "Szyfruj"
     *
     * @param a
     * @return
     */
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
     *
     */
    private class PrzyciskSzyfruj implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String Msg = textWejscie.getText();
            String tKey = kluczWejscie.getText();

            start = System.nanoTime();
            new AES();

            byte[] enc = AES.szyfruj(Msg.getBytes(), tKey.getBytes());

            String CMsg = new String(enc);
            CMsg = toHexString(enc);

            textWyjscie.append(CMsg);
            end = System.nanoTime();
            JOptionPane.showMessageDialog(null, "Czas wykonania szyfrowania w sekundach: " + (end - start) / 1000000000.0);

        }
    }

    /**
     * Obsługa przycisku "Deszyfruj"
     */
    private class PrzyciskDeszyfruj implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String Msg = textWejscie.getText().replace("\r\n", "");
            String tKey = kluczWejscie.getText().replace("\r", "");
            start = System.nanoTime();
            new AES();
            byte[] CMsg = toByteArray(Msg);

            byte[] dec = AES.odszyfruj(CMsg, tKey.getBytes());

            textWyjscie.append(new String(dec));
            end = System.nanoTime();
            JOptionPane.showMessageDialog(null, "Czas wykonania deszyfrowania w sekundach: " + (end - start) / 1000000000.0);

        }
    }

    /**
     * Obsługa przycisku "Autorzy"
     */
    private class PrzyciskAutorzy implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Autorzy:"
                    + "\n- Goniprowski Mateusz"
                    + "\n- Niesłuchowski Kamil"
                    + "\n- Załuska Paweł");
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
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        Kryptografia obiekt = new Kryptografia();
        obiekt.setLocationRelativeTo(null);
    }
}
