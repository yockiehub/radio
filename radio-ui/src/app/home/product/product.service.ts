import { Injectable } from '@angular/core';
import { Product, SingleProduct, ComposedProduct } from './product.model';
import { tap, take, map, switchMap } from 'rxjs/operators';

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
      take(1),
      tap( products => {
        console.log('Fetching products: ', products);
        this._products.next(products);
      })
    );
  }

  addSingleProduct(singleProduct: SingleProduct) {
    /*
    let prodId;
    return this.http.post<SingleProduct>('http://127.0.0.1:8080/products/addsingle', {... singleProduct}).pipe(
      // take(1),
      switchMap(
        resp => {
          console.log('Single product added: ', resp);
          prodId = resp.id;
          return this.products;
        }
      ),
      take(1),
      tap(products => {
        singleProduct.id = prodId;
        this._products.next(products.concat(singleProduct));
      })
    );
*/

    return this.http.post<SingleProduct>('http://127.0.0.1:8080/products/addsingle', {... singleProduct}).subscribe(
      resp => {
        console.log('Single product added: ', resp);
        this.products.pipe(
          take(1),
          tap(products => {
            singleProduct.id = resp.id;
            this._products.next(products.concat(singleProduct));
          })
        );
      }
    );
  }
}
