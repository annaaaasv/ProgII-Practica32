package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioExemplars extends JDialog {
    private JPanel contentPane;
    private JButton btnAfegirExem;
    private JList<String> listExemplars;
    private Adaptador adaptador;

    public FrmGestioExemplars(AppBiblioUB parent, Adaptador adaptador) {
        super(parent, "Gestió Exemplars", true);
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        actualitzarLlista();

        btnAfegirExem.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirExemplar frm = new FrmAfegirExemplar(parent, adaptador);
                frm.setVisible(true);
                actualitzarLlista();
            }
        });
    }

    public void actualitzarLlista(){
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String e : adaptador.recuperarExemplars()){
            model.addElement(e);
        }
        listExemplars.setModel(model);
    }
}
