package sk.stuba.fei.uim.oop.assignment3.requests;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.objects.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartRequest {

    private List<Product> shoppingList;

    private boolean payed;

    public CartRequest() {
        this.shoppingList = new ArrayList<Product>();
        this.payed = false;
    }
}
