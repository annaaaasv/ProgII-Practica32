package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioPrestecs extends JDialog {
    private JPanel contentPane;
    private JButton btnAfegir;
    private JList<String> listPrestecs;
    private JButton btnRetornar;
    private JCheckBox chkNoRetornats;
    private Adaptador adaptador;

    public FrmGestioPrestecs(AppBiblioUB parent, Adaptador adaptador) {
        super(parent, "Gestió préstecs", true);
        this.adaptador = adaptador;
        setContentPane(contentPane);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        btnRetornar.setEnabled(false);

        actualitzarLlista();

        btnAfegir.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirPrestec frm = new FrmAfegirPrestec(parent, adaptador);
                frm.setVisible(true);
                actualitzarLlista();
            }
        });
        btnRetornar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos = listPrestecs.getSelectedIndex();

                try{
                    adaptador.retornarPrestec(pos, chkNoRetornats.isSelected());
                    actualitzarLlista();
                }catch(BiblioException ex){
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, ex.getMessage());
                }
            }
        });
        chkNoRetornats.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                actualitzarLlista();
            }
        });
        listPrestecs.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnRetornar.setEnabled(listPrestecs.getSelectedIndex() != -1);
            }
        });
    }

    public void actualitzarLlista(){
        DefaultListModel<String> model = new DefaultListModel<>();
        if(chkNoRetornats.isSelected()){
            for(String p : adaptador.recuperarPrestecsNoRetornats()){
                model.addElement(p);
            }
        }else{
            for(String p : adaptador.recuperarPrestecs()){
                model.addElement(p);
            }
        }
        listPrestecs.setModel(model);
    }


}
