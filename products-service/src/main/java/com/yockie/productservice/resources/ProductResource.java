package com.yockie.productservice.resources;

import com.yockie.productservice.models.ComposedProduct;
import com.yockie.productservice.models.Product;
import com.yockie.productservice.models.SingleProduct;
import com.yockie.productservice.repositories.ProductBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// import com.yockie.productservice.repositories.StockRepository;

@RestController
@RequestMapping("products")
public class ProductResource {

    @Autowired
    ProductBaseRepository productBaseRepository;

    /*@Autowired
    StockRepository stockRepository;*/

    @RequestMapping("/addtostock")
    public String addProductToStock() {
        ArrayList<Long> al = new ArrayList<>();

        Product p1 = new SingleProduct("Green shirt", "it is a green shirt", 100);
        al.add(p1.getId());
        Product p2 = new ComposedProduct("Green shirt", "it is a green shirt", al);

        productBaseRepository.save(p1);
        productBaseRepository.save(p2);

        // stockRepository.save((new Stock(100, p1.getId())));

        return "Products added";
    };

    @RequestMapping("/testdel/{productId}")
    public String deleteProduct(@PathVariable("productId") Long id) {
        productBaseRepository.delete(productBaseRepository.findById(id));
        return "Product deleted";
    }

    @RequestMapping("/getall")
    public List<Product> getAllProducts() {
        List<Product> all = productBaseRepository.findAll();
        return all;
    }

    @RequestMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") Long id) {

        return productBaseRepository.findById(id);
    }
}
