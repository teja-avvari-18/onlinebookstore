

import { Component, OnInit } from '@angular/core';
import { Cart } from '../../models/cart';
import { CartService } from '../../service/cart.service';



@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  carts!: Cart[];

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.getCartsOfUser(15);
  }

  // getCart(): void {
  //   this.cartService.getCart()
  //     .subscribe(carts => this.carts = carts);
  // }

  getCartsOfUser(id:number){
    this.cartService.getCartsByUserId(id).subscribe(data =>{
      this.carts = data
    } );
    }

    removeCart(id:number){
      this.cartService.removeCart(id).subscribe(data => {
        alert('Cart Deleted Successfully');
      });
    }



  }


  
