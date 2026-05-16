package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppBiblioUB extends JFrame {

    private JPanel panelPrincipal;
    private JButton btnGestUsu;
    private JButton btnCarregar;
    private JButton btnGuardar;
    private JButton btnGestPres;
    private JButton btnGestExem;
    private Adaptador adaptador;

    public AppBiblioUB(){
        adaptador = new Adaptador();
        setTitle("App Biblio UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setSize(600, 500);
        setLocationRelativeTo(null);

        btnGestUsu.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioUsuaris frm = new FrmGestioUsuaris(AppBiblioUB.this, adaptador);
                frm.setVisible(true);
            }
        });
        btnGestExem.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioExemplars frm = new FrmGestioExemplars(AppBiblioUB.this, adaptador);
                frm.setVisible(true);
            }
        });
        btnGestPres.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioPrestecs frm = new FrmGestioPrestecs(AppBiblioUB.this, adaptador);
                frm.setVisible(true);
            }
        });
    }


    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            AppBiblioUB app = new AppBiblioUB();
            app.setVisible(true);
        });
    }

}
