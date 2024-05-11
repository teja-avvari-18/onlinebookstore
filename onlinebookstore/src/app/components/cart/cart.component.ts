

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from '../../models/cart';
import { Orders } from '../../models/orders';
import { CartService } from '../../service/cart.service';
import { OrderService } from '../../service/order.service';



@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  carts!: Cart[];
  order: Orders = new Orders();
  uid = Number(localStorage.getItem('id'));

  constructor(private cartService: CartService,private orderService:OrderService,private router:Router) { }

  ngOnInit(): void {
    this.getCartsOfUser(this.uid);
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

    placeOrder(){
      this.orderService.createOrder(this.uid).subscribe(data => {
        console.log(data);
        alert("Your order is successfully placed");
        this.router.navigate(['/user/orders']);
      })
    }



  }


  
