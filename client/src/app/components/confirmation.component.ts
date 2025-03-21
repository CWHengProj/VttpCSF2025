import { Component } from '@angular/core';

@Component({
  selector: 'app-confirmation',
  standalone: false,
  templateUrl: './confirmation.component.html',
  styleUrl: './confirmation.component.css'
})
export interface Response{
  date:Date,
  orderId:string,
  paymentId:string,
  total:number

}
export class ConfirmationComponent {

  // TODO: Task 5
  response!:Response
  

}
