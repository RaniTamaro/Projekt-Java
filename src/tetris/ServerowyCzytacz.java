package tetris;

import java.io.*;


public class ServerowyCzytacz extends Thread
{
    private BufferedReader in;
    private static Tetris okno;
    private static OknoGry oknoGry;
    private static WatkiGry watki;
    private static StartBrakPolaczenia start;
    
    public ServerowyCzytacz(Tetris okno, OknoGry oknoGry){
        this.okno = okno;
        this.oknoGry = oknoGry;
        
        oknoGry.setNazwaPrzecinika("Wczytuję");
        oknoGry.setWynikPrzeciwnika("0");
        oknoGry.setStatusPrzeciwnika("Gra w toku");
        try {
            in = new BufferedReader(
                    new InputStreamReader(okno.socket.getInputStream()));
        } catch (IOException e) {}
    }
    
    public void zakonczGre(){
        try{
            System.out.println("Dostalem zakończenie!");
            String zwyciezca = in.readLine();
            oknoGry.zatrzymajGre(zwyciezca);
            System.out.println("Zwycięża: " + zwyciezca);
            
        }catch (Exception e){ }
    }
    
    public void run()
    {
        try{
            String wiadomosc = in.readLine();
            while(true){
                String line = in.readLine();
                
                System.out.println("Mam wiadomosc: " + line);
                if(line.startsWith("Połączono")){
                    okno.polaczonoPrzeciwnika();
                }
                
                if(line.startsWith("Przeciwnik")){
                    String nazwaPrzeciwnika = in.readLine();
                    oknoGry.setNazwaPrzecinika(nazwaPrzeciwnika);
                }

                if (line.startsWith("Wynik")){
                    String przekazanyWynik = in.readLine();
                    System.out.println("Otrzymalem wynik" + przekazanyWynik);
                    if(przekazanyWynik.equalsIgnoreCase("Koniec") || przekazanyWynik == null){
                        oknoGry.setStatusPrzeciwnika("Koniec");
    //                    break;
                    }
                    else if(przekazanyWynik.startsWith("Zakończono")){
                        zakonczGre();
                        break;
                    }
                    else{
                        oknoGry.setWynikPrzeciwnika(przekazanyWynik);
                    }
                }
                
                if(line.startsWith("Zakończono")){
                    zakonczGre();
                    break;
                }
            }
        }
        catch (Exception e) { }
    }
}
