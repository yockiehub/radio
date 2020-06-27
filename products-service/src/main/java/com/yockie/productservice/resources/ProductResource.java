package com.yockie.productservice.resources;

import com.yockie.productservice.models.ComposedProduct;
import com.yockie.productservice.models.Product;
import com.yockie.productservice.models.SingleProduct;
import com.yockie.productservice.repositories.ProductBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// import com.yockie.productservice.repositories.StockRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8081"},
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

    @RequestMapping("getvirtualstock/{composedProductId}")
    @GetMapping
    public int getComposedProductVirtualStock(@PathVariable("composedProductId") Long id) {
        ComposedProduct cp = (ComposedProduct) productBaseRepository.findById(id);
        int virtualStock = Integer.MAX_VALUE;
        SingleProduct sp;

        for (Map.Entry<Long, Integer> prod: cp.getProds().entrySet()) {
            sp = (SingleProduct) productBaseRepository.findById(prod.getKey());
            if (sp.getAmount()/prod.getValue() < virtualStock) {
                virtualStock = sp.getAmount()/prod.getValue();
            }
        }
        return virtualStock;
    }
    @RequestMapping("/getall")
    @GetMapping
    public List<Product> getAllProducts() {
        return productBaseRepository.findAll();
    }

    @RequestMapping("/{productId}")
    @GetMapping
    public Product getProduct(@PathVariable("productId") Long id) {

        Product product = productBaseRepository.findById(id);
        if (product instanceof ComposedProduct) {
            ComposedProduct composedProduct = (ComposedProduct) product;
            composedProduct.setVirtualAmount(this.getComposedProductVirtualStock(id));
            return composedProduct;
        }
        return product;
    }

    @RequestMapping("/addamount/{singleProductId}")
    @PutMapping()
    public String increaseAmount(@PathVariable("singleProductId") Long singleProductId, @RequestBody int amount) {
        SingleProduct p = (SingleProduct) productBaseRepository.findById(singleProductId);
        p.setAmount(p.getAmount() + amount);
        productBaseRepository.save(p);

        return p.toString();
    }

    @RequestMapping("/reduceamount/{productId}")
    @PutMapping()
    public String decreaseAmount(@PathVariable("productId") Long productId, @RequestBody int amount) {
        List<Product> productList = new ArrayList<>();
        Product product = productBaseRepository.findById(productId);
        if (product instanceof SingleProduct) {
            if (((SingleProduct) product).getAmount() >= amount) {
                ((SingleProduct) product).setAmount(((SingleProduct) product).getAmount() - amount);
                productList.add(product);
            } else {
                return "Operation was not possible";
            }
        } else if (product instanceof ComposedProduct) {
            if (getComposedProductVirtualStock(productId) >= amount) {
                SingleProduct p;
                for(Map.Entry<Long, Integer> prod: ((ComposedProduct) product).getProds().entrySet()) {
                    p = (SingleProduct) productBaseRepository.findById(prod.getKey());
                    p.setAmount((p.getAmount() - amount * prod.getValue()));
                    productList.add(p);
                }
            } else {
                return "Operation was not possible";
            }
        }
        productBaseRepository.saveAll(productList);

        return product.toString();
    }
}
