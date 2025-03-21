import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-confirmation',
  standalone: false,
  templateUrl: './confirmation.component.html',
  styleUrl: './confirmation.component.css'
})

export class ConfirmationComponent implements OnInit{
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  // TODO: Task 5
  response!:Response
  

}
export interface Response{
  date:Date,
  orderId:string,
  paymentId:string,
  total:number

}