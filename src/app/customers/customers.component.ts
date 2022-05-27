import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../services/customer.service";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  public customers: any
  errorMessage!: string;
  constructor(private customerService:CustomerService) { }

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe( {
     next: (data)=>{
       this.customers = data;
     },
      error: (err => {
        this.errorMessage = err.message
      })
    })
  }

}
