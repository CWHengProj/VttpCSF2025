import { Component } from '@angular/core';
import { Menu } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  cart:Menu[]=[]
  updateCart(event:any){
    this.cart = event.value
  }
  
}
