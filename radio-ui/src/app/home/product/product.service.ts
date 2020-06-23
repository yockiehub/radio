import { Injectable } from '@angular/core';
import { Product } from './product.model';
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

  
}
