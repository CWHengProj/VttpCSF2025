// You may use this file to create any models
export interface Menu{
    _id:string,
    name:string,
    price:number,
    description:string
    quantity:number
}
export interface Payload{
    username:string,
    password:string,
    items:Menu[]
}
