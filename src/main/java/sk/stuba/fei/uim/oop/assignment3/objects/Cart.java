package sk.stuba.fei.uim.oop.assignment3.objects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductInCart> shoppingList = new ArrayList<ProductInCart>();

    private boolean payed;

    public ProductInCart findProductById(long productId){
        if(this.shoppingList.size()==0) return null;
        for(var p:this.shoppingList){
            if(p.getId()==productId) return p;
        }
        return null;
    }

    public boolean containsProduct(long productId){
        if(findProductById(productId) instanceof ProductInCart) return true;
        else return false;

    }

    public void addProductToList(Product product){
        ProductInCart productInCart = new ProductInCart(product);
        this.shoppingList.add(productInCart);
    }

    public double getCartPrice(){

        double price = 0;

        //if empty
        if(this.shoppingList.size()==0) return 0;

        //else
        for(var product:this.shoppingList){
            price+=product.getPrice()*product.getAmount();
        }

        return Math.round(price*100)/100;

    }
}
