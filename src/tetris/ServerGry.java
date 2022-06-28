package tetris;

import java.io.*;
import java.net.*;
import java.util.*;

class OpisGracza
{
    PrintWriter wyjscie;
    String nazwa;
    int wynik;
    
    public OpisGracza(String nazwa, PrintWriter wyjscie)
    {
        this.nazwa = nazwa;
        this.wyjscie = wyjscie;
        wynik = 0;
    }
    
    public void setWynik(int wynik){
        this.wynik = wynik;
    }
    
    public int getWynik(){
        return wynik;
    }
}

class ObslugaGracza extends Thread
{
    private Socket socket;
    private ObslugaGracza przeciwnik;
    private BufferedReader wejscie;
    private PrintWriter wyjscie;
    private OpisGracza gracz;
    private OpisGracza wygrany;
    
    static ArrayList<OpisGracza> gracze = new ArrayList<>();
    
    public ObslugaGracza(Socket socket)
    {
        try{
            this.socket = socket;
            wejscie = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            wyjscie = new PrintWriter(
                    new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                            true);
            wyjscie.println("Wiadomosc systemowa");
            wyjscie.println("Oczekuje na połączenie z przeciwnikiem");
        }
        catch (Exception ex) {System.out.println("Blad przy tworzeniu strumieni");}
    }
    
    public void setPrzeciwnik(ObslugaGracza przeciwnik){
        this.przeciwnik = przeciwnik;
    }
    
    public void przeslijPrzeciwnikowi(PrintWriter out, String wynikPrzekaz){
        przeciwnik.gracz.wyjscie.println(wynikPrzekaz);
    }
    
    public String sprawdzZwyciezce(){
        
        if(gracz.getWynik() < przeciwnik.gracz.getWynik() || gracz.getWynik() == przeciwnik.gracz.getWynik()){
            wygrany = przeciwnik.gracz;
        }
        else{
            wygrany = gracz;
        }
        
        return wygrany.nazwa;
    }
    
    public void zapiszGracza(){
        
    }
    
    public void run()
    {
        String nazwaGracza = null;
        try
        {
            wyjscie.println("Połączono z przeciwnikiem");
            
            while(true){
                String wiadomosc = wejscie.readLine();
                
                if(wiadomosc.startsWith("Nazwa")){
                    nazwaGracza = wejscie.readLine();
                    System.out.println("Gracz "+nazwaGracza +" zalogowal sie.");
                    
                    gracz = new OpisGracza(nazwaGracza, wyjscie);
                    gracze.add(gracz);
                }
                

                if(wiadomosc.startsWith("Obaj")){
                    przeslijPrzeciwnikowi(wyjscie, "Przeciwnik znaleziony");
                    przeslijPrzeciwnikowi(wyjscie, gracz.nazwa);
                }
                
                
                if (wiadomosc.startsWith("Wynik")){
                    String otrzymanyWynik = wejscie.readLine();
                    przeslijPrzeciwnikowi(wyjscie, "Wynik");
                    if(otrzymanyWynik.equalsIgnoreCase("Koniec")){
                        for(OpisGracza gr : gracze){
                            gr.wyjscie.println("Zakończono grę");
                            gr.wyjscie.println(sprawdzZwyciezce());
                        }
                        gracze.remove(gracz);
                        gracze.remove(przeciwnik);
                    }
                    else{
                        gracz.setWynik(Integer.parseInt(otrzymanyWynik));
                        przeslijPrzeciwnikowi(wyjscie, otrzymanyWynik);
                    }
                }

            }
        }
        catch(Exception e){
            System.out.println("Problem w komunikacji z klientem");
        }
        finally{
            try
            {
                wejscie.close();
                wyjscie.close();
                socket.close();
            } catch(Exception e) {
                System.out.println("Problem z zamknięciem połączenia");
            }
        }
    }
}

public class ServerGry 
{
    public static final int serverPort = 7777;
    ServerSocket s;
    private ObslugaGracza gracze[];
    private static ObslugaGracza gracz;
    
    public ServerGry()
    {
         try {
            s = new ServerSocket(serverPort);
            System.out.println("Serwer uruchomiony");
            gracze = new ObslugaGracza[2];
        } 
        catch (Exception e) {
            System.out.println("Nie można utworzyć gniazda");
            System.exit(1);
        }
    }
    
    public void dzialaj()
    {
        Socket socket = null;
        try {
            while(true){
                ObslugaGracza gracz1 = new ObslugaGracza(s.accept());
                ObslugaGracza gracz2 = new ObslugaGracza(s.accept());
                gracz1.setPrzeciwnik(gracz2);
                gracz2.setPrzeciwnik(gracz1);

                gracz1.start();
                gracz2.start();
            }
        } 
        catch (Exception e) {
            System.out.println("Problem połączenia z klientem");
        } finally {
            try {
                s.close();
                System.out.println("Koniec pracy serwera");
            } catch (Exception e) {
                System.out.println("Problem z zamykaniem połączenia");
            }
        }
    }
    
    public static void main(String[] args) 
    {
        ServerGry server = new ServerGry();
        server.dzialaj();
    }
}
