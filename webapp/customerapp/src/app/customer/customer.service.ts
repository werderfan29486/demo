import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Customer } from './customer';
import {Observable, throwError} from 'rxjs';
import {catchError} from "rxjs/operators";

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

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };

   public updateName(id: number, value: Customer): Observable<any> {
      console.log("updating to url: "+this.usersUrl)
      //return this.http.put(`${this.usersUrl}/1`, value);
      console.log("Updating user with id: "+value.id+"And new name: "+value.name)
     return this.http.put<Customer>('http://localhost:8181/Kundenverwaltung/customers/1', value).pipe(catchError(catchError(this.handleError)));
   }



}
