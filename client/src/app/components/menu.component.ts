import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import {  Menu } from '../models';

@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {
  http = inject(HttpClient);
  menuItems: Menu[] = [];
  cart:Menu[] =[]
  totalCost:number=0
  orderTally:number =0
  
  ngOnInit(){
    //retrieve the menu from the database
    //TODO: display the items from the menu to menu
    this.http.get<Menu[]>(`/api/menu`).subscribe({
      next: (data) => {
        this.menuItems = data
      },
      error: (err) => {
        console.error(err)
      }
    })
  }
  addItemToCart(item:Menu, index:number){
    console.log("item added.")
    if (item.quantity == null){
      this.menuItems[index].quantity=1
      this.cart.push(item)
    }
    else{
      this.menuItems[index].quantity++
      this.cart=this.cart.filter((currentItem:Menu)=>{return item.name!==currentItem.name})
      this.cart.push(item)
    }
    this.orderTally++
    this.totalCost+= item.price

    //add the item to a cart, if it exists increment
    
  }
  removeItemFromCart(item:Menu,index:number){
    console.log("item removed.")
    if (item.quantity>0){
      this.menuItems[index].quantity--
      this.cart=this.cart.filter((currentItem:Menu)=>{return item.name!==currentItem.name})
      this.cart.push(item)
    }
    else{
      this.cart=this.cart.filter((currentItem:Menu)=>{return item.name!==currentItem.name})
    }
    if(this.orderTally>0){
      this.orderTally--
    }
    if(this.totalCost>0){
      this.totalCost-=item.price
    }
    //decrement the item from the cart, if number hits 0 remove it.
  }
  
}