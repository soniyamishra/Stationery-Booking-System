import React from "react";
import axios  from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Form, Button ,Link,NavLink} from 'react-router-dom';

class ShowReviews extends React.Component
{   
  //  state = { review: { login: {},product: {},},};
  constructor(props)
  {
      super(props);    
      this.state={AllBooking:[],errMsg:""} ;
  }   
  
  componentDidMount(){
    axios.get(`http://localhost:2211/review/product/${this.props.match.params.id}`).then((responseData) => {
            console.log(responseData);
            this.setState({ AllBooking: responseData.data })
            // console.log(product.productId);

        }).catch((error) => {
            console.log("Some error in reading the data ");
            this.setState({ errMsg: "Error In Reading product Data" })
        })
        

  }
    render()
    {
      // var prodList = this.state.AllBooking; 
      // var p = prodList[0].product.productId ;
       var ProductList=this.state.AllBooking
      .map(
        (productList,index)=>{
          return(
            <div key={productList.id}>
              <h1 align="left">Name : {productList.product.productName}</h1>
              <h1 align="left">Brand Name : {productList.product.productBrand}</h1>
              <h1 align="left">Model Name : {productList.product.productModel}</h1>
              <h1 align="left">Price : {productList.product.productPrice} Rs.</h1>
            </div>
          )
        }

      )
      var product1=ProductList[0];
      // var prodId=product1[Object.keys(product1)[0]];
      // var prodId = product1.productId;

      // var ProductList2=this.state.AllBooking.map(
      //   (productList2,index)=>{
      //     return(
      //       <div key={productList2.id}>
              
      //       </div>
      //     )
      //   }
     
      // )
      
      var ReviewList=this.state.AllBooking.map(
      (reviewList,index)=>
       {
        return (<tr key={reviewList.reviewId}>
          <td>{reviewList.login.username}</td>
          <td>{reviewList.reviewComment}</td>
          {/* <td>{reviewList.product.productId}</td> */}
          <td>{reviewList.ratingNumber}</td>
          <td><NavLink exact to={`/UpdateReview/${reviewList.reviewId}`} type="button">Update</NavLink></td>
          </tr>)
          // {`/ShowReviews/${prod.productId}`}
        }
     );
        return( 
          <div className="container-sm">
         {product1}
          <div>
          </div>
         <div className="container-sm">
          <div>

            <div className="py-4">
            <table class="table border shadow" >
            <thead class="thead-dark">
              <h4   backgroung-color="black" align="center" padding="200px" > All Reviews</h4>  
              <h4 align="left"> &nbsp;&nbsp;&nbsp;OverAll Review is: 3</h4>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

             
            </thead>
              <tbody> 
            <table border="1" class="table border shadow">  
            <tr>
                <th scope="col" className = "bg-dark text-white">UserName</th>
                <th scope="col" className = "bg-dark text-white">Comment</th>
               <th scope="col" className = "bg-dark text-white">Rating</th>  
               <th scope="col" className = "bg-dark text-white">Action</th>     
            </tr>  
            { ReviewList.reverse()}
            </table> 
            </tbody>  
            </table>
        </div>
        </div>
        </div>
        </div>

        )
      
    }
    
}

export default ShowReviews;