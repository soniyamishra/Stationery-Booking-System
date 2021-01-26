import React from 'react';
import { NavLink } from 'react-router-dom';
import SearchBox from './SearchBox';

//import {BrowserRouter as Router,Route,Link ,NavLink} 
//from "react-router-dom";
import axios from "axios";
import  ReactPaginate from 'react-paginate';
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
class ManageProduct extends React.Component {
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



    render() {
        

        const { product, searchField } = this.state;
        var filteredData = product.filter(productData => {
    
            if (productData.productName.toLowerCase().includes(searchField.toLowerCase())) {
                return productData;

            }
            else if (productData.productBrand.toLowerCase().includes(searchField.toLowerCase())) {
                return productData;

            }
            else if (productData.productModel.toLowerCase().includes(searchField.toLowerCase())) {
                return productData;

            }

        })
         
            .map(
                (productList, index) => {
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
                        </tr>


                    )
                }
            );

        return (
            <div>


                <h1>List Of Products</h1>
                

                <table class="table table-striped table-hover" border="2">
                    <thead>
                        <tr>
                            <th scope="col md-2" className="bg-dark text-white">Product Name</th>
                            <th scope="col md-2" className="bg-dark text-white">Brand Name</th>
                            <th scope="col md-2" className="bg-dark text-white">Model Name</th>
                            <th scope="col md-2" className="bg-dark text-white">Price</th>
                            <th scope="col md-2" className="bg-dark text-white">Available Count</th>
                            <th scope="col md-2" className="bg-dark text-white">Action</th>
                        </tr>

                    </thead>

                    <tbody>
                        <tr>

                            <td>

                                <SearchBox placeholder="Product Name" id="form1" class="form-control" handleChange={(e) => this.setState({ searchField: e.target.value })} />

                            </td>
                             <td>
                                <SearchBox placeholder="Brand Name" handleChange={(e) => this.setState({ searchField: e.target.value })} />
                            </td>
                            <td>
                                <SearchBox placeholder="Model Name" handleChange={(e) => this.setState({ searchField: e.target.value })} />
                            </td>
                            <td></td><td></td><td></td>

                        </tr>
                        {filteredData}


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

export default ManageProduct;