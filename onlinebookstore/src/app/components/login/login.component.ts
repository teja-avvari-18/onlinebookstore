
import { Component } from '@angular/core'; 
import { Router } from '@angular/router'; 
import { HttpClient } from '@angular/common/http'; 
import { User } from '../../models/user';
import { UserService } from '../../service/user.service';

@Component({ 
  selector: 'app-login',
  templateUrl: './login.component.html', 
  styleUrls: ['./login.component.css'] 
}) 
export class LoginComponent { 
  user: User = new User();
  
  constructor(private userService: UserService, private router: Router) { }
  
  onSubmit(): void { 
    this.userService.logIn(this.user.emailId, this.user.password).subscribe({
      next: (loggedInUser) => { 
        this.router.navigate(['/home']); 
      }, 
      error: (error) => { 
        alert("Please Register"); 
      }
    });
  }
}
