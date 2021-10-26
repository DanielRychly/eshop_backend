package sk.stuba.fei.uim.oop.assignment3.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.objects.Product;

import java.util.ArrayList;

@Repository
public interface IProductRepository extends CrudRepository<Product,Long> {
    @Override
    ArrayList<Product> findAll();
}
