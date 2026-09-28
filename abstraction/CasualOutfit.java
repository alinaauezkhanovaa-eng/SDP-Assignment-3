package abstraction;

import bridge.FashionItemProvider;
import domain.FashionCategory;
import domain.FashionItem;

import java.util.ArrayList;
import java.util.List;

public class CasualOutfit extends Outfit {

    public CasualOutfit(FashionItemProvider provider) {
        super(provider);
    }

    @Override
    public List<FashionItem> assembleLook() {
        List<FashionItem> assembledLook = new ArrayList<>();

        List<FashionItem> tops = provider.fetchItems(FashionCategory.TOP);
        if (!tops.isEmpty()) assembledLook.add(tops.get(0));

        List<FashionItem> bottoms = provider.fetchItems(FashionCategory.BOTTOM);
        if (!bottoms.isEmpty()) assembledLook.add(bottoms.get(0));

        return assembledLook;
    }
}