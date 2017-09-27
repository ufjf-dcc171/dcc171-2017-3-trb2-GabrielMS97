package br.ufjf.dcc171;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Janela extends JFrame {

    private final JButton addMesa = new JButton("Adicionar Mesa");
    private final JButton excluirMesa = new JButton("Excluir Mesa");
    private final JButton addPedido = new JButton("Fazer Pedido");
    private final JButton excluirItemPedido = new JButton("Excluir item do pedido");
    private final JButton fecharPedido = new JButton("Fechar Pedido");
    private final JPanel painel1 = new JPanel(new GridLayout(5, 1));

    public Janela() throws HeadlessException {
        super("Sistema de Gestão de Pedidos - Lanchonete");

        painel1.add(addMesa);
        painel1.add(excluirMesa);
        painel1.add(addPedido);
        painel1.add(excluirItemPedido);
        painel1.add(fecharPedido);
        add(painel1, BorderLayout.WEST);

        addPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JanelaPedidos jp = new JanelaPedidos();
                jp.setSize(300,300);
                jp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jp.setLocationRelativeTo(null);
                jp.setVisible(true);
            }
        });
    }

}
