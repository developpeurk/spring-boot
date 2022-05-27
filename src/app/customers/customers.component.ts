import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../services/customer.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  public customers$!: Observable<any>
  errorMessage!: string;
  constructor(private customerService:CustomerService) { }

  ngOnInit(): void {
  this.customers$ = this.customerService.getCustomers();
  }

}
