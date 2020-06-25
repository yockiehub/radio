package com.yockie.productservice.resources;

import com.yockie.productservice.models.ComposedProduct;
import com.yockie.productservice.models.Product;
import com.yockie.productservice.models.SingleProduct;
import com.yockie.productservice.repositories.ProductBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// import com.yockie.productservice.repositories.StockRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8100",
        allowedHeaders = {"Authorization", "Cache-Control", "Content-Type","Access-Control-Allow-Origin"},
        methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("products")
public class ProductResource {

    @Autowired
    ProductBaseRepository<Product> productBaseRepository;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/addsingle")
    @PostMapping
    public SingleProduct addSingleProduct(@RequestBody SingleProduct product){

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
        System.out.println(product.getAmount());
        productBaseRepository.save(product);

        // restTemplate.put("http://127.0.0.1:8082/stock/addsingle", product.getId());

        return product;
    };

    @RequestMapping("/addcomposed")
    @PostMapping
    public ComposedProduct addComposedProduct(@RequestBody ComposedProduct product) {

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
        productBaseRepository.save(product);

        return product;
    };

    @RequestMapping("/delete/{productId}")
    @DeleteMapping
    public String deleteProduct(@PathVariable("productId") Long id) {

        productBaseRepository.delete(productBaseRepository.findById(id));

        // restTemplate.delete("http://127.0.0.1:8082/stock/delete/" + id);
        return "Product deleted";
    }

    @RequestMapping("/getall")
    @GetMapping
    public List<Product> getAllProducts() {

        /*List<Product> allProds = productBaseRepository.findAll();
        allProds.stream().map( product -> {
            Stock stock = restTemplate.getForObject("http://127.0.0.1:8082/stock/getstock/" + product.getId(), Stock.class);
            return
        })*/

        // restTemplate.getForObject("http://127.0.0.1:8082/stock/getall",);

        return productBaseRepository.findAll();
    }

    @RequestMapping("/{productId}")
    @GetMapping
    public Product getProduct(@PathVariable("productId") Long id) {

        return productBaseRepository.findById(id);
    }
}
