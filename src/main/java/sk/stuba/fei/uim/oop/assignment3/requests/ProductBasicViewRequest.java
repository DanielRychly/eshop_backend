package sk.stuba.fei.uim.oop.assignment3.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBasicViewRequest {
    private long productId;
    private int amount;
}
