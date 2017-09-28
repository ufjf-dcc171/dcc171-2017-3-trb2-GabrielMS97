package br.ufjf.dcc171;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaPedidos extends JFrame {

    private final JLabel etiqueta1 = new JLabel("Selecione um item");
    private final JComboBox<String> opcoes = new JComboBox<>(new String[]{"Hambúrguer - R$2,50","X-Burger - R$3,00", "X-Frango - R$3,50", "X-Bacon - R$4,50","X-Pernil - R$5,00", "X-Picanha - R$6,00" ,"X-Tudo - R$6,50", "Coca-Cola 500ml - R$4,00", "Coca-Cola 750ml - R$ 5,50", "Fanta 500ml - R$3,50", "Pepsi 500ml - R$3,50", "Sprite 500ml - R$3,00", "Sukita 500ml - R$2,50", "Suco Natural 500ml - R$2,00"});
    private final JLabel etiqueta2 = new JLabel("Informe a quantidade");
    private final JTextField txtQuantidade = new JTextField(5);
    private final JButton btnPedido = new JButton("Fazer Pedido");
    private final JLabel etiquetaF1 = new JLabel("");
    private final JLabel etiquetaF2 = new JLabel("");
    

    public JanelaPedidos() throws HeadlessException {
        super("Pedido");

        JPanel painel = new JPanel(new GridLayout(3,2));

        //btnPedido.setEnabled(false);
        
        painel.add(etiqueta1);
        painel.add(opcoes);
        painel.add(etiquetaF1);
        painel.add(etiquetaF2);
        painel.add(etiqueta2);
        painel.add(txtQuantidade);
        add(btnPedido, BorderLayout.SOUTH);
        add(painel, BorderLayout.NORTH);
        
        btnPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if("".equals(txtQuantidade.getText()) || "0".equals(txtQuantidade.getText()))
                {
                    JOptionPane.showMessageDialog(null, "O seu pedido está vazio","Pedido Vazio!!" ,JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    txtQuantidade.setText("");
                    txtQuantidade.requestFocus();
                }              
            }
        });
        
    }
}
