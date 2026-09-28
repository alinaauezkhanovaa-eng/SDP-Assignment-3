import abstraction.CasualOutfit;
import abstraction.FormalOutfit;
import abstraction.Outfit;
import bridge.FashionItemProvider;
import domain.FashionItem;
import legacy.LegacyArchive;
import selector.ProviderSelector;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== VINTAGE FASHION LOOK CURATOR SYSTEM ===\n");

        LegacyArchive externalLegacySystem = new LegacyArchive();

        System.out.println("--- Scenario 1: Assembling Formal Outfit from Brand Archives ---");
        FashionItemProvider brandProvider = ProviderSelector.selectProvider("brand", externalLegacySystem);
        Outfit formalLook = new FormalOutfit(brandProvider);
        printOutfitDetails(formalLook);

        System.out.println("--- Scenario 2: Assembling Casual Outfit from Vintage Archives ---");
        FashionItemProvider vintageProvider = ProviderSelector.selectProvider("vintage", externalLegacySystem);
        Outfit casualLook = new CasualOutfit(vintageProvider);
        printOutfitDetails(casualLook);

        System.out.println("--- Scenario 3: Assembling Formal Outfit from Legacy System (via Adapter) ---");
        FashionItemProvider legacyAdapterProvider = ProviderSelector.selectProvider("legacy", externalLegacySystem);
        formalLook.setProvider(legacyAdapterProvider);
        printOutfitDetails(formalLook);
    }

    private static void printOutfitDetails(Outfit outfit) {
        List<FashionItem> items = outfit.assembleLook();
        for (FashionItem item : items) {
            System.out.printf("  - [%s] %s (%s) - $%.2f\n",
                    item.category(), item.name(), item.brand(), item.price());
        }
        System.out.printf("Total Estimated Value: $%.2f\n\n", outfit.calculateTotalValue(items));
    }
}