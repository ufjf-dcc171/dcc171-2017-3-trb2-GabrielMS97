package br.ufjf.dcc171;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Janela extends JFrame {

    private final JButton btnAddMesa = new JButton("Adicionar Mesa");
    private final JButton btnExcluirMesa = new JButton("Excluir Mesa");
    private final JButton btnAddPedido = new JButton("Fazer Pedido");
    private final JButton btnExcluirItemPedido = new JButton("Excluir item do pedido");
    private final JButton btnFecharConta = new JButton("Fechar Conta");
    private final JPanel painel1 = new JPanel(new GridLayout(5, 1));
    private final List<Mesa> mesas;
    private final JList<Mesa> lstMesas = new JList<>(new DefaultListModel<>());
    private final JList<Pedido> lstPedidos = new JList<>(new DefaultListModel<>());
    private final JanelaPedidos jp = new JanelaPedidos();

    public Janela(List<Mesa> sampleData) throws HeadlessException {
        super("Sistema de Gestão de Pedidos - Lanchonete");
        setMinimumSize(new Dimension(800,350));

        this.mesas = sampleData;
        lstMesas.setModel(new MesasListModel(mesas));
        lstMesas.setPreferredSize(new Dimension(120,250));
        add(new JScrollPane(lstMesas), BorderLayout.WEST);
        add(new JScrollPane(lstPedidos), BorderLayout.CENTER);
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        painel1.add(btnAddMesa);
        painel1.add(btnExcluirMesa);
        painel1.add(btnAddPedido);
        painel1.add(btnExcluirItemPedido);
        painel1.add(btnFecharConta);
        add(painel1, BorderLayout.EAST);

        lstMesas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                Mesa selected = lstMesas.getSelectedValue();
                if (selected != null) {
                    System.out.println(selected);
                    lstPedidos.setModel(new PedidosListModel(selected.getPedidos()));
                } else {
                    lstPedidos.setModel(new DefaultListModel<>());
                }
            }
        });
        jp.setJanelaMesas(this);

        btnAddPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Mesa selected = lstMesas.getSelectedValue();
                if (selected == null) {
                    JOptionPane.showMessageDialog(null, "Selecione a mesa que fez o pedido!!", "Selecione uma mesa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    jp.setSize(360, 200);
                    jp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    jp.setLocationRelativeTo(null);
                    jp.setVisible(true);
                    jp.solicitaPedido();
                }
            }
        });

        btnAddMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer num;
                num = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da mesa"));
                Mesa m = new Mesa(num, "Mesa " + num);
                mesas.add(m);
                lstMesas.updateUI();
            }
        });

        btnExcluirMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Mesa selected = lstMesas.getSelectedValue();
                if (selected != null) {
                    mesas.remove(lstMesas.getSelectedValue());
                    lstMesas.clearSelection();
                    lstMesas.updateUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione a mesa que deseja excluir antes", "Selecione uma mesa!!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnExcluirItemPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Pedido selected = lstPedidos.getSelectedValue();
                if (selected == null) {
                    JOptionPane.showMessageDialog(null, "Selecione o pedido que deseja excluir!!", "Selecione um pedido", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    lstMesas.getSelectedValue().getPedidos().remove(selected);
                    lstMesas.updateUI();
                    lstPedidos.updateUI();
                }
            }
        });

        btnFecharConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Mesa selected = lstMesas.getSelectedValue();
                if (selected == null) {
                    JOptionPane.showMessageDialog(null, "Selecione a mesa que terá a conta fechada!!", "Selecione uma mesa", JOptionPane.INFORMATION_MESSAGE);
                } 
                else
                {
                    if(selected.getPedidos().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Você não pode fechar a conta de uma mesa vazia!" , "Mesa vazia!!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    System.out.println(selected.getConta());
                }
            }
        });

    }

    public void addPedido(Pedido p) {
        double val = p.getQtdItens() * p.getPreco();
        lstMesas.getSelectedValue().setConta(val);
        lstMesas.getSelectedValue().getPedidos().add(p);
        lstMesas.updateUI();
        lstPedidos.updateUI();
        jp.setVisible(false);
    }
}
