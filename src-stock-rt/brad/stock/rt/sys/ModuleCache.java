package brad.stock.rt.sys;

import brad.stock.sdk.api.StockModule;

import java.util.Iterator;
import java.util.TreeSet;

public class ModuleCache implements Iterable<StockModule> {

    private TreeSet<StockModule> moduleList;

    {
        moduleList = new TreeSet<>();
    }

    public void add(StockModule stockModule) {
        moduleList.add(stockModule);
    }

    @Override
    public Iterator<StockModule> iterator() {
        return moduleList.iterator();
    }
}
