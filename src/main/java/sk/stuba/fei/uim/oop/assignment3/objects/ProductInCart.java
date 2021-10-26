package sk.stuba.fei.uim.oop.assignment3.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class ProductInCart{

    /*
    *  ProductInCart je nova trieda, lebo Product je Entita, a trocha som sa zamotal
    *  pri pridavani entity Product do Kolekcie v Cart - z nejakeho dovodu po ulozeni
    *  spravneho mnozstva produktu do kosika sa v kosiku zrazu ocitlo mnozstvo
    *  produktu zo skladu, nie to, co som pridal
    *
    * */

    private long id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    //copy constructor
    public ProductInCart(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.unit = product.getUnit();
        this.price = product.getPrice();
    }



}
