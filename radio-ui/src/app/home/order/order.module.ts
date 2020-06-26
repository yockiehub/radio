import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { OrderPageRoutingModule } from './order-routing.module';

import { OrderPage } from './order.page';
import { CreateOrderComponent } from './create-order/create-order.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    IonicModule,
    OrderPageRoutingModule
  ],
  entryComponents: [CreateOrderComponent],
  declarations: [OrderPage, CreateOrderComponent]
})
export class OrderPageModule {}
