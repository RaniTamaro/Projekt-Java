package tetris;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

//Okienko dialogowe w oczekiwaniu na połączenie drugiego gracza
public class StartBrakPolaczenia extends JDialog 
{   
    private JTextField nazwa = new JTextField(20);
    JLabel podajNazwe = new JLabel();
    private JButton ok;
    
    public String getNazwaGracza(){
        return nazwa.getText();
    }
    
    public StartBrakPolaczenia(JFrame okno)
    {
        super(okno);
        this.setTitle("Połącz z serwerem");
        init();
    }
    
    public void init()
    {
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JLabel tytul = new JLabel("Łączenie z serwerem");
        tytul.setFont(new Font("Tahoma", 0, 30));
        tytul.setHorizontalAlignment(JLabel.CENTER);
        
        podajNazwe.setText("Oczekuje przeciwnika");
        podajNazwe.setHorizontalAlignment(JLabel.CENTER);
        
        ok = new JButton("Ok");
        ok.setEnabled(false);
        
        nazwa.setEnabled(false);
        
        JPanel p1 = new JPanel(new GridLayout(4, 1));
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        
        p2.add(nazwa);
        p3.add(ok);
        
        p1.add(tytul);
        p1.add(podajNazwe);
        p1.add(p2);
        p1.add(p3);
        
        add(p1);
    }
    
}
