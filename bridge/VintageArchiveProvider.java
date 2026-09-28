package bridge;

import domain.FashionCategory;
import domain.FashionItem;
import java.util.ArrayList;
import java.util.List;

public class VintageArchiveProvider implements FashionItemProvider {
    private final List<FashionItem> items = new ArrayList<>();

    public VintageArchiveProvider() {
        items.add(new FashionItem("VNT-101", "Jean Paul Gaultier", "1996 Cyberpunk Mesh Top", 2100.0, FashionCategory.TOP));
        items.add(new FashionItem("VNT-102", "Helmut Lang", "1998 Painter Denim Jeans", 1200.0, FashionCategory.BOTTOM));
        items.add(new FashionItem("VNT-103", "Yohji Yamamoto", "1995 Pour Homme Long Coat", 4100.0, FashionCategory.OUTERWEAR));
    }

    @Override
    public List<FashionItem> fetchItems(FashionCategory category) {
        return items.stream()
                .filter(item -> item.category() == category)
                .toList();
    }
}