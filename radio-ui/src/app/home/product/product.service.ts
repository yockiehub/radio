import { Injectable } from '@angular/core';
import { Product, SingleProduct, ComposedProduct } from './product.model';
import { tap, take, map } from 'rxjs/operators';

import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private _products = new BehaviorSubject<Product[]>([]);

  constructor(
    private http: HttpClient
  ) { }

  get products() {
    return this._products.asObservable();
  }

  getProduct(productId: string) {
    return this.http.get<any>(`http://127.0.0.1:8080/products/${productId}`).subscribe( prod => {
      let p: Product;
      if (prod.hasOwnProperty('prods')) {
        p = new ComposedProduct(
          prod.id,
          prod.name,
          prod.description,
          prod.prods
        );
      } else {
        p = new SingleProduct(
          prod.id,
          prod.name,
          prod.description,
        );
      }
      console.log('Product: ', p);
      return p;
    });
  }

  fetchProducts() {
    return this.http.get<Product[]>('http://127.0.0.1:8080/products/getall').pipe(
      tap( products => {
        console.log('Fetching products: ', products);
        this._products.next(products);
        return products;
      })
    );
    /* .subscribe( products => {
      console.log('Fetching products: ', products);
      this._products.next(products);
      return products;
    });
    */
  }

  addSingleProduct(singleProduct: SingleProduct) {
    this.http.post<SingleProduct>('http://127.0.0.1:8080/products/addcsingle', {... singleProduct}).subscribe(
      resp => {
        console.log('Single product added: ', resp);
      }
    );
  }
}
