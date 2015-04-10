package kryptografia2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Archiwum extends JFrame {

    /**
     * Konstruktor klasy Archiwum
     * @param klucze 
     */
    public Archiwum(String klucze) {
        super("Archiwum");
        String[] archiwum = klucze.split("\n");
        JList lista = new JList(archiwum);
        lista.setSelectedIndex(1);
        
        add(new JLabel()); 
        add(new JScrollPane(lista), BorderLayout.CENTER);

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList wyborLista = (JList) mouseEvent.getSource();
                
                if (mouseEvent.getClickCount() == 2) {
                    int index = wyborLista.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = wyborLista.getModel().getElementAt(index);
                        new Kryptografia2(o.toString());
                        dispose();
                    }
                }
            }
        };
        lista.addMouseListener(mouseListener);

        setSize(350, 350);
        setVisible(true);
    }
}
