package br.ufjf.dcc171;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;

public class Trabalho1Lab3 {

    public static void main(String[] args) {
        Janela j = new Janela(getSampleData());
        j.setSize(800, 350);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
    
    private static List<Mesa> getSampleData()
    {
        Mesa mesa1 = new Mesa(1, "Mesa 1");
        Mesa mesa2 = new Mesa(2, "Mesa 2");
        Mesa mesa3 = new Mesa(3, "Mesa 3");
        Mesa mesa4 = new Mesa(4, "Mesa 4");
        
        Pedido p1 = new Pedido(3, "AA", 3.0, new Date());
        mesa1.getPedidos().add(p1);
        
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa1);
        mesas.add(mesa2);
        mesas.add(mesa3);
        mesas.add(mesa4);
        
        return mesas;
    }

}
