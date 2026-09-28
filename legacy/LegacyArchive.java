package legacy;

public class LegacyArchive {

    public String[] queryRawDatabase(int numericCategoryCode, boolean includeArchived) throws LegacyDatabaseCorruptException {
        if (numericCategoryCode < 101 || numericCategoryCode > 105) {
            throw new LegacyDatabaseCorruptException("CRITICAL_DB_ERR: Invalid numeric category code [" + numericCategoryCode + "]");
        }

        return switch (numericCategoryCode) {
            case 101 -> new String[]{
                    "LEG-001;Alexander McQueen;1998 Dante Lace Top;3100.00",
                    "LEG-002;John Galliano;1995 Newspaper Print Shirt;2400.00"
            };
            case 104 -> new String[]{
                    "LEG-003;Thierry Mugler;1997 Vamp Armor Jacket;6500.00"
            };
            case 103 -> new String[]{
                    "LEG-004;Prada;1999 Sport Flame Boots;1100.00"
            };
            default -> new String[0];
        };
    }
}