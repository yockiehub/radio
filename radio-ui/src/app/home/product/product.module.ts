import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ProductPageRoutingModule } from './product-routing.module';

import { ProductPage } from './product.page';
import { CreateProductComponent } from './create-product/create-product.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    ProductPageRoutingModule
  ],
  entryComponents: [CreateProductComponent],
  declarations: [ProductPage, CreateProductComponent]
})
export class ProductPageModule {}
