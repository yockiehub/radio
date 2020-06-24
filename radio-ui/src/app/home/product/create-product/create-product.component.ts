import { Component, OnInit, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { FormGroup, FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss'],
})
export class CreateProductComponent implements OnInit {

  @Input() selectedMode: 'single' | 'composed';
  form: FormGroup;
  formSubs: Subscription;
  numberOfBooks: number[];

  constructor(
    private modalCtrl: ModalController
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
      });
    }
    if (this.selectedMode === 'composed') {
      // TODO

      let group = {}
      group['numberOfBooks']= new FormControl('');
      this.form = new FormGroup(group);
      this.formSubs = this.form.valueChanges.subscribe(val => {
          this.handleNumberChange(val);
      });

      this.form = new FormGroup({
        name: new FormControl(null, {
          updateOn: 'blur',
          // validators: [Validators.required, Validators.maxLength(40)]
        }),
        description: new FormControl(null, {
          updateOn: 'blur',
          // validators: [Validators.required, Validators.maxLength(180)]
        }),
        prodIds: new FormControl(null, {
          updateOn: 'blur',
        })
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
        }
      },
      'confirm');
    }
  }

  handleNumberChange(val: number){
    // Try to Remove Control for higher number if any.
    const val_plus_one = val + 1;
    try{
      this.form.removeControl(val_plus_one.toString());
    } catch {}
    // Add formControls for 1 - number of books that user enters.
    const numbers = Array(val).fill(val).map((_, idx) =>  idx + 1 );
    numbers.forEach((num) => {
      const fc = new FormControl('');
      this.form.addControl(num.toString(), fc);
    });
    this.numberOfBooks = numbers;
}

}
