import { Component, OnInit } from '@angular/core';
import { Product, SingleProduct, ComposedProduct } from '../product.model';
import { ActivatedRoute } from '@angular/router';
import { NavController, LoadingController } from '@ionic/angular';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.page.html',
  styleUrls: ['./edit.page.scss'],
})
export class EditPage implements OnInit {

  product: Product;
  isLoading = false;
  isSingle = true;

  constructor(
    private activatedRoute: ActivatedRoute,
    private navCtrl: NavController,
    private productService: ProductService,
    private loadingCtrl: LoadingController
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paraMap => {
      if (!paraMap.has('productId')) {
        this.navCtrl.navigateBack('/home/tabs/product');
        return;
      }
      const productId = paraMap.get('productId');
      this.isLoading = true;
      this.productService.getProduct(productId).subscribe(
        prod => {
          if (prod instanceof SingleProduct) {
            this.product = prod as SingleProduct;
            console.log(prod);
          }
          if (prod instanceof ComposedProduct) {
            this.isSingle = false;
            this.product = prod as ComposedProduct;
            console.log(prod);
          }
          this.isLoading = false;
        }
      );
    });
  }

}
