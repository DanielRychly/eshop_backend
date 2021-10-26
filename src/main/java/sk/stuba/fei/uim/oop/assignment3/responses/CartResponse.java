package sk.stuba.fei.uim.oop.assignment3.responses;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.objects.Cart;
import sk.stuba.fei.uim.oop.assignment3.objects.ProductBasicView;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartResponse {

    private long id;
    private List<ProductBasicView> shoppingList;
    private boolean payed;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.shoppingList = new ArrayList<>();
        if(cart.getShoppingList().size()!=0){
            for(var productInCart: cart.getShoppingList()){
                var basic = new ProductBasicView(productInCart);
                this.shoppingList.add(basic);
            }
        }
        this.payed = cart.isPayed();
    }
}
