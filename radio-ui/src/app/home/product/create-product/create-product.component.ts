import { Component, OnInit, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { FormGroup, FormControl, FormArray, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss'],
})
export class CreateProductComponent implements OnInit {

  @Input() selectedMode: 'single' | 'composed';
  form: FormGroup;
  prods: FormArray; //  = new FormArray([]);

  orderForm: FormGroup;
  items: FormArray;

  constructor(
    private modalCtrl: ModalController,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    if (this.selectedMode === 'single') {
      this.form = new FormGroup({
        name: new FormControl(null, {
          updateOn: 'blur',
          // validators: [Validators.required, Validators.maxLength(40)]
        }),
        description: new FormControl(null, {
          updateOn: 'blur',
          // validators: [Validators.required, Validators.maxLength(180)]
        }),
        amount: new FormControl(null, {
          updateOn: 'blur',
          // validators: [Validators.required, Validators.maxLength(180)]
        }),
      });
    }
    if (this.selectedMode === 'composed') {
      /*
      this.prodIds = new FormArray([]);
      this.addProdToList();
      this.form = new FormGroup({
        name: new FormControl(null, {
          updateOn: 'blur',
          // validators: [Validators.required, Validators.maxLength(40)]
        }),
        description: new FormControl(null, {
          updateOn: 'blur',
          // validators: [Validators.required, Validators.maxLength(180)]
        }),
        prodIds: this.prodIds,
       });
       */
      /*this.form = this.formBuilder.group({
        name: '',
        description: '',
        prods: this.formBuilder.array([ this.addItem() ])
      });*/

      this.orderForm = this.formBuilder.group({
        name: '',
        description: '',
        items: this.formBuilder.array([ this.createItem() ])
      });
    }
  }

  onCancel() {
    this.modalCtrl.dismiss(null, 'cancel');
  }

  onConfirm() {
    console.log('OnConfirm: ', this.form);
    if (this.selectedMode === 'single') {
      this.modalCtrl.dismiss({
        productData: {
          name: this.form.value.name,
          description: this.form.value.description,
          amount: this.form.value.amount
        }
      },
      'confirm');
    }
    if (this.selectedMode === 'composed') {
      this.modalCtrl.dismiss({
        productData: {
          name: this.orderForm.value.name,
          description: this.orderForm.value.description,
          prods: this.orderForm.value.items
        }
      },
      'confirm');
    }
  }

  addProdToList(): FormGroup {
    return this.formBuilder.group({
      prodId: '',
      amount: '',
    });
    /*
    this.prodIds.push(new FormGroup({
      prodId: new FormControl(null, {
        updateOn: 'blur',
      }),
      amount: new FormControl(null, {
        updateOn: 'blur',
      }),
    }));
    */
   }

   /*addItem(): void {
    this.prods = this.form.get('prods') as FormArray;
    this.prods.push(this.addProdToList());
  }*/

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
