package br.ufjf.dcc171;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Janela extends JFrame {

    private final JButton addMesa = new JButton("Adicionar Mesa");
    private final JButton excluirMesa = new JButton("Excluir Mesa");
    private final JButton addPedido = new JButton("Fazer Pedido");
    private final JButton excluirItemPedido = new JButton("Excluir item do pedido");
    private final JButton fecharPedido = new JButton("Fechar Pedido");
    private final JPanel painel1 = new JPanel(new GridLayout(5, 1));
    private final List<Mesa> mesas;
    private final JList<Mesa> lstMesas = new JList<>(new DefaultListModel<>());
    private final JList<Pedido> lstPedidos = new JList<>(new DefaultListModel<>());

    public Janela(List<Mesa> sampleData) throws HeadlessException {
        super("Sistema de Gestão de Pedidos - Lanchonete");

        this.mesas = sampleData;
        lstMesas.setModel(new MesasListModel(mesas));
        add(new JScrollPane(lstMesas), BorderLayout.CENTER);
        add(new JScrollPane(lstPedidos), BorderLayout.EAST);
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        painel1.add(addMesa);
        painel1.add(excluirMesa);
        painel1.add(addPedido);
        painel1.add(excluirItemPedido);
        painel1.add(fecharPedido);
        add(painel1, BorderLayout.WEST);
        
        lstMesas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                Mesa selected = lstMesas.getSelectedValue();
                if (selected != null) {
                    System.out.println(selected);
                    lstPedidos.setModel((new PedidosListModel(selected.getPedidos())));
                } else {
                    lstPedidos.setModel((new DefaultListModel<>()));
                }
            }
        });

        addPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JanelaPedidos jp = new JanelaPedidos();
                jp.setSize(300,200);
                jp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jp.setLocationRelativeTo(null);
                jp.setVisible(true);
            }
        });
    }
}
