import { Component, OnInit, Input } from '@angular/core';
import { Customer } from '../customer/customer';
import { CustomerService } from '../customer/customer.service';
import { CustomerListComponent} from '../customer-list/customer-list.component';
import { ActivatedRoute } from '@angular/router';
import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms'
import {Observable} from "rxjs";

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css'],
  providers: [CustomerListComponent]
})
export class CustomerDetailsComponent implements OnInit {

  @Input() customer: Customer;
  constructor(private customerService:CustomerService, private listComponent:CustomerListComponent, private activatedRoute:ActivatedRoute, private fb: FormBuilder) { }

  id;
  formSubmitAttempt: boolean;

  ngOnInit() {

      this.activatedRoute.paramMap.subscribe(params => {
        this.id = params.get('id');
      });
      this.listComponent.findCustomerById(this.id).subscribe(data => {
        this.customer = data;
      });
  }

  isShow = false;
  private testing: Observable<any>;

    toggleDisplay() {
      this.isShow = !this.isShow;
    }

  updateName() {
      console.log("Hello before update"+this.newName.get("aNewName").value)
      console.log("A id: "+this.id)
      let customer = new Customer()
      customer.id = this.id
      customer.name = this.newName.get("aNewName").value
      this.testing = this.customerService.updateName(this.id, customer)
    this.testing.subscribe(next => next.toString())
      console.log("Message: "+this.testing)
    }

    newName = new FormGroup({
      aNewName : new FormControl('', Validators.required)
    });


  onSubmit() {
    this.formSubmitAttempt = true;
    console.log("hello this is a value: "+this.newName.get("aNewName").value)
    if (this.newName.value.valid) {
      console.log("hello this is a value: "+this.newName.get("name"))
      this.updateName();
      }
      else {

      }
    }


}
