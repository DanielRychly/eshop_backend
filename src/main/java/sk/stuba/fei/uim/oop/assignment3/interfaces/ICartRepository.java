package sk.stuba.fei.uim.oop.assignment3.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.objects.Cart;

import java.util.ArrayList;

@Repository
public interface ICartRepository extends CrudRepository<Cart,Long> {
    @Override
    ArrayList<Cart> findAll();

}
