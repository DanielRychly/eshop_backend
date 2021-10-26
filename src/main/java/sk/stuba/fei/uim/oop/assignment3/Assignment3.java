package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.stuba.fei.uim.oop.assignment3.interfaces.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.objects.Product;

import java.util.stream.Stream;

@SpringBootApplication
public class Assignment3 {

    public static void main(String[] args) {
        SpringApplication.run(Assignment3.class, args);
    }

    @Bean
    CommandLineRunner init(IProductRepository productRepository) {
        return args -> {
            Stream.of("Smartphone", "TV", "Printer", "Clock").forEach(name -> {
                Product p = new Product(name);

                productRepository.save(p);
            });
            var arr = productRepository.findAll();
            for (Product p : arr)
            {
               System.out.println(p.getName());
            }
        };
    }

}
