package tetris;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;


//Klasa główna gry
public class Tetris 
{
    private static OknoGry oknoGry;
    private static MenuGlowne menuGlowne;
    private static TablicaWynikow tablicaWyników;
    private static KoniecGry koniecGry;
    private static StartBrakPolaczenia startBP;
    private static Start start;
    
    Socket socket = null;
    private PrintWriter out;
    private ServerowyCzytacz czytacz;
    private static boolean polaczenie = false;
    
    public Tetris()
    {
        try {
            socket = new Socket("localhost", 7777);
            out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()),
                        true);
            
            System.out.println("Klient połączony");
            
            oknoGry = new OknoGry();
            oknoGry.setStrumien(out);
            czytacz = new ServerowyCzytacz(this, oknoGry);
            czytacz.start();
            startBP = new StartBrakPolaczenia(oknoGry);
            start = new Start(oknoGry, out);
            if(!polaczenie){
                startBP.setVisible(true);
            }
            else{
                start.setVisible(true);
            }
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Nieudane połączenie z serwerem", "Ostrzeżenie", JOptionPane.WARNING_MESSAGE);
            System.exit(1);
        }
    }
    
    //Metody łączące wszystkie okienka ze sobą
    public static void start()
    {
        oknoGry.setVisible(true);
        oknoGry.start();
    }
    
    public static void pokazTabliceWynikow()
    {
        try{
            tablicaWyników.wyczyscTablice();
        }
        catch(Exception e){}
        tablicaWyników.wczytajPlik();
        tablicaWyników.setVisible(true);
    }
    
    public static void pokazMenu()
    {
        menuGlowne.setVisible(true);
    }
    
    public static void gameOver(int wynik, String wygral)
    {
        String nazwaGracza = start.getNazwaGracza();
        oknoGry.setVisible(false);
        koniecGry.wyswietlOkno(wynik, nazwaGracza, wygral);
    }
    
    public void polaczonoPrzeciwnika(){
        polaczenie = true;
        try{
            startBP.dispose();
            start.setVisible(true);
        } catch (Exception e){ }
    }
    
    public static void main(String[] args) 
    {   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                menuGlowne = new MenuGlowne();
                tablicaWyników = new TablicaWynikow();
                koniecGry = new KoniecGry(oknoGry);

                Tetris t = new Tetris();
            }
        });
    }
}
