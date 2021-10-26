package sk.stuba.fei.uim.oop.assignment3.responses;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.objects.Product;

@Getter
public class ProductResponse {

    private long id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.unit = product.getUnit();
        this.price = product.getPrice();
    }
}
