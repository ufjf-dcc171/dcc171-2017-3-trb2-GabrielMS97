package br.ufjf.dcc171;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaPedidos extends JFrame {

    private final JLabel etiqueta1 = new JLabel("Selecione um item");
    private final JComboBox<String> opcoes = new JComboBox<>(new String[]{"Hambúrguer - R$2,50", "X-Burger - R$3,00", "X-Frango - R$3,50", "X-Bacon - R$4,50", "X-Pernil - R$5,00", "X-Picanha - R$6,00", "X-Tudo - R$6,50", "Coca-Cola 500ml - R$4,00", "Coca-Cola 750ml - R$ 5,50", "Fanta 500ml - R$3,50", "Pepsi 500ml - R$3,50", "Sprite 500ml - R$3,00", "Sukita 500ml - R$2,50", "Suco Natural 500ml - R$2,00"});
    private final JLabel etiqueta2 = new JLabel("Informe a quantidade");
    private final JTextField txtQuantidade = new JTextField(5);
    private final JButton btnPedido = new JButton("Fazer Pedido");
    private final JLabel etiquetaF1 = new JLabel("");
    private final JLabel etiquetaF2 = new JLabel("");
    private String itens[] = {"Hambúrguer - R$2,50", "X-Burger - R$3,00", "X-Frango - R$3,50", "X-Bacon - R$4,50", "X-Pernil - R$5,00", "X-Picanha - R$6,00", "X-Tudo - R$6,50", "Coca-Cola 500ml - R$4,00", "Coca-Cola 750ml - R$ 5,50", "Fanta 500ml - R$3,50", "Pepsi 500ml - R$3,50", "Sprite 500ml - R$3,00", "Sukita 500ml - R$2,50", "Suco Natural 500ml - R$2,00"};
    private Double precos[] = {2.50, 3.00, 3.50, 4.50, 5.00, 6.00, 6.50, 4.00, 5.50, 3.50, 3.50, 3.00, 2.50, 2.00};
    private Janela janela;

    public JanelaPedidos() throws HeadlessException {
        super("Pedido");
        setMinimumSize(new Dimension(360,200));

        JPanel painel = new JPanel(new GridLayout(3, 2));

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
                try {
                    if ("".equals(txtQuantidade.getText()) || "0".equals(txtQuantidade.getText())) {
                        JOptionPane.showMessageDialog(null, "O seu pedido está vazio", "Pedido Vazio!!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int qtd;
                        int i = arrumaPedido();
                        JOptionPane.showMessageDialog(null, "Pedido Realizado: " + itens[i] + "\nQuantidade: " + txtQuantidade.getText() + "\nTotal: R$" + (precos[i] * Double.parseDouble(txtQuantidade.getText())), "Pedido", JOptionPane.INFORMATION_MESSAGE);

                        qtd = Integer.parseInt(txtQuantidade.getText());

                        Pedido p = new Pedido(null, null, precos[i], null);
                        p.setCriado(new Date());
                        p.setItem(itens[i]);
                        p.setQtdItens(Integer.parseInt(txtQuantidade.getText()));

                        janela.addPedido(p);

                        System.out.println(qtd + " " + itens[i] + " " + precos[i] + new Date());

                        txtQuantidade.setText("");
                        txtQuantidade.requestFocus();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite apenas valores numéricos inteiros!!" , "Valor incompatível", JOptionPane.ERROR_MESSAGE);
                }

            }

            private int arrumaPedido() {
                int i = 0;
                while (opcoes.getSelectedItem() != itens[i]) {
                    i++;
                }
                System.out.println(i);
                return i;
            }
        });

    }

    public void setJanelaMesas(Janela janela) {
        this.janela = janela;
    }

    void solicitaPedido() {
        setLocationRelativeTo(null);
        setVisible(true);
        txtQuantidade.setText("");
        txtQuantidade.requestFocus();
    }
}
