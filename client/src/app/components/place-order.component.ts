import { Component, inject, Input, OnInit } from '@angular/core';
import { Menu, Payload } from '../models';
import { CartService } from '../services/cart.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-place-order',
  standalone: false,
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent implements OnInit{

  cartService=inject(CartService)
  cart:Menu[]=[]
  fb = inject(FormBuilder)
  userForm!:FormGroup
  route =inject(Router)
  http = inject(HttpClient)
  total:number =0
  // TODO: Task 3
  
  ngOnInit(): void {
    console.log("at place order component.")
    this.cart = this.cartService.getCurrentCart()
    this.cartService.cart$.subscribe(updatedCart => {
      this.cart = updatedCart
    })
    this.userForm = this.fb.group({
      username: this.fb.control<string>('',Validators.required),
      password: this.fb.control<string>('',Validators.required)
    })
    this.calculateTotalCost()
  }
  dropCart() {
    console.log("start over. cart dropped.")
    this.cartService.updateCart([])
    this.route.navigate(['/'])
    }
  calculateTotalCost(){
    this.total=0
    this.cart.forEach((item:Menu)=>{
      this.total+=(item.quantity * item.price)
    })
    return this.total;
  }

  submitOrder() {
    const payload:Payload ={
      username: this.userForm.get('username')?.value,
      password: this.userForm.get('password')?.value,
      items: this.cart
      
  
    }
    this.http.post<string>(`/api/food_order`,payload).subscribe(
      {next: (data)=>
        {console.log(data),
          this.route.navigate(['confirmation'])
        },
        error:(err) => {console.log(err)
          window.alert("Unauthorized user! You are under arrest")
        }

      }
    )
  }
  
  
}