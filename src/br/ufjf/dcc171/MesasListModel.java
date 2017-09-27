package br.ufjf.dcc171;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class MesasListModel implements ListModel<Mesa> {
    
    private final List<Mesa> mesas;
    private final List<ListDataListener> dataListeners;

    public MesasListModel(List<Mesa> mesas) {
        this.mesas = mesas;
        this.dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return mesas.size();
    }

    @Override
    public Mesa getElementAt(int i) {
        return mesas.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
        this.dataListeners.add(ll);
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        this.dataListeners.remove(ll);
    }

}
