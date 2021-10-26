package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.interfaces.IShopService;
import sk.stuba.fei.uim.oop.assignment3.objects.Cart;
import sk.stuba.fei.uim.oop.assignment3.objects.Product;
import sk.stuba.fei.uim.oop.assignment3.requests.AmountRequest;
import sk.stuba.fei.uim.oop.assignment3.requests.ProductBasicViewRequest;
import sk.stuba.fei.uim.oop.assignment3.requests.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.responses.AmountResponse;
import sk.stuba.fei.uim.oop.assignment3.responses.CartResponse;
import sk.stuba.fei.uim.oop.assignment3.responses.ProductResponse;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShopController {

    @Autowired
    private IShopService service;

    @GetMapping("/product")
    public ArrayList<ProductResponse> getAllProducts(){

        var result = new ArrayList<ProductResponse>();
        for(Product a : this.service.getAllProductsFromProductRepository()) {
            result.add(new ProductResponse(a));
        }
        return result;

    }

    @GetMapping("product/{id}")
    public ProductResponse getProductById(@PathVariable long id){

        Product p = this.service.findProductByIdInProductRepository(id);
        ProductResponse result = new ProductResponse(p);
        return result;

    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        return new ProductResponse(this.service.createProductInProductRepository(request));
    }

    @PutMapping("product/{id}")
    public ProductResponse updateProduct(@PathVariable long id, @RequestBody ProductRequest request){
        Product result = null;
        result = service.updateProductInProductRepository(id, request);
        return new ProductResponse(result);
    }

    @DeleteMapping("product/{id}")
    public ProductResponse deleteProduct(@PathVariable long id){
        Product result = null;
        result = this.service.deleteProductFromProductRepository(id);
        return new ProductResponse(result);

    }

    @GetMapping("/product/{id}/amount")
    public AmountResponse getProductAmountById(@PathVariable long id){

        Product p = this.service.findProductByIdInProductRepository(id);
        return new AmountResponse(p);
    }

    @PostMapping("/product/{id}/amount")
    public AmountResponse addProductAmount(@PathVariable long id, @RequestBody AmountRequest request){
        Product p = this.service.incrementProductAmountInProductRepository(id, request.getAmount());
        return new AmountResponse(p);
    }

    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse createShoppingCart(){

        return new CartResponse(this.service.createCart());
    }

    @GetMapping("/cart/{id}")
    public CartResponse getShoppingCart(@PathVariable long id){

        Cart cart = this.service.findCartById(id);
        CartResponse result = new CartResponse(cart);
        return result;

    }

    @DeleteMapping("/cart/{id}")
    public CartResponse deleteShoppingCart(@PathVariable long id){
        Cart result = null;
        result = this.service.deleteCart(id);
        return new CartResponse(result);
    }

    @PostMapping("/cart/{id}/add")
    public CartResponse addToShoppingCart(@PathVariable long id, @RequestBody ProductBasicViewRequest request){
        Cart result = null;
        result = this.service.addToCart(id,request.getProductId(),request.getAmount());
        return new CartResponse(result);
    }

    @GetMapping("/cart/{id}/pay")
    @ResponseStatus(HttpStatus.OK)
    public String payForShoppingCart(@PathVariable long id){

        double price;
        price = this.service.pay(id);
        return String.valueOf(price);

    }
}
