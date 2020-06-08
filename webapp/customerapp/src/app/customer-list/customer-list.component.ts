import { Component, OnInit, Input } from '@angular/core';
import { Customer } from '../customer/customer';
import { CustomerService } from '../customer/customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})

export class CustomerListComponent implements OnInit {

  title = 'Kundenübersicht'
  customers: Customer[];
  @Input() customer: Customer;


  constructor(private customerService: CustomerService) {
   this.title = 'Kundenübersicht';
   }

  ngOnInit() {
    this.customerService.findAll().subscribe(data => {
    this.customers = data;
    });
    }

   findCustomerById(id: number) {
      this.customerService.findCustomerById(id);
   }

   deleteCustomer(id: number) {
      let resp = this.customerService.deleteCustomer(id);
        resp.subscribe((data) => this.customers=data);
   }

   deleteAllCustomers() {
      this.customerService.deleteAllCustomers()
      .subscribe (data => {
      console.log(data);
      this.ngOnInit();
      },
      error => console.log('ERROR: ' + error));
   }

}
