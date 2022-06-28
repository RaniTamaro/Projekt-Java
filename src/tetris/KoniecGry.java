package tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Klasa tworzy okno dialogowe informujące o zakończeniu gry
public class KoniecGry extends JDialog implements ActionListener
{
    private JLabel JLWynik, JLNazwaGracza, JLStatus;
    private JButton zamknij;
    private int wynik;
    private String nazwaGracza;
    
    public static TablicaWynikow tablicaWynikow;
    
    public KoniecGry(JFrame okno)
    {
        super(okno);
        this.setTitle("Koniec gry");
        
    }
    
    public void init()
    {
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JLabel tytul = new JLabel("Koniec Gry");
        tytul.setFont(new Font("Tahoma", 0, 36));
        tytul.setHorizontalAlignment(JLabel.CENTER);
        
        zamknij = new JButton("Zamknij");
        zamknij.setSize(100, 100);
        zamknij.addActionListener(this);
        
        JPanel p1 = new JPanel(new GridLayout(5, 1));
        JPanel p2 = new JPanel();
        
        p2.add(zamknij);
        
        p1.add(tytul);
        p1.add(JLStatus);
        p1.add(JLWynik);
        p1.add(JLNazwaGracza);
        p1.add(p2);
        
        add(p1);
    }
    
    public void wyswietlOkno(int wynik, String nazwaGracza, String wygral)
    {
        this.wynik = wynik;
        this.nazwaGracza = nazwaGracza;
        
        JLWynik = new JLabel("Wynik: " + wynik);
        JLWynik.setHorizontalAlignment(JLabel.CENTER);
        JLNazwaGracza = new JLabel("Gracz: " + nazwaGracza);
        JLNazwaGracza.setHorizontalAlignment(JLabel.CENTER);
        JLStatus = new JLabel("Nazwa zwycięzcy: " + wygral);
        JLStatus.setHorizontalAlignment(JLabel.CENTER);
        
        init();
        setVisible(true);
    }
    
    public void zamknijOkno(){
        tablicaWynikow = new TablicaWynikow();
        System.out.println(nazwaGracza);
        tablicaWynikow.dodajGracza(nazwaGracza, wynik);
        dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        zamknijOkno();
    }
}
