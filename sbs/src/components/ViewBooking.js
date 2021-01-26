import React from "react"
import axios  from "axios";
import {BrowserRouter as Router,Route,Link ,NavLink} 
from "react-router-dom";
class ViewBooking extends React.Component
{
     constructor(props)
    {
        super(props);    
        this.state={AllBooking:[],errMsg:""}  
       
    }
    componentDidMount()
    {
        console.log(" In ShowAllEmpComponent  componentDidMount  excecuted");
        axios.get(" http://localhost:2211/productbooking")       
        .then((responseBooking)=>{console.log(responseBooking);
        this.setState({AllBooking:responseBooking.data})
        })
        .catch((error)=>{console.log("Some error in product booking ");
         this.setState({errMsg:"Error In product booking"})})

    }
    render()
    {  
        var bookingList=this.state.AllBooking.map(
            (booking,index)=>
            {
              return (<tr  key={booking.bookingId}>
                  <td>{booking.product.productName}</td>
                  <td>{booking.product.productPrice}</td>
                  <td>{booking.productQuantity}</td>
                  <td>{booking.address}{booking.city}</td>
                  <td>{booking.state}</td>
                  <td>{booking.zipcode}</td>
                  <td> <NavLink exact  to={`/DeleteBooking/${booking.bookingId}`} button className="mr-4 btn btn-success">Booking Cancel</NavLink></td>
                         
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
export default ViewBooking;
