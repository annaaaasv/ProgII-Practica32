package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAfegirExemplar extends JDialog {
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtTitol;
    private JTextField txtAutor;
    private JCheckBox chkAdmetPL;
    private JButton btnAcceptar;
    private JButton btnCancelar;
    private JLabel etAutor;
    private JLabel etTitol;
    private JLabel etId;
    private Adaptador adaptador;


    public FrmAfegirExemplar(AppBiblioUB parent, Adaptador adaptador) {
        super(parent, "Afegir exemplar", true);
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setSize(500, 300);
        setLocationRelativeTo(parent);


        btnAcceptar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText().trim();
                String titol = txtTitol.getText().trim();
                String autor = txtAutor.getText().trim();
                boolean admetPL = chkAdmetPL.isSelected();

                if(id.isEmpty() || titol.isEmpty() || autor.isEmpty()){
                    JOptionPane.showMessageDialog(FrmAfegirExemplar.this,
                            "Tots els camps són obligatoris");
                    return;
                }

                try{
                    adaptador.afegirExemplar(id, titol, autor, admetPL);
                    dispose();
                }catch(BiblioException ex){
                    JOptionPane.showMessageDialog(FrmAfegirExemplar.this, ex.getMessage());
                }

            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
