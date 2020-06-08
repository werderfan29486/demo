import { Component, OnInit, Input } from '@angular/core';
import { Customer } from '../customer/customer';
import { CustomerService } from '../customer/customer.service';
import { CustomerListComponent} from '../customer-list/customer-list.component';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css'],
  providers: [CustomerListComponent]
})
export class CustomerDetailsComponent implements OnInit {

  @Input() customer: Customer;
  constructor(private customerService:CustomerService, private listComponent:CustomerListComponent) { }

  ngOnInit() {
            this.listComponent.findCustomerById(id).subscribe(data => {
            this.customer = data;
            });
  }

}
