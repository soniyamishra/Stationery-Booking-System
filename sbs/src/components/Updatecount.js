import React from 'react';
import axios from 'axios';


class Updatecount extends React.Component {
    constructor(props){
        super(props);
        this.state={
            productCount:0,
            productPrice:0,
            productCountError:"",
            productPriceError:"",
            product: []
        }
        this.changeProductCount= this.changeProductCount.bind(this);
        this.changeProductPrice= this.changeProductPrice.bind(this);
        this.update = this.update.bind(this);

    }   
   

    changeProductCount(event){
        this.setState({
            productCount:event.target.value
        })
    }

    
    changeProductPrice(event){
        this.setState({
            productPrice:event.target.value
        })
    }

    
    componentDidMount() {
        axios.get(`http://localhost:2211/product/${this.props.match.params.id}`).then((responseData) => {
            console.log(responseData);
            this.setState({ product: responseData.data })

        }).catch((error) => {
            console.log("Some error in reading the data ");
            this.setState({ errMsg: "Error In Reading product Data" })
        })
        alert(`${this.state.product.productName}`)
    }


    update(event){
        console.log("productName"+ this.state.product.productName,
        "productModel"+ this.state.product.productBrand,
        "productBrand"+this.state.product.productModel,
        "productPrice"+ this.state.productPrice,
        "productCount"+ this.state.productCount,
        "productUpdatedAt"+"2021-01-21T17:46:52.29678"
        )
        const url =`http://localhost:2211/product/update/`;
        axios.put(url,{  
            productName: this.state.product.productName,
    productModel: this.state.product.productModel,
    productBrand: this.state.product.productBrand,
    productPrice: this.state.productPrice,
    productCount: this.state.productCount,
        }).then((responseEmpData) => {
            console.log(responseEmpData);
        }).catch((error) => {
            console.log(error);
    console.log("Some error in updating product data");
        })
    }


    render() {
        return (
            <div className= "container-sm mt-4 pd-4">  
            <div class="card">
              <div class="card-header">
                <h4>Update Product Count & Price </h4>
              </div>
            <div class="card-body"></div>
                <form>
  
                <div class="col-sm-5">
                <label for="exampleFormControlInput1" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="productName" value={this.state.product.productName} readOnly/>
                                 <p></p>

                    <label for="exampleFormControlInput1" class="form-label">Brand Name</label>
                    <input type="text" class="form-control" id="brandName" value={this.state.product.productBrand} readOnly />
                    <p></p>


                    <label for="exampleFormControlInput1" class="form-label">Model Name</label>
                    <input type="text" class="form-control" id="modelName" value={this.state.product.productModel} readOnly/>
                    <p></p>

                    <label for="exampleFormControlInput1" class="form-label">Product Count</label>
                    <input type="number" class="form-control" id="productCount" onChange={this.changeProductCount} placeholder="Product Count" required />
                    <p>{this.state.productCountError}</p>
                    <label for="exampleFormControlInput1" class="form-label">Product Price</label>
                    <input type="number" class="form-control" id="brandName" onChange={this.changeProductPrice} placeholder="Product Price" required />
                    <p>{this.state.productPriceError}</p>

                    <button type="button" class="btn btn-primary" onClick={this.update}>Update</button>     

                </div> 
                </form>               
            </div>
        </div>
        )
    }
} export default Updatecount;