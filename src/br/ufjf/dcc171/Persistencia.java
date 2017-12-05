package br.ufjf.dcc171;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Persistencia {

    private File arquivo = new File("historico.txt");
    private FileWriter historico;

    public Persistencia(int numeroMesa, double valorConta, String garcon, List<Pedido> listaPedidos, String abertura, String fechamento) {
        if (!arquivo.exists()) {
            try {
                historico = new FileWriter("historico.txt");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível criar o diretório desejado", "ERRO", JOptionPane.ERROR_MESSAGE);
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
            escritorArquivo.append("Pedido na mesa " + numeroMesa + " || ");
            escritorArquivo.append("Valor final: R$" + d.format(valorConta) + " || ");
            escritorArquivo.append("10% do garçon: " + garcon);
            escritorArquivo.append("\n");
            escritorArquivo.append("Itens Pedidos " + listaPedidos);
            escritorArquivo.append("\n");
            escritorArquivo.append("Aberto em " + abertura);
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

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public FileWriter getHistorico() {
        return historico;
    }

    public void setHistorico(FileWriter historico) {
        this.historico = historico;
    }

}
