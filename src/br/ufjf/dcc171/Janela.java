package br.ufjf.dcc171;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
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
    private JanelaPedidos janelaPedidos = new JanelaPedidos();
    //private Integer contadorMesas = 4;

    public Janela(List<Mesa> sampleData) throws HeadlessException {
        super("Sistema de Gestão de Pedidos - Lanchonete");

        this.mesas = sampleData;
        lstMesas.setModel(new MesasListModel(mesas));
        add(new JScrollPane(lstMesas), BorderLayout.CENTER);
        add(new JScrollPane(lstPedidos), BorderLayout.EAST);
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        painel1.add(btnAddMesa);
        painel1.add(btnExcluirMesa);
        painel1.add(btnAddPedido);
        painel1.add(btnExcluirItemPedido);
        painel1.add(btnFecharConta);
        add(painel1, BorderLayout.WEST);
        
        janelaPedidos.setJanela(this);
        
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

        btnAddPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Mesa selected = lstMesas.getSelectedValue();
                if(selected == null)
                {
                    JOptionPane.showMessageDialog(null, "Selecione a mesa que fez o pedido!!", "Selecione uma mesa", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JanelaPedidos jp = new JanelaPedidos();
                    jp.setSize(360,200);
                    jp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    jp.setLocationRelativeTo(null);
                    jp.setVisible(true);
                }
            }
        });
        
        btnAddMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //contadorMesas++;
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
                if(selected != null)
                {
                    mesas.remove(lstMesas.getSelectedValue());
                    lstMesas.clearSelection();
                    lstMesas.updateUI();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Selecione a mesa que deseja excluir antes", "Selecione uma mesa!!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        btnExcluirItemPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Pedido selected = lstPedidos.getSelectedValue();
                if(selected == null)
                {
                    JOptionPane.showMessageDialog(null, "Selecione o pedido que deseja excluir!!", "Selecione um pedido", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
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
                if(selected == null)
                {
                    JOptionPane.showMessageDialog(null, "Selecione a mesa que terá a conta fechada!!", "Selecione uma mesa", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    
                }
            }
        });
 
    }
    
    public void addPedido(Pedido p) {
        lstMesas.getSelectedValue().getPedidos().add(p);
        lstMesas.updateUI();
        lstPedidos.updateUI();
    }
}
