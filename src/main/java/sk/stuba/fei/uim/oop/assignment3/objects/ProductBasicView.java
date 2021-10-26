package sk.stuba.fei.uim.oop.assignment3.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBasicView {
    private long productId;
    private int amount;

    public ProductBasicView(ProductInCart productInCart) {
        this.productId = productInCart.getId();
        this.amount = productInCart.getAmount();
    }

}
