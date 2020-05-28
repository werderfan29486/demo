import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Customer } from './customer';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class CustomerService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
   this.usersUrl = 'http://localhost:8181/Kundenverwaltung/customers';
   }

   public findAll(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.usersUrl);
   }

 /*  public addCustomer(customer: Customer) {
    return this.http.post(this.usersUrl, customer);
   }
 */
   public addCustomer(customer: Customer): Observable<any> {
    return this.http.post(`${this.usersUrl}`+'add', customer);
  }



}
