import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Menu } from '../models';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartSubject = new BehaviorSubject<Menu[]>([])
  public cart$ = this.cartSubject.asObservable()
  
  updateCart(cart: Menu[]) {
    this.cartSubject.next(cart)
  }
  
  getCurrentCart() {
    return this.cartSubject.value
  }
}