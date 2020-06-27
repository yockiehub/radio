import { Component, OnInit } from '@angular/core';
import { Order } from '../order.model';
import { ActivatedRoute } from '@angular/router';
import { NavController } from '@ionic/angular';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.page.html',
  styleUrls: ['./detail.page.scss'],
})
export class DetailPage implements OnInit {

  prodsInOrder: {}[];
  isLoading = false;
  order: Order;

  constructor(
    private activatedRoute: ActivatedRoute,
    private navCtrl: NavController,
    private orderService: OrderService,
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paraMap => {
      console.log('ParamMap: ', paraMap);
      if (!paraMap.has('orderId')) {
        this.navCtrl.navigateBack('/home/tabs/order');
        return;
      }
      const orderId = paraMap.get('orderId');
      this.isLoading = true;
      this.orderService.getOrder(orderId).subscribe(
        order => {
          this.order = order as Order;
          this.prodsInOrder = Object.keys(this.order.prods).map( id => {
            return {id, amount: this.order.prods[id]};
          });
          console.log(this.order, this.prodsInOrder);
          this.isLoading = false;
        }
      );
    });
  }

}
