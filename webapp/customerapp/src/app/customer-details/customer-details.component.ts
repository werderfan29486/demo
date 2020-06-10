import { Component, OnInit, Input } from '@angular/core';
import { Customer } from '../customer/customer';
import { CustomerService } from '../customer/customer.service';
import { CustomerListComponent} from '../customer-list/customer-list.component';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms'

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

    toggleDisplay() {
      this.isShow = !this.isShow;
    }

  updateName() {
       this.activatedRoute.paramMap.subscribe(params => {
              this.id = params.get('id')});
      this.customerService.updateName(this.id, {name: this.customer.name})
      .subscribe(
      data => {
        console.log(data);
        this.customer = data as Customer;
      },
      error => console.log(error));
      }

    newName = new FormGroup({
      'name': new FormControl('', Validators.required)
    });

  get name(){
      	return this.newName.get('name')
        }

  onSubmit() {
    this.formSubmitAttempt = true;
    if (this.newName.valid) {
      this.updateName();
      }
      else {
      this.validateAllFormFields(this.newName)
      }
    }

    validateAllFormFields(formGroup: FormGroup) {         //{1}
      Object.keys(formGroup.controls).forEach(field => {  //{2}
        const control = formGroup.get(field);             //{3}
        if (control instanceof FormControl) {             //{4}
          control.markAsTouched({ onlySelf: true });
        } else if (control instanceof FormGroup) {        //{5}
          this.validateAllFormFields(control);            //{6}
        }
      });
    }

}
