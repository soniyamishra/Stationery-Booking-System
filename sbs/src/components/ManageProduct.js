import React from 'react';
import {NavLink} from 'react-router-dom';
import SearchBox from './SearchBox';

//import {BrowserRouter as Router,Route,Link ,NavLink} 
//from "react-router-dom";
import axios from "axios";
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
class ManageProduct extends React.Component
{
     constructor(props) {
        super(props);
        this.state = {
            searchField:'',
            product: [],
            errMsg: ""
        }
      
    }
    componentDidMount() {
        axios.get(`http://localhost:2211/product/allproduct`).then((responseData) => {
            console.log(responseData);
            this.setState({ product: responseData.data })

        }).catch((error) => {
            console.log("Some error in reading the data ");
            this.setState({ errMsg: "Error In Reading product Data" })
        })
    }


    render(){
          const {product,searchField} = this.state;
        var filteredData=  product.filter(productData => {

            if(productData.productName.toLowerCase().includes(searchField.toLowerCase()))
            {	
                return productData;
        
            }
            else if(productData.productBrand.toLowerCase().includes(searchField.toLowerCase()))
            {
                return productData;
        
            }
            else if(productData.productModel.toLowerCase().includes(searchField.toLowerCase()))
            {
                return productData;
        
            }
            
        })
        .map(
        (productList,index)=>
        {
        return (    
            <tr key={productList.productId}> 
                <td> {productList.productName} </td>  
                <td>{productList.productBrand} </td> 
                <td> {productList.productModel}</td> 
                <td> {productList.productCount}</td> 
                <td> {productList.productPrice}</td> 
                <td>
                    <NavLink exact to={`/Updatecount/${productList.productId}`} button className="mr-4 btn btn-success">Update</NavLink>
                    
                </td>

            </tr>)
        }
    );

        return(
            <div>
                <h1>List Of Products</h1>
                    <table class="table table-striped table-hover" border="2">
                        <thead>
                            <tr>
                                <th scope="col md-2" className = "bg-dark text-white">Product Name</th>
                                <th scope="col md-2" className = "bg-dark text-white">Model Name</th>
                                <th scope="col md-2" className = "bg-dark text-white">Brand Name</th>
                                <th scope="col md-2" className = "bg-dark text-white">Price</th>
                                <th scope="col md-2" className = "bg-dark text-white">Available Count</th>
                                <th scope="col md-2" className = "bg-dark text-white">Action</th>
                            </tr>
                            
                        </thead>
                        
                    <tbody>
                    <tr> 
            
           <td>            
                <SearchBox placeholder = "Product Name" id="form1" class="form-control" handleChange = { (e) => this.setState({searchField : e.target.value})} />
           </td>
           <td>  
                <SearchBox placeholder = "Model Name" handleChange = { (e) => this.setState({searchField : e.target.value})}/>  
           </td> 
           <td>  
                <SearchBox placeholder = "Brand Name" handleChange = { (e) => this.setState({searchField : e.target.value})}/>  
           </td> 
           <td></td><td></td><td></td>
          
            </tr>  
                        {filteredData}
                    </tbody>
                    </table>
                    
            </div>
        )
    }

}

export default ManageProduct;