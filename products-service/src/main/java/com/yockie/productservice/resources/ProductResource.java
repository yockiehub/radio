package com.yockie.productservice.resources;

import com.yockie.productservice.models.ComposedProduct;
import com.yockie.productservice.models.Product;
import com.yockie.productservice.models.SingleProduct;
import com.yockie.productservice.repositories.ProductBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// import com.yockie.productservice.repositories.StockRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8101")
@RequestMapping("products")
public class ProductResource {

    @Autowired
    ProductBaseRepository<Product> productBaseRepository;

    @RequestMapping("/addsingle")
    @PostMapping
    public SingleProduct addSingleProduct(@RequestBody SingleProduct product) {

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
        productBaseRepository.save(product);

        return product;
    };

    @RequestMapping("/addcomposed")
    @PostMapping
    public ComposedProduct addComposedProduct(@RequestBody ComposedProduct product) {

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
        System.out.println(product.getProdIds());
        productBaseRepository.save(product);

        return product;
    };

    @RequestMapping("/delete/{productId}")
    @DeleteMapping
    public String deleteProduct(@PathVariable("productId") Long id) {

        productBaseRepository.delete(productBaseRepository.findById(id));
        return "Product deleted";
    }

    @RequestMapping("/getall")
    @GetMapping
    public List<Product> getAllProducts() {

        return productBaseRepository.findAll();
    }

    @RequestMapping("/{productId}")
    @GetMapping
    public Product getProduct(@PathVariable("productId") Long id) {

        return productBaseRepository.findById(id);
    }
}
