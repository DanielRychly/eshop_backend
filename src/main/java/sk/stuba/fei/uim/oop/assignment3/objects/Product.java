package sk.stuba.fei.uim.oop.assignment3.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.requests.ProductRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@NoArgsConstructor

public class Product{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    //copy constructor
    public Product(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.unit = product.getUnit();
        this.price = product.getPrice();
    }

    public Product(ProductRequest productRequest){
        this.name = productRequest.getName();
        this.description = productRequest.getDescription();
        this.amount = productRequest.getAmount();
        this.unit = productRequest.getUnit();
        this.price = productRequest.getPrice();
    }

    public Product(String name){
        this.name = name;
        this.description = "defualt desc";
        this.amount = 1;
        this.unit = "defualt desc";
        this.price = 100;
    }

}
