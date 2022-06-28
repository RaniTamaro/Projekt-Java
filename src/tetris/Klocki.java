package tetris;

import java.awt.*;
import java.util.Random;

public class Klocki {
    private int[][] ksztalt;
    private Color kolor;
    private int x, y;
    private int[][][] ksztalty;
    private int obecnaPozycja;
    
    //Tablica dostępnych kolorów klocka
    private Color[] dostepneKolory = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.PINK};
    
    public Klocki(int[][] ksztalt){
        this.ksztalt = ksztalt;
        initKsztalt();
    }
    
    private void initKsztalt()
    {
        ksztalty = new int[4][][];
        
        for(int i = 0; i <4; i++)
        {
            int r = ksztalt[0].length;
            int c = ksztalt.length;
            
            ksztalty[i] = new int[r][c];
            
            for(int y = 0; y < r; y++){
                for(int x = 0; x < c; x++)
                {
                    ksztalty[i][y][x] = ksztalt[c - x - 1][y];
                }
            }
            ksztalt = ksztalty[i];
        }
    }
    
    public void pojawienie(int szerokoscPolaGry)
    {
        //Ustawienie obrotu klocka w sposób losowy
        Random r = new Random();
        obecnaPozycja = r.nextInt(ksztalt.length);
        
        ksztalt = ksztalty[obecnaPozycja];
        y = - getWysokosc();
        x = r.nextInt(szerokoscPolaGry - getSzerokosc());
        
        //Losowanie koloru klocka
        kolor = dostepneKolory[r.nextInt(dostepneKolory.length)];
    }
    
    public int[][] getKsztalt() { return ksztalt; }
    
    public Color getKolor() { return kolor; }
    
    public int getWysokosc() { return ksztalt.length; }
    
    public int getSzerokosc() { return ksztalt[0].length; }
    
    public int getX() { return x; }
    
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    
    public void setY(int y) { this.y = y; }
    
    public void opadanie() { y++; }
    
    public void ruchLewo() { x--; }
    
    public void ruchPrawo() { x++; }
    
    public void obroc() 
    {
        obecnaPozycja++;
        if(obecnaPozycja > 3)
        {
            obecnaPozycja = 0;
        }
        ksztalt = ksztalty[obecnaPozycja];
    }
    
    public void cofnijObrot(){
        obecnaPozycja--;
        if(obecnaPozycja < 0){
            obecnaPozycja = 0;
        }
        
        ksztalt = ksztalty[obecnaPozycja];
    }
    
    public int getKoncowaKrawedz() { return y + getWysokosc(); }
    
    public int getLewaKrawedz() { return x; }
    
    public int getPrawaKrawedz() { return x + getSzerokosc(); }
}
