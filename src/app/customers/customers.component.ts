import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../services/customer.service";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  public customers$!: Observable<Array<Customer>>
  errorMessage!: string;
  constructor(private customerService:CustomerService) { }

  ngOnInit(): void {
  this.customers$ = this.customerService.getCustomers();
  }

}
