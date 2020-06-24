import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product, ComposedProduct, SingleProduct } from './product.model';
import { ProductService } from './product.service';
import { Subscription } from 'rxjs';
import { ActionSheetController, ModalController } from '@ionic/angular';
import { Router } from '@angular/router';
import { CreateProductComponent } from './create-product/create-product.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.page.html',
  styleUrls: ['./product.page.scss'],
})
export class ProductPage implements OnInit, OnDestroy {

  public products: Product[];
  private productSub: Subscription;

  private product: SingleProduct = new SingleProduct(
    null,
    'Shirt',
    'A green shirt',
  );

  prods: Map<number, number> = new Map<number, number>();

  private composedProduct: ComposedProduct;
  isComposed = false;

  constructor(
    private http: HttpClient,
    private productService: ProductService,
    private actionSheetCtrl: ActionSheetController,
    private modalCtrl: ModalController
  ) { }

  ngOnInit() {
    this.productSub = this.productService.products.subscribe(products => {
      console.log('Showing products OnInit product page: ', products);
      this.products = products;
    });
  }

  ngOnDestroy() {
    this.productSub.unsubscribe();
  }

  ionViewWillEnter() {
    this.productService.fetchProducts().subscribe();
  }

  createProduct() {
    this.actionSheetCtrl.create({
      header: 'Choose option',
      buttons: [
        {
          text: 'Single product',
          handler: () => {
            this.isComposed = false;
            this.openCreateProductModal('single');
          }
        },
        {
          text: 'Composed product',
          handler: () => {
            this.isComposed = true;
            this.openCreateProductModal('composed');
          }
        }
      ]
    }).then(actionSheetEl => {
      actionSheetEl.present();
    });
  }

  openCreateProductModal(mode: 'single' | 'composed') {

    this.modalCtrl.create({
      component: CreateProductComponent,
      componentProps: { selectedMode: mode }
    }).then(modalEl => {
      modalEl.present();
      return modalEl.onDidDismiss();
    }).then(resultData => {
      console.log(resultData.data, resultData.role);
      if (resultData.role === 'confirm') {
        const data = resultData.data.productData;
        console.log('Cheking the resp data: ', data.hasOwnProperty('name'));
        // TODO distinguish between adding single or composed product
        this.productService.addSingleProduct(new SingleProduct(
          null,
          data.name,
          data.description
        ));
        console.log('Modal to add product should now close');
      }
    });
  }

  testMethod() {
    this.productService.getProduct('1');
  }

  createProductTest() {
    this.prods[1] = 3;
    this.prods[2] = 5;

    this.composedProduct = new ComposedProduct(
      null,
      'Shirt Pack',
      'A blue shirt and a green shirt',
      this.prods
    );

    this.http.post('http://127.0.0.1:8080/products/addcomposed', {... this.composedProduct}).subscribe(
      resp => {
        console.log('Got response: ', resp);
      }
    );

  }

}
