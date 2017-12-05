package br.ufjf.dcc171;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PedidoDAO {

    private static Scanner input;
    private File arquivo = new File("pedido.txt");

    public PedidoDAO() {
    }

    public void salvar(ArrayList<Pedido> pedido) throws IOException {
        try {
            FileWriter fw = new FileWriter("item.txt", false);
            BufferedWriter arquivoPedido = new BufferedWriter(fw);
            for (Pedido p : pedido) {
                arquivoPedido.write(p.getQtdItens() + "|||" + p.getItem() + "|||" + p.getPreco() + "|||" + p.getCriado() + "|||");
                arquivoPedido.newLine();
            }
            arquivoPedido.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pedido> buscar() throws IOException {
        ArrayList<Pedido> pedido = new ArrayList<>();
        input = new Scanner(new FileReader("item.txt")).useDelimiter("|||");
        input.useLocale(Locale.ENGLISH);
        try {
            while (input.hasNext()) {
                Pedido p = new Pedido();
                p.setQtdItens(input.nextInt());
                p.setItem(input.next());
                //p.setPreco(input.nextDouble());
                //p.setCriado(input.next());
                pedido.add(p);
            }
        } catch (NoSuchElementException elementException) {
            System.out.println("Todas as leituras de item foram feitas.");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating.");
        }
        input.close();
        return pedido;
    }

    public boolean isVazio() throws IOException {
        try {
            Scanner input;
            input = new Scanner("pedido.txt");
            if (arquivo.exists() && arquivo.length() != 0) {
                input.close();
                return false;
            } else {
                input.close();
                return true;
            }
        } catch (SecurityException securityException) {
            System.err.println("Permissão de leitura negada.");
            System.exit(1);
        }
        return false;
    }

}
