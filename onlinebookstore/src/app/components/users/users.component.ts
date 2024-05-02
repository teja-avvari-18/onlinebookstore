import { Component } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
  

  user:User = new User();

  constructor(private userService:UserService){}

  newUser() {
    this.userService.createNewUser(this.user).subscribe(data => {
      console.log(data);
      alert('Your account created sucessfully');
    })
  }

}
