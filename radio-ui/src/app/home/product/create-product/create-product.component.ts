import { Component, OnInit, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss'],
})
export class CreateProductComponent implements OnInit {

  @Input() selectedMode: 'single' | 'composed';
  form: FormGroup;

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

}
