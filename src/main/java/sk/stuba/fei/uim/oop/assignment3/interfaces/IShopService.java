package sk.stuba.fei.uim.oop.assignment3.interfaces;

import sk.stuba.fei.uim.oop.assignment3.objects.Cart;
import sk.stuba.fei.uim.oop.assignment3.objects.Product;
import sk.stuba.fei.uim.oop.assignment3.objects.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.requests.ProductRequest;

import java.util.ArrayList;

public interface IShopService {

    public ArrayList<Product> getAllProductsFromProductRepository();
    public Product createProductInProductRepository(ProductRequest request);
    public Product updateProductInProductRepository(long id, ProductRequest request);
    public Product deleteProductFromProductRepository(long id);
    public Product findProductByIdInProductRepository(long id);
    public Product incrementProductAmountInProductRepository(long id, int amount);
    public Product decrementProductAmountInProductRepository(long id, int amount);
    public ProductInCart incrementProductAmountInCartRepository(long cartId, long productId, int amountToIncrement);
    public Cart createCart();
    public Cart findCartById(long id);
    public Cart deleteCart(long id);
    public Cart addToCart(long cartId, long productId, int amount);
    public double pay(long id);



}
