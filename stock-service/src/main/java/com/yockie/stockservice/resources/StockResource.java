package com.yockie.stockservice.resources;

import com.yockie.stockservice.models.Stock;
import com.yockie.stockservice.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("stock")
public class StockResource {

    @Autowired
    StockRepository stockRepository;

    @RequestMapping("/test")
    @GetMapping
    public String Test() {
        List<Stock> slist = new ArrayList<>();
        slist.add(new Stock(20, 1L));
        slist.add(new Stock(20, 2L));
        slist.add(new Stock(20, 3L));
        stockRepository.saveAll(slist);

        return "Added 3 products";
    }

    @RequestMapping("/addsingle")
    @PutMapping
    public String addSingleProductToStock(@RequestBody Long singleProductId) {
        Stock stock = new Stock(0, singleProductId);
        stockRepository.save(stock);
        return stock.toString();
    }

    @RequestMapping("/getstock/{singleProductId}")
    @GetMapping
    public Stock getProductStock(@PathVariable("singleProductId") Long singleProductId) {

        return stockRepository.findBySingleProductId(singleProductId);

    }

    @RequestMapping("/addstock/{singleProductId}")
    @PutMapping()
    public String addProductStock(@PathVariable("singleProductId") Long singleProductId, @RequestBody int amount) {
        Stock s = stockRepository.findById(singleProductId);
        s.setAmount(s.getAmount() + amount);
        stockRepository.save(s);

        return s.toString();
    }

    @RequestMapping("/reducestock/{singleProductId}")
    @PutMapping()
    public String reduceProductStock(@PathVariable("singleProductId") Long singleProductId, @RequestBody int amount) {
        Stock s = stockRepository.findById(singleProductId);
        s.setAmount(s.getAmount() - amount);
        stockRepository.save(s);

        return s.toString();
    }

    @RequestMapping("/getall")
    @GetMapping
    public List<Stock> getAllProductStock() {

        return stockRepository.findAll();

    }

    @RequestMapping("/delete/{singleProductId}")
    @DeleteMapping
    public String deleteStock(@PathVariable("singleProductId") Long singleProductId) {
        stockRepository.delete(stockRepository.findById(singleProductId));

        return "Stock deleted";
    }



    // For demo purposes
    public String resetAllStock() {
        List<Stock> stockList = stockRepository.findAll();
        stockList.forEach(stock -> {
            stock.setAmount(100);
            stockRepository.save(stock);
        });

        return "All stock set to 100";
    }
}
