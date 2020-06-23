import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product, ComposedProduct, SingleProduct } from './product.model';
import { ProductService } from './product.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-product',
  templateUrl: './product.page.html',
  styleUrls: ['./product.page.scss'],
})
export class ProductPage implements OnInit {

  public products: Product[];
  private productSub: Subscription;

  private product: SingleProduct = new SingleProduct(
    null,
    'Shirt',
    'A green shirt',
  );

  prods: Map<number, number> = new Map<number, number>();

  private composedProduct: ComposedProduct;

  constructor(
    private http: HttpClient,
    private productService: ProductService
  ) { }

  ngOnInit() {
    this.productSub = this.productService.products.subscribe(products => {
      console.log('Showing products OnInit product page: ', products);
      this.products = products;
    });
  }

  ionViewWillEnter() {
    this.productService.fetchProducts().subscribe();
  }

  testMethod() {
    this.productService.getProduct('1');
  }

  createProductTest() {
    this.prods[1] = 3;
    this.prods[2] = 5;

    this.composedProduct = new ComposedProduct(
      null,
      'Shirt Pack',
      'A blue shirt and a green shirt',
      this.prods
    );

    this.http.post('http://127.0.0.1:8080/products/addcomposed', {... this.composedProduct}).subscribe(
      resp => {
        console.log('Got response: ', resp);
      }
    );

  }

}
