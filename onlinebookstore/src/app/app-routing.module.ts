import { NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { adminauthGuard } from './adminauth.guard';
import { AddBookComponent } from './components/add-book/add-book.component';
import { AdminbooksComponent } from './components/adminbooks/adminbooks.component';
import { AdminpageComponent } from './components/adminpage/adminpage.component';
import { BookUpdateComponent } from './components/book-update/book-update.component';
import { BooksComponent } from './components/books/books.component';
import { CartComponent } from './components/cart/cart.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { OrdersComponent } from './components/orders/orders.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { UserOrdersComponent } from './components/user-orders/user-orders.component';
import { UsersComponent } from './components/users/users.component';
import { userauthGuard } from './userauth.guard';

const routes: Routes = [
  {path:'',component:LoginComponent},
 {path:'signUp',component:UsersComponent},
 {path:'home',canActivate:[userauthGuard],component:HomepageComponent},
 {path:'books',canActivate:[userauthGuard],component:BooksComponent},
 {path:'carts',canActivate:[userauthGuard],component:CartComponent},
 {path:'user/orders',canActivate:[userauthGuard],component:UserOrdersComponent},

 {path:'books/update/:id',canActivate:[adminauthGuard],component:BookUpdateComponent},
 {path:'books/add',canActivate:[adminauthGuard],component:AddBookComponent},
 {path:'admin/books',canActivate:[adminauthGuard],component:AdminbooksComponent},
 {path:'admin',canActivate:[adminauthGuard],component:AdminpageComponent},
 {path:'orders',canActivate:[adminauthGuard],component:OrdersComponent},
 
 
 

 {path:'**',component:PagenotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
