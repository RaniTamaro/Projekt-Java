package klocki;

import tetris.Klocki;

public class KsztaltI extends Klocki
{
    public KsztaltI()
    {
        super(new int[][] {{1, 1, 1, 1}});
    }
    
    @Override
    public void obroc()
    {
        super.obroc();
        
        if(this.getSzerokosc() == 1){
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        }
        else{
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}
