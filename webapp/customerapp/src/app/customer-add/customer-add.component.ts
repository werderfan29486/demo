import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer/customer.service';
import { Customer } from '../customer/customer';
import { FormGroup, FormControl, Validators } from '@angular/forms'


@Component({
  selector: 'app-customer-add',
  templateUrl: './customer-add.component.html',
  styleUrls: ['./customer-add.component.css']
})
export class CustomerAddComponent implements OnInit {


  constructor(private customerservice:CustomerService ) { }

  customer : Customer = new Customer();
  submitted = false;
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  postalCodePattern = "^[0-9]{5}$"
  houseNumberPattern = "[0-9]{1,3}"
  formSubmitAttempt: boolean;

  ngOnInit() {
  }

  newCustomer(): void {
    this.submitted = false;
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
  this.formSubmitAttempt = true;
  if (this.customerAddForm.valid) {
    this.save();
    }
    else {
    this.validateAllFormFields(this.customerAddForm)
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

  onReset() {
          this.submitted = false;
          this.customerAddForm.reset();
      }

       get f() { return this.customerAddForm.controls; }



  customerAddForm = new FormGroup({
    'name': new FormControl('', Validators.required),
    'firstName': new FormControl('', Validators.required),
    'street': new FormControl('', Validators.required),
    'houseNumber': new FormControl('', [Validators.required, Validators.pattern(this.houseNumberPattern)]),
    'postalCode': new FormControl('', [Validators.required, Validators.pattern(this.postalCodePattern)]),
    'email': new FormControl('', [Validators.required, Validators.pattern(this.emailPattern)]),
    'phoneNumber': new FormControl('', Validators.required)
  });

  get name(){
  	return this.customerAddForm.get('name')
    }

  get firstName(){
  	return this.customerAddForm.get('firstName')
  }

  get street(){
    	return this.customerAddForm.get('street')
  }

  get houseNumber(){
      	return this.customerAddForm.get('houseNumber')
  }

  get postalCode(){
      	return this.customerAddForm.get('postalCode')
  }

  get email(){
      	return this.customerAddForm.get('email')
  }

  get phoneNumber(){
      	return this.customerAddForm.get('phoneNumber')
  }










}
