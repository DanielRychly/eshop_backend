package sk.stuba.fei.uim.oop.assignment3.responses;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.objects.Product;

@Getter
@Setter
public class AmountResponse {

    private int amount;

    public AmountResponse(Product p) {
        this.amount = p.getAmount();
    }
}
