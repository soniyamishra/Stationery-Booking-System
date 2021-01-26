import React from 'react';
import axios from "axios";
//import {BrowserRouter as Router,Route,Link ,NavLink} 
//from "react-router-dom";
//import axios from "axios";
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
  function isvalid(val) {
    let  validProductFieldName = RegExp('[A-Za-z0-9\s]+');
    if(validProductFieldName.test(val)){
      return true;
    }
    else{
        return false;
    }
 }

//  const validProductFieldName = RegExp('[A-Za-z0-9\s]+');

//  const validateForm = errorMessage => {
//     let valid = true;
//     Object.values(errorMessage).forEach(val => val.length > 0 && (valid = false));
//     return valid;
//   };

class AddProduct extends React.Component
{   
    constructor(props)
    {
        super(props);
        this.state = {
            productName :"",
            productModel : "",
            productBrand : "",
            productPrice : {},
            productCount : {},
            productCreatedAt : null,
            productDeletedAt : null,
            productUpdatedAt : null,
            productDeletedFlag : 'N',
            errors:{
                productName :"",
                productModel : "",
                productBrand : "",
                productPrice : "",
                productCount : "",
            }
        } 
        this.handleChange=this.handleChange.bind(this) ;
    }

    handleChange(event)
    {   
    event.preventDefault();
    const { name, value } = event.target;
    let errors = this.state.errors;
    
    switch (name) {
        case 'productName': 
            errors.productName = 
            value.length <2
            ? 'Product Name must be 2 characters long!'
            : '';
        break;
        case 'productModel': 
        errors.productModel = 
            value.length <2
            ? 'Product Name must be 2 characters long!'
            : '';
        break;
        case 'productBrand': 
        errors.productBrand = 
            value.length <2
            ? 'Product Name must be 2 characters long!'
            : '';
        break;
        case 'productPrice': 
        errors.productPrice = 
            value.length ===0
            ? 'Product Price cannot be null or 0!'
            : '';
        break;
        case 'productCount': 
        errors.productCount = 
            value.length ===0
            ? 'Product Price cannot be null or 0!'
            : '';
        break;
        default:
        break;
     }

        this.setState({errors, [name]: value}, ()=> {
            console.log(errors)
        });

        this.setState({
            ...this.state,
            [event.target.name] : event.target.value
        })
    }

    handleSubmit = (event)=>{
        event.preventDefault();
        console.log(this.state.productCount)
        if(this.state.productName === null || this.state.productBrand === null || this.state.productModel ===null ||
            this.state.productPrice.length === 0 || this.state.productCount.length === 0)
          {     
              
              alert("All Field are mandatory")
          }
        else if(this.state.productName.length === 0)
        {
            alert("Product Name is Mandtory Field")
        }
        else if(this.state.productName.length < 2)
        {
            alert("Product Name must be 2 characters long!")
        }
        else if(this.state.productBrand.length === 0)
        {
            alert("Product Brand is Mandtory Field")
        }
        else if(this.state.productBrand.length < 2)
        {
            alert("Product Brand must be 2 characters long!")
        }
        else if(this.state.productModel.length === 0)
        {
            alert("Product Model is Mandtory Field")
        }
        else if(this.state.productModel.length < 2)
        {
            alert("Product Model must be 2 characters long!")
        }
        // else if(isvalid(this.state.productName) || isvalid(this.state.productModel) || isvalid(this.state.productBrand))
        // {
        //     alert("Product Name,Model and Brand can contain characters and digits only")
        // }
       else
       {
        axios.post(`http://localhost:2211/product`, {
            productName: this.state.productName,
            productModel: this.state.productModel,
            productBrand: this.state.productBrand,
            productPrice: this.state.productPrice,
            productCount: this.state.productCount,
          })
          .then(function (response) {
            console.log(response);
            alert("Product is added successfully");
          })
          .then(
              ()=>{
                  this.setState( {
                    productName :"",
                    productModel : "",
                    productBrand : "",
                    productPrice : {},
                    productCount : {},
                    productCreatedAt : null,
                    productDeletedAt : null,
                    productUpdatedAt : null,
                    productDeletedFlag : 'N',
                } )
              }
          )
          .catch(function (error) {
             alert("Some thing went wrong !.. 'Invalid Data or network issue'")
          })
        }
     
    }

    render(){

        return(
        <div className= "mt-4">
            <div className="card">
                <div className="card-header">
                   <h4>Add Product </h4> 
                </div>
                <div className="card-body">
                    <form className="row g-3">
                        <div className="col-md-6">
                            <label for="productName" className="form-label">Product Name</label>
                            <input 
                            type="text" 
                            className="form-control" 
                            id="productName" 
                            required 
                            pattern="[A-Za-z0-9\s]+"
                            name="productName"
                            value = {this.state.productName}
                            onChange={this.handleChange}
                            />
                            <div>{this.state.errors.productName}</div>
                        </div>
                        
                        <div className="col-md-6">
                            <label for="productModel" className="form-label">Model Name</label>
                            <input 
                            type="text" 
                            className="form-control" 
                            id="productModel" 
                            required 
                            pattern="[A-Za-z0-9\s]+"
                            name="productModel"
                            value = {this.state.productModel}
                            onChange={this.handleChange}
                            />
                            <div>{this.state.errors.productModel}</div>
                        </div>
                       
                        <div className="col-md-6">
                            <label for="productBrand" className="form-label">Brand Name</label>
                            <input 
                            type="text" 
                            className="form-control" 
                            id="productBrand" 
                            required 
                            pattern="[A-Za-z0-9\s]+"
                            name="productBrand"
                            value = {this.state.productBrand}
                            onChange={this.handleChange}
                            />
                             <div>{this.state.errors.productBrand}</div>
                        </div>
                       
                        <div className="col-md-6">
                            <label for="productCount" className="form-label">Available Count</label>
                            <input 
                            type="number" 
                            className="form-control" 
                            id="productCount" 
                            required
                            name="productCount"
                            value = {this.state.productCount}
                            onChange={this.handleChange}
                            />
                             <div>{this.state.errors.productCount}</div>
                        </div>
                       
                        <div className="col-md-6 mb-4">
                            <label for="productPrice" className="form-label">Product Price</label>
                            <input 
                            type="number" 
                            className="form-control" 
                            id="productPrice" 
                            required
                            name="productPrice"
                            value = {this.state.productPrice}
                            onChange={this.handleChange}
                            />
                             <div>{this.state.errors.productPrice}</div>
                        </div>
                       
                        <div className="col-12">
                            <div className="row">
                                <div className ="col-2">
                                    <button className="btn btn-success" type="submit" onClick={this.handleSubmit}>Submit</button>
                                </div>
                                <div className ="col-2">
                                    <button className="btn btn-dark" type="reset">Reset</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        )
    }
}


export default AddProduct;