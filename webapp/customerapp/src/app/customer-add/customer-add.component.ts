import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer/customer.service';
import { Customer } from '../customer/customer';


@Component({
  selector: 'app-customer-add',
  templateUrl: './customer-add.component.html',
  styleUrls: ['./customer-add.component.css']
})
export class CustomerAddComponent implements OnInit {


  constructor(private customerservice:CustomerService ) { }
  
  customer : Customer = new Customer();
  submitted = false;

  ngOnInit() {
  }

  newCustomer(): void {
    this.submitted = false;
    this.customer = new Customer();
  }

  save() {
    this.customerservice.addCustomer(this.customer)
      .subscribe(
        data => {
        console.log(data); 
        this.submitted = true;
      }, 
      error => console.log(error));
    this.customer = new Customer();
  }

  onSubmit() {
    this.save();
  }



}
