import React from 'react';
import axios from "axios";
//import {BrowserRouter as Router,Route,Link ,NavLink} 
//from "react-router-dom";
//import axios from "axios";
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
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
        } 
        this.handleChange=this.handleChange.bind(this) ;
    }

    handleChange(event)
    {   
        this.setState({
            ...this.state,
            [event.target.name] : event.target.value
        })
    }

    handleSubmit = (event)=>{
        event.preventDefault();
        axios.post(`http://localhost:2211/product`, {
            ...this.state,
          })
          .then(function (response) {
            console.log(response);
            
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
            console.log(error);
          })

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