package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.border.*;
import klocki.*;

public class PoleGry extends JPanel
{
    private int rzedy;
    private int kolumny =  10;
    private int wielkoscKomorki;
    private int wynik = 0;
    private Color[][] tlo;
    
    private Klocki klocek;
    
    //Tablica wygenerowanych klocków
    private Klocki[] klocki;
    
    public PoleGry(JPanel panel){
        this.setBounds(panel.getBounds());
        this.setBackground(panel.getBackground());
        this.setBorder(panel.getBorder());
        
        wielkoscKomorki = this.getBounds().width / kolumny;
        rzedy = this.getBounds().height / wielkoscKomorki;
        
        klocki = new Klocki[]{ new KsztaltI(), new KsztaltJ(), new KsztaltL(), new KsztaltO(), new KsztaltS(), new KsztaltT(), new KsztaltZ() };
    }
    
    public void initSitkaTla()
    {
        tlo = new Color[rzedy][kolumny];
    }
    
    public void stworzKlocek()
    {
        Random r = new Random();
        klocek = klocki[r.nextInt(klocki.length)];
        klocek.pojawienie(kolumny);
    }
    
    public boolean blokPozaRama()
    {
        if(klocek.getY() < 0)
        {
            klocek = null;
            return true;
        }
        return false;
    }
    
    public boolean opadanieBloku()
    {
        try
        {
            if (dotykaDolu()== false)
            {
                return false;
            }
            
            klocek.opadanie();
            repaint();
        }
        catch(Exception e) {}
        
        return true;
    }
    
    public void przesunPrawo()
    {
        if(klocek == null) return;
        if(!dotykaPrawej()) return;
        
        klocek.ruchPrawo();
        repaint();
    }
    
    public void przesunLewo()
    {
        if(klocek == null) return;
        if(!dotykaLewej()) return;
        
        klocek.ruchLewo();
        repaint();
    }
    
    public void puscKlocek()
    {
        if(klocek == null) return;
        while(dotykaDolu()){
            klocek.opadanie();
        }
        
        repaint();
    }
    
    public void obrocKlocek()
    {
        if(klocek == null) return;
        klocek.obroc();
        
        if(klocek.getLewaKrawedz() < 0) klocek.setX(0);
        if(klocek.getPrawaKrawedz() >= kolumny) klocek.setX(kolumny - klocek.getSzerokosc());
        if(klocek.getKoncowaKrawedz() >= rzedy) klocek.setY(rzedy - klocek.getWysokosc());
        
        int[][] ksztalt = klocek.getKsztalt();
        int wysokosc = klocek.getWysokosc();
        int szerokosc = klocek.getSzerokosc();
        
        repaint();
    }
    
    public boolean dotykaKlocka()
    {
        int wysokosc = klocek.getWysokosc();
        int szerokosc = klocek.getSzerokosc();
        int[][] ksztalt = klocek.getKsztalt();
        
        for(int row = 0; row < wysokosc; row++){
            for(int col = 0; col < szerokosc; col++){
                if(ksztalt[row][col] != 0){
                    
                }
            }
        }

        return true;
    }
    
    private boolean dotykaDolu()
    {
        try
        {
            if (klocek.getKoncowaKrawedz() == rzedy)
            {
                return false;
            }

            int[][] ksztalt = klocek.getKsztalt();
            int sz = klocek.getSzerokosc();
            int wys = klocek.getWysokosc();

            for(int col = 0; col < sz; col++)
            {
                for(int row = wys - 1; row >= 0; row--)
                {
                    if(ksztalt[row][col] != 0){
                        int x = col + klocek.getX();
                        int y = row + klocek.getY() + 1;

                        if(y < 0) break;

                        if(tlo[y][x] != null) return false;
                        break;
                    }
                }
            }
        }
        catch(Exception e) {}
        
        return true;
    }
    
    private boolean dotykaLewej()
    {
        if(klocek.getLewaKrawedz() == 0){
            return false;
        }
        
        int[][] ksztalt = klocek.getKsztalt();
        int sz = klocek.getSzerokosc();
        int wys = klocek.getWysokosc();
        
        for(int row = 0; row < wys; row++)
        {
            for(int col = 0; col < sz; col++)
            {
                if(ksztalt[row][col] != 0){
                    int x = col + klocek.getX() - 1;
                    int y = row + klocek.getY();
                    
                    if(y < 0) break;
                    
                    if(tlo[y][x] != null) return false;
                    break;
                }
            }
        }
        
        return true;
    }
    
    private boolean dotykaPrawej()
    {
        if(klocek.getPrawaKrawedz() == kolumny){
            return false;
        }
        
        int[][] ksztalt = klocek.getKsztalt();
        int sz = klocek.getSzerokosc();
        int wys = klocek.getWysokosc();
        
        for(int row = 0; row < wys; row++)
        {
            for(int col = sz - 1; col >= 0; col--)
            {
                if(ksztalt[row][col] != 0){
                    int x = col + klocek.getX() + 1;
                    int y = row + klocek.getY();
                    
                    if(y < 0) break;
                    
                    if(tlo[y][x] != null) return false;
                    break;
                }
            }
        }
        
        return  true;
    }
    
    public int wyczyscLinie()
    {
        boolean zapelnionaLinia;
        int wyczyszczoneLinie = 0;
        
        for (int r = rzedy - 1; r >= 0; r--)
        {
            zapelnionaLinia = true;
            for (int c = 0; c < kolumny; c++)
            {
                if(tlo[r][c] == null)
                {
                    zapelnionaLinia = false;
                    break;
                }
            }
            
            if(zapelnionaLinia)
            {
                wyczyszczoneLinie++;
                czyszczenieLinii(r);
                przesunNaDol(r);
                czyszczenieLinii(0);
                
                r++;
                
                repaint();
            }
        }
        wynik += wyczyszczoneLinie;
        return wynik;
    }
    
    public void czyszczenieLinii(int r)
    {
        for(int i = 0; i < kolumny; i++)
        {
            tlo[r][i] = null;
        }
    }
    
    public void przesunNaDol(int r)
    {
        for(int row = r; row > 0; row--)
        {
            for(int col = 0; col < kolumny; col++)
            {
                tlo[row][col] = tlo[row - 1][col];
            }
        }
    }
    
    public void przeniescDoTla()
    {
        int[][] ksztalt = klocek.getKsztalt();
        int wysokosc = klocek.getWysokosc();
        int szerokosc = klocek.getSzerokosc();
        
        int xPos = klocek.getX();
        int yPos = klocek.getY();
        
        Color kolor = klocek.getKolor();
        
        for(int r = 0; r < wysokosc; r++)
        {
            for(int c = 0; c < szerokosc; c++)
            {
                
                if(ksztalt[r][c] == 1)
                {
                    tlo[r + yPos][c + xPos] = kolor;
                }
            }
        }
    }
    
    private void rysujKlocek(Graphics g){
        int wysokosc = klocek.getWysokosc();
        int szerokosc = klocek.getSzerokosc();
        Color kolorKlocka = klocek.getKolor();
        int[][] ksztal = klocek.getKsztalt();
        
        for (int row = 0; row < wysokosc; row++)
        {
            for (int col = 0; col < szerokosc; col++)
            {
                if (ksztal[row][col] == 1)
                {
                    int x = (klocek.getX() + col) * wielkoscKomorki;
                    int y = (klocek.getY() + row) * wielkoscKomorki;
                    
                    rysujKwadrat(g, kolorKlocka, x, y);
                }
            }
        }
    }
    
    private void rysujTlo(Graphics g)
    {
        Color kolor;
        
        for(int r = 0; r <rzedy; r++)
        {
            for(int c = 0; c < kolumny; c++)
            {
                kolor = tlo[r][c];
                
                
                if(kolor != null)
                {
                    int x = c * wielkoscKomorki;
                    int y = r * wielkoscKomorki;
                    
                    rysujKwadrat(g, kolor, x, y);
                }
            }
        }
    }
    
    private void rysujKwadrat(Graphics g, Color kolor, int x, int y)
    {
        g.setColor(kolor);
        g.fillRect(x, y, wielkoscKomorki, wielkoscKomorki);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, wielkoscKomorki, wielkoscKomorki);
    }
    
    //Nadpisanie metody pozwalającej na rysowanie kształtów
    @Override
    public void paintComponent(Graphics g)
    {
        //Odnoszę się do podstawowej metody rysującej komponent
        super.paintComponent(g);
        
        rysujTlo(g);
        rysujKlocek(g);
    }
}
