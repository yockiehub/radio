import { Injectable } from '@angular/core';
import { Product, SingleProduct, ComposedProduct } from './product.model';
import { tap, take, map, switchMap, filter } from 'rxjs/operators';

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
    return this.http.get<any>(`http://127.0.0.1:8080/products/${productId}`).subscribe(
      prod => {
        let p: Product;
        if (prod.hasOwnProperty('prods')) {
          p = new ComposedProduct(
            prod.id,
            prod.name,
            prod.description,
            prod.prods,
            prod.virtualAmount
          );
        } else {
          p = new SingleProduct(
            prod.id,
            prod.name,
            prod.description,
            prod.amount
          );
        }
        console.log('Product: ', p);
        return p;
      }
    );
  }

  fetchProducts() {
    return this.http.get<Product[]>('http://127.0.0.1:8080/products/getall').pipe(
      //take(1),
      tap( products => {
        console.log('Fetching products: ', products);
        this._products.next(products);
      })
    );
  }

  addSingleProduct(singleProduct: SingleProduct) {
    return this.http.post<SingleProduct>('http://127.0.0.1:8080/products/addsingle', {... singleProduct});
    /*return this.http.post<SingleProduct>('http://127.0.0.1:8080/products/addsingle', {... singleProduct}).subscribe(
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
    );*/
  }

  addComposedProduct(composedProduct: ComposedProduct) {
    return  this.http.post<ComposedProduct>('http://127.0.0.1:8080/products/addcomposed', {... composedProduct});
  }

  deleteProduct(productId: number) {
    return this.http.delete(`http://127.0.0.1:8080/products/delete/${productId}`, {responseType: 'text'}); /*.subscribe(
      () => {
        this.products.pipe(
          take(1),
          tap(products => {
            this._products.next(products.filter(p => p.id !== productId));
          })
        );
      }
    );*/
  }

  calculateComposedProductStock(composedProductId: string) {
    // Get composed product
    // console.log(this.getProduct(composedProductId));

    /*
    let prodsComp; // : Array<{}>;
    let singleAmount: Array<{}>;
    // Find product by id
    return this.products.pipe(
      take(1),
      switchMap( products => {
        return products.filter( product => {
          return product.id === composedProductId;
        });
      }),
      // Find products composing the composed product
      take(1),
      tap( product => {
        console.log('target product: ', product);
        const p = product as ComposedProduct;
        console.log('target product as composed product: ', p);
        prodsComp = p.prods; // as Array<{}>;
        return prodsComp.forEach( prod => {
          this.products.pipe(
            // take(1),
            map( products => {
              return products.filter( produ => {
                return produ.id === prod[0];
              });
            }),
            // Compare amount of products with the availability
            tap( products => {
              products.forEach( produ => {
                const pr = produ as SingleProduct;
                singleAmount[pr.id] = pr.amount;
              });
            })
          );
          console.log('Prods comp: ', prodsComp);
          console.log('Per product: ', singleAmount);
        });
      })
    );
    */
  }
}
