import { Component, OnInit } from '@angular/core';
import {Form, FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDetails} from "../model/account.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {


  accountFormGroup!: FormGroup
  currentPage: number = 0
  pageSize: number = 5
  account$!: Observable<AccountDetails>
  operationFormGroup!: FormGroup
  errorMessage!: string


  constructor(private fb: FormBuilder, private accountService: AccountsService) { }

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId: this.fb.control("")
    })
    this.operationFormGroup = this.fb.group({
      operationType: this.fb.control(null),
      amount: this.fb.control(0),
      description : this.fb.control(null),
      accountDestination: this.fb.control(null)
    })
  }

  handleSearchAccount() {
    let accountId: string = this.accountFormGroup.value.accountId
    this.account$=this.accountService.getAccount(accountId, this.currentPage, this.pageSize).pipe(
      catchError(err => {
        this.errorMessage = err.message
        return throwError(err)
      })
    )
  }

  gotoPage(page: number) {
    this.currentPage = page
    this.handleSearchAccount()
  }

  handleAccountOperation() {
    let accoundId: string = this.accountFormGroup.value.accountId
    let operationType = this.operationFormGroup.value.operationType
    let amount: number = this.operationFormGroup.value.amount
    let description: string = this.operationFormGroup.value.description
    let accountDestination: string = this.operationFormGroup.value.accountDestination


   // const { accoundId, operationType, amount, description, accountDestination, accountSource }: { accoundId: string, operationType: string, amount: number, description:string, accountDestination:string, accountSource:String  } = this.operationFormGroup.value

    if(operationType == 'DEBIT'){
        this.accountService.debit(accoundId, amount, description).subscribe({
          next: (data) => {
            alert("Success DEBIT")
            this.operationFormGroup.reset()
            this.handleSearchAccount()
          }, error: err => {
            console.log(err)
          }
        })
    } else if(operationType == 'CREDIT'){
        this.accountService.credit(accoundId, amount, description).subscribe({
          next: (data) => {
            alert("Success credit")
            this.operationFormGroup.reset()
            this.handleSearchAccount()
          }, error: err => {
            console.log(err)
          }
        })
    } else if(operationType == 'TRANSFER'){
      this.accountService.transfer(accoundId,accountDestination, amount, description).subscribe({
        next: (data) => {
          alert("Success TRANSFER")
          this.operationFormGroup.reset()
          this.handleSearchAccount()
        }, error: err => {
          console.log(err)
        }
      })
    }

  }
}
