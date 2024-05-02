import { NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './components/add-book/add-book.component';
import { AdminbooksComponent } from './components/adminbooks/adminbooks.component';
import { BookUpdateComponent } from './components/book-update/book-update.component';
import { BooksComponent } from './components/books/books.component';
import { CartComponent } from './components/cart/cart.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { OrdersComponent } from './components/orders/orders.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { PlaceOrderComponent } from './components/place-order/place-order.component';
import { UserOrdersComponent } from './components/user-orders/user-orders.component';
import { UsersComponent } from './components/users/users.component';

const routes: Routes = [
  {path:'',component:LoginComponent},
 {path:'home',component:HomepageComponent},
 {path:'signUp',component:UsersComponent},
 {path:'orders',component:OrdersComponent},
 {path:'books',component:BooksComponent},
 {path:'books/add',component:AddBookComponent},
 {path:'carts',component:CartComponent},
 {path:'orders/add',component:PlaceOrderComponent},
 {path:'books/update/:id',component:BookUpdateComponent},
 {path:'admin/books',component:AdminbooksComponent},
 {path:'user/orders',component:UserOrdersComponent},
 
 

 {path:'**',component:PagenotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
