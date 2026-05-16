package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAfegirPrestec extends JDialog {
    private JPanel contentPane;
    private JComboBox<String> cmbUsu;
    private JComboBox<String> cmbExem;
    private JButton btnAcceptar;
    private JButton btnCancelar;
    private JCheckBox chkLlarg;
    private JLabel etUsu;
    private JLabel etExem;
    private Adaptador adaptador;

    public FrmAfegirPrestec(AppBiblioUB parent, Adaptador adaptador) {
        super(parent, "Afegir préstec", true);
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setSize(500, 300);
        setLocationRelativeTo(parent);

        for(String e : adaptador.recuperarExemplars()){
            cmbExem.addItem(e);
        }
        for(String u: adaptador.recuperarUsuaris()){
            cmbUsu.addItem(u);
        }

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
        btnAcceptar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int posExem = cmbExem.getSelectedIndex();
                int posUsu = cmbUsu.getSelectedIndex();
                boolean esLlarg = chkLlarg.isSelected();

                try{
                    adaptador.afegirPrestec(posExem, posUsu, esLlarg);
                    dispose();
                }catch(BiblioException ex){
                    JOptionPane.showMessageDialog(FrmAfegirPrestec.this, ex.getMessage());
                }
            }
        });
    }
}
