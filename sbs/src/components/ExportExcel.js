import React, { Component } from 'react'  
import axios from 'axios';  
import ReactHTMLTableToExcel from 'react-html-table-to-excel'; 
 
export class ExportExcel extends Component 
{  
    constructor(props) 
    {  
        super(props)  
        this.state =
        {  
            ProductData: []  
        }  
    }  
    componentDidMount()
    {  
        axios.get('http://localhost:2211/product/allproduct').then(response =>
        {  
            console.log(response.data);  
            this.setState
            ({  
                ProductData: response.data  
            });  
        }).catch((error) => {
            console.log("Some error in reading the data ");
            this.setState({ errMsg: "Error In Reading product Data" })
        })
    }  
    render() 
    {  
        var data=
            this.state.ProductData.map((p, index) => 
            {  
                return (<tr key={p.productId}>  
                <td >{p.productId}</td>  
                <td >{p.productName}</td>  
                <td >{p.productModel}</td>  
                <td >{p.productBrand}</td>  
                <td >{p.productPrice}</td>  
                <td >{p.productCount}</td>  
                <td >{p.productCreatedAt}</td> 
                <td >{p.productUpdatedAt}</td> 
                <td >{p.productDeletedAt}</td>
                <td style={{ paddingRight: "114px" }} >{p.productDeletedFlag}</td>  
                </tr>  )
            })  
        
        return (  
            <div> 
                <h1 align="center"><i className="fas fa-swatchbook"> Stationary Booking System </i></h1> <br/>
                <table class="table table-striped table-hover" border="2" id="Product" class="table">  
                <thead>  
                <tr>  
                    <th scope="col" className = "bg-dark text-white">Product
                        Id</th>  
                    <th scope="col" className = "bg-dark text-white" >Product
                        Name</th>  
                    <th scope="col" className = "bg-dark text-white">Product
                        Model</th>  
                    <th scope="col" className = "bg-dark text-white">Product
                        Brand</th>  
                    <th scope="col" className = "bg-dark text-white">Product
                        Price</th>  
                    <th scope="col" className = "bg-dark text-white">Product
                        Count</th>  
                    <th scope="col" className = "bg-dark text-white">Product
                        CreatedAt</th>  
                    <th scope="col" className = "bg-dark text-white">Product
                        UpdatedAt</th> 
                    <th scope="col" className = "bg-dark text-white">Product
                        DeletedAt</th>
                    <th scope="col" className = "bg-dark text-white"> Product
                        DeletedFlag</th> 
                </tr>  
                
                </thead>  
                <tbody>    
                    {data}         
                  
            </tbody> 
                </table>  
                <div class="text-center">  
                    <ReactHTMLTableToExcel  
                        className="btn btn-success"  table="Product" filename="Products"  sheet="Products"  
                        buttonText="DOWNLOAD"  />  
                </div>  
            </div>  
        )  
    }  
}  
export default ExportExcel;