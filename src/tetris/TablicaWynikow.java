package tetris;

import java.io.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class TablicaWynikow extends javax.swing.JFrame {

    private DefaultTableModel dt;
    private String nazwaPliku = "TablicaWynikow.bin";
    private int wynik;
    private String nazwaGracza;
    
    //Tablica wyników przechowuje dane w pliku
    public TablicaWynikow() {
        initComponents();
        dt = (DefaultTableModel) tabelaWynikow.getModel();
        wczytajPlik();
        wyczyscTablice();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaWynikow = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        BMenu.setText("Menu Główne");
        BMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMenuActionPerformed(evt);
            }
        });

        tabelaWynikow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Gracz", "Wynik"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaWynikow.setFocusable(false);
        jScrollPane1.setViewportView(tabelaWynikow);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 266, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMenuActionPerformed
        this.setVisible(false);
        Tetris.pokazMenu();
    }//GEN-LAST:event_BMenuActionPerformed

    public void dodajGracza(String nazwaGracza, int wynik)
    {
        this.wynik = wynik;
        this.nazwaGracza = nazwaGracza;
        this.setVisible(true);
        
        dt.addRow(new Object[] {this.nazwaGracza, this.wynik} );
        wczytajPlik();
        zapiszDoPliku();
    }
    
    private void zapiszDoPliku()
    {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(nazwaPliku, true))) {
                out.writeInt(wynik);
                out.writeUTF(nazwaGracza);
            }
            catch (IOException ex){ System.out.println(ex); }
    }
    
    public void wczytajPlik()
    {
        try(DataInputStream in = new DataInputStream(new FileInputStream(nazwaPliku))){
            while(true){ 
                int wyn = in.readInt();
                String nazwaGr = in.readUTF();
                
                dt.addRow(new Object[]{nazwaGr, wyn} );
            }
        }
        catch (EOFException ex){ }
        catch (IOException ex){ }
    }
    
    public void wyczyscTablice()
    {
        int rows = dt.getRowCount();
        
        for(int i = rows - 1;  i >= 0; i--)
        {
            dt.removeRow(i);
        }
    }
    
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
            java.util.logging.Logger.getLogger(TablicaWynikow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablicaWynikow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablicaWynikow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablicaWynikow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablicaWynikow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaWynikow;
    // End of variables declaration//GEN-END:variables
}
