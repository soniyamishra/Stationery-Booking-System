
import React from 'react';
import {NavLink} from 'react-router-dom';
import SearchBox from './SearchBox';
import authHeader from "../services/auth-header";

import axios from "axios";
class DeleteBooking extends React.Component
{
     constructor(props) {
        super(props);
        this.state = {
            product_booking: [],
            errMsg: ""
        }
       
      
    }
    componentDidMount() {
        axios.get(`http://localhost:8080/productbooking/`, { headers: authHeader() }).then((responseData) => {
            console.log(responseData);
            this.setState({ product_booking: responseData.data })

        }).catch((error) => {
            console.log("Some error in reading the data ");
            this.setState({ errMsg: "Error In Reading product Data" })
        })
    }


    delete = (id) => {//(id){
      alert(id)
      // axios.get(`http://localhost:2211/product/${id}`).then((responseData) => {
      //     console.log(responseData);
      //     this.setState({ product: responseData.data })

      // }).catch((error) => {
      //     console.log("Some error in reading the data ");
      //     this.setState({ errMsg: "Error In Reading product Data" })
      // })
      console.log(this.state.product_booking)
      for(const product_booking of this.state.product_booking){
      
          if(product_booking.bookingId == id){
              console.log(product_booking);
              const url =`http://localhost:8080/productbooking/delete/`;
              axios.put((url),{ ...product_booking }, { headers: authHeader() })
              .then((responseData) => {
                          console.log(responseData);
                    alert("Booking Cancel Successfully!")
                    this.props.history.push("./ViewBooking")
              })
              .catch((error) => {
                  console.log(error);
                  console.log("Some error in updating product data");
              })
       
              break;
          }
      }
      axios.get(`http://localhost:8080/productbooking`, { headers: authHeader() }).then((responseData) => {
          console.log(responseData);
          this.setState({ product_booking: responseData.data })

      }).catch((error) => {
          console.log("Some error in reading the data ");
          this.setState({ errMsg: "Error In Reading product Data" })
      })
}
render()
{  
    var bookingList=this.state.product_booking.map(
        (booking,index)=>
        {
          return (<tr  key={booking.bookingId}>
              <td>{booking.product.productName}</td>
              <td>{booking.product.productPrice}</td>
              <td>{booking.productQuantity}</td>
              <td>{booking.address}{booking.city}</td>
              <td>{booking.state}</td>
              <td>{booking.zipcode}</td>
              <td>
              <button type="submit" className="mr-4 btn btn-success" onClick = {() => {this.delete(`${booking.bookingId}`)}}>Booking Cancel</button>  
                </td>
              
                     
              </tr>)
        }
   );
return( 
    <div className="py-4">
         
        <table class="table table-striped table-hover"  border="3">
        
        <tr class="tr_top">
            <td colspan = "7"> <h2 align="center" padding="200px"> All Bookings </h2></td>

        </tr>
        <tr> 
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Product Quantity</th>
            <th>Address</th> 
            <th>State</th> 
            <th>Zipcode</th>   
            <th>Action</th>            
        </tr>  
           { bookingList} 
        </table>
    </div>
    )
}
}
export default DeleteBooking;
