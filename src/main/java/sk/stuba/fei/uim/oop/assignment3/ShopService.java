package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.CartAlreadyPaidException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.CartNotFoundException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotEnoughProductsException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.ProductNotFoundException;
import sk.stuba.fei.uim.oop.assignment3.interfaces.ICartRepository;
import sk.stuba.fei.uim.oop.assignment3.interfaces.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.interfaces.IShopService;
import sk.stuba.fei.uim.oop.assignment3.objects.Cart;
import sk.stuba.fei.uim.oop.assignment3.objects.Product;
import sk.stuba.fei.uim.oop.assignment3.objects.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.requests.ProductRequest;

import java.util.ArrayList;

@Service
public class ShopService implements IShopService {

    private IProductRepository productRepository;
    private ICartRepository cartRepository;

    @Autowired
    public ShopService(IProductRepository productRepository, ICartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public ArrayList<Product> getAllProductsFromProductRepository() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProductInProductRepository(ProductRequest request) {
        Product product = new Product(request);
        return this.productRepository.save(product);

    }

    @Override
    public Product updateProductInProductRepository(long id, ProductRequest request){

        Product updated = null;

        try {
            updated = this.productRepository.findById(id).get();
        }catch (RuntimeException e){
            throw new ProductNotFoundException();
        }

        if(updated!=null){

            if(request.getAmount()!=0) {
                updated.setAmount(request.getAmount());
            }
            if(request.getDescription()!=null) {
                updated.setDescription(request.getDescription());
            }
            if(request.getPrice()!=0) {
                updated.setPrice(request.getPrice());
            }
            if(request.getName()!=null) {
                updated.setName(request.getName());
            }
            if(request.getUnit()!=null) {
                updated.setUnit(request.getUnit());
            }
        }

        return this.productRepository.save(updated);

    }

    @Override
    public Product deleteProductFromProductRepository(long id){

        Product toDelete = findProductByIdInProductRepository(id);
        this.productRepository.delete(toDelete);
        return toDelete;

    }

    @Override
    public Product findProductByIdInProductRepository(long id){

        Product toFind = null;
        try {
            toFind = this.productRepository.findById(id).get();
        } catch(RuntimeException e){
            throw new ProductNotFoundException();
        }

        return toFind;

    }

    @Override
    public Product incrementProductAmountInProductRepository(long id, int amount){

        Product toIncrement = findProductByIdInProductRepository(id);
        toIncrement.setAmount(toIncrement.getAmount()+amount);
        return this.productRepository.save(toIncrement);

    }

    public Product decrementProductAmountInProductRepository(long id, int amount){

        Product toDecrement = findProductByIdInProductRepository(id);
        toDecrement.setAmount(toDecrement.getAmount()-amount);
        return this.productRepository.save(toDecrement);

    }

    public ProductInCart incrementProductAmountInCartRepository(long cartId, long productId, int amountToIncrement){

        Cart cart;
        try {
            cart = this.cartRepository.findById(cartId).get();
        }catch(RuntimeException e){
            throw new CartNotFoundException();
        }
        for(var product: cart.getShoppingList()){
            if (product.getId() == productId){
                product.setAmount(product.getAmount()+amountToIncrement);
                this.cartRepository.save(cart);
                return product;
            }

        }
        return null;

    }

    @Override
    public Cart createCart(){

        Cart cart = new Cart();
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart findCartById(long id){
        Cart toFind = null;
        try {
            toFind = this.cartRepository.findById(id).get();
        } catch(RuntimeException e){
            throw new ProductNotFoundException();
        }

        return toFind;
    }

    @Override
    public Cart deleteCart(long id){
        Cart toDelete = null;
        try {
            toDelete = this.cartRepository.findById(id).get();
            this.cartRepository.delete(toDelete);
        } catch(RuntimeException e){
            throw new ProductNotFoundException();
        }

        return toDelete;
    }

    public Cart addToCart(long cartId,long productId,int amount){

        Cart cart = null;
        Product product = null;

        //find cart else 404
        try {
            cart = this.cartRepository.findById(cartId).get();

        } catch(RuntimeException e){

            throw new CartNotFoundException();
        }

        //find product, else 404
        try{
            product = this.productRepository.findById(productId).get();

        }catch(RuntimeException e){
            throw new ProductNotFoundException();
        }

        //Pokiaľ je už košík zaplatený vráti 400 a stav košíka
        // ani skladu sa nezmení.
        if(cart.isPayed()) {
            throw new CartAlreadyPaidException();
        }

        //pokial nie je dostatok prokutov vráti 400 a stav košíka
        // ani skladu sa nezmení.
        if(this.productRepository.findById(productId).get().getAmount()<amount) {
            throw new NotEnoughProductsException();
        }

        //Pokiaľ košík už daný produkt obsahuje množstvo
        // sa len pričíta.
        if(cart.containsProduct(productId)){

            this.decrementProductAmountInProductRepository(productId,amount);
            this.incrementProductAmountInCartRepository(cartId,productId,amount);
            this.cartRepository.save(cart);
        }

        //Pokiaľ košík este daný produkt neobsahuje
        //Pridá daný produkt a odpočíta množstvo produktu
        // zo skladu v danom množstve do košíka s daným ID.
        if(!cart.containsProduct(productId)){
            Product toAdd = new Product(findProductByIdInProductRepository(productId)); //todo tu je asi chyba
            toAdd.setAmount(amount);
            cart.addProductToList(toAdd);
            this.decrementProductAmountInProductRepository(productId,amount);
            this.cartRepository.save(cart); //todo tu je problem pridavania

        }

        return cart;
    }

    public double pay(long id){

        Cart cart = null;

        //find cart
        try {
            cart = this.cartRepository.findById(id).get();

        } catch(RuntimeException e){

            throw new CartNotFoundException();
        }

        //Pokiaľ je už košík zaplatený vráti 400 a stav košíka
        // ani skladu sa nezmení.
        if(cart.isPayed()) throw new CartAlreadyPaidException();

        cart.setPayed(true);
        this.cartRepository.save(cart);
        return cart.getCartPrice();
    }
}
