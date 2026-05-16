package prog2.vista;

import javax.swing.*;
import prog2.adaptador.Adaptador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAfegirUsuari extends JDialog {
    private JPanel contentPane;
    private JTextField txtEmail;
    private JTextField txtNom;
    private JTextField txtAdreca;
    private JCheckBox chkEstudiant;
    private JButton btnAcceptar;
    private JButton btnCancelar;
    private JLabel etEmail;
    private JLabel etAdreca;
    private JLabel etNom;
    private Adaptador adaptador;

    public FrmAfegirUsuari(AppBiblioUB parent, Adaptador adaptador) {
        super(parent, "Afegir usuari", true);
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setSize(600,300);
        setLocationRelativeTo(parent);


        btnAcceptar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String nom = txtNom.getText().trim();
                String adreca = txtAdreca.getText().trim();
                boolean estudiant = chkEstudiant.isSelected();

                if(email.isEmpty() || nom.isEmpty() || adreca.isEmpty()){
                    JOptionPane.showMessageDialog(FrmAfegirUsuari.this,
                            "Tots els camps són obligatoris");
                    return;
                }

                try{
                    adaptador.afegirUsuari(email, nom, adreca, estudiant);
                    dispose();
                }catch(BiblioException ex){
                    JOptionPane.showMessageDialog(FrmAfegirUsuari.this, ex.getMessage());
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
