package abstraction;

import bridge.FashionItemProvider;
import domain.FashionItem;
import java.util.ArrayList;
import java.util.List;

public abstract class Outfit {
    protected FashionItemProvider provider;

    public Outfit(FashionItemProvider provider) {
        this.provider = provider;
    }

    public void setProvider(FashionItemProvider provider) {
        this.provider = provider;
    }

    public abstract List<FashionItem> assembleLook();

    public double calculateTotalValue(List<FashionItem> items) {
        return items.stream().mapToDouble(FashionItem::price).sum();
    }
}