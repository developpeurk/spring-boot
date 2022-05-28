import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {AccountDetails} from "../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http: HttpClient) { }

  public getAccount(accountId: String, page: number, size: number):Observable<AccountDetails>{
    return this.http.get<AccountDetails>(environment.backendHost +"/accounts/" + accountId + "/pageOperations?page="+page + "&size="+size)
  }

  public debit(accountId: String, amount: number, description: string){
    let data = { accountId: accountId, amount: amount, description: description}
    return this.http.post(environment.backendHost +"/accounts/debit", data)
  }

  public credit(accountId: String, amount: number, description: string){
    let data = { accountId: accountId, amount: amount, description: description}
    return this.http.post(environment.backendHost +"/accounts/credit", data)
  }

  public transfer(accountSource: String, accountDestination: String, amount: number, description: string){
    let data = { accountSource: accountSource, accountDestination: accountDestination ,amount: amount, description: description}
    return this.http.post(environment.backendHost +"/accounts/transfer", data)
  }
}
