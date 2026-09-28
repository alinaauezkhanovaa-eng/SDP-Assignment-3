package selector;

import adapter.LegacyArchiveAdapter;
import bridge.BrandArchiveProvider;
import bridge.FashionItemProvider;
import bridge.VintageArchiveProvider;
import legacy.LegacyArchive;

public class ProviderSelector {

    public static FashionItemProvider selectProvider(String sourceType, LegacyArchive legacyArchive) {
        if (sourceType == null) {
            throw new IllegalArgumentException("Source type cannot be null");
        }

        return switch (sourceType.trim().toLowerCase()) {
            case "brand" -> new BrandArchiveProvider();
            case "vintage" -> new VintageArchiveProvider();
            case "legacy" -> new LegacyArchiveAdapter(legacyArchive);
            default -> throw new IllegalArgumentException("Unknown archive provider type: " + sourceType);
        };
    }
}