package bridge;

import domain.FashionCategory;
import domain.FashionItem;
import java.util.ArrayList;
import java.util.List;

public class BrandArchiveProvider implements FashionItemProvider {
    private final List<FashionItem> items = new ArrayList<>();

    public BrandArchiveProvider() {
        items.add(new FashionItem("BND-01", "Balenciaga", "2001 Runway Oversized Blazer", 3500.0, FashionCategory.OUTERWEAR));
        items.add(new FashionItem("BND-02", "Vivienne Westwood", "1993 Corset Top", 2800.0, FashionCategory.TOP));
        items.add(new FashionItem("BND-03", "Maison Margiela", "1999 Tabi Boots", 1900.0, FashionCategory.FOOTWEAR));
        items.add(new FashionItem("BND-04", "Valentino", "2005 Silk Tailored Trousers", 1400.0, FashionCategory.BOTTOM));
    }

    @Override
    public List<FashionItem> fetchItems(FashionCategory category) {
        return items.stream()
                .filter(item -> item.category() == category)
                .toList();
    }
}