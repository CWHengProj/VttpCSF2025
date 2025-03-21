import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlaceOrderComponent } from './components/place-order.component';
import { ConfirmationComponent } from './components/confirmation.component';
import { MenuComponent } from './components/menu.component';

const routes: Routes = [
  {path: '', component:MenuComponent},
  {path: 'placeorder', component:PlaceOrderComponent},
  {path: 'confirmation', component:ConfirmationComponent},
  {path: '**', redirectTo:'/', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }