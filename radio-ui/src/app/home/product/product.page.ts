import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product, ComposedProduct, SingleProduct } from './product.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.page.html',
  styleUrls: ['./product.page.scss'],
})
export class ProductPage implements OnInit {

  private product: SingleProduct = new SingleProduct(
    null,
    'Shirt',
    'A green shirt',
  );

  private composedProduct: ComposedProduct = new ComposedProduct(
    null,
    'Shirt Pack',
    'A blue shirt and a green shirt',
    [1, 2]
  );

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit() {
  }

  createProductTest() {
    this.http.post('http://127.0.0.1:8080/products/addsingle', {... this.product}).subscribe(
      resp => {
        console.log('Got response: ', resp);
      }
    );
  }

}
