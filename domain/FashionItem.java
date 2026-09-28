package domain;

public record FashionItem(
        String id,
        String brand,
        String name,
        double price,
        FashionCategory category
) {}