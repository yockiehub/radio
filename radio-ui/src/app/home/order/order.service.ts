import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Order } from './order.model';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private _orders = new BehaviorSubject<Order[]>([]);

  constructor(
    private http: HttpClient
  ) { }

  get orders() {
    return this._orders.asObservable();
  }


  fetchOrders() {
    return this.http.get<Order[]>('http://127.0.0.1:8081/orders/getall').pipe(
      tap( orders => {
        console.log('Fetching orders: ', orders);
        this._orders.next(orders);
      })
    );
  }

  addOrder(order: Order) {
    return  this.http.post<Order>('http://127.0.0.1:8081/orders/addorder', {... order});
  }

  deleteOrder(orderId: number) {
    return this.http.delete(`http://127.0.0.1:8081/orders/delete/${orderId}`, {responseType: 'text'});
  }
}
