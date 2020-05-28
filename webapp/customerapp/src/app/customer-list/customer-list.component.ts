import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer/customer';
import { CustomerService } from '../Customer/customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customers: Customer[];

  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.customerService.findAll().subscribe(data => {
    this.customers = data;
    });
    }

}
