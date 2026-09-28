package adapter;

import bridge.FashionItemProvider;
import domain.FashionCategory;
import domain.FashionItem;
import exception.ArchiveProviderException;
import legacy.LegacyArchive;
import legacy.LegacyDatabaseCorruptException;

import java.util.ArrayList;
import java.util.List;

public class LegacyArchiveAdapter implements FashionItemProvider {
    private final LegacyArchive legacyArchive;

    public LegacyArchiveAdapter(LegacyArchive legacyArchive) {
        if (legacyArchive == null) {
            throw new IllegalArgumentException("LegacyArchive instance cannot be null");
        }
        this.legacyArchive = legacyArchive;
    }

    @Override
    public List<FashionItem> fetchItems(FashionCategory category) {
        try {
            int numericCode = mapCategoryToNumericCode(category);
            String[] rawData = legacyArchive.queryRawDatabase(numericCode, true);

            List<FashionItem> items = new ArrayList<>();
            for (String record : rawData) {
                items.add(parseRawRecord(record, category));
            }
            return items;
        } catch (LegacyDatabaseCorruptException e) {
            throw new ArchiveProviderException("Failed to retrieve items from Legacy Archive for category: " + category, e);
        } catch (Exception e) {
            throw new ArchiveProviderException("Data parsing error in Legacy Archive adapter", e);
        }
    }

    private int mapCategoryToNumericCode(FashionCategory category) {
        return switch (category) {
            case TOP -> 101;
            case BOTTOM -> 102;
            case FOOTWEAR -> 103;
            case OUTERWEAR -> 104;
            case ACCESSORY -> 105;
        };
    }

    private FashionItem parseRawRecord(String record, FashionCategory category) {
        String[] parts = record.split(";");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Corrupted record format: " + record);
        }
        return new FashionItem(
                parts[0].trim(),
                parts[1].trim(),
                parts[2].trim(),
                Double.parseDouble(parts[3].trim()),
                category
        );
    }
}