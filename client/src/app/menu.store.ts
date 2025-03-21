
import { ComponentStore } from '@ngrx/component-store';
import { Menu } from './models';

const  INIT: Menu[] =[]

export class MenuStore extends ComponentStore<Menu[]> {

    
    constructor() {
        // init to empty array
        super(INIT);
    }

    // mutator - crud operations here to the cart, which the others will subscribe to
    //retrieve the cart
    readonly cart$ =
    //add items to the cart
    //remove items from the cart


}