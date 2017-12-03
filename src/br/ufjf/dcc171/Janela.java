package br.ufjf.dcc171;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final JPanel painel1 = new JPanel(new GridLayout(6, 1));
    private final List<Mesa> mesas;
    private final JList<Mesa> lstMesas = new JList<>(new DefaultListModel<>());
    private final JList<Pedido> lstPedidos = new JList<>(new DefaultListModel<>());
    private final JanelaPedidos jp = new JanelaPedidos();
    private final JButton btnHistoricoPedidos = new JButton("Histórico de Pedidos");
    //private final StringBuilder escritorArquivo = new StringBuilder();
    private String garcom = "";

    public Janela(List<Mesa> sampleData) throws HeadlessException {
        super("Sistema de Gestão de Pedidos - Lanchonete");
        setMinimumSize(new Dimension(800, 350));

        this.mesas = sampleData;
        lstMesas.setModel(new MesasListModel(mesas));
        lstMesas.setPreferredSize(new Dimension(120, 250));
        add(new JScrollPane(lstMesas), BorderLayout.WEST);
        add(new JScrollPane(lstPedidos), BorderLayout.CENTER);
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        painel1.add(btnAddMesa);
        painel1.add(btnExcluirMesa);
        painel1.add(btnAddPedido);
        painel1.add(btnExcluirItemPedido);
        painel1.add(btnFecharConta);
        painel1.add(btnHistoricoPedidos);
        add(painel1, BorderLayout.EAST);

        lstMesas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                Mesa selected = lstMesas.getSelectedValue();
                if (selected != null) {
                    //System.out.println(selected);
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
                Calendar c = Calendar.getInstance();
                Date data = c.getTime();
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String s = fmt.format(data);
                selected.setAbertura(s);
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
                try {
                    Integer num;
                    num = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da mesa"));
                    Mesa m = new Mesa(num, "Mesa " + num);
                    mesas.add(m);
                    lstMesas.updateUI();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um número inteiro!!", "Erro!", JOptionPane.ERROR_MESSAGE);
                }
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
                String fechamento = "";
                if (selected == null) {
                    JOptionPane.showMessageDialog(null, "Selecione a mesa que terá a conta fechada!!", "Selecione uma mesa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (selected.getPedidos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Você não pode fechar a conta de uma mesa vazia!", "Mesa vazia!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int resp = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja fechar a conta? \n" + "Uma vez fechada a conta, não será possível adicionar mais pedidos.", "Fechamento do pedido", JOptionPane.YES_NO_OPTION);
                        if (resp == JOptionPane.YES_OPTION) {
                            int g = JOptionPane.showConfirmDialog(null, "Podemos adicionar os 10% do Garçon?", "Gorjeta", JOptionPane.YES_NO_OPTION);
                            if (g == JOptionPane.YES_OPTION) {
                                garcom = "SIM";
                                double val = (selected.getConta() / 10);
                                selected.setConta(val);
                                //System.out.println(selected.getConta());
                                int n = selected.getNumero();
                                DecimalFormat d = new DecimalFormat();
                                d.setMaximumFractionDigits(2);
                                d.setMinimumFractionDigits(2);
                                Calendar c = Calendar.getInstance();
                                Date data = c.getTime();
                                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                String s = fmt.format(data);
                                fechamento = s;
                                JOptionPane.showMessageDialog(null, "Conta fechada em: " + s + "\nValor a pagar: R$" + d.format(selected.getConta()), "Conta Fechada", JOptionPane.INFORMATION_MESSAGE);
                                mesas.remove(selected);
                                Mesa m = new Mesa(n, "Mesa " + n);
                                mesas.add(m);
                                lstMesas.clearSelection();
                                lstMesas.updateUI();
                                lstPedidos.updateUI();
                            } else {
                                //System.out.println(selected.getConta());
                                garcom = "NÃO";
                                int n = selected.getNumero();
                                DecimalFormat d = new DecimalFormat();
                                d.setMaximumFractionDigits(2);
                                d.setMinimumFractionDigits(2);
                                Calendar c = Calendar.getInstance();
                                Date data = c.getTime();
                                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                String s = fmt.format(data);
                                fechamento = s;
                                JOptionPane.showMessageDialog(null, "Conta fechada em: " + s + "\nValor a pagar: R$" + d.format(selected.getConta()), "Conta Fechada", JOptionPane.INFORMATION_MESSAGE);
                                mesas.remove(selected);
                                Mesa m = new Mesa(n, "Mesa " + n);
                                mesas.add(m);
                                lstMesas.clearSelection();
                                lstMesas.updateUI();
                                lstPedidos.updateUI();
                            }
                        }
                    }

                    FileWriter fw;
                    try {
                        fw = new FileWriter("historico.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        StringBuilder escritorArquivo = new StringBuilder();
                        DecimalFormat d = new DecimalFormat();
                        d.setMinimumFractionDigits(2);
                        d.setMaximumFractionDigits(2);
                        Calendar c = Calendar.getInstance();
                        //Date data = c.getTime();
                        //SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        //String s = fmt.format(data);
                        escritorArquivo.append("Pedido na mesa " + selected.getNumero() + " || ");
                        escritorArquivo.append("Valor final: R$" + d.format(selected.getConta()) + " || ");
                        escritorArquivo.append("10% do garçon: " + garcom);
                        escritorArquivo.append("\n");
                        escritorArquivo.append("Itens Pedidos " + selected.getPedidos());
                        escritorArquivo.append("\n");
                        escritorArquivo.append("Aberto em " + selected.getAbertura());
                        escritorArquivo.append("\n");
                        escritorArquivo.append("Fechado em " + fechamento);
                        escritorArquivo.append("\n");
                        escritorArquivo.append("\n");
                        bw.write(escritorArquivo.toString());
                        bw.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        btnHistoricoPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JanelaHistorico jh = new JanelaHistorico();
                jh.setLocationRelativeTo(null);
                jh.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                jh.setVisible(true);
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
