package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioUsuaris extends JDialog {
    private JPanel contentPane;
    private JButton btnAfegir;
    private JList<String> listUsuaris;
    private Adaptador adaptador;

    public FrmGestioUsuaris(AppBiblioUB parent, Adaptador adaptador) {
        super(parent, "Gestió usuaris", true);
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setSize(700, 400);
        setLocationRelativeTo(parent);

        actualitzarLlista();

        btnAfegir.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirUsuari frm = new FrmAfegirUsuari(parent, adaptador);
                frm.setVisible(true);
                actualitzarLlista();
            }
        });
    }

    public void actualitzarLlista(){
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String u : adaptador.recuperarUsuaris()){
            model.addElement(u);
        }
        listUsuaris.setModel(model);
    }
}
