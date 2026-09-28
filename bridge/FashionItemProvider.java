package bridge;

import domain.FashionCategory;
import domain.FashionItem;
import java.util.List;

public interface FashionItemProvider {
    List<FashionItem> fetchItems(FashionCategory category);
}