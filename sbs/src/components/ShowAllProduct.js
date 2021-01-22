import React from 'react';
//import { NavLink } from 'react-router-dom';
import {BrowserRouter as Router,Route,Link ,NavLink} 
from "react-router-dom";
import axios from "axios";
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
class ShowAllProduct extends React.Component
{   
    constructor(props)
    {
        super(props); 
        this.state={AllProductData:[]} 
    }

    componentDidMount()
    {
            console.log(" Add product componentDidMount excecuted");
            axios.get(`http://localhost:2211/product/allproduct`)
            .then((responseProdData)=>{console.log(responseProdData);
            this.setState({AllProductData:responseProdData.data})
            })
            .catch((error)=>{console.log("Some error in product read data ");
            })
    }

    render(){

        var productList=this.state.AllProductData.map(
            (prod,index)=>
            {
            return (    
                <tr key={prod.id}>
                    <td scope="row"> {prod.productName} </td>  
                    <td> {prod.productModel} </td>
                    <td> {prod.brandName} </td> 
                    <td> {prod.productPrice} Rs.</td> 
                    <td><NavLink exact to = "/ProductBooking" type="button"> Place Order</NavLink></td>
                    <td><NavLink exact to="/AddReview" type="button"> View </NavLink></td>
                </tr>)
            }
        );
        return(
            <div className="container-sm">
                <h1>List Of Products</h1>
                <table class="table table-striped table-hover" border="2">
                    <thead>
                        <tr>
                            <th scope="col" className = "bg-dark text-white">Product Name</th>
                            <th scope="col" className = "bg-dark text-white">Model Name</th>
                            <th scope="col" className = "bg-dark text-white">Brand Name</th>
                            <th scope="col" className = "bg-dark text-white">Price</th>
                            <th scope="col" className = "bg-dark text-white">Action</th>
                            <th scope="col" className = "bg-dark text-white">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {productList}
                    </tbody>
                </table>
            </div>
        )
    }

}
export default ShowAllProduct;