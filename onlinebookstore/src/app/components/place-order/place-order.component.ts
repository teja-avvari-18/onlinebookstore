import { Component } from '@angular/core';
import { Orders } from '../../models/orders';
import { OrderService } from '../../service/order.service';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent {

  order : Orders = new Orders();

  

  constructor(private orderService:OrderService){}

  placeOrder(id:number){
    this.orderService.createOrder(15).subscribe(data=> {
      console.log(data);
      
    })
  }

}
