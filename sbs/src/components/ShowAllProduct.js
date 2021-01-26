import React from 'react';
import { NavLink } from 'react-router-dom';
import SearchBox from './SearchBox';

//import {BrowserRouter as Router,Route,Link ,NavLink} 
//from "react-router-dom";
import axios from "axios";
import  ReactPaginate from 'react-paginate';
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
class DeleteProduct extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            offset: 0,
            perPage: 5,
            currentPage: 0,
            searchField: '',   
            orgtableData: [],         
            product: [],
            errMsg: ""
        }

        this.handlePageClick = this.handlePageClick.bind(this);
    }

    handlePageClick = (e) => {
        const selectedPage = e.selected;
        const offset = selectedPage * this.state.perPage;

        this.setState({
            currentPage: selectedPage,
            offset: offset
        }, () => {
            this.loadMoreData()
        });

    };

    loadMoreData() {
		const data = this.state.orgtableData;
		
		const slice = data.slice(this.state.offset, this.state.offset + this.state.perPage)
		this.setState({
			pageCount: Math.ceil(data.length / this.state.perPage),
			product:slice
		})
	
    }

    componentDidMount() {
        axios.get(`http://localhost:2211/product/allproduct`).then((responseData) => {
            console.log(responseData);
            var data = responseData.data;
				
            var slice = data.slice(this.state.offset, this.state.offset + this.state.perPage)

            this.setState({
                pageCount: Math.ceil(data.length / this.state.perPage),
                    orgtableData :responseData.data,
                 product: slice 
                })
            console.log(data);
        }).catch((error) => {
            console.log("Some error in reading the data ");
            this.setState({ errMsg: "Error In Reading product Data" })
        })
    }

    render(){

        var productList=this.state.product.map(
            (prod,index)=>
            {
            return (    
                <tr key={prod.id}>
                    <td scope="row"> {prod.productName} </td>  
                    <td> {prod.productModel} </td>
                    <td> {prod.productBrand} </td> 
                    <td> {prod.productPrice} Rs.</td> 
                   
                    <td><NavLink exact to = {`/ProductBooking/${prod.productId}`}type="button" className="mr-4 btn btn-success"> Place Order</NavLink></td>
                    <td> <NavLink exact to={`/ShowReviews/${prod.productId}`} button className="mr-4 btn btn-success">Reviews</NavLink></td>
                    <td> <NavLink exact to={`/AddReview/${prod.productId}`} button className="mr-4 btn btn-success">AddReview</NavLink></td>
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
                            <th colspan='3'scope="col" className = "bg-dark text-white">Action</th>
                          
                        </tr>
                    </thead>
                    <tbody>
                        {productList}
                    </tbody>
                </table>
                <ReactPaginate
                    previousLabel={"prev"}
                    nextLabel={"next"}
                    breakLabel={"..."}
                    breakClassName={"break-me"}
                    pageCount={this.state.pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={this.handlePageClick}
                    containerClassName={"pagination"}
                    subContainerClassName={"pages pagination"}
                    activeClassName={"active"}/>
            </div>
        )
    }

}

export default DeleteProduct;