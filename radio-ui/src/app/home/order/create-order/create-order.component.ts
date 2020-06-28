import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray, FormBuilder, FormControl } from '@angular/forms';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.scss'],
})
export class CreateOrderComponent implements OnInit {

  orderForm: FormGroup;
  items: FormArray;

  constructor(
    private modalCtrl: ModalController,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.orderForm = this.formBuilder.group({
      customer: '',
      items: this.formBuilder.array([ this.createItem() ])
    });
  }

  onCancel() {
    this.modalCtrl.dismiss(null, 'cancel');
  }

  onConfirm() {
    console.log('OnConfirm: ', this.orderForm);
    this.modalCtrl.dismiss({
      productData: {
        customer: this.orderForm.value.customer,
        prods: this.orderForm.value.items
      }
    },
    'confirm');
  }

  createItem(): FormGroup {
    return this.formBuilder.group({
      prodId: '',
      amount: ''
    });
  }

  addItem(): void {
    this.items = this.orderForm.get('items') as FormArray;
    this.items.push(this.createItem());
  }

}
