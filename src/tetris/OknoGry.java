package tetris;

import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import javax.swing.*;

//Klasa jest odpowiedzialna za wyświetlanie głównego okna z grą
public class OknoGry extends JFrame
{
    private PoleGry pole;
    private WatkiGry watek;
    private PrintWriter wyjscie;
    private String wynikPrzeciwnika;
    private String wynikGracza;
    
    public OknoGry() {
        initComponents();
        
        pole = new PoleGry(Plansza);
        this.add( pole );
        this.getContentPane().setLayout(null);
        
        initControls();
        
        start();
    }
    
    //Ustawienie działania przycisków
    public void initControls()
    {
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        
        am.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                pole.przesunPrawo();
            }
        });
        
        am.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                pole.przesunLewo();
            }
        });
        
        am.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                pole.obrocKlocek();
            }
        });
        
        am.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                pole.puscKlocek();
            }
        });
    }
    
    public void start()
    {
        pole.initSitkaTla();
        watek = new WatkiGry(pole, this, wyjscie);
        watek.start();
    }
    
    public void aktualizujWynik(int wynik)
    {
        this.wynikGracza = String.valueOf(wynik);
        JLWynik.setText("Wynik: "+wynik);
    }
    
    public void aktualizujLevel(int level)
    {
        JLLevel.setText("Level: "+level);
    }
    
    public void wyswietlJDialog(int wynik)
    {
        JLWynikKoncowy.setText("Wynik: "+ wynik);
        KoniecGry.pack();
        KoniecGry.setLocationRelativeTo(this);
        KoniecGry.setVisible(true);
    }
    
    public void setStrumien(PrintWriter wyjscie){
        this.wyjscie = wyjscie;
    }
    
    public void setNazwaPrzecinika(String nazwa){
        this.JLNazwaPrzeciwnika.setText("Nazwa: " + nazwa);
    }
    
    public void setWynikPrzeciwnika(String wynik){
        this.wynikPrzeciwnika = wynik;
        this.JLWynikPrzeciwnika.setText("Wynik: " + wynik);
    }
    
    public void setStatusPrzeciwnika(String status){
        this.JLStatusPrzeciwnika.setText("Status: " + status);
    }
    
    public void zatrzymajGre(String wygral){
        watek.zatrzymaj(wygral);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        KoniecGry = new javax.swing.JDialog();
        BZamknijOkno = new javax.swing.JButton();
        jLabel = new javax.swing.JLabel();
        JLWynikKoncowy = new javax.swing.JLabel();
        JLNazwa = new javax.swing.JLabel();
        Plansza = new javax.swing.JPanel();
        JLWynik = new javax.swing.JLabel();
        JLLevel = new javax.swing.JLabel();
        BMenu = new javax.swing.JButton();
        JLPrzeciwnik = new javax.swing.JLabel();
        JLNazwaPrzeciwnika = new javax.swing.JLabel();
        JLWynikPrzeciwnika = new javax.swing.JLabel();
        JLStatusPrzeciwnika = new javax.swing.JLabel();

        KoniecGry.setResizable(false);

        BZamknijOkno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BZamknijOkno.setText("Zamknij");
        BZamknijOkno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BZamknijOknoActionPerformed(evt);
            }
        });

        jLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel.setText("Koniec Gry");

        JLWynikKoncowy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JLWynikKoncowy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLWynikKoncowy.setText("Wynik: ");

        JLNazwa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JLNazwa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLNazwa.setText("Nazwa:");

        javax.swing.GroupLayout KoniecGryLayout = new javax.swing.GroupLayout(KoniecGry.getContentPane());
        KoniecGry.getContentPane().setLayout(KoniecGryLayout);
        KoniecGryLayout.setHorizontalGroup(
            KoniecGryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KoniecGryLayout.createSequentialGroup()
                .addGroup(KoniecGryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KoniecGryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(KoniecGryLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(KoniecGryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLWynikKoncowy, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLNazwa, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 118, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(KoniecGryLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(BZamknijOkno, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KoniecGryLayout.setVerticalGroup(
            KoniecGryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KoniecGryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JLWynikKoncowy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JLNazwa)
                .addGap(18, 18, 18)
                .addComponent(BZamknijOkno)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setResizable(false);

        Plansza.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Plansza.setPreferredSize(new java.awt.Dimension(331, 462));

        javax.swing.GroupLayout PlanszaLayout = new javax.swing.GroupLayout(Plansza);
        Plansza.setLayout(PlanszaLayout);
        PlanszaLayout.setHorizontalGroup(
            PlanszaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );
        PlanszaLayout.setVerticalGroup(
            PlanszaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        JLWynik.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLWynik.setText("Wynik: 0");
        JLWynik.setFocusable(false);

        JLLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLLevel.setText("Level: 1");
        JLLevel.setFocusable(false);

        BMenu.setText("Menu Główne");
        BMenu.setFocusable(false);
        BMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMenuActionPerformed(evt);
            }
        });

        JLPrzeciwnik.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JLPrzeciwnik.setText("Przeciwnik:");
        JLPrzeciwnik.setFocusable(false);

        JLNazwaPrzeciwnika.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLNazwaPrzeciwnika.setText("Nazwa:");
        JLNazwaPrzeciwnika.setFocusable(false);

        JLWynikPrzeciwnika.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLWynikPrzeciwnika.setText("Wynik: 0");
        JLWynikPrzeciwnika.setFocusable(false);

        JLStatusPrzeciwnika.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JLStatusPrzeciwnika.setText("Status: ");
        JLStatusPrzeciwnika.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Plansza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(JLPrzeciwnik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLNazwaPrzeciwnika, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLWynikPrzeciwnika, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLWynik, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JLStatusPrzeciwnika, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BMenu)
                        .addGap(29, 29, 29)
                        .addComponent(JLWynik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JLLevel)
                        .addGap(18, 18, 18)
                        .addComponent(JLPrzeciwnik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLNazwaPrzeciwnika)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLWynikPrzeciwnika)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLStatusPrzeciwnika))
                    .addComponent(Plansza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMenuActionPerformed
        watek.interrupt();
        this.setVisible(false);
        Tetris.pokazMenu();
    }//GEN-LAST:event_BMenuActionPerformed

    private void BZamknijOknoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BZamknijOknoActionPerformed
        KoniecGry.dispose();
    }//GEN-LAST:event_BZamknijOknoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OknoGry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OknoGry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OknoGry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OknoGry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OknoGry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BMenu;
    private javax.swing.JButton BZamknijOkno;
    private javax.swing.JLabel JLLevel;
    private javax.swing.JLabel JLNazwa;
    private javax.swing.JLabel JLNazwaPrzeciwnika;
    private javax.swing.JLabel JLPrzeciwnik;
    private javax.swing.JLabel JLStatusPrzeciwnika;
    private javax.swing.JLabel JLWynik;
    private javax.swing.JLabel JLWynikKoncowy;
    private javax.swing.JLabel JLWynikPrzeciwnika;
    private javax.swing.JDialog KoniecGry;
    private javax.swing.JPanel Plansza;
    private javax.swing.JLabel jLabel;
    // End of variables declaration//GEN-END:variables
}
