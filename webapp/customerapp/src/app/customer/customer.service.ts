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

   public addCustomer(customer: Customer): Observable<any> {
    return this.http.post(`${this.usersUrl}`, customer);
  }

   public deleteCustomer(id: number): Observable<any> {
      return this.http.delete(`${this.usersUrl}/${id}`);
    }

   public deleteAllCustomers(): Observable<any> {
      return this.http.delete(this.usersUrl);
   }

   public findCustomerById(id: number): Observable<any> {
      return this.http.get(`${this.usersUrl}/${id}`);
   }



}
