package abstraction;

import bridge.FashionItemProvider;
import domain.FashionCategory;
import domain.FashionItem;

import java.util.ArrayList;
import java.util.List;

public class FormalOutfit extends Outfit {

    public FormalOutfit(FashionItemProvider provider) {
        super(provider);
    }

    @Override
    public List<FashionItem> assembleLook() {
        List<FashionItem> assembledLook = new ArrayList<>();

        List<FashionItem> outerwear = provider.fetchItems(FashionCategory.OUTERWEAR);
        if (!outerwear.isEmpty()) assembledLook.add(outerwear.get(0));

        List<FashionItem> footwear = provider.fetchItems(FashionCategory.FOOTWEAR);
        if (!footwear.isEmpty()) assembledLook.add(footwear.get(0));

        return assembledLook;
    }
}