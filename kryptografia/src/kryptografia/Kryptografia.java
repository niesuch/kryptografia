package kryptografia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kryptografia extends JFrame {

    public Kryptografia() {
        super("Kryptografia");
        Container container = getContentPane();
        container.setLayout(null);
        
        JTextArea textAreal = new JTextArea();
        //textAreal.setPreferredSize(new Dimension(300, 100));
        container.add(textAreal);
        textAreal.setBounds(10, 10, 300, 150);
        
        JTextArea textArea2 = new JTextArea();
        //textArea2.setPreferredSize(new Dimension(100, 300));
        container.add(textArea2);
        textArea2.setBounds(320, 10, 100, 310);
        
        JTextArea textArea3 = new JTextArea();
        //textArea3.setPreferredSize(new Dimension(100, 300));
        container.add(textArea3);
        textArea3.setBounds(430, 10, 100, 310);
        
        JTextArea textArea4 = new JTextArea();
        //textArea4.setPreferredSize(new Dimension(100, 300));
        container.add(textArea4);
        textArea4.setBounds(540, 10, 100, 310);
        
        JTextArea textArea5 = new JTextArea();
        //textArea4.setPreferredSize(new Dimension(100, 300));
        container.add(textArea5);
        textArea5.setBounds(10, 170, 300, 150);

        JButton szyfrujButton = new JButton("Szyfruj");   
        container.add(szyfrujButton);
        szyfrujButton.setBounds(180, 350, 150, 30);
        
        JButton deszufrujButton = new JButton("Deszyfruj");   
        container.add(deszufrujButton);
        deszufrujButton.setBounds(340, 350, 150, 30);
        
        setSize(665, 450);
        setVisible(true);
    }

    public static void main(String args[]) {
        Kryptografia test = new Kryptografia();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
