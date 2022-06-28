package tetris;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatkiGry extends Thread
{
    private PoleGry pole;
    private OknoGry okno;
    private int wynik = 0;
    private int level = 1;
    private int punktyNaLevel = 3;
    private int iterator = 1;
    
    private int predkosc = 1000;
    private int szybkoscLevel = 150;
    
    private PrintWriter wyjscie;
    
    public WatkiGry(PoleGry pole, OknoGry okno, PrintWriter wyjscie)
    {
        this.pole = pole;
        this.okno = okno;
        this.wyjscie = wyjscie;
        
        okno.aktualizujWynik(wynik);
        okno.aktualizujLevel(level);
    }
    
    public void wyslijWynik(String wynikServer){
        System.out.println("Przekazuje wynik: " +wynikServer);
        wyjscie.println("Wynik");
        wyjscie.println(wynikServer);
    }
    
    public void zatrzymaj(String wygral){
        Tetris.gameOver(wynik, wygral);
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            pole.stworzKlocek();
            while (pole.opadanieBloku())
            {
                try 
                {
                    Thread.sleep(predkosc);
                }
                catch (InterruptedException ex) { return; }
            }
            
            if(pole.blokPozaRama())
            {
                wyslijWynik("Koniec");
                break;
            }
            
            pole.przeniescDoTla();
            wynik = pole.wyczyscLinie();
            okno.aktualizujWynik(wynik);
            
            wyslijWynik(""+wynik);
            if(iterator == 1){
                wyjscie.println("Obaj gracz");
                iterator = 2;
            }
            
            int lvl = wynik / punktyNaLevel + 1;
            if(lvl > level)
            {
                level = lvl;
                okno.aktualizujLevel(level);
                predkosc -= szybkoscLevel;
            }
        }
    }
}
