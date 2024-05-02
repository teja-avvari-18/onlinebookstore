import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { OrdersComponent } from './components/orders/orders.component';
import { CartComponent } from './components/cart/cart.component';
import { BooksComponent } from './components/books/books.component';
import { BookUpdateComponent } from './components/book-update/book-update.component';
import { AddBookComponent } from './components/add-book/add-book.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UsersComponent } from './components/users/users.component';
// import { AddToCartComponent } from './components/add-to-cart/add-to-cart.component';
import { PlaceOrderComponent } from './components/place-order/place-order.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { HeaderComponent } from './components/header/header.component';
import { CartService } from './service/cart.service';
import { UserService } from './service/user.service';
import { AdminbooksComponent } from './components/adminbooks/adminbooks.component';
import { UserOrdersComponent } from './components/user-orders/user-orders.component';
import { AdminheaderComponent } from './components/adminheader/adminheader.component';



@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    LoginComponent,
    OrdersComponent,
    CartComponent,
    BooksComponent,
    BookUpdateComponent,
    AddBookComponent,
    UsersComponent,
    PlaceOrderComponent,
    PagenotfoundComponent,
    HeaderComponent,
    AdminbooksComponent,
    UserOrdersComponent,
    AdminheaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    provideClientHydration(),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
