package br.ufjf.dcc171;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class Persistencia {
    private File arquivo = new File("funcionamento.txt");
    private FileWriter historico;
    
    public Persistencia() {
        if (!arquivo.exists())
            try {
                historico = new FileWriter("funcionamento.txt");        
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível criar o diretório desejado");
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
