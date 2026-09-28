import abstraction.FormalOutfit;
import abstraction.Outfit;
import adapter.LegacyArchiveAdapter;
import bridge.BrandArchiveProvider;
import bridge.FashionItemProvider;
import domain.FashionCategory;
import domain.FashionItem;
import exception.ArchiveProviderException;
import legacy.LegacyArchive;
import legacy.LegacyDatabaseCorruptException;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdapterAndBridgeTest {

    static class LegacyArchiveStub extends LegacyArchive {
        @Override
        public String[] queryRawDatabase(int numericCategoryCode, boolean includeArchived) throws LegacyDatabaseCorruptException {
            if (numericCategoryCode == 104) { // OUTERWEAR
                return new String[]{"TEST-01;Gucci;1999 Leather Coat;5000.00"};
            }
            return new String[0];
        }
    }

    static class LegacyArchiveFailingStub extends LegacyArchive {
        @Override
        public String[] queryRawDatabase(int numericCategoryCode, boolean includeArchived) throws LegacyDatabaseCorruptException {
            throw new LegacyDatabaseCorruptException("Corrupted DB state");
        }
    }

    @Test
    void testBridgeDelegationWithBrandProvider() {
        FashionItemProvider provider = new BrandArchiveProvider();
        Outfit outfit = new FormalOutfit(provider);

        List<FashionItem> items = outfit.assembleLook();
        assertFalse(items.isEmpty());
        assertEquals("Balenciaga", items.get(0).brand());
    }

    @Test
    void testAdapterSuccessfulTranslation() {
        LegacyArchive stub = new LegacyArchiveStub();
        FashionItemProvider adapter = new LegacyArchiveAdapter(stub);

        List<FashionItem> items = adapter.fetchItems(FashionCategory.OUTERWEAR);

        assertEquals(1, items.size());
        assertEquals("Gucci", items.get(0).brand());
        assertEquals(5000.00, items.get(0).price());
    }

    @Test
    void testAdapterExceptionTranslation() {
        LegacyArchive failingStub = new LegacyArchiveFailingStub();
        FashionItemProvider adapter = new LegacyArchiveAdapter(failingStub);

        assertThrows(ArchiveProviderException.class, () -> adapter.fetchItems(FashionCategory.TOP));
    }
}