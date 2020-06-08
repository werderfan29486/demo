import { Component, OnInit, Input } from '@angular/core';
import { Customer } from '../customer/customer';
import { CustomerService } from '../customer/customer.service';
import { CustomerListComponent} from '../customer-list/customer-list.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css'],
  providers: [CustomerListComponent]
})
export class CustomerDetailsComponent implements OnInit {

  @Input() customer: Customer;
  constructor(private customerService:CustomerService, private listComponent:CustomerListComponent, private activatedRoute:ActivatedRoute) { }

  id;

  ngOnInit() {
      this.activatedRoute.paramMap.subscribe(params => {
        this.id = params.get('id');
      });
      this.listComponent.findCustomerById(this.id).subscribe(data => {
        this.customer = data;
      });
  }

}
