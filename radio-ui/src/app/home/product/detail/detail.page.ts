import { Component, OnInit } from '@angular/core';
import { Product, SingleProduct, ComposedProduct } from '../product.model';
import { ActivatedRoute } from '@angular/router';
import { NavController, LoadingController } from '@ionic/angular';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.page.html',
  styleUrls: ['./detail.page.scss'],
})
export class DetailPage implements OnInit {

  prodsInComp: {}[];
  product: SingleProduct | ComposedProduct;
  isLoading = false;
  isSingle = true;

  constructor(
    private activatedRoute: ActivatedRoute,
    private navCtrl: NavController,
    private productService: ProductService,
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
            // TODO : Get to show the products within the composed product
            this.prodsInComp = Object.keys(this.product.prods).map( id => {
              return {id, amount: (this.product as ComposedProduct).prods[id]};
            });
            console.log(this.product, this.prodsInComp);
          }
          this.isLoading = false;
        }
      );
    });
  }

}
