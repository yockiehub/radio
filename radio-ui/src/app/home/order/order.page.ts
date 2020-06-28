import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Order } from './order.model';
import { Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ModalController, LoadingController, IonItemSliding, IonSelect } from '@ionic/angular';
import { OrderService } from './order.service';
import { CreateOrderComponent } from './create-order/create-order.component';

@Component({
  selector: 'app-order',
  templateUrl: './order.page.html',
  styleUrls: ['./order.page.scss'],
})
export class OrderPage implements OnInit, OnDestroy {

  orders: Order[];
  private orderSub: Subscription;
  @ViewChild('mySelect') selectRef: IonSelect;
  hideList = true;

  constructor(
    private http: HttpClient,
    private orderService: OrderService,
    private modalCtrl: ModalController,
    private loadingCtrl: LoadingController
    ) { }

  ngOnInit() {
    this.orderSub = this.orderService.orders.subscribe(orders => {
      console.log('Showing products OnInit product page: ', orders);
      this.orders = orders;
    });
  }

  ngOnDestroy() {
    this.orderSub.unsubscribe();
  }

  ionViewWillEnter() {
    this.orderService.fetchOrders().subscribe();
  }

  createOrder() {
    this.openCreateOrderModal();
  }

  openCreateOrderModal() {
    this.modalCtrl.create({
      component: CreateOrderComponent,
    }).then(modalEl => {
      modalEl.present();
      return modalEl.onDidDismiss();
    }).then(resultData => {
      console.log(resultData.data, resultData.role);
      if (resultData.role === 'confirm') {
        const data = resultData.data.productData;
        const prods = {};
        data.prods.forEach(element => {
          prods[element.prodId] = element.amount;
        });
        this.orderService.addOrder(new Order(
          null,
          'created',
          data.customer,
          new Date().toString(),
          null,
          prods
        )).subscribe( res => {
          this.orderService.fetchOrders().subscribe();
        });
        console.log('Modal to add product should now close');
      }
    });
  }

  onEditOrderStatus(slidingItem: IonItemSliding) {
    this.selectRef.open();
    slidingItem.close();

  }

  setStatus(orderId: string) {
    console.log(this.selectRef.value);
    this.orderService.getOrder(orderId).subscribe( order => {
      order.status = this.selectRef.value;
      this.orderService.changeOrderStatus(order).subscribe(res => {
        this.orderService.fetchOrders().subscribe();
      });
    });
  }

  onDeleteOrder(orderId: number, slidingItem: IonItemSliding) {
    slidingItem.close();
    this.loadingCtrl.create({
      message: 'Deleting order...'
    }).then(loadingEl => {
      loadingEl.present();
      this.orderService.deleteOrder(orderId).subscribe(
        () => {
          this.orderService.fetchOrders().subscribe();
          loadingEl.dismiss();
        }
      );
    });
  }

  testMethod() {
    const prods: Map<number, number> = new Map<number, number>();
    prods[1] = 2;
    prods[2] = 3;

    const order = new Order(
      null,
      'Created',
      'Test SA',
      new Date().toString(),
      new Date().toString(),
      prods
    );
    this.http.post('http://127.0.0.1:8081/orders/addorder', {... order}).subscribe(
      resp => {
        console.log('Got response: ', resp);
      }
    );
  }

}
