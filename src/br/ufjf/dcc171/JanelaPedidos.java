package br.ufjf.dcc171;

import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaPedidos extends JFrame {

    private final JLabel etiqueta1 = new JLabel("Selecione um item");
    private final JComboBox<String> opcoes = new JComboBox<>(new String[] {"X-Burger - 2,50", "X-Frango - 3,50", "X-Bacon - 4,50"});
    private final JLabel etiqueta2 = new JLabel("Informe a quantidade");
    private final JTextField txtQuantidade = new JTextField(5);
    private final JButton btnPedido = new JButton("Fazer Pedido");
    
    public JanelaPedidos() throws HeadlessException {
        super("Pedido");
        
        //JPanel painel = new JPanel(new GridBagLayout());
        Box vertical = Box.createVerticalBox();
        
        btnPedido.setEnabled(false);
        vertical.add(etiqueta1);
        vertical.add(opcoes);
        vertical.add(etiqueta2);
        vertical.add(txtQuantidade);
        vertical.add(btnPedido);
        add(vertical);
    }
}
