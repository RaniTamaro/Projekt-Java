package tetris;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

//Okienko dialogowe proszące o podanie swojej nazwy
public class Start extends JDialog implements ActionListener, KeyListener
{   
    private JTextField nazwa = new JTextField(20);
    private JButton ok;
    private PrintWriter wyjscie;
    
    public String getNazwaGracza(){
        return nazwa.getText();
    }
    
    public Start(JFrame okno, PrintWriter wyjscie)
    {
        super(okno);
        this.setTitle("Połącz z serwerem");
        this.wyjscie = wyjscie;
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
        
        JLabel podajNazwe = new JLabel("Połączono z przeciwnikiem. Podaj nazwę użytkownika:");
        podajNazwe.setHorizontalAlignment(JLabel.CENTER);
        
        ok = new JButton("Ok");
        ok.setFocusTraversalKeysEnabled(true);
        ok.addActionListener(this);

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

    public void zakonczOkienko(){
        wyjscie.println("Nazwa gracza");
        wyjscie.println(nazwa.getText());
        dispose();
        Tetris.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        zakonczOkienko();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) { zakonczOkienko(); }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    
}
